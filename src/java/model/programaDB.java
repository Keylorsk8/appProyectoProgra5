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
public class programaDB {
    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    private LinkedList<programa> listaP = new LinkedList<programa>();

    public programaDB(Connection conn) {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public programaDB() {
        super();
    }
    
    public LinkedList<programa> consultarPrograma() throws SNMPExceptions, SQLException {
        String select = "";
        LinkedList<programa> listaPrograma = new LinkedList<programa>();

        try {
            //open();
            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT IdPrograma,NombreCurso,Descripcion from Programa";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los catálogos   
            while (rsPA.next()) {

                String nombreCurso = rsPA.getString("NombreCurso");
                String descripcion = rsPA.getString("Descripcion");
                String codigoPrograma = rsPA.getString("IdPrograma");

                programa dep = new programa(codigoPrograma,nombreCurso,descripcion);
                listaPrograma.add(dep);
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
        return listaPrograma;
    }

    public void mvRegitroPrograma(programa pvoPrograma)
            throws SNMPExceptions, SQLException {
        String strSQL = "";
        try {
           //Se obtienen los valores del objeto 
            programa cur = new programa();
            cur = pvoPrograma;
            strSQL
                    = "INSERT  INTO Programa(IdPrograma,NombreCurso,Descripcion) VALUES('"
                    + cur.getCodigoPrograma()+ "','"
                    + cur.getNombreCurso()+ "','"
                    + cur.getDescripcion()+ "')";
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

    public void actualizarPrograma(programa programap) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        //Se obtienen los valores del objeto Cliente
        programa c = new programa();
        c = programap;

        //Datos de CLiente         
        String nombreCurso = c.getNombreCurso();
        String descripcion = c.getDescripcion();
        String codigoPrograma = c.getCodigoPrograma();

        //Se crea la sentencia de actualización
        String update
                = "UPDATE Programa SET Descripcion = '" + descripcion + "', NombreCurso='" + nombreCurso + "' where IdPrograma = '" + codigoPrograma + "';";
        //Se ejecuta la sentencia SQL
        accesoDatos.ejecutaSQL(update);
    }
    
       public LinkedList<programa> moTodo() throws SNMPExceptions, SQLException{
        String select= " ";
        LinkedList<programa> listaCand= new LinkedList<programa>();
        
        try{
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos= new AccesoDatos();
            
            //Se crea la sentencia de Busqueda
            select=
                    "Select IdPrograma,NombreCurso,Descripcion from Programa";
            //se ejecuta la sentencia sql
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos
            while(rsPA.next()){
                
                String codigoPrograma = rsPA.getString("IdPrograma");
                String nombreCurso =rsPA.getString("NombreCurso");
                String descripcion= rsPA.getString("Descripcion");
               
                
                //se construye el objeto.
                programa perCandidato= new programa(codigoPrograma,nombreCurso,descripcion);
                
                listaCand.add(perCandidato);
            }
            rsPA.close();//se cierra el ResultSeat.
            
        }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(SNMPExceptions | ClassNotFoundException | NamingException e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }finally{
            
        }
        return listaCand;
    }
}
