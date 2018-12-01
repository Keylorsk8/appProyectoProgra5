/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author KeylorSk8
 */
public class Funcionario {
    
    private int id;
    private String contraseña;
    private int idTipoUsuario;
    private String email;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String fechaNacimiento;
    private String edad;
    private int idDireccion;
    private int codProv;
    private int codDist;
    private int codCan;
    private int codBa;
    private String otrasSeñas;
    private int idPrograma;
    private boolean estado;
    private int codFunIngreso;
    private String fechaIngreso;
    private int codFunEdito;
    private String fechaEdito;
    private boolean primeraVez;
    private boolean solicitud;
    private boolean estadoSolicitud;
    
    public Funcionario(){
    }

    public Funcionario(int id, String contraseña, int idTipoUsuario, String email, 
            String nombre, String apellido1, String apellido2, String fechaNacimiento, int idDireccion, 
            int idPrograma, boolean estado, int codFunIngreso, String fechaIngreso, 
            int codFunEdito, String fechaEdito, boolean primeraVez) {
        this.id = id;
        this.contraseña = contraseña;
        this.idTipoUsuario = idTipoUsuario;
        this.email = email;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fechaNacimiento = fechaNacimiento;
        this.idDireccion = idDireccion;
        this.idPrograma = idPrograma;
        this.estado = estado;
        this.codFunIngreso = codFunIngreso;
        this.fechaIngreso = fechaIngreso;
        this.codFunEdito = codFunEdito;
        this.fechaEdito = fechaEdito;
        this.primeraVez = primeraVez;
    }

    public boolean isSolicitud() {
        return solicitud;
    }

    public void setSolicitud(boolean solicitud) {
        this.solicitud = solicitud;
    }

    public boolean isEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(boolean estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }
    
    

    public String getOtrasSeñas() {
        return otrasSeñas;
    }

    public void setOtrasSeñas(String otrasSeñas) {
        this.otrasSeñas = otrasSeñas;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public int getCodProv() {
        return codProv;
    }

    public void setCodProv(int codProv) {
        this.codProv = codProv;
    }

    public int getCodDist() {
        return codDist;
    }

    public void setCodDist(int codDist) {
        this.codDist = codDist;
    }

    public int getCodCan() {
        return codCan;
    }

    public void setCodCan(int codCan) {
        this.codCan = codCan;
    }

    public int getCodBa() {
        return codBa;
    }

    public void setCodBa(int codBa) {
        this.codBa = codBa;
    }

    public int getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(int idPrograma) {
        this.idPrograma = idPrograma;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getCodFunIngreso() {
        return codFunIngreso;
    }

    public void setCodFunIngreso(int codFunIngreso) {
        this.codFunIngreso = codFunIngreso;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public int getCodFunEdito() {
        return codFunEdito;
    }

    public void setCodFunEdito(int codFunEdito) {
        this.codFunEdito = codFunEdito;
    }

    public String getFechaEdito() {
        return fechaEdito;
    }

    public void setFechaEdito(String fechaEdito) {
        this.fechaEdito = fechaEdito;
    }

    public boolean isPrimeraVez() {
        return primeraVez;
    }

    public void setPrimeraVez(boolean primeraVez) {
        this.primeraVez = primeraVez;
    }
}
