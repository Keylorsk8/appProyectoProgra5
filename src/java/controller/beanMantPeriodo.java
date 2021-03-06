/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.SNMPExceptions;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import javax.naming.NamingException;
import model.Periodo;
import model.PeriodoDB;

/**
 *
 * @author Pablo
 */
@Named(value = "beanMantPeriodo")
@SessionScoped
public class beanMantPeriodo implements Serializable {

    /**
     * Creates a new instance of beanMantPeriodo
     */
    public beanMantPeriodo() {
        Date hoy = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        this.setFechaInicio(sd.format(hoy));
        this.setFechaFinal(sd.format(hoy));
    }

    String idNombre = "";
    String fechaInicio = " ";
    String fechaFinal = " ";
    String nombre = " ";

    int id = 0;

    Calendar cal = Calendar.getInstance();
    int anio = cal.get(cal.YEAR);

    String mensajeId;
    String mensajefechaInicio;
    String mensajefechaFinal;
    String mensajeNombre;
    String mensajeAnio;
    String mensajeAlerta;

    LinkedList<Periodo> listaTablaPeriodo = new LinkedList<>();

    public LinkedList<Periodo> getListaTablaPeriodo() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        PeriodoDB dDB = new PeriodoDB();
        LinkedList<Periodo> listaTabla = new LinkedList<>();

        if (!this.idNombre.equals("")) {
            listaTabla = buscarPeriodoBean();
        } else {
            listaTabla = dDB.consultarPeriodo();
        }

