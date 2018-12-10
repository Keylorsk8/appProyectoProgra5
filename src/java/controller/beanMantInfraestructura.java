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
import java.util.Iterator;
import java.util.LinkedList;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import model.Funcionario;
import model.Infraestructura;
import model.InfraestructuraDB;
import model.Programa;
import model.ProgramaDB;
import model.TipoInfraestructura;
import model.TipoInfraestructuraDB;

/**
 *
 * @author Pablo
 */
@Named(value = "beanMantInfraestructura")
@SessionScoped
public class beanMantInfraestructura implements Serializable {

    /**
     * Creates a new instance of beanMantInfraestructura
     */
    public beanMantInfraestructura() {
    }
    Funcionario fun;
    int id = 0;
    int capacidad = 0;
    int idTipoInfraestructura = 0;
    String nombre = " ";
    String ubicacion = " ";
    int idPrograma = 0;
    String idNombre = "";
    String mensajeId2 = " ";

    String mensajeId = " ";
    String mensajeCapacidad = " ";
    String mensajeIdTipoInfraestructura = " ";
    String mensajeNombre = " ";
    String mensajeUbicacion = " ";
    String mensajeidPrograma = " ";

    String mensajeAlerta = " ";

    public void cancelar() {
        this.setId(0);
        this.setCapacidad(0);
        this.setIdPrograma(0);
        this.setIdTipoInfraestructura(0);
        this.setNombre(" ");
        this.setUbicacion(" ");
        this.setMensajeCapacidad(" ");
        this.setMensajeId(" ");
        this.setMensajeIdTipoInfraestructura(" ");
        this.setMensajeNombre(" ");
        this.setMensajeUbicacion(" ");
        this.setMensajeidPrograma(" ");
        this.setIdNombre(" ");
        this.setMensajeAlerta(" ");
        this.setListaTablaInfraestructura(listaTablaInfraestructura);
    }

    private LinkedList<SelectItem> listaCandCmb = new LinkedList();

    public LinkedList<SelectItem> getListaCandCmb()
            throws SNMPExceptions, SQLException {
        return listaCandCmb;
    }

    public void setListaCandCmb(LinkedList<SelectItem> listaCandCmb) {
        this.listaCandCmb = listaCandCmb;
    }
    private LinkedList<SelectItem> listaCandCmb1 = new LinkedList();

    public LinkedList<SelectItem> getListaCandCmb1()
            throws SNMPExceptions, SQLException {
        return listaCandCmb1;
    }

    public void setListaCandCmb1(LinkedList<SelectItem> listaCandCmb1) {
        this.listaCandCmb1 = listaCandCmb1;
    }

    LinkedList<Infraestructura> listaTablaInfraestructura = new LinkedList<>();

    public LinkedList<Infraestructura> getListaTablaInfraestructura() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        InfraestructuraDB dDB = new InfraestructuraDB();
        LinkedList<Infraestructura> listaTabla = new LinkedList<>();

        if (!this.idNombre.equals(" ")) {
            listaTabla = this.buscarInfraestructuraBean();
        } else {
            listaTabla = dDB.consultarInfraestructura();
        }

