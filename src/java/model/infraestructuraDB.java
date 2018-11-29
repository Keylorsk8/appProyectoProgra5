/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.naming.NamingException;

/**
 *
 * @author Pablo
 */
public class infraestructuraDB {
    
     private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    private LinkedList<infraestructura> listaP = new LinkedList<infraestructura>();

    public infraestructuraDB(Connection conn) {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }
    
    public infraestructuraDB(){
        super();
    }
    
     public LinkedList<infraestructura> consultarInfraestructura() throws SNMPExceptions, SQLException {
        String select = "";
        LinkedList<infraestructura> listaInfraestructura = new LinkedList<infraestructura>();

        try {
            //open();
            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT Id,Capacidad,IdTipoInfraestructura,Nombre,Ubicacion,IdPrograma  from Infraestructura";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los catálogos   
            while (rsPA.next()) {
                  int id = rsPA.getInt("Id");
                  int capacidad = rsPA.getInt("Capacidad");
                  int idTipoInfraestructura = rsPA.getInt("IdTipoInfraestructura");    
                  String nombre = rsPA.getString("Nombre");   
                  String ubicacion = rsPA.getString("Ubicacion");
                  int idPrograma = rsPA.getInt("IdPrograma");
               
                infraestructura dep = new infraestructura(id,capacidad,idTipoInfraestructura,nombre,ubicacion,idPrograma);
                listaInfraestructura.add(dep);
            }
            rsPA.close();

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        } finally {

        }
        return listaInfraestructura;
    }

    public void mvRegitroInfraestructura(infraestructura pvoInfraestructura)
            throws SNMPExceptions, SQLException {
        String strSQL = "";
        try {
//Se obtienen los valores del objeto 
            infraestructura cur = new infraestructura();
            cur = pvoInfraestructura;
            strSQL
                    = "INSERT  INTO Infraestructura(Id,Capacidad,IdTipoInfraestructura,Nombre,Ubicacion,IdPrograma) VALUES("
                    + cur.getId() + ","
                    + cur.getCapacidad() + ","
                    + cur.getIdTipoInfraestructura() + ",'"
                    + cur.getNombre() + "','"
                    + cur.getUbicacion() + "',"
                    + cur.getIdPrograma() + ")";
//Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQL/*, sqlBitacora*/);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        } finally {
        }
    }

    public void actualizarInfraestructura(infraestructura infraestructurap ) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        //Se obtienen los valores del objeto Cliente
        infraestructura c = new infraestructura();
        c = infraestructurap;

        //Datos de CLiente 
        int id = c.getId();
        int capacidad = c.getCapacidad();
        int idTipoInfraestructura = c.getIdTipoInfraestructura();
        String nombre = c.getNombre();
        String ubicacion = c.getUbicacion();
        int idPrograma = c.getIdPrograma();

                
        

        //Se crea la sentencia de actualización
        String update
                = "UPDATE Infraestructura SET Capacidad = " + capacidad + ", IdTipoInfraestructura=" + idTipoInfraestructura + ",Nombre='"+nombre+"',Ubicacion='"+ubicacion + "',IdPrograma=" + idPrograma+ "where Id = " + id + ";";
        //Se ejecuta la sentencia SQL
        accesoDatos.ejecutaSQL(update);
    }
    
}
