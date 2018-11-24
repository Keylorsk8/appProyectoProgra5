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
public class oferta {
    private String tipoInfraestructura;
    private String personal;
    private String curso;
    private Date fechaInicio;
    private Date fechaFinal;
    private int horaInicio;
    private int horaFinal;
    
    public oferta(){
    this.tipoInfraestructura=" ";
    this.personal=" ";
    this.curso=" ";
    this.fechaInicio=null;
    this.fechaFinal=null;
    this.horaInicio=0;
    this.horaFinal=0;
    }
    
    public oferta(String tipoInfraestructurap,String personalp,String cursop,Date fechaIniciop,Date fechaFinalp,int horaIniciop,int horaFinalp){
        this.tipoInfraestructura=tipoInfraestructurap;
        this.personal=personalp;
        this.curso=cursop;
        this.fechaInicio=fechaIniciop;
        this.fechaFinal=fechaFinalp;
        this.horaInicio=horaIniciop;
        this.horaFinal=horaFinalp;
    }

    public String getTipoInfraestructura() {
        return tipoInfraestructura;
    }

    public void setTipoInfraestructura(String tipoInfraestructura) {
        this.tipoInfraestructura = tipoInfraestructura;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
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

    public int getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
    }

    public int getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(int horaFinal) {
        this.horaFinal = horaFinal;
    }
    
    
}
