/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Date;

/**
 *
 * @author Pablo
 */
public class Oferta {
    private int id;
    private String descripcion;
    private boolean estado;
    private Date fechaInicio;
    private Date fechaFinal;
    private Date horaInicio;
    private Date horaFinal;
    private String codFunIngreso;
    private Date fechaIngreso;
    private String codFunEdito;
    private Date fechaEdito;
    private int idCurso;
    private int idInfraestructura;
    
    public Oferta(){
     this.id=0;
     this.descripcion=" ";
     this.estado=false;
     this.fechaInicio=null;
     this.fechaFinal=null;
     this.horaInicio=null;
     this.horaFinal=null;
     this.codFunIngreso=" ";
     this.fechaIngreso=null;
     this.codFunEdito=" ";
     this.fechaEdito=null;
     this.idCurso=0;
     this.idInfraestructura=0;
    }
    
    public Oferta(int idp,String descripcionp,boolean estadop,Date fechaIniciop,Date fechaFinalp,Date horaIniciop,Date horaFinalp,String codFunIngresop,Date fechaIngresop,String codFunEditop,Date fechaEditop,int idCursop,int idInfraestructurap){
      this.id=idp;
      this.descripcion=descripcionp;
      this.estado=estadop;
      this.fechaInicio=fechaIniciop;
      this.fechaFinal=fechaFinalp;
      this.horaInicio=horaIniciop;
      this.horaFinal=horaFinalp;
      this.codFunIngreso=codFunIngresop;
      this.fechaIngreso=fechaIngresop;
      this.codFunEdito=codFunEditop;
      this.fechaEdito=fechaEditop;
      this.idCurso=idCursop;
      this.idInfraestructura=idInfraestructurap;
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

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(Date horaFinal) {
        this.horaFinal = horaFinal;
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

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public int getIdInfraestructura() {
        return idInfraestructura;
    }

    public void setIdInfraestructura(int idInfraestructura) {
        this.idInfraestructura = idInfraestructura;
    }
    
    

   
    
    
}
