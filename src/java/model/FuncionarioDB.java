/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.naming.NamingException;

/**
 *
 * @author KeylorSk8
 */
public class FuncionarioDB {

    AccesoDatos accesoDatos = new AccesoDatos();

    public void solicitarCuenta(Funcionario fun) throws SNMPExceptions, ClassNotFoundException, SQLException, NamingException {
        String strSQL1;
        String strSQL2;
        try {
            //Se obtienen los valores del objeto 
            strSQL1 = "INSERT  INTO Funcionario("
                    + "[Id]\n"
                    + ",[Contrasenia]\n"
                    + ",[IdTipoUsuario]\n"
                    + ",[Email]\n"
                    + ",[Nombre]\n"
                    + ",[Apellido1]\n"
                    + ",[Apellido2]\n"
                    + ",[FechaNacimiento]\n"
                    + ",[Edad]\n"
                    + ",[IdDireccion]\n"
                    + ",[Estado]\n"
                    + ",[CodFunIngreso]\n"
                    + ",[FechaIngreso]\n"
                    + ",[CodFunEdito]\n"
                    + ",[FechaEdito]\n"
                    + ",[PrimeraVez]\n"
                    + ",[Solicitud]\n"
                    + ",[EstadoSolicitud])"
                    + "VALUES("
                    + fun.getId() + ",'"
                    + fun.getContraseña() + "',"
                    + fun.getIdTipoUsuario() + ",'"
                    + fun.getEmail() + "','"
                    + fun.getNombre() + "','"
                    + fun.getApellido1() + "','"
                    + fun.getApellido2() + "','"
                    + fun.getFechaNacimiento() + "','"
                    + fun.getEdad() + "',"
                    + fun.getIdDireccion() + ","
                    + (fun.isEstado() ? 1 : 0) + ",'"
                    + fun.getCodFunIngreso() + "','"
                    + fun.getFechaIngreso() + "','"
                    + fun.getCodFunEdito() + "','"
                    + fun.getFechaEdito() + "',"
                    + (fun.isPrimeraVez() ? 1 : 0) + ","
                    + 1 + ","
                    + 0 + ");";
            System.out.println(strSQL1);
            strSQL2 = "Insert into Direccion "
                    + "([Id]\n"
                    + ",[IdProvincia]\n"
                    + ",[IdCanton]\n"
                    + ",[IdDistrito]\n"
                    + ",[IdBarrio]\n"
                    + ",[Otras_Senias]"
                    + ")values ("
                    + fun.getId() + ","
                    + fun.getCodProv() + ","
                    + fun.getCodCan() + ","
                    + fun.getCodDist() + ","
                    + fun.getCodBa() + ",'"
                    + fun.getOtrasSeñas()
                    + "')";
            //Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQL2);
            accesoDatos.ejecutaSQL(strSQL1);
        } catch (SNMPExceptions e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        } finally {
        }
    }

    public Funcionario validadExistenciaFuncionario(int idFuncionario) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        Funcionario fun = null;
        try {
            //open();
            //Se instancia la clase de acceso a datos
            accesoDatos = new AccesoDatos();
            //Se crea la sentencia de búsqueda
            String select = "SELECT * from Funcionario where id = " + idFuncionario;
            //Se llena el arryaList con los catálogos
            try ( //Se ejecuta la sentencia SQL
                    ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select)) {
                //Se llena el arryaList con los catálogos
                while (rsPA.next()) {
                    fun = new Funcionario();
                }
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (SNMPExceptions | ClassNotFoundException | NamingException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        } finally {
        }
        return fun;
    }

