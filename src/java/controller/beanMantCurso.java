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
import java.util.Iterator;
import java.util.LinkedList;
import javax.faces.model.SelectItem;
import javax.naming.NamingException;
import model.Curso;
import model.CursoDB;
import model.Programa;
import model.ProgramaDB;

/**
 *
 * @author Pablo
 */
@Named(value = "beanMantCurso")
@SessionScoped
public class beanMantCurso implements Serializable {

    /**
     * Creates a new instance of beanMantCurso
     */
    
    int id = 0;
    String descripcion = " ";
    boolean estado = false;
    String codFunIngreso=" ";
    Date fechaIngreso=null;
    String codFunEdito=" ";
    Date fechaEdito=null;
    int idPrograma=0;
    
    
    String mensajeId = " ";
    String mensajeDescripcion = " ";
    String mensajeEstado = " ";
    String mensajeCodFunEdito=" ";
    String mensajeCodFunIngreso=" ";
    String mensajeFechaIngreso=" ";
    String mensajeFechaEdito=" ";
    String mensajeIdPrograma=" ";
    
    String mensajesetMensajeAct=" ";
    String mensajeAlerta=" ";
    
    private LinkedList<SelectItem> listaCandCmb= new LinkedList(); 
    
     public LinkedList<SelectItem> getListaCandCmb()
            throws SNMPExceptions, SQLException {
        return listaCandCmb;
    }

    public void setListaCandCmb(LinkedList<SelectItem> listaCandCmb) {
        this.listaCandCmb = listaCandCmb;
    }
    
    LinkedList<Curso> listaTablaCurso = new LinkedList<Curso>();

    public LinkedList<Curso> getListaTablaCurso() throws SNMPExceptions, SQLException {
        return listaTablaCurso;
    }

    public void setListaTablaCurso(LinkedList<Curso> listaTablaCurso) {
        this.listaTablaCurso = listaTablaCurso;
    }
    
    
    public beanMantCurso(){
        
    }
    
    
    public void actualizaDatos() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException{
        CursoDB cDB = new CursoDB();
        Curso cur = new Curso();
        
        cur.setId(id);
        cur.setCodFunEdito(codFunEdito);
        cur.setCodFunIngreso(codFunIngreso);
        cur.setDescripcion(descripcion);
        cur.setEstado(estado);
        cur.setFechaEdito(fechaEdito);
        cur.setFechaIngreso(fechaIngreso);
        cur.setIdPrograma(idPrograma);
        
        cDB.actualizarCurso(cur);
        this.setMensajesetMensajeAct("Actualizacion Realizada");
        this.FiltroTabla();
    }
    
    public void FiltroTabla() throws SNMPExceptions, SQLException{
        LinkedList<Curso> listaD = new LinkedList<Curso>();
        CursoDB dDB = new CursoDB();

            
        listaD = dDB.consultarCurso();
        if(listaD.size() > 0){
           this.setListaTablaCurso(listaD); 
        }
        else{
            if(listaD.size() == 0){
                this.setMensajeAlerta("No existe informacion de curso");
            }
        
        }
    }
    public void ingresarRegistro()throws 
     SNMPExceptions, SQLException, NamingException, ClassNotFoundException{    
        CursoDB dDB = new CursoDB();
        Curso depUTN = new Curso();
        
        try{
         if(this.id==0){
             this.mensajeId="Id es Requerido";
         }
         if(this.descripcion.equals(" ")){
             this.mensajeDescripcion="Descripcion es Requerido";
         }
         if(this.estado==false){
             this.mensajeEstado="Estado es Requerido";
         }
          if(this.codFunIngreso.equals(" ")){
             this.mensajeCodFunIngreso="CodFunIngreso es Requerido";
         }
          if(this.fechaIngreso==null){
              this.mensajeFechaIngreso="FechaIngreso es Requerido";
          }
         if(this.codFunEdito.equals(" ")){
             this.mensajeCodFunEdito="CodFunEdito es Requerido";
         }
         if(this.fechaEdito==null){
             this.mensajeFechaEdito="FechaEdito es Requerido";
         }
         if(this.idPrograma==0){
             this.mensajeIdPrograma="IdPrograma es Requerido";
         }
         
         if(this.id>1){
             this.mensajeId=" ";
         }
         if(!this.descripcion.equals(" ")){
             this.mensajeDescripcion=" ";
         }
         if(this.estado!=false){
             this.mensajeEstado=" ";
         }
          if(!this.codFunIngreso.equals(" ")){
             this.mensajeCodFunIngreso=" ";
         }
          if(this.fechaIngreso!=null){
              this.mensajeFechaIngreso=" ";
          }
         if(!this.codFunEdito.equals(" ")){
             this.mensajeCodFunEdito=" ";
         }
         if(this.fechaEdito!=null){
             this.mensajeFechaEdito=" ";
         }
         if(this.idPrograma!=0){
             this.mensajeIdPrograma=" ";
         }
         
         
            
          
          depUTN.setId(id);
          depUTN.setDescripcion(descripcion);
          depUTN.setEstado(estado);
          depUTN.setCodFunIngreso(codFunIngreso);
          depUTN.setFechaIngreso(fechaIngreso);
          depUTN.setCodFunEdito(codFunEdito);
          depUTN.setFechaEdito(fechaEdito);
          depUTN.setIdPrograma(idPrograma);
          
          dDB.mvRegitroCurso(depUTN);
      mensajeAlerta="Realizado con exito";
        }
        catch(Exception e){            
        }
        
    }
    
