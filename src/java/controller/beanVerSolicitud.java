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
import java.util.LinkedList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import model.Correo;
import model.Funcionario;
import model.FuncionarioDB;
import model.Programa;
import model.ProgramaDB;

/**
 *
 * @author KeylorSk8
 */
@Named(value = "beanVerSolicitud")
@SessionScoped
public class beanVerSolicitud implements Serializable {

    String idSolitud;
    Funcionario usuario;
    Funcionario solicitud;
    String razon = "";
    String mensaje;
    String[] chkCoordinador;
    String idPrograma;

    LinkedList<SelectItem> listaProgramas;

    /**
     * Creates a new instance of beanVerSolicitud
     *
     * @throws DAO.SNMPExceptions
     * @throws java.sql.SQLException
     * @throws javax.naming.NamingException
     * @throws java.lang.ClassNotFoundException
     */
    public beanVerSolicitud() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        usuario = (Funcionario) session.getAttribute("user");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hola Mundo"));
    }

    public void aceptarSolicitud() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException, IOException {
        solicitud = new FuncionarioDB().seleccionarFuncionario(idSolitud);
        solicitud.setCodFunIngreso(usuario.getId());
        solicitud.setFechaIngreso("GETDATE()");
        solicitud.setCodFunEdito(usuario.getId());
        solicitud.setFechaEdito("GETDATE()");
        solicitud.setSolicitud(false);
        solicitud.setCodFunEdito(this.generarCodigo());
        solicitud.setContraseña(this.generarContrasenia());
        solicitud.setEstadoSolicitud(true);
        try {
            if (chkCoordinador[0].equals("true")) {
                solicitud.setIdTipoUsuario(2);
                solicitud.setIdPrograma(Integer.parseInt(idPrograma));
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        String mensaje;
        if (solicitud.getIdTipoUsuario() == 2) {
            mensaje = "<Strong><h1>Bienvenido Coordinador DEAS</h1></Strong>"
                    + "<br>"
                    + "<p>Su cuenta ha sido aceptada y eres un nuevo coordinador DEAS,"
                    + "acontinuación te mostramos tus datos para accesar a tú cuenta.</p>"
                    + "<ul>"
                    + "<li>Usuario: " + solicitud.getId() + "</li>"
                    + "<li>Contraseña temporal: " + solicitud.getContraseña() + "</li>"
                    + "<li>Código de Acceso: " + solicitud.getCodFunEdito() + "</li>"
                    + "</ul>";
        } else {
            mensaje = "<Strong><h1>Bienvenido Funcionario DEAS</h1></Strong>"
                    + "<br>"
                    + "<p>Su cuenta ha sido aceptada y eres un nuevo Funcionario DEAS,"
                    + "acontinuación te mostramos tus datos para accesar a tú cuenta.</p>"
                    + "<ul>"
                    + "<li>Usuario: " + solicitud.getId() + "</li>"
                    + "<li>Contraseña temporal: " + solicitud.getContraseña() + "</li>"
                    + "<li>Código de Acceso: " + solicitud.getCodFunEdito() + "</li>"
                    + "</ul>";
        }
        if (Correo.enviarCorreo(solicitud.getEmail(), "Cuenta DEAS Aceptada", mensaje)) {
            new FuncionarioDB().actualizarFuncionario(solicitud);
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().getExternalContext().redirect("mantenimientoCoordinacionNuevosUsuarios.xhtml");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario Aceptado con éxito"));
        }
    }

    public void rechazarSoicitud() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException, IOException {
        if (razon.equals("")) {
            setMensaje("Digite la razón de rechazó");
        } else {
            solicitud = new FuncionarioDB().seleccionarFuncionario(idSolitud);
            solicitud.setCodFunIngreso(usuario.getId());
            solicitud.setFechaIngreso("GETDATE()");
            solicitud.setCodFunEdito(usuario.getId());
            solicitud.setFechaEdito("GETDATE()");
            solicitud.setSolicitud(false);
            solicitud.setCodFunEdito(usuario.getId());
            solicitud.setContraseña("");
            solicitud.setEstadoSolicitud(false);
            String mensaje = "<Strong><h1>Su cuenta DEAS fue rechazada</h1></Strong>"
                        + "<br>"
                        + "<p>Su cuenta ha sido rechazada"
                        + "por la siguiente razón</p>"
                        + "<p>"+razon+"</p>";
            if (Correo.enviarCorreo(solicitud.getEmail(), "Cuenta DEAS Rechazada", mensaje)) {
                new FuncionarioDB().actualizarFuncionario(solicitud);
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().getFlash().setKeepMessages(true);
                FacesContext.getCurrentInstance().getExternalContext().redirect("mantenimientoCoordinacionNuevosUsuarios.xhtml");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario Rechazado"));
            }
        }
    }

    public String generarContrasenia() {
        String NUMEROS = "0123456789";
        String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
        String ESPECIALES = "ñÑ";
        String key = NUMEROS + MAYUSCULAS + MINUSCULAS + ESPECIALES;
        String pswd = "";
        for (int i = 0; i < 8; i++) {
            pswd += (key.charAt((int) (Math.random() * key.length())));
        }
        return pswd;
    }

    public int generarCodigo() {
        int num1 = (int) (10000 * Math.random());
        String num = "" + num1;
        if(num.length() < 4){
            return Integer.parseInt(num) + 1000;
        }else{
            return Integer.parseInt(num);
        }
    }

    public LinkedList<SelectItem> getListaProgramas() throws SNMPExceptions {
        ProgramaDB logica = new ProgramaDB();
        LinkedList<Programa> programas = logica.seleccionarProgramasSinCoordinador();
        LinkedList<SelectItem> listaPro = new LinkedList<>();
        programas.forEach((pro) -> {
            listaPro.add(new SelectItem(pro.getId(), pro.getNombre()));
        });
        return listaPro;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }

    public String[] getChkCoordinador() {
        return chkCoordinador;
    }

    public void setChkCoordinador(String[] chkCoordinador) {
        this.chkCoordinador = chkCoordinador;
    }

    public String getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(String idPrograma) {
        this.idPrograma = idPrograma;
    }

    public void setListaProgramas(LinkedList<SelectItem> listaProgramas) {
        this.listaProgramas = listaProgramas;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getIdSolitud() {
        return idSolitud;
    }

    public void setIdSolitud(String idSolitud) {
        this.idSolitud = idSolitud;
    }

    public Funcionario getUsuario() {
        return usuario;
    }

    public void setUsuario(Funcionario usuario) {
        this.usuario = usuario;
    }

    public Funcionario getSolicitud() throws SNMPExceptions, SQLException, NamingException, NamingException, ClassNotFoundException {
        FuncionarioDB logica = new FuncionarioDB();
        return logica.seleccionarFuncionario(this.getIdSolitud());
    }

    public void setSolicitud(Funcionario solicitud) {
        this.solicitud = solicitud;
    }

}