    public Funcionario seleccionarFuncionario(String idFuncionario) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        Funcionario fun = null;
        try {
            //open();
            //Se instancia la clase de acceso a datos
            accesoDatos = new AccesoDatos();
            //Se crea la sentencia de búsqueda
            String select = "SELECT * from Funcionario where id = " + idFuncionario;
            //Se llena el arryaList con los catálogos
            try ( //Se ejecuta la sentencia SQL
                    ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select)) {
                //Se llena el arryaList con los catálogos
                while (rsPA.next()) {
                    int id = rsPA.getInt("Id");
                    String contra = rsPA.getString("Contrasenia");
                    int tipoUsuario = rsPA.getInt("idTipoUsuario");
                    String email = rsPA.getString("Email");
                    String nombre = rsPA.getString("Nombre");
                    String apellido1 = rsPA.getString("Apellido1");
                    String apellido2 = rsPA.getString("Apellido2");
                    String fechaNacimiento = rsPA.getString("FechaNacimiento");
                    String edad = rsPA.getString("Edad");
                    int idDireccion = rsPA.getInt("IdDireccion");
                    boolean Estado = rsPA.getInt("Estado") == 1;
                    String codFunIngreso = rsPA.getString("CodFunIngreso");
                    String fechaIngreso = rsPA.getString("FechaIngreso");
                    String codFunEdito = rsPA.getString("CodFunEdito");
                    String fechaEdito = rsPA.getString("FechaEdito");
                    boolean primeraVez = rsPA.getInt("PrimeraVez") == 1;
                    boolean solicitud = rsPA.getInt("Solicitud") == 1;
                    boolean estadoSolicitud = rsPA.getInt("EstadoSolicitud") == 1;
                    fun = new Funcionario(id, contra, tipoUsuario, email, nombre, apellido1, apellido2, fechaNacimiento, idDireccion, 0, Estado, Integer.parseInt(codFunIngreso), fechaIngreso, Integer.parseInt(codFunEdito), fechaEdito, primeraVez);
                    fun.setSolicitud(solicitud);
                    fun.setEstadoSolicitud(estadoSolicitud);
                    fun.setEdad(edad);
                }
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (SNMPExceptions | ClassNotFoundException | NamingException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        } finally {

        }
        return fun;
    }

