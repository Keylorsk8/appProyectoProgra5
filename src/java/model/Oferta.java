package model;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Date;

/**
 *
 * @author Pablo
 */
public class Oferta {
    private int id;
    private String descripcion;
    private String estado;
    private String fechaInicio;
    private String fechaFinal;
    private int horaInicio;
    private int horaFinal;
    private int idCurso;
    private int idInfraestructura;
    private int idPeriodo;
    private boolean estadov;
    
    public Oferta(){
     this.id=0;
     this.descripcion="";
     
     this.fechaInicio="";
     this.fechaFinal="";
     this.horaInicio=0;
     this.horaFinal=0;
     this.idCurso=0;
     this.idInfraestructura=0;
     this.idPeriodo=0;
  
    }
    
    public Oferta(int idp,String descripcionp,String estadop,String fechaIniciop,String fechaFinalp,int horaIniciop,int horaFinalp,int idCursop,int idInfraestructurap,int idPeriodop){
      this.id=idp;
      this.descripcion=descripcionp;
      this.estado=estadop;
      this.fechaInicio=fechaIniciop;
      this.fechaFinal=fechaFinalp;
      this.horaInicio=horaIniciop;
      this.horaFinal=horaFinalp;
      this.idPeriodo=idPeriodop;  
      this.idCurso=idCursop;
      this.idInfraestructura=idInfraestructurap;
    }
    
    public Oferta(int idp,String descripcionp,boolean estadop,String fechaIniciop,String fechaFinalp,int horaIniciop,int horaFinalp,int idCursop,int idInfraestructurap,int idPeriodop){
      this.id=idp;
      this.descripcion=descripcionp;
      this.estadov=estadop;
      this.fechaInicio=fechaIniciop;
      this.fechaFinal=fechaFinalp;
      this.horaInicio=horaIniciop;
      this.horaFinal=horaFinalp;
      this.idPeriodo=idPeriodop;  
      this.idCurso=idCursop;
      this.idInfraestructura=idInfraestructurap;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String Estado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public int getIdInfraestructura() {
        return idInfraestructura;
    }

    public void setIdInfraestructura(int idInfraestructura) {
        this.idInfraestructura = idInfraestructura;
    }

    public int getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(int idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public boolean isEstadov() {
        return estadov;
    }

    public void setEstadov(boolean estadov) {
        this.estadov = estadov;
    }
    
    

   
    
    
}
