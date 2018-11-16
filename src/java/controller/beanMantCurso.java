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
@Named(value = "beanMantCurso")
@SessionScoped
public class beanMantCurso implements Serializable {

    /**
     * Creates a new instance of beanMantCurso
     */
    public beanMantCurso() {
        this.setMensajeCodigo(" ");
        this.setMensajeDescripcion(" ");
        this.setMensajePrograma(" ");
    }
    
    String codigo = " ";
    String descripcion = " ";
    int codigoPrograma = 0;
    String mensajeCodigo = " ";
    String mensajeDescripcion = " ";
    String mensajePrograma = " ";
    
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
    
    public String getMensajePrograma() {
        return mensajePrograma;
    }
    
    public void setMensajePrograma(String mensajePrograma) {
        this.mensajePrograma = mensajePrograma;
    }
    
    public String getCodigo() {
        return codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public int getCodigoPrograma() {
        return codigoPrograma;
    }
    
    public void setCodigoPrograma(int codigoPrograma) {
        this.codigoPrograma = codigoPrograma;
    }
    
    public void validacion() {
        
        try {
            if (this.codigo.equals(" ")) {
                this.mensajeCodigo = "Codigo Requerido ";                
            }            
            if (this.descripcion.equals(" ")) {
                this.mensajeDescripcion = "Descripcion Requerido ";
            }            
            
            if (this.codigoPrograma == 0) {
                this.mensajePrograma = "Codigo del Programa Requerido ";
            }
            
              if (!this.codigo.equals(" ")) {
                this.mensajeCodigo = " ";
            }            
            if (!this.descripcion.equals(" ")) {
                this.mensajeDescripcion = " ";
            }            
            
            if (this.codigoPrograma >= 1) {
                this.mensajePrograma = "";
            }
            
        } catch (Exception e) {
            e.toString();
        }
        
    }
    
    public void cancelar() {
        this.setCodigo(" ");
        this.setCodigoPrograma(0);
        this.setDescripcion(" ");
        this.setMensajeCodigo(" ");
        this.setMensajeDescripcion(" ");
        this.setMensajePrograma(" ");
    }
    
}
