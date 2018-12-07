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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import model.Funcionario;
import model.Programa;
import model.ProgramaDB;

/**
 *
 * @author Pablo
 */
@Named(value = "beanMantPrograma")
@SessionScoped
public class beanMantPrograma implements Serializable {
    Funcionario fun;
    /**
     * Creates a new instance of beanMantPrograma
     */
    public beanMantPrograma() {
       
    }
    private int id;
    private String nombre;
    private boolean estado;
    private String codFunIngreso;
    private String fechaIngreso;
    private String codFunEdito;
    private String fechaEdito;
    private int idCoordinador;
    String estadoValidador;
    private String idNombre="";

    String mensajeId = " ";
    String mensajeNombre = " ";
    String mensajeEstado = " ";
    String mensajeCodFunIngreso = " ";
    String mensajefechaIngreso = " ";
    String mensajeCodFunEdito = " ";
    String mensajeFechaEdito = " ";
    String mensajeAlerta = " ";

    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    LinkedList<Programa> listaTablaPrograma = new LinkedList<>();

    public LinkedList<Programa> getListaTablaPrograma() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        ProgramaDB dDB = new ProgramaDB();
        LinkedList<Programa> listaTabla = new LinkedList<>();
        
       
            if(!this.idNombre.equals("")){
              listaTabla = buscarProgramaBean();
            }
             else{
             listaTabla = dDB.consultarPrograma();
        }

        return listaTabla;
    }

    public void setListaTablaPrograma(LinkedList<Programa> listaTablaPrograma) {
        this.listaTablaPrograma = listaTablaPrograma;
    }
    
    public LinkedList<Programa> buscarProgramaBean()throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException{
        ProgramaDB dDB = new ProgramaDB();
      LinkedList<Programa> Programa = new LinkedList<>();
      return Programa = dDB.buscarPrograma(idNombre);  
       
    }

    public void actualizaDatos() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        ProgramaDB cDB = new ProgramaDB();
        Programa cur = new Programa();

        cur.setNombre(this.getNombre());
        cur.setId(this.getId());
        cur.setEstado(this.isEstado());
        if(cur.isEstado()==true){
            this.estadoValidador="Activo";
        }
        else{
            this.estadoValidador="Inactivo";
        }
         HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
         fun = (Funcionario) session.getAttribute("user");

        cDB.actualizarPrograma(cur,fun);
    }

    public void ingresarRegistro() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        ProgramaDB dDB = new ProgramaDB();
        Programa depUTN = new Programa();

        try {
            if (this.id == 0) {
                this.mensajeId = "Id es Requerido";
            }
            if (this.nombre.equals(" ")) {
                this.mensajeNombre = "Nombre es Requerido";
            }
            if (this.estadoValidador.equals("--Seleccione--")) {
                this.mensajeEstado = "Estado Requerdido";
            }
            if (this.id > 1) {
                this.mensajeId = " ";
            }
            if (!this.nombre.equals(" ")) {
                this.mensajeNombre = " ";
            }
            if (!this.estadoValidador.equals("--Seleccione--")) {
                this.mensajeEstado = " ";
                if (this.estadoValidador.equals("Inactivo")) {
                    this.estado = false;
                } else {
                    this.estado = true;
                }
            }
             HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
             fun = (Funcionario) session.getAttribute("user");
            
            depUTN.setId(id);
            depUTN.setEstado(estado);
            depUTN.setNombre(nombre);       
            dDB.mvRegitroPrograma(depUTN,fun);          
            mensajeAlerta = "Realizado con exito";
        } catch (SNMPExceptions | SQLException e) {
            System.out.println("Error :" + e);
            System.out.println("Mensaje :" + e.getMessage());
        }
    }

    public void asignaDatos(Programa dep) {
        setId(dep.getId());
        setEstado(dep.isEstado());
        setNombre(dep.getNombre());
        
         if(this.isEstado()==true){
            this.estadoValidador="Activo";
        }
        else{
            this.estadoValidador="Inactivo";
        }
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
        this.setIdNombre("");
        
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

    public String getEstadoValidador() {
        return estadoValidador;
    }

    public void setEstadoValidador(String estadoValidador) {
        this.estadoValidador = estadoValidador;
    }

    public int getIdCoordinador() {
        return idCoordinador;
    }

    public void setIdCoordinador(int idCoordinador) {
        this.idCoordinador = idCoordinador;
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public Funcionario getFun() {
        return fun;
    }

    public void setFun(Funcionario fun) {
        this.fun = fun;
    }

    public String getIdNombre() {
        return idNombre;
    }

    public void setIdNombre(String idNombre) {
        this.idNombre = idNombre;
    }
    
    
}
