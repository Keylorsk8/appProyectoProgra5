/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;

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
    }
    
    String periodo=" ";
    Date fechaInicio=null;
    Date fechaFinal=null;
    String descripcion=" ";
    
    String mensajePeriodo;
    String mensajefechaInicio;
    String mensajefechaFinal;
    String mensajedescripcion;
    
    
    public void validacion(){
        
        if(this.descripcion.equals(" ")){
            this.mensajedescripcion="Requerido la descripcion";
        }
        if(this.fechaFinal == null){
            this.mensajefechaFinal="Requerida la fecha final";
        }
        if(this.fechaInicio == null){
            this.mensajefechaInicio="Requerida la fecha inicio";
        }
        if(this.periodo.equals("--Seleccione--")){
            this.mensajePeriodo="Requerdo el periodo";
        }
        
        if(!this.descripcion.equals(" ")){
            this.mensajedescripcion=" ";
        }
        if(this.fechaFinal != null){
            this.mensajefechaFinal=" ";
        }
        if(this.fechaInicio != null){
            this.mensajefechaInicio=" ";
        }
        if(!this.periodo.equals("--Seleccione--")){
            this.mensajePeriodo=" ";
        }
        
    }
    
    public void cancelar(){
    this.setDescripcion(" ");
    this.setFechaFinal(null);
    this.setFechaInicio(null);
    this.setMensajePeriodo(" ");
    this.setMensajedescripcion(" ");
    this.setMensajefechaFinal(" ");
    this.setMensajefechaInicio(" ");
    this.setPeriodo(" ");
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMensajePeriodo() {
        return mensajePeriodo;
    }

    public void setMensajePeriodo(String mensajePeriodo) {
        this.mensajePeriodo = mensajePeriodo;
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

    public String getMensajedescripcion() {
        return mensajedescripcion;
    }

    public void setMensajedescripcion(String mensajedescripcion) {
        this.mensajedescripcion = mensajedescripcion;
    }
    
    
        
}
