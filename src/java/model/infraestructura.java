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
public class infraestructura {
    private String tipoInfraestructura;
    private String codigoInfraestructura;
    private String descripcion;
    private int capacidad;
    private String ubicacion;
    
    public infraestructura(){
        this.tipoInfraestructura=" ";
        this.codigoInfraestructura=" ";
        this.descripcion=" ";
        this.capacidad=0;
        this.ubicacion=" ";
        }
    public infraestructura(String tipoInfraestructurap,String codigoInfraestructurap,String descripcionp,int capacidadp,String ubicacionp){
        this.tipoInfraestructura=tipoInfraestructurap;
        this.codigoInfraestructura=codigoInfraestructurap;
        this.descripcion=descripcionp;
        this.capacidad=capacidadp;
        this.ubicacion=ubicacionp;
    }

    public String getTipoInfraestructura() {
        return tipoInfraestructura;
    }

    public void setTipoInfraestructura(String tipoInfraestructura) {
        this.tipoInfraestructura = tipoInfraestructura;
    }

    public String getCodigoInfraestructura() {
        return codigoInfraestructura;
    }

    public void setCodigoInfraestructura(String codigoInfraestructura) {
        this.codigoInfraestructura = codigoInfraestructura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    
    
}
