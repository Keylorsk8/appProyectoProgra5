/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Pablo
 */
@Named(value = "beanMantInfraestructura")
@SessionScoped
public class beanMantInfraestructura implements Serializable {

    /**
     * Creates a new instance of beanMantInfraestructura
     */
    public beanMantInfraestructura() {
    }
    
    String tipoInfraestructura=" ";
    String codigo=" ";
    String descripcion=" ";
    int capacidad=0;
    String ubicacion= " ";
    
    String mensajeTipo=" ";
    String mensajeCodigo=" ";
    String mensajeDescripcion=" ";
    String mensajeCapacidad=" ";
    String mensajeUbicacion=" ";
    
    
    
     public void validacion() {
        
        try {
            if(this.capacidad == 0){
                this.mensajeCapacidad="Capacidad es Requerido";
            }
            if(this.codigo.equals(" ")){
                this.mensajeCodigo="Codigo es Requerido";
            }
            if(this.descripcion.equals(" ")){
                this.mensajeDescripcion="Descripcion es Requerido";
            }
            if(this.tipoInfraestructura.equals("--Seleccione--")){
                this.mensajeTipo="Tipo Infraestructura es Requerido";
            }
            if(this.ubicacion.equals(" ")){
               this.mensajeUbicacion="Ubicacion es Requerido";     
            }
            
               if(this.capacidad >= 1){
                this.mensajeCapacidad=" ";
            }
            if(!this.codigo.equals(" ")){
                this.mensajeCodigo=" ";
            }
            if(!this.descripcion.equals(" ")){
                this.mensajeDescripcion=" ";
            }
            if(!this.tipoInfraestructura.equals("--Seleccione--")){
                this.mensajeTipo=" ";
            }
            if(!this.ubicacion.equals(" ")){
               this.mensajeUbicacion=" ";     
            }
            
        } catch (Exception e) {
            e.toString();
        }
        
    }
    
    public void cancelar() {
       this.setCapacidad(0);
       this.setCodigo(" ");
       this.setDescripcion(" ");
       this.setMensajeCapacidad(" ");
       this.setMensajeCodigo(" ");
       this.setMensajeDescripcion(" ");
       this.setMensajeTipo(" ");
       this.setMensajeUbicacion(" ");
       this.setTipoInfraestructura(" ");
       this.setUbicacion(" ");
        
    }

    public String getTipoInfraestructura() {
        return tipoInfraestructura;
    }

    public void setTipoInfraestructura(String tipoInfraestructura) {
        this.tipoInfraestructura = tipoInfraestructura;
    }

 

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public String getMensajeTipo() {
        return mensajeTipo;
    }

    public void setMensajeTipo(String mensajeTipo) {
        this.mensajeTipo = mensajeTipo;
    }

    public String getMensajeCodigo() {
        return mensajeCodigo;
    }

    public void setMensajeCodigo(String mensajeCodigo) {
        this.mensajeCodigo = mensajeCodigo;
    }

    public String getMensajeDescripcion() {
        return mensajeDescripcion;
    }

    public void setMensajeDescripcion(String mensajeDescripcion) {
        this.mensajeDescripcion = mensajeDescripcion;
    }

    public String getMensajeCapacidad() {
        return mensajeCapacidad;
    }

    public void setMensajeCapacidad(String mensajeCapacidad) {
        this.mensajeCapacidad = mensajeCapacidad;
    }

    public String getMensajeUbicacion() {
        return mensajeUbicacion;
    }

    public void setMensajeUbicacion(String mensajeUbicacion) {
        this.mensajeUbicacion = mensajeUbicacion;
    }
    
    
    
}
