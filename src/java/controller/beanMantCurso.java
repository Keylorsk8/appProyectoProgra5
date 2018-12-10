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
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import model.Curso;
import model.CursoDB;
import model.Funcionario;
import model.Programa;
import model.ProgramaDB;

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
    Funcionario fun;
    int id = 0;
    String descripcion = " ";
    boolean estado = false;
    String codFunIngreso = " ";
    Date fechaIngreso = null;
    String codFunEdito = " ";
    Date fechaEdito = null;
    int idPrograma = 0;
    String idNombre = "";

    String estadoValidador = " ";
    String mensajeId = " ";
    String mensajeDescripcion = " ";
    String mensajeEstado = " ";
    String mensajeIdPrograma = " ";
    String mensajeId2 = " ";
    String mensajesetMensajeAct = " ";
    String mensajeAlerta = " ";
    LinkedList<SelectItem> listaCandCmb = new LinkedList();
    LinkedList<Curso> listaTablaCurso = new LinkedList<>();

    public beanMantCurso() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        fun = (Funcionario) session.getAttribute("user");
    }

    public LinkedList<Curso> buscarCursoBean() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        CursoDB dDB = new CursoDB();
        LinkedList<Curso> curso = new LinkedList<>();
        return curso = dDB.buscarCurso(idNombre);
    }

    public void actualizaDatos() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        CursoDB cDB = new CursoDB();
        Curso cur = new Curso();
        if (!validar()) {
            cur.setId(this.getId());
            cur.setDescripcion(this.getDescripcion());
            cur.setEstado(this.estadoValidador.equals("Activo"));
            cur.setIdPrograma(this.getIdPrograma());
            cDB.actualizarCurso(cur, fun);
        }
    }

    public String nombrePrograma(int idPrograma) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        LinkedList<Programa> periodo = new ProgramaDB().buscarPrograma(String.valueOf(idPrograma));
        Programa cu = periodo.get(0);
        return cu.getNombre();
    }

    public void ingresarRegistro() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        CursoDB dDB = new CursoDB();
        Curso depUTN = new Curso();
        if (!validar()) {
            depUTN.setId(id);
            depUTN.setDescripcion(descripcion);
            depUTN.setEstado(estado);
            depUTN.setIdPrograma(idPrograma);
            dDB.mvRegitroCurso(depUTN, fun);
            mensajeAlerta = "Realizado con exito";
        }
    }

    public boolean validar() {
        boolean errores = false;
        if (this.id == 0) {
            this.mensajeId = "Id es Requerido";
            errores = true;
        }
        if (this.descripcion.equals(" ")) {
            this.mensajeDescripcion = "Descripción es Requerido";
            errores = true;
        }
        if (this.estadoValidador.equals("--Seleccione--")) {
            this.mensajeEstado = "Estado es Requerido";
            errores = true;
        }
        if (this.idPrograma == 0) {
            this.mensajeIdPrograma = "Programa es Requerido";
            errores = true;
        }
        if (!this.estadoValidador.equals("--Seleccione--")) {
            this.estado = this.estadoValidador.equals("Activo");
        } else {
            mensajeAlerta = "Estado es Requerido";
            errores = true;
        }
        return errores;
    }

    public void asignaDatos(Curso dep) {
        this.setId(dep.getId());
        setDescripcion(dep.getDescripcion());
        setEstado(dep.isEstado());
        setCodFunIngreso(dep.getCodFunIngreso());
        setFechaIngreso(dep.getFechaIngreso());
        setCodFunEdito(dep.getCodFunEdito());
        setFechaEdito(dep.getFechaEdito());
        setIdPrograma(dep.getIdPrograma());

        if (dep.isEstado()) {
            this.estadoValidador = "Activo";
        } else {
            this.estadoValidador = "Inactivo";
        }
    }

    public LinkedList<SelectItem> getListaPrograma() throws SNMPExceptions, SQLException {
        String nomCandidato = "";
        int numCandidato = 0;
        LinkedList<Programa> lista = new LinkedList<>();
        ProgramaDB cDB = new ProgramaDB();
        lista = cDB.moTodo();
        LinkedList resultList = new LinkedList();
        resultList.add(new SelectItem(0, "Seleccione Programa"));
        for (Iterator iter = lista.iterator();
                iter.hasNext();) {

            Programa cand = (Programa) iter.next();
            numCandidato = cand.getId();
            nomCandidato = cand.getNombre();
            resultList.add(new SelectItem(numCandidato, nomCandidato));

        }
        return resultList;
    }

    public void setListaPrograma(LinkedList<SelectItem> listaPrograma) {
        this.listaCandCmb = listaPrograma;
    }

    public String getEstadoValidador() {
        return estadoValidador;
    }

    public void setEstadoValidador(String estadoValidador) {
        this.estadoValidador = estadoValidador;
    }

    public String getMensajesetMensajeAct() {
        return mensajesetMensajeAct;
    }

    public void setMensajesetMensajeAct(String mensajesetMensajeAct) {
        this.mensajesetMensajeAct = mensajesetMensajeAct;
    }

    public LinkedList<Curso> getListaTablaCurso() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        CursoDB dDB = new CursoDB();
        LinkedList<Curso> listaTabla = new LinkedList<>();
        if (!this.idNombre.equals("")) {
            listaTabla = this.buscarCursoBean();
        } else {
            listaTabla = dDB.consultarCurso();
        }
        return listaTabla;
    }

    public void setListaTablaCurso(LinkedList<Curso> listaTablaCurso) {
        this.listaTablaCurso = listaTablaCurso;
    }

    public String getMensajeAlerta() {
        return mensajeAlerta;
    }

    public void setMensajeAlerta(String mensajeAlerta) {
        this.mensajeAlerta = mensajeAlerta;
    }

    public void cancelar() {
        this.setDescripcion(" ");
        this.setEstado(false);
        this.setId(0);
        this.setIdPrograma(0);
        this.setMensajeAlerta(" ");
        this.setMensajeDescripcion(" ");
        this.setMensajeEstado(" ");
        this.setMensajeId(" ");
        this.setMensajeIdPrograma(" ");
        this.setMensajesetMensajeAct(" ");
        this.setIdNombre("");
        this.setListaTablaCurso(listaTablaCurso);
        this.setEstadoValidador("");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getCodFunEdito() {
        return codFunEdito;
    }

    public void setCodFunEdito(String codFunEdito) {
        this.codFunEdito = codFunEdito;
    }

    public Date getFechaEdito() {
        return fechaEdito;
    }

    public void setFechaEdito(Date fechaEdito) {
        this.fechaEdito = fechaEdito;
    }

    public int getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(int idPrograma) {
        this.idPrograma = idPrograma;
    }

    public String getMensajeId() {
        return mensajeId;
    }

    public void setMensajeId(String mensajeId) {
        this.mensajeId = mensajeId;
    }

    public String getMensajeDescripcion() {
        return mensajeDescripcion;
    }

    public void setMensajeDescripcion(String mensajeDescripcion) {
        this.mensajeDescripcion = mensajeDescripcion;
    }

    public String getMensajeEstado() {
        return mensajeEstado;
    }

    public void setMensajeEstado(String mensajeEstado) {
        this.mensajeEstado = mensajeEstado;
    }

    public String getMensajeIdPrograma() {
        return mensajeIdPrograma;
    }

    public void setMensajeIdPrograma(String mensajeIdPrograma) {
        this.mensajeIdPrograma = mensajeIdPrograma;
    }

    public String getIdNombre() {
        return idNombre;
    }

    public void setIdNombre(String idNombre) {
        this.idNombre = idNombre;
    }

    public Funcionario getFun() {
        return fun;
    }

    public void setFun(Funcionario fun) {
        this.fun = fun;
    }

    public String getMensajeId2() {
        return mensajeId2;
    }

    public void setMensajeId2(String mensajeId2) {
        this.mensajeId2 = mensajeId2;
    }

    public LinkedList<SelectItem> getListaCandCmb() throws SNMPExceptions, SQLException {
        return listaCandCmb;
    }

    public void setListaCandCmb(LinkedList<SelectItem> listaCandCmb) {
        this.listaCandCmb = listaCandCmb;
    }
}
