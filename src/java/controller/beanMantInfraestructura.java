/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.SNMPExceptions;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import javax.faces.model.SelectItem;
import javax.naming.NamingException;
import model.curso;
import model.cursoDB;
import model.infraestructura;
import model.infraestructuraDB;
import model.programa;
import model.programaDB;

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
    
     int id = 0;
     int capacidad = 0;
     int idTipoInfraestructura = 0;    
     String nombre = " ";   
     String ubicacion = " ";
     int idPrograma = 0;
    
    String mensajeId=" ";
    String mensajeCapacidad=" ";
    String mensajeIdTipoInfraestructura=" ";
    String mensajeNombre=" ";
    String mensajeUbicacion=" ";  
    String mensajeidPrograma=" ";
    
    
    String mensajeAlerta=" ";
    
   
    
    public void cancelar() {
     this.setId(0);
     this.setCapacidad(0);
     this.setIdPrograma(0);
     this.setIdTipoInfraestructura(0);
     this.setNombre(" ");
     this.setUbicacion(" ");    
     this.setMensajeCapacidad(" ");
     this.setMensajeId(" ");
     this.setMensajeIdTipoInfraestructura(" ");
     this.setMensajeNombre(" ");
     this.setMensajeUbicacion(" ");
     this.setMensajeidPrograma(" ");    
    }
    
    private LinkedList<SelectItem> listaCandCmb= new LinkedList(); 
    
     public LinkedList<SelectItem> getListaCandCmb()
            throws SNMPExceptions, SQLException {
        return listaCandCmb;
    }

    public void setListaCandCmb(LinkedList<SelectItem> listaCandCmb) {
        this.listaCandCmb = listaCandCmb;
    }
    
    LinkedList<infraestructura> listaTablaInfraestructura = new LinkedList<infraestructura>();

    public LinkedList<infraestructura> getListaTablainfraestructura() throws SNMPExceptions, SQLException {
        return listaTablaInfraestructura;
    }

    public void setListaTablainfraestructura(LinkedList<infraestructura> listaTablainfraestructura) {
        this.listaTablaInfraestructura = listaTablainfraestructura;
    }
    
    public void FiltroTabla() throws SNMPExceptions, SQLException{
        LinkedList<infraestructura> listaD = new LinkedList<infraestructura>();
        infraestructuraDB dDB = new infraestructuraDB();

            
        listaD = dDB.consultarInfraestructura();
        if(listaD.size() > 0){
           this.setListaTablainfraestructura(listaD); 
        }
        else{
            if(listaD.size() == 0){
                this.setMensajeAlerta("No existe informacion de curso");
            }
        
        }
    }
    
    public void actualizaDatos() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException{
        infraestructuraDB cDB = new infraestructuraDB();
        infraestructura cur = new infraestructura();
        
        cur.setId(id);
          cur.setCapacidad(capacidad);
          cur.setIdPrograma(idPrograma);
          cur.setIdTipoInfraestructura(idTipoInfraestructura);
          cur.setNombre(nombre);
          cur.setUbicacion(ubicacion);
        
        cDB.actualizarInfraestructura(cur);
        this.setMensajeAlerta("Actualizacion Realizada");
        this.FiltroTabla();
    }
    
     public void ingresarRegistro()throws 
     SNMPExceptions, SQLException, NamingException, ClassNotFoundException{    
        infraestructuraDB dDB = new infraestructuraDB();
        infraestructura depUTN = new infraestructura();
        
        try{
           if(this.id == 0){
                this.mensajeId="Id es Requerido";
            }
            if(this.capacidad == 0){
                this.mensajeCapacidad="Capacidad es Requerido";
            }
            if(this.idTipoInfraestructura == 0){
                this.mensajeIdTipoInfraestructura="Tipo Infraestructura es Requerido";
            }
            if(this.nombre.equals(" ")){
                this.mensajeNombre="Nombre es Requerido";
            }
            if(this.ubicacion.equals(" ")){
                this.mensajeUbicacion="Ubicacion es Requerido";
            }          
            if(this.idPrograma == 0){
               this.mensajeidPrograma="IdPrograma es Requerido";     
            }
            
            
            if(this.id > 1){
                this.mensajeId=" ";
            }
            if(this.capacidad > 1){
                this.mensajeCapacidad=" ";
            }
            if(this.idTipoInfraestructura > 1){
                this.mensajeIdTipoInfraestructura=" ";
            }
            if(!this.nombre.equals(" ")){
                this.mensajeNombre=" ";
            }
            if(!this.ubicacion.equals(" ")){
                this.mensajeUbicacion=" ";
            }          
            if(this.idPrograma > 1){
               this.mensajeidPrograma=" ";     
            }
            
          
          depUTN.setId(id);
          depUTN.setCapacidad(capacidad);
          depUTN.setIdPrograma(idPrograma);
          depUTN.setIdTipoInfraestructura(idTipoInfraestructura);
          depUTN.setNombre(nombre);
          depUTN.setUbicacion(ubicacion);
          
          dDB.mvRegitroInfraestructura(depUTN);
          mensajeAlerta="Realizado con exito";
        }
        catch(Exception e){            
        }
        
    }
     
     public void asignaDatos(infraestructura dep){       
          setId(dep.getId());
          setCapacidad(dep.getCapacidad());
          setIdPrograma(dep.getIdPrograma());
          setIdTipoInfraestructura(dep.getIdTipoInfraestructura());
          setNombre(dep.getNombre());
          setUbicacion(dep.getUbicacion());
    }
    
     public LinkedList<SelectItem> getListaCand() throws SNMPExceptions, SQLException {
        int idPrograma=0;
        String nombre=" "; 
        LinkedList<programa> lista= new LinkedList<programa>();
        programaDB cDB= new programaDB();
        lista=cDB.moTodo();
        LinkedList resultList= new LinkedList();
        resultList.add(new SelectItem(0,"Seleccione Programa"));
        for(Iterator iter= lista.iterator();
                iter.hasNext();){
            
            programa cand= (programa)iter.next();
            idPrograma=cand.getId();
            nombre=cand.getNombre();
            resultList.add(new SelectItem(idPrograma,nombre));
            
        }
        return resultList;
    }


    public String getMensajeAlerta() {
        return mensajeAlerta;
    }

    public void setMensajeAlerta(String mensajeAlerta) {
        this.mensajeAlerta = mensajeAlerta;
    }

    public LinkedList<infraestructura> getListaTablaInfraestructura() {
        return listaTablaInfraestructura;
    }

    public void setListaTablaInfraestructura(LinkedList<infraestructura> listaTablaInfraestructura) {
        this.listaTablaInfraestructura = listaTablaInfraestructura;
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

    public String getMensajeId() {
        return mensajeId;
    }

    public void setMensajeId(String mensajeId) {
        this.mensajeId = mensajeId;
    }

    public String getMensajeCapacidad() {
        return mensajeCapacidad;
    }

    public void setMensajeCapacidad(String mensajeCapacidad) {
        this.mensajeCapacidad = mensajeCapacidad;
    }

    public String getMensajeIdTipoInfraestructura() {
        return mensajeIdTipoInfraestructura;
    }

    public void setMensajeIdTipoInfraestructura(String mensajeIdTipoInfraestructura) {
        this.mensajeIdTipoInfraestructura = mensajeIdTipoInfraestructura;
    }

    public String getMensajeNombre() {
        return mensajeNombre;
    }

    public void setMensajeNombre(String mensajeNombre) {
        this.mensajeNombre = mensajeNombre;
    }

    public String getMensajeUbicacion() {
        return mensajeUbicacion;
    }

    public void setMensajeUbicacion(String mensajeUbicacion) {
        this.mensajeUbicacion = mensajeUbicacion;
    }

    public String getMensajeidPrograma() {
        return mensajeidPrograma;
    }

    public void setMensajeidPrograma(String mensajeidPrograma) {
        this.mensajeidPrograma = mensajeidPrograma;
    }

    
  
    
    
    
}
