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
@Named(value = "beanMantOferta")
@SessionScoped
public class beanMantOferta implements Serializable {

    /**
     * Creates a new instance of beanMantOferta
     */
    public beanMantOferta() {
    }
    
    String infraestructura="";
    String personal="";
    String curso="";
    String fechaInicio="";
    String fechaFinal="";
    int horaInicio=0;
    int horaFinal=0;
    
    String mensajeInfra;
    String mensajePersonal;
    String mensajeCurso;
    String mensajefechaIncio;
    String mensajefechaFinal;
    String mensajehoraInicio;
    String mensajehoraFinal;
    String mensajeAlerta;
    
    public void validacion(){
        
        if(this.infraestructura.equals("")){
            this.setMensajeInfra("Debe de seleccionar una infraestructura ");
        }
        if(this.personal.equals("")){
            this.setMensajePersonal("Debe de seleccionar un personal");
        }
        if(this.curso.equals("")){
            this.setMensajeCurso("Debe de seleccionar un curso");
        }
        if(this.fechaFinal.equals("")){
            this.setMensajefechaFinal("Debe se seleccionar la fecha final");
        }       
        if(this.fechaInicio.equals("")){
            this.setMensajefechaIncio("Debe se seleccionar la fecha inicio");
        }
        if(this.horaInicio == 0){
            this.setMensajehoraInicio("Debe de seleccionar la hora inicio");
        }
        if(this.horaFinal==0){
            this.setMensajehoraFinal("Debe de seleccionar la hora final ");
        }
        
        
           if(!this.infraestructura.equals("--Seleccione--")){
            this.setMensajeInfra(" ");
        }
        if(!this.personal.equals("--Seleccione--")){
            this.setMensajePersonal(" ");
        }
        if(!this.curso.equals("--Seleccione--")){
            this.setMensajeCurso(" ");
        }
        if(!this.fechaFinal.equals("")){
            this.setMensajefechaFinal(" ");
        }
        
        if(!this.fechaInicio.equals("")){
            this.setMensajefechaIncio(" ");
        }
        if(this.horaInicio > 0){
            this.setMensajehoraInicio(" ");
        }
        if(this.horaFinal> 0){
            this.setMensajehoraFinal(" ");
        }
        
        
        
    }
    
    public void cancelar(){
        this.setCurso(" ");
        this.setFechaFinal(null);
        this.setFechaInicio(null);
        this.setHoraFinal(0);
        this.setHoraInicio(0);
        this.setInfraestructura(" ");
        this.setPersonal(" ");
        this.setMensajeCurso(" ");
        this.setMensajeInfra(" ");
        this.setMensajePersonal(" ");
        this.setMensajefechaFinal(" ");
        this.setMensajefechaIncio(" ");
        this.setMensajehoraFinal(" ");
        this.setMensajehoraInicio(" ");
        
        
    }

    public String getMensajeInfra() {
        return mensajeInfra;
    }

    public void setMensajeInfra(String mensajeInfra) {
        this.mensajeInfra = mensajeInfra;
    }

    public String getMensajePersonal() {
        return mensajePersonal;
    }

    public void setMensajePersonal(String mensajePersonal) {
        this.mensajePersonal = mensajePersonal;
    }

    public String getMensajeCurso() {
        return mensajeCurso;
    }

    public void setMensajeCurso(String mensajeCurso) {
        this.mensajeCurso = mensajeCurso;
    }

    public String getMensajefechaIncio() {
        return mensajefechaIncio;
    }

    public void setMensajefechaIncio(String mensajefechaIncio) {
        this.mensajefechaIncio = mensajefechaIncio;
    }

    public String getMensajefechaFinal() {
        return mensajefechaFinal;
    }

    public void setMensajefechaFinal(String mensajefechaFinal) {
        this.mensajefechaFinal = mensajefechaFinal;
    }

    public String getMensajehoraInicio() {
        return mensajehoraInicio;
    }

    public void setMensajehoraInicio(String mensajehoraInicio) {
        this.mensajehoraInicio = mensajehoraInicio;
    }

    public String getMensajehoraFinal() {
        return mensajehoraFinal;
    }

    public void setMensajehoraFinal(String mensajehoraFinal) {
        this.mensajehoraFinal = mensajehoraFinal;
    }
    
    

    public String getInfraestructura() {
        return infraestructura;
    }

    public void setInfraestructura(String infraestructura) {
        this.infraestructura = infraestructura;
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

    public String getMensajeAlerta() {
        return mensajeAlerta;
    }

    public void setMensajeAlerta(String mensajeAlerta) {
        this.mensajeAlerta = mensajeAlerta;
    }
    
    
    
    
}
