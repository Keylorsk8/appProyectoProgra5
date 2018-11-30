package model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Pablo
 */
public class Infraestructura {
    private int id;
    private int capacidad;
    private int idTipoInfraestructura;    
    private String nombre;   
    private String ubicacion;
    private int idPrograma;
    
    public Infraestructura(){
        this.id=0;
        this.capacidad=0;
        this.idTipoInfraestructura=0;
        this.nombre=" ";
        this.ubicacion=" ";
        this.idPrograma=0;     
        }
    public Infraestructura(int idp,int capacidadp,int idTipoInfraestructurap,String nombrep,String ubicacionp,int idProgramap){
        this.id=idp;
        this.capacidad=capacidadp;
        this.idTipoInfraestructura=idTipoInfraestructurap;
        this.nombre=nombrep;
        this.ubicacion=ubicacionp;
        this.idPrograma=idProgramap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getIdTipoInfraestructura() {
        return idTipoInfraestructura;
    }

    public void setIdTipoInfraestructura(int idTipoInfraestructura) {
        this.idTipoInfraestructura = idTipoInfraestructura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(int idPrograma) {
        this.idPrograma = idPrograma;
    }
}
