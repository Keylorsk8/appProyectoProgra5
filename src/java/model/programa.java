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
    private String codigoPrograma;
    private String nombreCurso;
    private String descripcion;
    
    public programa(){
   
    }
    
    public programa(String codigoProgramap,String nombreCursop,String descripcionp){
        this.codigoPrograma=codigoProgramap;
        this.nombreCurso=nombreCursop;
        this.descripcion=descripcionp;
    }

    public String getCodigoPrograma() {
        return codigoPrograma;
    }

    public void setCodigoPrograma(String codigoPrograma) {
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
