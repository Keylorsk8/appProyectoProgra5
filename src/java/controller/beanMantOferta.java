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
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import model.Infraestructura;
import model.InfraestructuraDB;
import model.Oferta;
import model.OfertaDB;
import model.Periodo;
import model.PeriodoDB;

/**
 *
 * @author Pablo
 */
@Named(value = "beanMantOferta")
@SessionScoped
public class beanMantOferta implements Serializable {

    /**
     * Creates a new instance of beanMantOferta
     */
    public beanMantOferta() {
        if (this.estado == false) {
            estadoValidador = "Inactivo";
        } else {
            if (this.estado == true) {
                estadoValidador = "Activo";
            }

        }

        Date hoy = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        this.setFechaInicio(sd.format(hoy));
        this.setFechaFinal(sd.format(hoy));

    }
    Funcionario fun;
    int idNumero = 0;
    int id = 0;
    int idInfraestructura = 0;
    int idCurso = 0;
    int idPeriodo = 0;
    String estadoValidador = " ";
    boolean estado = false;
    String fechaInicio = " ";
    String fechaFinal = " ";
    int horaInicio = 0;
    int horaFinal = 0;
    String descripcion = " ";

    String mensajeInfra;
    String mensajeCurso;
    String mensajefechaIncio;
    String mensajefechaFinal;
    String mensajehoraInicio;
    String mensajehoraFinal;
    String mensajeAlerta;
    String mensajeDescripcion;
    String mensajePeriodo;
    String mensajeEstado;

    LinkedList<Oferta> listaTablaOferta = new LinkedList<>();

