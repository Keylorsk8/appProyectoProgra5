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
import model.curso;
import model.cursoDB;
import model.programa;
import model.programaDB;

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
    
    LinkedList<curso> listaTablaCurso = new LinkedList<curso>();

    public LinkedList<curso> getListaTablaCurso() throws SNMPExceptions, SQLException {
        return listaTablaCurso;
    }

    public void setListaTablaCurso(LinkedList<curso> listaTablaCurso) {
        this.listaTablaCurso = listaTablaCurso;
    }
    
    
    public beanMantCurso(){
        
    }
    
    
    public void actualizaDatos() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException{
        cursoDB cDB = new cursoDB();
        curso cur = new curso();
        
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
        LinkedList<curso> listaD = new LinkedList<curso>();
        cursoDB dDB = new cursoDB();

            
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
        cursoDB dDB = new cursoDB();
        curso depUTN = new curso();
        
        try{
         if(this.id==0){
             this.mensajeId="Id es Requerido";
         }
         if(this.codFunEdito.equals(" ")){
             this.mensajeCodFunEdito="CodFunEdito es Requerido";
         }
         if(this.codFunIngreso.equals(" ")){
             this.mensajeCodFunIngreso="CodFunIngreso es Requerido";
         }
         if(this.descripcion.equals(" ")){
             this.mensajeDescripcion="Descripcion es Requerido";
         }
         if(this.estado==false){
             
         }
            
          
          depUTN.setCodigoCurso(codigo);
          depUTN.setCodigoPrograma(codigoPrograma);
          depUTN.setDescripcion(descripcion);
          
          dDB.mvRegitroCurso(depUTN);
      mensajeAlerta="Realizado con exito";
        }
        catch(Exception e){            
        }
        
    }
    
    public void asignaDatos(curso dep){       
        this.setCodigo(dep.getCodigoCurso());
        this.setDescripcion(dep.getDescripcion());
        this.setCodigoPrograma(dep.getDescripcion());
    }
    
     public LinkedList<SelectItem> getListaCand() throws SNMPExceptions, SQLException {
        String idPrograma="";
        String nombreCurso=" "; 
        LinkedList<programa> lista= new LinkedList<programa>();
        programaDB cDB= new programaDB();
        lista=cDB.moTodo();
        LinkedList resultList= new LinkedList();
        resultList.add(new SelectItem(0,"Seleccione Programa"));
        for(Iterator iter= lista.iterator();
                iter.hasNext();){
            
            programa cand= (programa)iter.next();
            idPrograma=cand.getCodigoPrograma();
            nombreCurso=cand.getNombreCurso();
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
        this.setCodigo(" ");
        this.setCodigoPrograma(" ");
        this.setDescripcion(" ");
        this.setMensajeCodigo(" ");
        this.setMensajeDescripcion(" ");
        this.setMensajePrograma(" ");
    }
    
}
