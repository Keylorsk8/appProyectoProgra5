package model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Date;
//Hola
/**
 *
 * @author Pablo
 */
public class Curso {
    private int id;
    private String descripcion;
    private boolean estado;
    private String codFunIngreso;
    private Date fechaIngreso;
    private String codFunEdito;
    private Date fechaEdito;
    private int idPrograma;
    
    public Curso(){
    this.id=0;
    this.descripcion=" ";
    this.estado=false;
    this.idPrograma=0;
    }
    
    public Curso(int idp,String descripcionp,boolean estadop,int idProgramap){
        this.id=idp;
        this.descripcion=descripcionp;
        this.estado=estadop;
        this.idPrograma=idProgramap;       
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getCodFunIngreso() {
        return codFunIngreso;
    }

    public void setCodFunIngreso(String codFunIngreso) {
        this.codFunIngreso = codFunIngreso;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getCodFunEdito() {
        return codFunEdito;
    }

    public void setCodFunEdito(String codFunEdito) {
        this.codFunEdito = codFunEdito;
    }

    public Date getFechaEdito() {
        return fechaEdito;
    }

    public void setFechaEdito(Date fechaEdito) {
        this.fechaEdito = fechaEdito;
    }

    public int getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(int idPrograma) {
        this.idPrograma = idPrograma;
    }
    
    

    
    
}
