/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.xml.ws.RespectBindingFeature;
import model.Funcionario;

/**
 *
 * @author KeylorSk8
 */
@Named(value = "beanMenuPrincipal")
@SessionScoped
public class beanMenuPrincipal implements Serializable {

    String saludo;
    String NombreCuenta;

    /**
     * Creates a new instance of beanMenuPrincipal
     */
    public beanMenuPrincipal() throws IOException {
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            Funcionario fun = (Funcionario) session.getAttribute("user");
            saludo = "Bienvenido " + fun.getNombre() + " " + fun.getApellido1() + " - Funcionario DEAS";
            NombreCuenta = fun.getNombre() + " " + fun.getApellido1();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        }
    }

    public String getSaludo() {
        return saludo;
    }

    public void setSaludo(String saludo) {
        this.saludo = saludo;
    }

    public String getNombreCuenta() {
        return NombreCuenta;
    }

    public void setNombreCuenta(String NombreCuenta) {
        this.NombreCuenta = NombreCuenta;
    }
}
