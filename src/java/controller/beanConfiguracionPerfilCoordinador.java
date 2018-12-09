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

/**
 *
 * @author KeylorSk8
 */
@Named(value = "beanConfiguracionPerfilCoordinador")
@SessionScoped
public class beanConfiguracionPerfilCoordinador implements Serializable {

    /**
     * Creates a new instance of beanConfiguracionPerfil
     */
    public beanConfiguracionPerfilCoordinador() {
    }

    public void cerrarSesion() throws IOException {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.removeAttribute("user");
        session.removeAttribute("TipoUsuario");
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }

}
