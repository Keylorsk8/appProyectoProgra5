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
import java.util.LinkedList;
import model.Funcionario;
import model.FuncionarioDB;

/**
 *
 * @author KeylorSk8
 */
@Named(value = "beanNuevosUsuarios")
@SessionScoped
public class beanNuevosUsuarios implements Serializable {

    String mensaje ="Nuevas solicitudes de Funcionarios";
    LinkedList<Funcionario> Funcionarios = new LinkedList<>();
    FuncionarioDB logica = new FuncionarioDB();
    /**
     * Creates a new instance of beanNuevosUsuarios
     */
    public beanNuevosUsuarios() throws SNMPExceptions {
        this.funcionariosNuevos();
    }
    
    public void funcionariosNuevos() throws SNMPExceptions{
        this.setMensaje("Nuevas solicitudes de Funcionarios");
        this.setFuncionarios(logica.seleccionarSolicitudFuncionarios());
    }
    
    public void funcionariosRechazados() throws SNMPExceptions{
        this.setMensaje("Solicitudes de funcionarios rechazadas");
        this.setFuncionarios(logica.seleccionarFuncionariosRechazados());
    }

    public LinkedList<Funcionario> getFuncionarios() {
        return Funcionarios;
    }

    public void setFuncionarios(LinkedList<Funcionario> Funcionarios) {
        this.Funcionarios = Funcionarios;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public FuncionarioDB getLogica() {
        return logica;
    }

    public void setLogica(FuncionarioDB logica) {
        this.logica = logica;
    }
}