    public LinkedList<Oferta> getListaTablaOferta() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        OfertaDB dDB = new OfertaDB();
        LinkedList<Oferta> listaTabla = new LinkedList<>();
        if (this.idNumero > 0) {
            listaTabla = this.buscarOfertaBean();
        } else {
            listaTabla = dDB.consultarOferta();
        }
        return listaTabla;
    }

    public String nombreCurso(int idCurso) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        LinkedList<Curso> curso = new CursoDB().buscarCurso(String.valueOf(idCurso));
        Curso cu = curso.get(0);
        return cu.getDescripcion();
    }

    public String nombreInfraestructura(int idInfraestructura) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        LinkedList<Infraestructura> infraestructura = new InfraestructuraDB().buscarInfraestructura(String.valueOf(idInfraestructura));
        Infraestructura cu = infraestructura.get(0);
        return cu.getNombre();
    }

    public String nombrePeriodo(int idPeriodo) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        LinkedList<Periodo> periodo = new PeriodoDB().buscarPeriodo(String.valueOf(idPeriodo));
        Periodo cu = periodo.get(0);
        return cu.getNombre();
    }

    public void setListaTablaOferta(LinkedList<Oferta> listaTablaOferta) {
        this.listaTablaOferta = listaTablaOferta;
    }

    public void ingresarRegistro()
            throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        OfertaDB dDB = new OfertaDB();
        Oferta depUTN = new Oferta();

        try {
            if (this.descripcion.equals(" ")) {
                this.mensajeDescripcion = "Descripcion es Requerido";
            }
            if (this.idInfraestructura == 0) {
                this.setMensajeInfra("Infraestructura es Requerido");
            }
            if (this.idCurso == 0) {
                this.setMensajeCurso("Curso es Requerido");
            }
            if (this.fechaFinal.equals(" ")) {
                this.setMensajefechaFinal("Fecha Final es Requerido");
            }
            if (this.fechaInicio.equals(" ")) {
                this.setMensajefechaIncio("Fecha Inicio es Requerido");
            }
            if (this.horaInicio == 0) {
                this.setMensajehoraInicio("Hora Inicio es Requerido");
            }
            if (this.horaFinal == 0) {
                this.setMensajehoraFinal("Hora Final es Requerido");
            }
            if (this.idPeriodo == 0) {
                this.mensajePeriodo = "Periodo es Requerido";
            }
            if (!this.descripcion.equals(" ")) {
                this.mensajeDescripcion = " ";
            }

            if (this.idInfraestructura > 0) {
                this.setMensajeInfra(" ");
            }
            if (this.idCurso > 0) {
                this.setMensajeCurso(" ");
            }
            if (!this.fechaFinal.equals(" ")) {
                this.setMensajefechaFinal(" ");
            }
            if (!this.fechaInicio.equals(" ")) {
                this.setMensajefechaIncio(" ");
            }
            if (this.horaInicio > 0) {
                this.setMensajehoraInicio(" ");
            }
            if (this.horaFinal > 0) {
                this.setMensajehoraFinal(" ");
            }

            if (!this.estadoValidador.equals("--Seleccione--")) {
                this.mensajeEstado = " ";
                if (this.estadoValidador.equals("Inactivo")) {
                    this.estado = false;
                } else {
                    this.estado = true;
                }
                if (!validar()) {
                    this.mensajefechaFinal = " ";
                    this.mensajefechaIncio = " ";
                }

                if (this.idPeriodo > 0) {
                    this.mensajePeriodo = " ";
                    if (this.idInfraestructura > 0) {
                        if (this.idCurso > 0) {
                            if (!this.fechaFinal.equals(" ")) {
                                if (!this.fechaInicio.equals(" ")) {
                                    if (this.horaInicio > 0) {
                                        if (this.horaFinal > 0) {
                                            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                                            fun = (Funcionario) session.getAttribute("user");

                                            depUTN.setDescripcion(descripcion);
                                            depUTN.setEstadov(estado);
                                            depUTN.setFechaFinal(transformarFechas(fechaFinal.substring(0, 10)));
                                            depUTN.setFechaInicio(transformarFechas(fechaInicio.substring(0, 10)));
                                            depUTN.setHoraFinal(horaFinal);
                                            depUTN.setHoraInicio(horaInicio);
                                            depUTN.setIdCurso(idCurso);
                                            depUTN.setIdInfraestructura(idInfraestructura);
                                            depUTN.setIdPeriodo(idPeriodo);

                                            dDB.mvRegitroOferta(depUTN, fun);
                                            mensajeAlerta = "Realizado con exito";
                                            cancelar();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                mensajeAlerta = "Porfavor llenar los datos";
            }
        } catch (SNMPExceptions | SQLException e) {
            System.out.println("Error :" + e);
            System.out.println("Mensaje :" + e.getMessage());
        }
    }

    public boolean validar() {
        boolean errores = false;

        if (!comparFechasInicio()) {
            mensajefechaIncio = "Digite una fecha posterior a la fecha actual";
            errores = true;
        }
        if (!comparFechasFinal()) {
            mensajefechaFinal = "Digite una fecha posterior a la fecha actual";
            errores = true;
        }

        return errores;
    }

    public boolean comparFechasInicio() {
        String sFecha = this.fechaInicio;
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sFecha);
        Date fechaSeleccionada = null;
        try {
            fechaSeleccionada = (Date) formatoDelTexto.parse(sFecha);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(fechaSeleccionada.toString());

        Date fechaActual = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(fechaActual);
        c.add(Calendar.HOUR, -2);
        fechaActual = (Date) c.getTime();
        if (fechaSeleccionada.before(fechaActual)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean comparFechasFinal() {
        String sFecha = this.fechaFinal;
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sFecha);
        Date fechaSeleccionada = null;
        try {
            fechaSeleccionada = (Date) formatoDelTexto.parse(sFecha);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(fechaSeleccionada.toString());

        Date fechaActual = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(fechaActual);
        c.add(Calendar.HOUR, -2);
        fechaActual = (Date) c.getTime();
        if (fechaSeleccionada.before(fechaActual)) {
            return false;
        } else {
            return true;
        }
    }

    public void cancelar() {
        this.setIdCurso(0);

        this.setHoraFinal(0);
        this.setHoraInicio(0);
        this.setIdInfraestructura(0);
        this.setMensajeCurso(" ");
        this.setMensajeInfra(" ");
        this.setMensajefechaFinal(" ");
        this.setMensajefechaIncio(" ");
        this.setMensajehoraFinal(" ");
        this.setMensajehoraInicio(" ");
        this.setDescripcion(" ");
        this.setIdPeriodo(0);
        this.setIdNumero(0);
        this.setMensajeAlerta(" ");
         Date hoy = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        this.setFechaInicio(sd.format(hoy));
        this.setFechaFinal(sd.format(hoy));

    }

    public LinkedList<Oferta> buscarOfertaBean() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        OfertaDB dDB = new OfertaDB();
        LinkedList<Oferta> oferta = new LinkedList<>();
        return oferta = dDB.buscarOferta(idNumero);
    }

    public LinkedList<SelectItem> getListaPeriodo() throws SNMPExceptions, SQLException {
        String nomCandidato = "";
        int numCandidato = 0;
        LinkedList<Periodo> lista = new LinkedList<Periodo>();
        PeriodoDB cDB = new PeriodoDB();
        lista = cDB.moTodo();
        LinkedList resultList = new LinkedList();
        resultList.add(new SelectItem(0, "--Seleccione--"));
        for (Iterator iter = lista.iterator();
                iter.hasNext();) {

            Periodo cand = (Periodo) iter.next();
            numCandidato = cand.getId();
            nomCandidato = cand.getNombre();
            resultList.add(new SelectItem(numCandidato, nomCandidato));

        }
        return resultList;
    }

    public String transformarFechas(String fecha) {
        String fcha = fecha;

        String anio = fcha.substring(0, 4);
        String mes = fcha.substring(5, 7);
        String dia = fcha.substring(8, 10);
        fcha = anio + "-" + dia + "-" + mes;

        return fcha;
    }

    public void setListaPeriodo(LinkedList<SelectItem> listaPeriodo) {
        this.listaPeriodoCmb = listaPeriodo;
    }

    private LinkedList<SelectItem> listaPeriodoCmb = new LinkedList();

    public LinkedList<SelectItem> getListaPeriodoCmb()
            throws SNMPExceptions, SQLException {
        return listaPeriodoCmb;
    }

    public void setListaPeriodoCmb(LinkedList<SelectItem> listaCandCmb) {
        this.listaPeriodoCmb = listaCandCmb;
    }

    public LinkedList<SelectItem> getListaCurso() throws SNMPExceptions, SQLException {
        String nomCandidato = "";
        int numCandidato = 0;
        LinkedList<Curso> lista = new LinkedList<Curso>();
        CursoDB cDB = new CursoDB();
        lista = cDB.moTodo();
        LinkedList resultList = new LinkedList();
        resultList.add(new SelectItem(0, "--Seleccione--"));
        for (Iterator iter = lista.iterator();
                iter.hasNext();) {

            Curso cand = (Curso) iter.next();
            numCandidato = cand.getId();
            nomCandidato = cand.getDescripcion();
            resultList.add(new SelectItem(numCandidato, nomCandidato));

        }
        return resultList;
    }

    public void setListaCurso(LinkedList<SelectItem> listaCurso) {
        this.listaCursoCmb = listaCurso;
    }

    private LinkedList<SelectItem> listaCursoCmb = new LinkedList();

    public LinkedList<SelectItem> getListaCursoCmb()
            throws SNMPExceptions, SQLException {
        return listaCursoCmb;
    }

    public void setListaCursoCmb(LinkedList<SelectItem> listaCandCmb) {
        this.listaCursoCmb = listaCandCmb;
    }

    public LinkedList<SelectItem> getListaInfraestructura() throws SNMPExceptions, SQLException {
        String nomCandidato = "";
        int numCandidato = 0;
        LinkedList<Infraestructura> lista = new LinkedList<Infraestructura>();
        InfraestructuraDB cDB = new InfraestructuraDB();
        lista = cDB.moTodo();
        LinkedList resultList = new LinkedList();
        resultList.add(new SelectItem(0, "--Seleccione--"));
        for (Iterator iter = lista.iterator();
                iter.hasNext();) {

            Infraestructura cand = (Infraestructura) iter.next();
            numCandidato = cand.getId();
            nomCandidato = cand.getNombre();
            resultList.add(new SelectItem(numCandidato, nomCandidato));
        }
        return resultList;
    }

    public void setListaInfraestructura(LinkedList<SelectItem> listaInfraestructura) {
        this.listaInfraestructuraCmb = listaInfraestructura;
    }

    private LinkedList<SelectItem> listaInfraestructuraCmb = new LinkedList();

    public LinkedList<SelectItem> getListaInfraestructuraCmb()
            throws SNMPExceptions, SQLException {
        return listaInfraestructuraCmb;
    }

    public void setListaInfraestructuraCmb(LinkedList<SelectItem> listaCandCmb) {
        this.listaInfraestructuraCmb = listaCandCmb;
    }

    public void asignaDatos(Oferta dep) {
        this.setDescripcion(dep.getDescripcion());
        this.setEstado(dep.isEstadov());
        if (this.isEstado() == true) {
            this.estadoValidador = "Activo";
        } else {
            this.estadoValidador = "Inactivo";
        }
        this.setFechaFinal(dep.getFechaFinal().substring(0, 10));
        this.setFechaInicio(dep.getFechaInicio().substring(0, 10));
        this.setHoraFinal(dep.getHoraFinal());
        this.setHoraInicio(dep.getHoraInicio());
        this.setIdCurso(dep.getIdCurso());
        this.setIdInfraestructura(dep.getIdInfraestructura());
        this.setIdPeriodo(dep.getIdPeriodo());
        this.setId(dep.getId());
    }

    public void actualizaDatos() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        OfertaDB cDB = new OfertaDB();
        Oferta cur = new Oferta();

        cur.setId(id);
        cur.setDescripcion(descripcion);
        if (this.estadoValidador.equals("Activo")) {
            this.estado = true;
        } else {
            this.estado = false;
        }
        cur.setEstadov(this.isEstado());
        cur.setFechaFinal(transformarFechas(fechaFinal.substring(0, 10)));
        cur.setFechaInicio(transformarFechas(fechaInicio.substring(0, 10)));
        cur.setHoraFinal(horaFinal);
        cur.setHoraInicio(horaInicio);
        cur.setIdCurso(idCurso);
        cur.setIdInfraestructura(idInfraestructura);
        cur.setIdPeriodo(idPeriodo);

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        fun = (Funcionario) session.getAttribute("user");
        cDB.actualizarOferta(cur, fun);
        cancelar();
        mensajeAlerta = "Editado con éxito";
    }

    public String getMensajeInfra() {
        return mensajeInfra;
    }

    public void setMensajeInfra(String mensajeInfra) {
        this.mensajeInfra = mensajeInfra;
    }

    public String getMensajeCurso() {
        return mensajeCurso;
    }

    public void setMensajeCurso(String mensajeCurso) {
        this.mensajeCurso = mensajeCurso;
    }

    public String getMensajefechaIncio() {
        return mensajefechaIncio;
    }

    public void setMensajefechaIncio(String mensajefechaIncio) {
        this.mensajefechaIncio = mensajefechaIncio;
    }

    public String getMensajefechaFinal() {
        return mensajefechaFinal;
    }

    public void setMensajefechaFinal(String mensajefechaFinal) {
        this.mensajefechaFinal = mensajefechaFinal;
    }

    public String getMensajehoraInicio() {
        return mensajehoraInicio;
    }

    public void setMensajehoraInicio(String mensajehoraInicio) {
        this.mensajehoraInicio = mensajehoraInicio;
    }

    public String getMensajehoraFinal() {
        return mensajehoraFinal;
    }

    public void setMensajehoraFinal(String mensajehoraFinal) {
        this.mensajehoraFinal = mensajehoraFinal;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public int getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
    }

    public int getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(int horaFinal) {
        this.horaFinal = horaFinal;
    }

    public String getMensajeAlerta() {
        return mensajeAlerta;
    }

    public void setMensajeAlerta(String mensajeAlerta) {
        this.mensajeAlerta = mensajeAlerta;
    }

    public int getIdInfraestructura() {
        return idInfraestructura;
    }

    public void setIdInfraestructura(int idInfraestructura) {
        this.idInfraestructura = idInfraestructura;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public int getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(int idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMensajeDescripcion() {
        return mensajeDescripcion;
    }

    public void setMensajeDescripcion(String mensajeDescripcion) {
        this.mensajeDescripcion = mensajeDescripcion;
    }

    public Funcionario getFun() {
        return fun;
    }

    public void setFun(Funcionario fun) {
        this.fun = fun;
    }

    public int getIdNumero() {
        return idNumero;
    }

    public void setIdNumero(int idNumero) {
        this.idNumero = idNumero;
    }

    public String getMensajePeriodo() {
        return mensajePeriodo;
    }

    public void setMensajePeriodo(String mensajePeriodo) {
        this.mensajePeriodo = mensajePeriodo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMensajeEstado() {
        return mensajeEstado;
    }

    public void setMensajeEstado(String mensajeEstado) {
        this.mensajeEstado = mensajeEstado;
    }

    public String getEstadoValidador() {
        return estadoValidador;
    }

    public void setEstadoValidador(String estadoValidador) {
        this.estadoValidador = estadoValidador;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
