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
public class Programa {
    private int id;
    private String nombre;
    private boolean estado;
    private String codFunIngreso;
    private String fechaIngreso;
    private String codFunEdito;
    private String fechaEdito;
    private int idCoordinador;
    private String esta;
    
    
    public Programa(){
       this.id=0;
       this.nombre=" ";

       this.codFunIngreso=" ";
       this.fechaIngreso=null;
       this.codFunEdito=" ";
       this.fechaEdito=null;  
       this.idCoordinador=0;
    }
    public Programa(int idp,String nombrep,boolean estadop){
       this.id=idp;
       this.nombre=nombrep;
       this.estado=estadop;

    }
 
    
    public Programa(int idp,String nombrep,String estadop){
       this.id=idp;
       this.nombre=nombrep;
       this.esta=estadop;

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getCodFunEdito() {
        return codFunEdito;
    }

    public void setCodFunEdito(String codFunEdito) {
        this.codFunEdito = codFunEdito;
    }

    public String getFechaEdito() {
        return fechaEdito;
    }

    public void setFechaEdito(String fechaEdito) {
        this.fechaEdito = fechaEdito;
    }

    public int getIdCoordinador() {
        return idCoordinador;
    }

    public void setIdCoordinador(int idCoordinador) {
        this.idCoordinador = idCoordinador;
    }

    public String getEsta() {
        return esta;
    }

    public void setEsta(String esta) {
        this.esta = esta;
    }
    
    

}