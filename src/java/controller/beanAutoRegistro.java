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
import java.util.Iterator;
import java.util.LinkedList;
import javax.faces.model.SelectItem;
import model.Canton;
import model.CantonDB;
import model.Provincia;
import model.ProvinciaDB;

/**
 *
 * @author KeylorSk8
 */
@Named(value = "beanAutoRegistro")
@SessionScoped
public class beanAutoRegistro implements Serializable {

    String identificacion;
    String nombre;
    String apellido1;
    String apellido2;
    String fechaNaciemiento;
    String Email;
    String provincia = "1";
    String canton ="1";
    String distrito ="1";
    String barrio ="1";
    String otrasSenias ="1";
    
    LinkedList<SelectItem> provincias = new LinkedList<>();
    LinkedList<SelectItem> distritos = new LinkedList<>();
    LinkedList<SelectItem> cantones = new LinkedList<>();
    LinkedList<SelectItem> barrios = new LinkedList<>();
    /**
     * Creates a new instance of beanAutoRegistro
     */
    public beanAutoRegistro() {
    }
    
    public void actualizar() throws SNMPExceptions{
        LinkedList<SelectItem> cantones1 = this.getCantones();
        this.setCantones(cantones1);
    }
    
    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getFechaNaciemiento() {
        return fechaNaciemiento;
    }

    public void setFechaNaciemiento(String fechaNaciemiento) {
        this.fechaNaciemiento = fechaNaciemiento;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getOtrasSenias() {
        return otrasSenias;
    }

    public void setOtrasSenias(String otrasSenias) {
        this.otrasSenias = otrasSenias;
    }

    public LinkedList<SelectItem> getProvincias() throws SNMPExceptions {
        ProvinciaDB logica = new ProvinciaDB();
        LinkedList<Provincia> lista = logica.seleccionarProvincias();
        
        int id = 0;
        String nombre = "";
        
        LinkedList resultList = new LinkedList();
        
         for (Iterator iter = lista.iterator();iter.hasNext();) {
            Provincia pro = (Provincia) iter.next();
            id = pro.getId();
            nombre = pro.getNombre();
            resultList.add(new SelectItem(id,nombre));
        }
        return resultList;
    }

    public void setProvincias(LinkedList<SelectItem> provincias) {
        this.provincias = provincias;
    }

    public LinkedList<SelectItem> getDistritos() {
        return distritos;
    }

    public void setDistritos(LinkedList<SelectItem> distritos) {
        this.distritos = distritos;
    }

    public LinkedList<SelectItem> getCantones() throws SNMPExceptions {
        CantonDB logica = new CantonDB();
        LinkedList<Canton> lista = logica.seleccionarCantones(Integer.parseInt(this.getProvincia()));
        
        int id = 0;
        String nombre = "";
      
        LinkedList resultList = new LinkedList();
        
         for (Iterator iter = lista.iterator();iter.hasNext();) {
            Canton can = (Canton) iter.next();
            id = can.getId();
            nombre = can.getNombre();
            resultList.add(new SelectItem(id,nombre));
        }
        return resultList;
    }

    public void setCantones(LinkedList<SelectItem> cantones) {
        this.cantones = cantones;
    }

    public LinkedList<SelectItem> getBarrios() {
        return barrios;
    }

    public void setBarrios(LinkedList<SelectItem> barrios) {
        this.barrios = barrios;
    }
    
    
}
