/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.SNMPExceptions;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import model.Barrio;
import model.BarrioDB;
import model.Canton;
import model.CantonDB;
import model.Direccion;
import model.DireccionDB;
import model.Distrito;
import model.DistritoDB;
import model.Funcionario;
import model.Provincia;
import model.ProvinciaDB;
import model.Telefono;
import model.TelefonoDB;

/**
 *
 * @author KeylorSk8
 */
@Named(value = "beanConfiguracionPerfil")
@SessionScoped
public class beanConfiguracionPerfil implements Serializable {

    Funcionario fun;
    Direccion dir;
    
    String numero;
    String tipoTelefono;
    String mensajes;
    
    LinkedList<SelectItem> provincias = new LinkedList<>();
    LinkedList<SelectItem> cantones = new LinkedList<>();
    LinkedList<SelectItem> distritos = new LinkedList<>();
    LinkedList<SelectItem> barrios = new LinkedList<>();
    LinkedList<Telefono> telefonos = new LinkedList<>();
    
    /**
     * Creates a new instance of beanConfiguracionPerfil
     */
    public beanConfiguracionPerfil() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        fun = (Funcionario) session.getAttribute("user");
        dir = new DireccionDB().seleccionarDireccion(fun.getId());
    }
    
    public void guardarNumero(){
        if(this.numero.equals("")){
            mensajes = "Digite el número a insertar";
        }else{
            Telefono tel = null;
            try {
                tel = new Telefono(Integer.parseInt(tipoTelefono), Integer.parseInt(numero));
                TelefonoDB logica = new TelefonoDB();
                logica.insertarTelefono(tel, fun);
                numero = "";
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                mensajes = "Digite un número correcto";
            }
        }
    }

    public void cerrarSesion() throws IOException {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.removeAttribute("user");
        session.removeAttribute("TipoUsuario");
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }

    public String getMensajes() {
        return mensajes;
    }

    public LinkedList<Telefono> getTelefonos() {
        return new TelefonoDB().seleccionarTelefonos(fun.getId());
    }

    public void setTelefonos(LinkedList<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    public void setMensajes(String mensajes) {
        this.mensajes = mensajes;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipoTelefono() {
        return tipoTelefono;
    }

    public void setTipoTelefono(String tipoTelefono) {
        this.tipoTelefono = tipoTelefono;
    }

    public Funcionario getFun() {
        return fun;
    }

    public void setFun(Funcionario fun) {
        this.fun = fun;
    }

    public Direccion getDir() {
        return dir;
    }

    public void setDir(Direccion dir) {
        this.dir = dir;
    }
    
    public void valueChangedProvincias(AjaxBehaviorEvent event) throws SNMPExceptions {
        LinkedList<SelectItem> cantones1 = this.getCantones();
        this.setCantones(cantones1);
        LinkedList<SelectItem> distritos1 = this.getDistritos();
        this.setDistritos(distritos1);
        LinkedList<SelectItem> barrios1 = this.getBarrios();
        this.setBarrios(barrios1);
    }

    public void valueChangedCanton(AjaxBehaviorEvent event) throws SNMPExceptions {
        LinkedList<SelectItem> distritos1 = this.getDistritos();
        this.setDistritos(distritos1);
        LinkedList<SelectItem> barrios1 = this.getBarrios();
        this.setBarrios(barrios1);
    }

    public void valueChangedDistrito(AjaxBehaviorEvent event) throws SNMPExceptions {
        LinkedList<SelectItem> barrios1 = this.getBarrios();
        this.setBarrios(barrios1);
    }
    
    public LinkedList<SelectItem> getProvincias() throws SNMPExceptions {
        ProvinciaDB logica = new ProvinciaDB();
        LinkedList<Provincia> lista = logica.seleccionarProvincias();

        int id = 0;
        String nombre = "";

        LinkedList resultList = new LinkedList();

        for (Iterator iter = lista.iterator(); iter.hasNext();) {
            Provincia pro = (Provincia) iter.next();
            id = pro.getId();
            nombre = pro.getNombre();
            resultList.add(new SelectItem(id, nombre));
        }
        return resultList;
    }

    public void setProvincias(LinkedList<SelectItem> provincias) {
        this.provincias = provincias;
    }

    public LinkedList<SelectItem> getDistritos() throws SNMPExceptions {
        DistritoDB logica = new DistritoDB();
        LinkedList<Distrito> lista = logica.seleccionarDistritos(dir.getIdCanton(), dir.getIdProvincia());

        int id = 0;
        String nombre = "";

        LinkedList resultList = new LinkedList();

        for (Iterator iter = lista.iterator(); iter.hasNext();) {
            Distrito dis = (Distrito) iter.next();
            id = dis.getId();
            nombre = dis.getNombre();
            resultList.add(new SelectItem(id, nombre));
        }
        return resultList;
    }

    public void setDistritos(LinkedList<SelectItem> distritos) {
        this.distritos = distritos;
    }

    public LinkedList<SelectItem> getCantones() throws SNMPExceptions {
        CantonDB logica = new CantonDB();
        LinkedList<Canton> lista = logica.seleccionarCantones(dir.getIdProvincia());

        int id = 0;
        String nombre = "";

        LinkedList resultList = new LinkedList();

        for (Iterator iter = lista.iterator(); iter.hasNext();) {
            Canton can = (Canton) iter.next();
            id = can.getId();
            nombre = can.getNombre();
            resultList.add(new SelectItem(id, nombre));
        }
        return resultList;
    }

    public void setCantones(LinkedList<SelectItem> cantones) {
        this.cantones = cantones;
    }

    public LinkedList<SelectItem> getBarrios() throws SNMPExceptions {
        BarrioDB logica = new BarrioDB();
        LinkedList<Barrio> lista = logica.seleccionarBarrios(dir.getIdCanton(),dir.getIdProvincia(), dir.getIdDistrito());

        int id = 0;
        String nombre = "";

        LinkedList resultList = new LinkedList();

        for (Iterator iter = lista.iterator(); iter.hasNext();) {
            Barrio ba = (Barrio) iter.next();
            id = ba.getId();
            nombre = ba.getNombre();
            resultList.add(new SelectItem(id, nombre));
        }
        return resultList;
    }

    public void setBarrios(LinkedList<SelectItem> barrios) {
        this.barrios = barrios;
    }

}
