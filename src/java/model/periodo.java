/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Pablo
 */
public class periodo {
    private String periodo;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFinal;
    
    public periodo(){
        this.periodo=" ";
        this.descripcion=" ";
        this.fechaInicio=null;
        this.fechaFinal=null;      
    }
    
    public periodo(String periodop,String descripcionp,Date fechaIniciop,Date fechaFinalp){
        this.periodo=periodop;
        this.descripcion=descripcionp;
        this.fechaInicio=fechaIniciop;
        this.fechaFinal=fechaFinalp;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
    
    
    
}
