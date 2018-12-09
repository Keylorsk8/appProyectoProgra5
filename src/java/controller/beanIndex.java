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
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import model.Funcionario;
import model.FuncionarioDB;

/**
 *
 * @author KeylorSk8
 */
@Named(value = "beanIndex")
@SessionScoped
public class beanIndex implements Serializable {

    String usuario;
    String contraseña;
    String tipoUsuario;

    /**
     * Creates a new instance of beanIndex
     * @throws java.io.IOException
     */
    public beanIndex() throws IOException {
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        Funcionario fun = (Funcionario)session.getAttribute("user");
        if(fun != null){
            if(fun.getIdTipoUsuario() == 1){
                FacesContext.getCurrentInstance().getExternalContext().redirect("MenuPrincipal.xhtml");
            }else{
                FacesContext.getCurrentInstance().getExternalContext().redirect("MenuPrincipalCoordinador.xhtml");
            }
        }
    }

    public void iniciarSesion() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException, IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        if (validar()) {
            Funcionario fun = seleccionarUsuario();
            if (fun != null) {
                if (fun.getContraseña().equals(contraseña)) {
                    if (fun.getIdTipoUsuario() == 1 && Integer.parseInt(tipoUsuario) == 2) {
                        context.addMessage(null, new FacesMessage("Este usuario no es un Coordinador", "Cambie el tipo de usuario a Funcionario o Intentelo con otro usuario"));
                    }else{
                        Redireccionar(fun);
                    }
                } else {
                    context.addMessage(null, new FacesMessage("Contraseña incorrecta", "La contraseña indica no es correcta, intentelo de nuevo"));
                }
            } else {
                context.addMessage(null, new FacesMessage("Este usuario no existe", "El usuario indicado no existe, intente con otro o solicite una cuenta"));
            }
        }
    }
    
    
    public void Redireccionar(Funcionario fun) throws IOException{
        if(fun.getIdTipoUsuario() == 1){
            HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                session.setAttribute("user", fun);
                session.setAttribute("TipoUsuario",1);
            FacesContext.getCurrentInstance().getExternalContext().redirect("MenuPrincipal.xhtml");
        }
        if(fun.getIdTipoUsuario() == 2){
            if(Integer.parseInt(tipoUsuario) == 1){
                HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                session.setAttribute("user", fun);
                session.setAttribute("TipoUsuario",1);
                FacesContext.getCurrentInstance().getExternalContext().redirect("MenuPrincipal.xhtml");
            }else{
                HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                session.setAttribute("user", fun);
                session.setAttribute("TipoUsuario",2);
                FacesContext.getCurrentInstance().getExternalContext().redirect("MenuPrincipalCoordinador.xhtml");
            }
        }
    }

    public boolean validar() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (usuario.equals("")) {
            context.addMessage(null, new FacesMessage("Datos incompletos", "Completa los datos para iniciar sesión"));
            return false;
        } else {
            if (contraseña.equals("")) {
                context.addMessage(null, new FacesMessage("Datos incompletos", "Completa los datos para iniciar sesión"));
                return false;
            } else {
                if (tipoUsuario == null) {
                    context.addMessage(null, new FacesMessage("Datos incompletos", "Completa los datos para iniciar sesión"));
                    return false;
                } else {
                    return true;
                }
            }
        }
    }

    public Funcionario seleccionarUsuario() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        FuncionarioDB logica = new FuncionarioDB();
        return logica.seleccionarFuncionario(usuario);
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}

