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
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.LinkedList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.naming.NamingException;
import model.Barrio;
import model.BarrioDB;
import model.Canton;
import model.CantonDB;
import model.Distrito;
import model.DistritoDB;
import model.Funcionario;
import model.FuncionarioDB;
import model.Provincia;
import model.ProvinciaDB;

/**
 *
 * @author KeylorSk8
 */
@Named(value = "beanAutoRegistro")
@SessionScoped
public class beanAutoRegistro implements Serializable {

    String identificacion = "";
    String nombre = "";
    String apellido1 = "";
    String apellido2 = "";
    String fechaNaciemiento = "";
    String Email = "";
    String provincia = "1";
    String canton = "1";
    String distrito = "1";
    String barrio = "1";
    String otrasSenias = "";
    String mensajes = "";

    LinkedList<SelectItem> provincias = new LinkedList<>();
    LinkedList<SelectItem> cantones = new LinkedList<>();
    LinkedList<SelectItem> distritos = new LinkedList<>();
    LinkedList<SelectItem> barrios = new LinkedList<>();

    /**
     * Creates a new instance of beanAutoRegistro
     */
    public beanAutoRegistro() {
    }

    public void solicitarCuenta() throws SNMPExceptions, ClassNotFoundException, SQLException, NamingException {
        if (!validar()) {
            if (validarExistencia() == null) {
                solicitar();
                mensajes = "";
                FacesContext context = FacesContext.getCurrentInstance();
                limpiar();
                context.addMessage(null, new FacesMessage("Solicitud Exitosa", "Su cuenta ha sido solicitada con éxito,espere la respuesta de nuestro personal(normalmente tarda algunas horas)"));
            }else{
                mensajes = "<h3 style='background-color:lightcoral;text-align:center'>Este Usuario ya solicitó una cuenta</h3>";
            }
        }
    }

    public void limpiar() {
        this.setNombre("");
        this.setApellido1("");
        this.setApellido2("");
        this.setBarrio("1");
        this.setCanton("1");
        this.setDistrito("1");
        this.setProvincia("1");
        this.setEmail("");
        this.setIdentificacion("");
        this.setFechaNaciemiento("");
        this.setOtrasSenias("");
    }

    public Funcionario validarExistencia() throws SNMPExceptions, NamingException, SQLException, ClassNotFoundException {
        FuncionarioDB logica = new FuncionarioDB();
        return logica.validadExistenciaFuncionario(Integer.parseInt(identificacion));
    }

    public void solicitar() throws SNMPExceptions, ClassNotFoundException, SQLException, NamingException {
        FuncionarioDB logica = new FuncionarioDB();
        Funcionario fun = new Funcionario(Integer.parseInt(this.getIdentificacion()), "", 1, this.getEmail(),
                this.getNombre(), this.getApellido1(), this.getApellido2(), this.getFechaNaciemiento(),
                Integer.parseInt(this.getIdentificacion()), 0, true, 1, "01/01/1995", 1, "01/01/1995", true);
        fun.setCodProv(Integer.parseInt(provincia));
        fun.setCodCan(Integer.parseInt(canton));
        fun.setCodDist(Integer.parseInt(distrito));
        fun.setCodBa(Integer.parseInt(barrio));
        fun.setOtrasSeñas(otrasSenias);
        fun.setEdad(String.valueOf(calculaEdad(repararFecha(fechaNaciemiento))));
        logica.solicitarCuenta(fun);
    }

    public String repararFecha(String fechaNacimiento) {
        String newfecha;
        newfecha = fechaNacimiento.substring(8, 10) + "/";
        newfecha += fechaNacimiento.substring(5, 7) + "/";
        newfecha += fechaNacimiento.substring(0, 4);
        return newfecha;
    }

    public int calculaEdad(String fechaNacimiento) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaNac = LocalDate.parse(fechaNacimiento, fmt);
        LocalDate ahora = LocalDate.now();