    public void asignaDatos(Curso dep){       
          this.setId(dep.getId());
          setDescripcion(dep.getDescripcion());
          setEstado(dep.isEstado());
          setCodFunIngreso(dep.getCodFunIngreso());
          setFechaIngreso(dep.getFechaIngreso());
          setCodFunEdito(dep.getCodFunEdito());
          setFechaEdito(dep.getFechaEdito());
          setIdPrograma(dep.getIdPrograma());
    }
    
     public LinkedList<SelectItem> getListaCand() throws SNMPExceptions, SQLException {
        int idPrograma=0;
        String nombreCurso=" "; 
        LinkedList<Programa> lista= new LinkedList<Programa>();
        ProgramaDB cDB= new ProgramaDB();
        lista=cDB.moTodo();
        LinkedList resultList= new LinkedList();
        resultList.add(new SelectItem(0,"Seleccione Programa"));
        for(Iterator iter= lista.iterator();
                iter.hasNext();){
            
            Programa cand= (Programa)iter.next();
            idPrograma=cand.getId();
            nombreCurso=cand.getNombre();
            resultList.add(new SelectItem(idPrograma,nombreCurso));
            
        }
        return resultList;
    }

    public void setListaCand(LinkedList<SelectItem> listaCand) {
        this.listaCandCmb = listaCand;
    }
    
    
    

    public String getMensajesetMensajeAct() {
        return mensajesetMensajeAct;
    }

    public void setMensajesetMensajeAct(String mensajesetMensajeAct) {
        this.mensajesetMensajeAct = mensajesetMensajeAct;
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
        this.setDescripcion(" ");
        this.setEstado(false);
        this.setFechaEdito(null);
        this.setFechaIngreso(null);
        this.setId(0);
        this.setIdPrograma(0);
        this.setMensajeAlerta(" ");
        this.setMensajeCodFunEdito(" ");
        this.setMensajeCodFunIngreso(" ");
        this.setMensajeDescripcion(" ");
        this.setMensajeEstado(" ");
        this.setMensajeFechaEdito(" ");
        this.setMensajeFechaIngreso(" ");
        this.setMensajeId(" ");
        this.setMensajeIdPrograma(" ");
        this.setMensajesetMensajeAct(" "); 
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public String getMensajeDescripcion() {
        return mensajeDescripcion;
    }

    public void setMensajeDescripcion(String mensajeDescripcion) {
        this.mensajeDescripcion = mensajeDescripcion;
    }

    public String getMensajeEstado() {
        return mensajeEstado;
    }

    public void setMensajeEstado(String mensajeEstado) {
        this.mensajeEstado = mensajeEstado;
    }

    public String getMensajeCodFunEdito() {
        return mensajeCodFunEdito;
    }

    public void setMensajeCodFunEdito(String mensajeCodFunEdito) {
        this.mensajeCodFunEdito = mensajeCodFunEdito;
    }

    public String getMensajeCodFunIngreso() {
        return mensajeCodFunIngreso;
    }

    public void setMensajeCodFunIngreso(String mensajeCodFunIngreso) {
        this.mensajeCodFunIngreso = mensajeCodFunIngreso;
    }

    public String getMensajeFechaIngreso() {
        return mensajeFechaIngreso;
    }

    public void setMensajeFechaIngreso(String mensajeFechaIngreso) {
        this.mensajeFechaIngreso = mensajeFechaIngreso;
    }

    public String getMensajeFechaEdito() {
        return mensajeFechaEdito;
    }

    public void setMensajeFechaEdito(String mensajeFechaEdito) {
        this.mensajeFechaEdito = mensajeFechaEdito;
    }

    public String getMensajeIdPrograma() {
        return mensajeIdPrograma;
    }

    public void setMensajeIdPrograma(String mensajeIdPrograma) {
        this.mensajeIdPrograma = mensajeIdPrograma;
    }
    
    
    
}
