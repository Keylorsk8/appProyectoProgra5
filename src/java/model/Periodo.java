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
public class Periodo {
    private String nombre;
    private String fechaInicio;
    private String fechaFinal;
    private int anio;
    private int id;
    public Periodo(int idp,String nombrep,String fechaIniciop,String fechaFinalp,int aniop){
        this.nombre=nombrep;
        this.fechaInicio=fechaIniciop;
        this.fechaFinal=fechaFinalp;
        this.anio=aniop;
        this.id=idp;
    }
    public Periodo(){
        this.nombre=" ";
        this.fechaInicio=null;
        this.fechaFinal=null;
        this.anio=0;
        this.id=0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
