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
import java.util.Date;
import java.util.LinkedList;
import javax.naming.NamingException;
import model.programa;

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
                    = "SELECT Id,Nombre,Estado,CodFunIngreso,FechaIngreso,CodFunEdito,FechaEdito from Programa";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los catálogos   
            while (rsPA.next()) {
                int id= rsPA.getInt("Id");
                String nombre = rsPA.getString("Nombre");
                boolean estado = rsPA.getBoolean("Estado");
                String codFunIngreso = rsPA.getString("CodFunIngreso");
                String fechaIngreso = rsPA.getString("FechaIngreso");
                String codFunEdito = rsPA.getString("CodFunEdito");
                String fechaEdito = rsPA.getString("FechaEdito");
                
                programa dep = new programa(id,nombre,estado,codFunIngreso,fechaIngreso,codFunEdito,fechaEdito);
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
                    = "INSERT  INTO Programa(Id,Nombre,Estado,CodFunIngreso,FechaIngreso= getDate(),CodFunEdito,FechaEdito=getdate()) VALUES('"
                    + cur.getId()+ ",'"
                    + cur.getNombre()+ "',"
                    + cur.isEstado()+ ",'"
                    + cur.getCodFunIngreso()+ "','"
                    + cur.getFechaIngreso() + "','"
                    + cur.getCodFunEdito() + "','"
                    + cur.getFechaEdito()+ "')";
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
        int id= c.getId();
        String nombre = c.getNombre();
        boolean estado= c.isEstado();
        String codFunIngreso = c.getCodFunIngreso();
        String fechaIngreso = c.getFechaIngreso();
        String codFunEdito = c.getCodFunEdito();
        String fechaEdito = c.getFechaEdito();
        
        

        //Se crea la sentencia de actualización
        String update
                = "UPDATE Programa SET Nombre = '" + nombre + "', Estado=" + estado + ",CodFunIngreso='"+codFunIngreso+"',FechaIngreso="+fechaIngreso+",CodFunEdito='"+codFunEdito+"',FechaEdito="+fechaEdito+" where Id = " + id + ";";
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
                    "Select Id,Nombre,Estado,CodFunIngreso,FechaIngreso = getDate(),CodFunEdito,FechaEdito=getDate() from Programa";
            //se ejecuta la sentencia sql
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos
            while(rsPA.next()){
                
                int id= rsPA.getInt("Id");
                String nombre = rsPA.getString("Nombre");
                boolean estado = rsPA.getBoolean("Estado");
                String codFunIngreso = rsPA.getString("CodFunIngreso");
                String fechaIngreso = rsPA.getString("FechaIngreso");
                String codFunEdito = rsPA.getString("CodFunEdito");
                String fechaEdito = rsPA.getString("FechaEdito");
               
                
                //se construye el objeto.
                programa perCandidato= new programa(id,nombre,estado,codFunIngreso,fechaIngreso,codFunEdito,fechaEdito);
                
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
