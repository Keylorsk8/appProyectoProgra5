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
    private int codigoCurso;
    private String descripcion;
    private int codigoPrograma;
    
    public curso(){
     this.codigoCurso=0;
     this.descripcion=" ";
     this.codigoPrograma=0;
    }
    
    public curso(int codigoCursop,String descripcionp,int codigoProgramap){
        this.codigoCurso=codigoCursop;
        this.descripcion=descripcionp;
        this.codigoPrograma=codigoProgramap;
    }

    public int getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(int codigoCurso) {
        this.codigoCurso = codigoCurso;
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
    
    
    
}