        Period periodo = Period.between(fechaNac, ahora);
        System.out.printf("Tu edad es: %s años, %s meses y %s días",
                periodo.getYears(), periodo.getMonths(), periodo.getDays());
        return periodo.getYears();
    }

    public boolean validar() {
        boolean errores = false;
        mensajes = "<ul style='padding:5px;background-color:lightcoral;border-radios:20px'>";
        //Identificacion
        if (!identificacion.equals("")) {
            try {
                if (Integer.parseInt(identificacion) < 0) {
                }
            } catch (Exception e) {
                mensajes += "<li>El número de Identifición debe ser numerico</li>";
                errores = true;
            }
        } else {
            mensajes += "<li>Digite un número de Documento</li>";
            errores = true;
        }
        //Nombre
        if (nombre.equals("")) {
            mensajes += "<li>Digite un nombre</li>";
            errores = true;
        }
        //Apellido 1
        if (apellido1.equals("")) {
            mensajes += "<li>Digite su primer Apellido</li>";
            errores = true;
        }
        //Apellido 2
        if (apellido2.equals("")) {
            mensajes += "<li>Digite su segundo Apellido</li>";
            errores = true;
        }
        //Fecha Nacimiento
        if (fechaNaciemiento.equals("")) {
            mensajes += "<li>Seleccione su fecha de nacimiento</li>";
            errores = true;
        }
        //Email
        if(Email.equals("")){
            mensajes += "<li>Digite su Email</li>";
            errores = true;
        }
        //Otras Señas
        if (otrasSenias.equals("")) {
            mensajes += "<li>Digite otras señas de su dirección</li>";
            errores = true;
        }
        mensajes += "</ul>";
        return errores;
    }

    public void valueChangedProvincias(AjaxBehaviorEvent event) throws SNMPExceptions {
        LinkedList<SelectItem> cantones1 = this.getCantones();
        this.setCantones(cantones1);
        LinkedList<SelectItem> distritos1 = this.getDistritos();
        this.setDistritos(distritos1);
        LinkedList<SelectItem> barrios1 = this.getBarrios();
        this.setBarrios(barrios1);
    }

    public void valueChangedCanton(AjaxBehaviorEvent event) throws SNMPExceptions {
        LinkedList<SelectItem> distritos1 = this.getDistritos();
        this.setDistritos(distritos1);
        LinkedList<SelectItem> barrios1 = this.getBarrios();
        this.setBarrios(barrios1);
    }

    public void valueChangedDistrito(AjaxBehaviorEvent event) throws SNMPExceptions {
        LinkedList<SelectItem> barrios1 = this.getBarrios();
        this.setBarrios(barrios1);
    }

    public String getMensajes() {
        return mensajes;
    }

    public void setMensajes(String mensajes) {
        this.mensajes = mensajes;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getFechaNaciemiento() {
        return fechaNaciemiento;
    }

    public void setFechaNaciemiento(String fechaNaciemiento) {
        this.fechaNaciemiento = fechaNaciemiento;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getOtrasSenias() {
        return otrasSenias;
    }

    public void setOtrasSenias(String otrasSenias) {
        this.otrasSenias = otrasSenias;
    }

    public LinkedList<SelectItem> getProvincias() throws SNMPExceptions {
        ProvinciaDB logica = new ProvinciaDB();
        LinkedList<Provincia> lista = logica.seleccionarProvincias();

        int id = 0;
        String nombre = "";

        LinkedList resultList = new LinkedList();

        for (Iterator iter = lista.iterator(); iter.hasNext();) {
            Provincia pro = (Provincia) iter.next();
            id = pro.getId();
            nombre = pro.getNombre();
            resultList.add(new SelectItem(id, nombre));
        }
        return resultList;
    }

    public void setProvincias(LinkedList<SelectItem> provincias) {
        this.provincias = provincias;
    }

    public LinkedList<SelectItem> getDistritos() throws SNMPExceptions {
        DistritoDB logica = new DistritoDB();
        LinkedList<Distrito> lista = logica.seleccionarDistritos(Integer.parseInt(canton), Integer.parseInt(provincia));

        int id = 0;
        String nombre = "";

        LinkedList resultList = new LinkedList();

        for (Iterator iter = lista.iterator(); iter.hasNext();) {
            Distrito dis = (Distrito) iter.next();
            id = dis.getId();
            nombre = dis.getNombre();
            resultList.add(new SelectItem(id, nombre));
        }
        return resultList;
    }

    public void setDistritos(LinkedList<SelectItem> distritos) {
        this.distritos = distritos;
    }

    public LinkedList<SelectItem> getCantones() throws SNMPExceptions {
        CantonDB logica = new CantonDB();
        LinkedList<Canton> lista = logica.seleccionarCantones(Integer.parseInt(provincia));

        int id = 0;
        String nombre = "";

        LinkedList resultList = new LinkedList();

        for (Iterator iter = lista.iterator(); iter.hasNext();) {
            Canton can = (Canton) iter.next();
            id = can.getId();
            nombre = can.getNombre();
            resultList.add(new SelectItem(id, nombre));
        }
        return resultList;
    }

    public void setCantones(LinkedList<SelectItem> cantones) {
        this.cantones = cantones;
    }

    public LinkedList<SelectItem> getBarrios() throws SNMPExceptions {
        BarrioDB logica = new BarrioDB();
        LinkedList<Barrio> lista = logica.seleccionarBarrios(Integer.parseInt(canton), Integer.parseInt(provincia), Integer.parseInt(distrito));

        int id = 0;
        String nombre = "";

        LinkedList resultList = new LinkedList();

        for (Iterator iter = lista.iterator(); iter.hasNext();) {
            Barrio ba = (Barrio) iter.next();
            id = ba.getId();
            nombre = ba.getNombre();
            resultList.add(new SelectItem(id, nombre));
        }
        return resultList;
    }

    public void setBarrios(LinkedList<SelectItem> barrios) {
        this.barrios = barrios;
    }
}