    public LinkedList<Funcionario> seleccionarSolicitudFuncionarios() throws SNMPExceptions {
        LinkedList<Funcionario> funcionarios = new LinkedList<>();
        Funcionario fun = null;
        try {
            //open();
            //Se instancia la clase de acceso a datos
            accesoDatos = new AccesoDatos();
            //Se crea la sentencia de búsqueda
            String select = "SELECT * from Funcionario where Solicitud = 1";
            //Se llena el arryaList con los catálogos
            try ( //Se ejecuta la sentencia SQL
                    ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select)) {
                //Se llena el arryaList con los catálogos
                while (rsPA.next()) {
                    int id = rsPA.getInt("Id");
                    String contra = rsPA.getString("Contrasenia");
                    int tipoUsuario = rsPA.getInt("idTipoUsuario");
                    String email = rsPA.getString("Email");
                    String nombre = rsPA.getString("Nombre");
                    String apellido1 = rsPA.getString("Apellido1");
                    String apellido2 = rsPA.getString("Apellido2");
                    String fechaNacimiento = rsPA.getString("FechaNacimiento");
                    String edad = rsPA.getString("Edad");
                    int idDireccion = rsPA.getInt("IdDireccion");
                    boolean Estado = rsPA.getInt("Estado") == 1;
                    String codFunIngreso = rsPA.getString("CodFunIngreso");
                    String fechaIngreso = rsPA.getString("FechaIngreso");
                    String codFunEdito = rsPA.getString("CodFunEdito");
                    String fechaEdito = rsPA.getString("FechaEdito");
                    boolean primeraVez = rsPA.getInt("PrimeraVez") == 1;
                    boolean solicitud = rsPA.getInt("Solicitud") == 1;
                    boolean estadoSolicitud = rsPA.getInt("EstadoSolicitud") == 1;
                    fun = new Funcionario(id, contra, tipoUsuario, email, nombre, apellido1, apellido2, fechaNacimiento, idDireccion, 0, Estado, Integer.parseInt(codFunIngreso), fechaIngreso, Integer.parseInt(codFunEdito), fechaEdito, primeraVez);
                    fun.setSolicitud(solicitud);
                    fun.setEstadoSolicitud(estadoSolicitud);
                    fun.setEdad(edad);
                    funcionarios.add(fun);
                }
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (SNMPExceptions | ClassNotFoundException | NamingException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        } finally {
        }
        return funcionarios;
    }

    public LinkedList<Funcionario> seleccionarFuncionariosRechazados() throws SNMPExceptions {
        LinkedList<Funcionario> funcionarios = new LinkedList<>();
        Funcionario fun = null;
        try {
            //open();
            //Se instancia la clase de acceso a datos
            accesoDatos = new AccesoDatos();
            //Se crea la sentencia de búsqueda
            String select = "SELECT * from Funcionario where Solicitud = 0 and EstadoSolicitud = 0";
            //Se llena el arryaList con los catálogos
            try ( //Se ejecuta la sentencia SQL
                    ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select)) {
                //Se llena el arryaList con los catálogos
                while (rsPA.next()) {
                    fun = new Funcionario();
                    int id = rsPA.getInt("Id");
                    String contra = rsPA.getString("Contrasenia");
                    int tipoUsuario = rsPA.getInt("idTipoUsuario");
                    String email = rsPA.getString("Email");
                    String nombre = rsPA.getString("Nombre");
                    String apellido1 = rsPA.getString("Apellido1");
                    String apellido2 = rsPA.getString("Apellido2");
                    String fechaNacimiento = rsPA.getString("FechaNacimiento");
                    String edad = rsPA.getString("Edad");
                    int idDireccion = rsPA.getInt("IdDireccion");
                    boolean Estado = rsPA.getInt("Estado") == 1;
                    String codFunIngreso = rsPA.getString("CodFunIngreso");
                    String fechaIngreso = rsPA.getString("FechaIngreso");
                    String codFunEdito = rsPA.getString("CodFunEdito");
                    String fechaEdito = rsPA.getString("FechaEdito");
                    boolean primeraVez = rsPA.getInt("PrimeraVez") == 1;
                    boolean solicitud = rsPA.getInt("Solicitud") == 1;
                    boolean estadoSolicitud = rsPA.getInt("EstadoSolicitud") == 1;
                    fun = new Funcionario(id, contra, tipoUsuario, email, nombre, apellido1, apellido2, fechaNacimiento, idDireccion, 0, Estado, Integer.parseInt(codFunIngreso), fechaIngreso, Integer.parseInt(codFunEdito), fechaEdito, primeraVez);
                    fun.setSolicitud(solicitud);
                    fun.setEstadoSolicitud(estadoSolicitud);
                    fun.setEdad(edad);
                    funcionarios.add(fun);
                }
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (SNMPExceptions | ClassNotFoundException | NamingException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        } finally {
        }
        return funcionarios;
    }

    public void actualizarFuncionario(Funcionario fun) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String strSQL1;
        String strSQL2;
        try {
            //Se obtienen los valores del objeto 
            strSQL1 = "UPDATE Funcionario set CodFunIngreso = '" + fun.getCodFunIngreso()
                    + "',FechaIngreso = " + fun.getFechaIngreso()
                    + ",CodFunEdito = '" + fun.getCodFunEdito()
                    + "',FechaEdito = " + fun.getFechaEdito()
                    + " ,Solicitud = 0, Contrasenia = '" + fun.getContraseña()
                    + "',EstadoSolicitud = " + (fun.isEstadoSolicitud()?1:0);
            if (fun.getIdTipoUsuario() == 2) {
                strSQL1 += ",IdTipoUsuario = 2,IdPrograma = " + fun.getIdPrograma();
            }
            strSQL1 += " where Id = " + fun.getId();
            strSQL2 = "UPDATE Programa set IdCoordinador ="+ fun.getId() +" where Id = " + fun.getIdPrograma();
            //Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQL1);
            if(fun.getIdTipoUsuario() == 2){
                accesoDatos.ejecutaSQL(strSQL2);
            }
        } catch (SNMPExceptions e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        } finally {
        }
    }

    public void primeraVez(Funcionario fun) {
        String update;
        try {
            update = "Update Funcionario set Contrasenia = '" + fun.getContraseña()
                    + "',PrimeraVez = 0 where Id = " + fun.getId();
            accesoDatos.ejecutaSQL(update);
        } catch (SNMPExceptions | ClassNotFoundException | SQLException | NamingException e) {
        }finally{
        }
    }
}