        return listaTabla;
    }

    public void setListaTablaInfraestructura(LinkedList<Infraestructura> listaTablaInfraestructura) {
        this.listaTablaInfraestructura = listaTablaInfraestructura;
    }

    public LinkedList<Infraestructura> buscarInfraestructuraBean() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        InfraestructuraDB dDB = new InfraestructuraDB();
        LinkedList<Infraestructura> infraestructura = new LinkedList<>();
        return infraestructura = dDB.buscarInfraestructura(idNombre);

    }

    public void actualizaDatos() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        InfraestructuraDB cDB = new InfraestructuraDB();
        Infraestructura cur = new Infraestructura();

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        fun = (Funcionario) session.getAttribute("user");

        cur.setId(this.getId());
        cur.setCapacidad(this.getCapacidad());
        cur.setIdTipoInfraestructura(this.getIdTipoInfraestructura());
        cur.setNombre(this.getNombre());
        cur.setUbicacion(this.getUbicacion());
        cur.setIdPrograma(this.getIdPrograma());
        cDB.actualizarInfraestructura(cur);
        cancelar();
        mensajeAlerta = "Editado con exito";

    }
    
    public String nombrePrograma(int idPrograma) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException{
        LinkedList<Programa> periodo = new ProgramaDB().buscarPrograma(String.valueOf(idPrograma));
        Programa cu = periodo.get(0);
        return cu.getNombre();
    }
    
    public String nombreTipoInfraestructura(int idPrograma) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException{
        LinkedList<TipoInfraestructura> tipoInfraestructura = new TipoInfraestructuraDB().buscarID(idPrograma);
        TipoInfraestructura cu = tipoInfraestructura.get(0);
        return cu.getNombre();
    }

    public void ingresarRegistro() throws
            SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        InfraestructuraDB dDB = new InfraestructuraDB();
        Infraestructura depUTN = new Infraestructura();

        try {
            if (this.id == 0) {
                this.mensajeId = "Id es Requerido";
            }
            if (this.capacidad == 0) {
                this.mensajeCapacidad = "Capacidad es Requerido";
            }
            if (this.idTipoInfraestructura == 0) {
                this.mensajeIdTipoInfraestructura = "Tipo Infraestructura es Requerido";
            }
            if (this.nombre.equals(" ")) {
                this.mensajeNombre = "Nombre es Requerido";
            }
            if (this.ubicacion.equals(" ")) {
                this.mensajeUbicacion = "Ubicacion es Requerido";
            }
            if (this.idPrograma == 0) {
                this.mensajeidPrograma = "IdPrograma es Requerido";
            }

            if (this.id >= 1) {
                this.mensajeId = " ";
            }
            if (this.capacidad >= 1) {
                this.mensajeCapacidad = " ";
            }
            if (this.idTipoInfraestructura >= 1) {
                this.mensajeIdTipoInfraestructura = " ";
            }
            if (!this.nombre.equals(" ")) {
                this.mensajeNombre = " ";
            }
            if (!this.ubicacion.equals(" ")) {
                this.mensajeUbicacion = " ";
            }
            if (this.idPrograma >= 1) {
                this.mensajeidPrograma = " ";

                if (this.id >= 1) {
                    if (this.capacidad >= 1) {
                        if (this.idTipoInfraestructura >= 1) {
                            if (!this.nombre.equals(" ")) {
                                if (!this.ubicacion.equals(" ")) {
                                    dDB.buscarID(id);
                                    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                                    fun = (Funcionario) session.getAttribute("user");

                                    depUTN.setId(id);
                                    depUTN.setCapacidad(capacidad);
                                    depUTN.setIdPrograma(idPrograma);
                                    depUTN.setIdTipoInfraestructura(idTipoInfraestructura);
                                    depUTN.setNombre(nombre);
                                    depUTN.setUbicacion(ubicacion);

                                    dDB.mvRegitroInfraestructura(depUTN);
                                    cancelar();
                                    mensajeAlerta = "Realizado con exito";
                                }
                            }
                        }
                    }
                }

            }
          else {
                mensajeAlerta = "Porfavor llenar los datos";
            }

        } catch (SNMPExceptions | SQLException e) {
            System.out.println("Error :" + e);
            System.out.println("Mensaje :" + e.getMessage());
        }

    }

    public LinkedList<SelectItem> getListaPrograma() throws SNMPExceptions, SQLException {
        String nomCandidato = "";
        int numCandidato = 0;
        LinkedList<Programa> lista = new LinkedList<Programa>();
        ProgramaDB cDB = new ProgramaDB();
        lista = cDB.moTodo();
        LinkedList resultList = new LinkedList();
        resultList.add(new SelectItem(0, "Seleccione Programa"));
        for (Iterator iter = lista.iterator();
                iter.hasNext();) {

            Programa cand = (Programa) iter.next();
            numCandidato = cand.getId();
            nomCandidato = cand.getNombre();
            resultList.add(new SelectItem(numCandidato, nomCandidato));

        }
        return resultList;
    }

    public void setListaPrograma(LinkedList<SelectItem> listaPrograma) {
        this.listaCandCmb = listaPrograma;
    }

    public LinkedList<SelectItem> getListaTipoInfra() throws SNMPExceptions, SQLException {
        String nomCandidato = "";
        int numCandidato = 0;
        LinkedList<TipoInfraestructura> lista = new LinkedList<TipoInfraestructura>();
        TipoInfraestructuraDB cDB = new TipoInfraestructuraDB();
        lista = cDB.moTodo();
        LinkedList resultList = new LinkedList();
        resultList.add(new SelectItem(0, "--Seleccione--"));
        for (Iterator iter = lista.iterator();
                iter.hasNext();) {

            TipoInfraestructura cand = (TipoInfraestructura) iter.next();
            numCandidato = cand.getId();
            nomCandidato = cand.getNombre();
            resultList.add(new SelectItem(numCandidato, nomCandidato));

        }
        return resultList;
    }

    public void setListaTipoInfra(LinkedList<SelectItem> listaTipoInfra) {
        this.listaCandCmb1 = listaTipoInfra;
    }

    public void asignaDatos(Infraestructura dep) {
        setId(dep.getId());
        setCapacidad(dep.getCapacidad());
        setIdPrograma(dep.getIdPrograma());
        setIdTipoInfraestructura(dep.getIdTipoInfraestructura());
        setNombre(dep.getNombre());
        setUbicacion(dep.getUbicacion());
    }

    public String getMensajeAlerta() {
        return mensajeAlerta;
    }

    public void setMensajeAlerta(String mensajeAlerta) {
        this.mensajeAlerta = mensajeAlerta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getIdTipoInfraestructura() {
        return idTipoInfraestructura;
    }

    public void setIdTipoInfraestructura(int idTipoInfraestructura) {
        this.idTipoInfraestructura = idTipoInfraestructura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(int idPrograma) {
        this.idPrograma = idPrograma;
    }

    public String getMensajeId() {
        return mensajeId;
    }

    public void setMensajeId(String mensajeId) {
        this.mensajeId = mensajeId;
    }

    public String getMensajeCapacidad() {
        return mensajeCapacidad;
    }

    public void setMensajeCapacidad(String mensajeCapacidad) {
        this.mensajeCapacidad = mensajeCapacidad;
    }

    public String getMensajeIdTipoInfraestructura() {
        return mensajeIdTipoInfraestructura;
    }

    public void setMensajeIdTipoInfraestructura(String mensajeIdTipoInfraestructura) {
        this.mensajeIdTipoInfraestructura = mensajeIdTipoInfraestructura;
    }

    public String getMensajeNombre() {
        return mensajeNombre;
    }

    public void setMensajeNombre(String mensajeNombre) {
        this.mensajeNombre = mensajeNombre;
    }

    public String getMensajeUbicacion() {
        return mensajeUbicacion;
    }

    public void setMensajeUbicacion(String mensajeUbicacion) {
        this.mensajeUbicacion = mensajeUbicacion;
    }

    public String getMensajeidPrograma() {
        return mensajeidPrograma;
    }

    public void setMensajeidPrograma(String mensajeidPrograma) {
        this.mensajeidPrograma = mensajeidPrograma;
    }

    public String getIdNombre() {
        return idNombre;
    }

    public void setIdNombre(String idNombre) {
        this.idNombre = idNombre;
    }

    public Funcionario getFun() {
        return fun;
    }

    public void setFun(Funcionario fun) {
        this.fun = fun;
    }

    public String getMensajeId2() {
        return mensajeId2;
    }

    public void setMensajeId2(String mensajeId2) {
        this.mensajeId2 = mensajeId2;
    }

}
