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
import java.util.LinkedList;
import javax.naming.NamingException;
import model.programa;
import model.programaDB;

/**
 *
 * @author Pablo
 */
@Named(value = "beanMantPrograma")
@SessionScoped
public class beanMantPrograma implements Serializable {

    /**
     * Creates a new instance of beanMantPrograma
     */
    public beanMantPrograma() {
    }
    String codigo = " ";
    String nombreCurso = " ";
    String descripcion = " ";
    String mensajeCodigo = " ";
    String mensajeDescripcion = " ";
    String mensajeNombreCurso = " ";
    String mensajeAlerta = " ";

    LinkedList<programa> listaTablaPrograma = new LinkedList<>();

    public LinkedList<programa> getListaTablaPrograma() throws SNMPExceptions, SQLException {
        programaDB dDB = new programaDB();
        return dDB.consultarPrograma();
    }

    public void setListaTablaPrograma(LinkedList<programa> listaTablaPrograma) {
        this.listaTablaPrograma = listaTablaPrograma;
    }

    public void actualizaDatos() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        programaDB cDB = new programaDB();
        programa cur = new programa();
        cur.setNombreCurso(this.getNombreCurso());
        cur.setDescripcion(this.getDescripcion());
        cur.setCodigoPrograma(this.getCodigo());
        cDB.actualizarPrograma(cur);
    }

    public void ingresarRegistro() throws
        SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        programaDB dDB = new programaDB();
        programa depUTN = new programa();

        try {
            if (this.codigo.equals(" ")) {
                this.mensajeCodigo = "Codigo Requerido ";
            }
            if (this.descripcion.equals(" ")) {
                this.mensajeDescripcion = "Descripcion Requerido ";
            }

            if (this.nombreCurso.equals(" ")) {
                this.mensajeNombreCurso = "Nombre del Curso Requerido";
            }

            if (!this.codigo.equals(" ")) {
                this.mensajeCodigo = " ";
            }
            if (!this.descripcion.equals(" ")) {
                this.mensajeDescripcion = " ";
            }

            if (!this.nombreCurso.equals(" ")) {
                this.mensajeNombreCurso = " ";
            }

            depUTN.setNombreCurso(nombreCurso);
            depUTN.setCodigoPrograma(codigo);
            depUTN.setDescripcion(descripcion);

            dDB.mvRegitroPrograma(depUTN);
            mensajeAlerta = "Realizado con exito";
        } catch (Exception e) {
        }

    }

    public void asignaDatos(programa dep) {
        this.setNombreCurso(dep.getNombreCurso());
        this.setDescripcion(dep.getDescripcion());
        this.setCodigo(dep.getCodigoPrograma());
    }

    public String getMensajeAlerta() {
        return mensajeAlerta;
    }

    public void setMensajeAlerta(String mensajeAlerta) {
        this.mensajeAlerta = mensajeAlerta;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void validacion() {

        try {
            if (this.codigo.equals(" ")) {
                this.mensajeCodigo = "Codigo Requerido ";
            }
            if (this.descripcion.equals(" ")) {
                this.mensajeDescripcion = "Descripcion Requerido ";
            }

            if (this.nombreCurso.equals(" ")) {
                this.mensajeNombreCurso = "Nombre del Curso Requerido";
            }

            if (!this.codigo.equals(" ")) {
                this.mensajeCodigo = " ";
            }
            if (!this.descripcion.equals(" ")) {
                this.mensajeDescripcion = " ";
            }

            if (!this.nombreCurso.equals(" ")) {
                this.mensajeNombreCurso = " ";
            }

        } catch (Exception e) {
            e.toString();
        }
    }

    public void cancelar() {
        this.setCodigo(" ");
        this.setDescripcion(" ");
        this.setNombreCurso(" ");
        this.setMensajeCodigo(" ");
        this.setMensajeDescripcion(" ");
        this.setMensajeNombreCurso(" ");
    }

    public String getMensajeCodigo() {
        return mensajeCodigo;
    }

    public void setMensajeCodigo(String mensajeCodigo) {
        this.mensajeCodigo = mensajeCodigo;
    }

    public String getMensajeDescripcion() {
        return mensajeDescripcion;
    }

    public void setMensajeDescripcion(String mensajeDescripcion) {
        this.mensajeDescripcion = mensajeDescripcion;
    }

    public String getMensajeNombreCurso() {
        return mensajeNombreCurso;
    }

    public void setMensajeNombreCurso(String mensajeNombreCurso) {
        this.mensajeNombreCurso = mensajeNombreCurso;
    }
}
