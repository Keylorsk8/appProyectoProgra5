/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Pablo
 */
public class programa {
    private int codigoPrograma;
    private String nombreCurso;
    private String descripcion;
    
    public programa(){
    this.codigoPrograma=0;
    this.nombreCurso=" ";
    this.descripcion=" ";
    }
    
    public programa(int codigoProgramap,String nombreCursop,String descripcionp){
        this.codigoPrograma=codigoProgramap;
        this.nombreCurso=nombreCursop;
        this.descripcion=descripcionp;
    }

    public int getCodigoPrograma() {
        return codigoPrograma;
    }

    public void setCodigoPrograma(int codigoPrograma) {
        this.codigoPrograma = codigoPrograma;
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
    
    
}
