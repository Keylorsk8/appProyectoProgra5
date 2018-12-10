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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Funcionario;
import model.FuncionarioDB;

/**
 *
 * @author KeylorSk8
 */
@Named(value = "beanPrimeraVez")
@SessionScoped
public class beanPrimeraVez implements Serializable {

    Funcionario fun;
    int tipoUsuario;
    String codigo = "";
    String contra = "";
    String contra2 = "";

    /**
     * Creates a new instance of beanPrimeraVez
     */
    public beanPrimeraVez() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        fun = (Funcionario) session.getAttribute("user");
        tipoUsuario = (int) session.getAttribute("TipoUsuario");
    }

    public void aceptar() throws IOException {
        if (!validar()) {
            if (validarCodigo()) {
                if (!validarContra()) {
                    FuncionarioDB logica = new FuncionarioDB();
                    fun.setContraseña(contra);
                    logica.primeraVez(fun);
                    if(tipoUsuario == 1){
                        FacesContext.getCurrentInstance().getExternalContext().redirect("MenuPrincipal.xhtml");
                    }else{
                        FacesContext.getCurrentInstance().getExternalContext().redirect("MenuPrincipalCoordinador.xhtml");
                    }
                }
            }
        }
    }

    public boolean validarCodigo() {
        if (!String.valueOf(fun.getCodFunEdito()).equals(codigo)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El código no coincide, intentelo de nuevo"));
            return false;
        } else {
            return true;
        }
    }

    public boolean validarContra() {
        boolean errores = false;
        String carac = "#,-*/:;";

        boolean num = false;
        boolean letras = false;
        boolean mayus = false;
        boolean minus = false;
        boolean esp = false;

        if (contra.length() < 8) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La contraseña debe tener 8 digitas minino y 12 máximo"));
            errores = true;
        }
        if (contra.length() > 12) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La contraseña debe tener 8 digitas minino y 12 máximo"));
        }
        for (int i = 0; i < contra.length(); i++) {
            try {
                Integer.parseInt(String.valueOf(contra.charAt(i)));
                num = true;
            } catch (Exception e) {
                letras = true;
            }
        }
        for (int i = 0; i < contra.length(); i++) {
            try {
                if (Character.isUpperCase(contra.charAt(i))) {
                    mayus = true;
                } else {
                    minus = true;
                }
            } catch (Exception e) {
            }
        }
        for (int i = 0; i < contra.length(); i++) {
            for (int j = 0; j < carac.length(); j++) {
                if (contra.charAt(i) == carac.charAt(j)) {
                    esp = true;
                }
            }
        }
        if (!num) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La contraseña debe contener minusculas"));
            errores = true;
        }
        if (!mayus) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La contraseña debe contener mayusculas"));
            errores = true;
        }
        if (!letras) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La contraseña debe contener letras"));
            errores = true;
        }
        if (esp) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La contraseña no debe contener caracteres especiales:  # , - * / : ; "));
            errores = true;
        }
        return errores;
    }

    public boolean validar() {
        boolean errores = false;
        if (codigo.equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Digite su código de acceso"));
            errores = true;
        }
        if (contra.equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Digite su nueva contraseña"));
            errores = true;
        }
        if (contra2.equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Confirme su nueva contraseña"));
            errores = true;
        }
        if (!contra.equals(contra2)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Las contraseñas no coinciden"));
            errores = true;
        }
        return errores;
    }

    public Funcionario getFun() {
        return fun;
    }

    public void setFun(Funcionario fun) {
        this.fun = fun;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getContra2() {
        return contra2;
    }

    public void setContra2(String contra2) {
        this.contra2 = contra2;
    }

}