        return listaTabla;
    }

    public void setListaTablaPeriodo(LinkedList<Periodo> listaTablaPeriodo) {
        this.listaTablaPeriodo = listaTablaPeriodo;
    }

    public LinkedList<Periodo> buscarPeriodoBean() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        PeriodoDB dDB = new PeriodoDB();
        LinkedList<Periodo> Periodo = new LinkedList<>();
        return Periodo = dDB.buscarPeriodo(idNombre);

    }

    public void actualizaDatos() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        PeriodoDB cDB = new PeriodoDB();
        Periodo cur = new Periodo();
        cur.setNombre(this.getNombre());
        cur.setFechaInicio(fechaInicio);
        cur.setFechaFinal(fechaFinal);
        cur.setAnio(this.getAnio());
        cur.setId(this.getId());
        cDB.actualizarPeriodo(cur);
        cancelar();
        mensajeAlerta = "Editado con éxito";
    }

    public void ingresarRegistro() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        PeriodoDB dDB = new PeriodoDB();
        Periodo depUTN = new Periodo();

        try {

            if (this.nombre.equals(" ")) {
                this.mensajeNombre = "Nombre es Requerido";
            }
            if (this.fechaInicio.equals("")) {
                this.mensajefechaInicio = "FechaInicio es Requerido";
            }
            if (this.fechaFinal.equals("")) {
                this.mensajefechaFinal = "FechaFinal es Requerido";
            }

            if (!this.nombre.equals(" ")) {
                this.mensajeNombre = " ";
            }
            if (!this.fechaInicio.equals("")) {
                this.mensajefechaInicio = " ";
            }
            if (!validar()) {
                this.mensajefechaFinal = " ";
                this.mensajefechaInicio = " ";
            }
            if (!this.fechaFinal.equals("")) {
                if (!this.fechaInicio.equals("")) {
                    if (!this.nombre.equals(" ")) {
                        this.mensajefechaFinal = " ";
                        depUTN.setNombre(nombre);
                        depUTN.setFechaInicio(fechaInicio);
                        depUTN.setFechaFinal(fechaFinal);
                        depUTN.setAnio(anio);
                        dDB.mvRegitroPeriodo(depUTN);
                        cancelar();
                        mensajeAlerta = "Realizado con exito";
                    }
                }
            } else {
                mensajeAlerta = "Porfavor llenar los datos";
            }

        } catch (SNMPExceptions | SQLException e) {
            System.out.println("Error :" + e);
            System.out.println("Mensaje :" + e.getMessage());
        }
    }

    public String transformarFechas(String fecha) {
        String fcha = fecha;

        String anio = fcha.substring(0, 4);
        String mes = fcha.substring(5, 7);
        String dia = fcha.substring(8, 10);
        fcha = anio + "-" + mes + "-" + anio;

        return fcha;
    }

    public boolean validar() {
        boolean errores = false;

        if (!comparFechasInicio()) {
            mensajefechaInicio = "Digite una fecha de entrega posterior a la fecha actual";
            errores = true;
        }
        if (!comparFechasFinal()) {
            mensajefechaFinal = "Digite una fecha de entrega posterior a la fecha actual";
            errores = true;
        }

        return errores;
    }

    public boolean comparFechasInicio() {
        String sFecha = this.fechaInicio;
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sFecha);
        Date fechaSeleccionada = null;
        try {
            fechaSeleccionada = (Date) formatoDelTexto.parse(sFecha);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(fechaSeleccionada.toString());

        Date fechaActual = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(fechaActual);
        c.add(Calendar.HOUR, -2);
        fechaActual = (Date) c.getTime();
        if (fechaSeleccionada.before(fechaActual)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean comparFechasFinal() {
        String sFecha = this.fechaFinal;
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sFecha);
        Date fechaSeleccionada = null;
        try {
            fechaSeleccionada = (Date) formatoDelTexto.parse(sFecha);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(fechaSeleccionada.toString());

        Date fechaActual = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(fechaActual);
        c.add(Calendar.HOUR, -2);
        fechaActual = (Date) c.getTime();
        if (fechaSeleccionada.before(fechaActual)) {
            return false;
        } else {
            return true;
        }
    }

    public void asignaDatos(Periodo dep) {
        this.setId(dep.getId());
        this.setNombre(dep.getNombre());
        this.setFechaInicio(dep.getFechaInicio().substring(0, 10));
        this.setFechaFinal(dep.getFechaFinal().substring(0, 10));
        this.setAnio(dep.getAnio());

    }

    public void cancelar() {
        this.setAnio(0);
        this.setId(0);
        this.setIdNombre("");
        this.setMensajeAlerta(" ");
        this.setMensajeAnio(" ");
        this.setMensajeId(" ");
        this.setMensajeNombre(" ");
        this.setMensajefechaFinal(" ");
        this.setMensajefechaInicio(" ");
        this.setNombre(" ");
         Date hoy = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        this.setFechaInicio(sd.format(hoy));
        this.setFechaFinal(sd.format(hoy));

        this.setListaTablaPeriodo(listaTablaPeriodo);
    }

    public String getIdNombre() {
        return idNombre;
    }

    public void setIdNombre(String idNombre) {
        this.idNombre = idNombre;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getMensajeId() {
        return mensajeId;
    }

    public void setMensajeId(String mensajeId) {
        this.mensajeId = mensajeId;
    }

    public String getMensajefechaInicio() {
        return mensajefechaInicio;
    }

    public void setMensajefechaInicio(String mensajefechaInicio) {
        this.mensajefechaInicio = mensajefechaInicio;
    }

    public String getMensajefechaFinal() {
        return mensajefechaFinal;
    }

    public void setMensajefechaFinal(String mensajefechaFinal) {
        this.mensajefechaFinal = mensajefechaFinal;
    }

    public String getMensajeNombre() {
        return mensajeNombre;
    }

    public void setMensajeNombre(String mensajeNombre) {
        this.mensajeNombre = mensajeNombre;
    }

    public String getMensajeAnio() {
        return mensajeAnio;
    }

    public void setMensajeAnio(String mensajeAnio) {
        this.mensajeAnio = mensajeAnio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMensajeAlerta() {
        return mensajeAlerta;
    }

    public void setMensajeAlerta(String mensajeAlerta) {
        this.mensajeAlerta = mensajeAlerta;
    }

}
