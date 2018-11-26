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
public class curso {
    private String codigoCurso;
    private String descripcion;
    private String codigoPrograma;
    
    public curso(){
     this.codigoCurso=" ";
     this.descripcion=" ";
     this.codigoPrograma=" ";
    }
    
    public curso(String codigoCursop,String descripcionp,String codigoProgramap){
        this.codigoCurso=codigoCursop;
        this.descripcion=descripcionp;
        this.codigoPrograma=codigoProgramap;
    }

    public String getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(String codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigoPrograma() {
        return codigoPrograma;
    }

    public void setCodigoPrograma(String codigoPrograma) {
        this.codigoPrograma = codigoPrograma;
    }
    
    
    
}
