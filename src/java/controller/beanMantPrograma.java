/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

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
    int codigo=0;
    String nombreCurso=" ";
    String descripcion=" ";
     String mensajeCodigo = " ";
    String mensajeDescripcion = " ";
    String mensajeNombreCurso = " ";

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
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
            if (this.codigo==0) {
                this.mensajeCodigo = "Codigo Requerido ";
            }            
            if (this.descripcion.equals(" ")) {
                this.mensajeDescripcion = "Descripcion Requerido ";
            }            
            
            if (this.nombreCurso.equals(" ")) {
                this.mensajeNombreCurso = "Nombre del Curso Requerido";
            }
            
             if (this.codigo >= 1) {
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
        this.setCodigo(0);
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
