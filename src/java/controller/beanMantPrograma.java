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
import java.util.Date;
import java.util.LinkedList;
import javax.naming.NamingException;
import model.programa;
import model.programaDB;

/**
 *
 * @author Pablo
 */
@Named(value = "beanMantPrograma")
@SessionScoped
public class beanMantPrograma implements Serializable {

    /**
     * Creates a new instance of beanMantPrograma
     */
    public beanMantPrograma() {
    }
    private int id;
    private String nombre;
    private boolean estado;
    private String codFunIngreso;
    private Date fechaIngreso;
    private String codFunEdito;
    private Date fechaEdito;
    
    String mensajeId = " ";
    String mensajeNombre=" ";
    String mensajeEstado=" ";
    String mensajeCodFunIngreso=" ";
    String mensajefechaIngreso=" ";
    String mensajeCodFunEdito=" ";
    String mensajeFechaEdito=" ";
    String mensajeAlerta = " ";

    LinkedList<programa> listaTablaPrograma = new LinkedList<>();

    public LinkedList<programa> getListaTablaPrograma() throws SNMPExceptions, SQLException {
        programaDB dDB = new programaDB();
        return dDB.consultarPrograma();
    }

    public void setListaTablaPrograma(LinkedList<programa> listaTablaPrograma) {
        this.listaTablaPrograma = listaTablaPrograma;
    }

    public void actualizaDatos() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        programaDB cDB = new programaDB();
        programa cur = new programa();
        
        cur.setNombre(this.getNombre());
        cur.setId(this.getId());
        cur.setCodFunEdito(this.getCodFunEdito());
        cur.setEstado(this.isEstado());
        cur.setCodFunIngreso(this.codFunIngreso);
        cur.setFechaEdito(this.fechaEdito);
        cur.setFechaIngreso(this.fechaIngreso);
        
        cDB.actualizarPrograma(cur);
    }

    public void ingresarRegistro() throws
        SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        programaDB dDB = new programaDB();
        programa depUTN = new programa();

        try {
            if(this.id==0){
                this.mensajeId = "Id es Requerido"; 
            }
            if(this.nombre.equals(" ")){
                this.mensajeNombre= "Nombre es Requerido";
            }
            if(this.codFunIngreso.equals(" ")){
                this.mensajeCodFunIngreso= "CodFunIngreso es Requerido";
            }
            if(this.fechaEdito == null){
                this.mensajeFechaEdito= "FechaEdito es Requerido";
            }
            if(this.fechaIngreso == null){
                this.mensajefechaIngreso= "FechaIngreso es Requerido";
            }
            if(this.codFunEdito.equals(" ")){
                this.mensajeCodFunEdito= "ConFunEdito es Requerido";
            }
            if(this.estado==false){
                this.mensajeEstado = "Estado Requerdido";
            }
            
            if(this.id>1){
                this.mensajeId = " "; 
            }
            if(!this.nombre.equals(" ")){
                this.mensajeNombre= " ";
            }
            if(!this.codFunIngreso.equals(" ")){
                this.mensajeCodFunIngreso= " ";
            }
            if(this.fechaEdito != null){
                this.mensajeFechaEdito= " ";
            }
            if(this.fechaIngreso != null){
                this.mensajefechaIngreso= " ";
            }
            if(!this.codFunEdito.equals(" ")){
                this.mensajeCodFunEdito= " ";
            }
            if(this.estado==true){
                this.mensajeEstado = " ";
            }
            

            depUTN.setId(id);
            depUTN.setCodFunEdito(codFunEdito);
            depUTN.setCodFunIngreso(codFunIngreso);
            depUTN.setEstado(estado);
            depUTN.setFechaEdito(fechaEdito);
            depUTN.setFechaIngreso(fechaIngreso);
            depUTN.setNombre(nombre);

            dDB.mvRegitroPrograma(depUTN);
            mensajeAlerta = "Realizado con exito";
        } catch (Exception e) {
        }

    }

    public void asignaDatos(programa dep) {
           setId(dep.getId());
            setCodFunEdito(dep.getCodFunEdito());
            setCodFunIngreso(dep.getCodFunIngreso());
           setEstado(dep.isEstado());
            setFechaEdito(dep.getFechaEdito());
            setFechaIngreso(dep.getFechaIngreso());
            setNombre(dep.getNombre());

    }

    public String getMensajeAlerta() {
        return mensajeAlerta;
    }

    public void setMensajeAlerta(String mensajeAlerta) {
        this.mensajeAlerta = mensajeAlerta;
    }

  

    
    public void cancelar() {
        this.setCodFunEdito(" ");
        this.setCodFunIngreso(" ");
        this.setEstado(false);
        this.setFechaEdito(null);
        this.setFechaIngreso(null);
        this.setId(0);
        this.setMensajeAlerta(" ");
        this.setMensajeCodFunEdito(" ");
        this.setMensajeCodFunIngreso(" ");
        this.setMensajeEstado(" ");
        this.setMensajeFechaEdito(" ");
        this.setMensajeId(" ");
        this.setMensajeNombre(" ");
        this.setMensajefechaIngreso(" ");
        this.setNombre(" ");
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

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getCodFunEdito() {
        return codFunEdito;
    }

    public void setCodFunEdito(String codFunEdito) {
        this.codFunEdito = codFunEdito;
    }

    public Date getFechaEdito() {
        return fechaEdito;
    }

    public void setFechaEdito(Date fechaEdito) {
        this.fechaEdito = fechaEdito;
    }

    public String getMensajeId() {
        return mensajeId;
    }

    public void setMensajeId(String mensajeId) {
        this.mensajeId = mensajeId;
    }

    public String getMensajeNombre() {
        return mensajeNombre;
    }

    public void setMensajeNombre(String mensajeNombre) {
        this.mensajeNombre = mensajeNombre;
    }

    public String getMensajeEstado() {
        return mensajeEstado;
    }

    public void setMensajeEstado(String mensajeEstado) {
        this.mensajeEstado = mensajeEstado;
    }

    public String getMensajeCodFunIngreso() {
        return mensajeCodFunIngreso;
    }

    public void setMensajeCodFunIngreso(String mensajeCodFunIngreso) {
        this.mensajeCodFunIngreso = mensajeCodFunIngreso;
    }

    public String getMensajefechaIngreso() {
        return mensajefechaIngreso;
    }

    public void setMensajefechaIngreso(String mensajefechaIngreso) {
        this.mensajefechaIngreso = mensajefechaIngreso;
    }

    public String getMensajeCodFunEdito() {
        return mensajeCodFunEdito;
    }

    public void setMensajeCodFunEdito(String mensajeCodFunEdito) {
        this.mensajeCodFunEdito = mensajeCodFunEdito;
    }

    public String getMensajeFechaEdito() {
        return mensajeFechaEdito;
    }

    public void setMensajeFechaEdito(String mensajeFechaEdito) {
        this.mensajeFechaEdito = mensajeFechaEdito;
    }

    
    
}
