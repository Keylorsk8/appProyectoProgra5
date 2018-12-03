package model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
public class ProgramaDB {
    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    private LinkedList<Programa> listaP = new LinkedList<Programa>();

    public ProgramaDB(Connection conn) {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public ProgramaDB() {
        super();
    }
    
    public LinkedList<Programa> consultarPrograma() throws SNMPExceptions, SQLException {
        String select = "";
        LinkedList<Programa> listaPrograma = new LinkedList<Programa>();

        try {
            //open();
            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT Id,Nombre,Estado from Programa";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los catálogos   
            while (rsPA.next()) {
                int id= rsPA.getInt("Id");
                String nombre = rsPA.getString("Nombre");
                boolean estado = rsPA.getBoolean("Estado");

                
                Programa dep = new Programa(id,nombre,estado);
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

    public void mvRegitroPrograma(Programa pvoPrograma)
            throws SNMPExceptions, SQLException {
        String strSQL = "";
        try {
           //Se obtienen los valores del objeto 
            Programa cur = new Programa();
            cur = pvoPrograma;
            strSQL
                    = "INSERT  INTO Programa(Id,Nombre,Estado,CodFunIngreso,FechaIngreso,CodFunEdito,FechaEdito,IdCoordinador) VALUES("
                     +cur.getId()+ ",'"
                    + cur.getNombre()+ "',"
                    +(cur.isEstado()?1:0) + ",'"
                    + "1"+ "',"
                    + "GetDate()" + ",'"
                    + "1" + "',"
                    + "GetDate()"+ ","
                    + 1+ ")";
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
    
     public LinkedList<Programa> buscarPrograma(int idp) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String select = "";
        LinkedList<Programa> listaPrograma = new LinkedList<Programa>();

        try {
            //open();
            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT Id,Nombre,Estado from Programa where Id="+idp;

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los catálogos   
            while (rsPA.next()) {
                int id= rsPA.getInt("Id");
                String nombre = rsPA.getString("Nombre");
                boolean estado = rsPA.getBoolean("Estado");

                
                Programa dep = new Programa(id,nombre,estado);
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


   

    public void actualizarPrograma(Programa programap) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        //Se obtienen los valores del objeto Cliente
        Programa c = new Programa();
        c = programap;

        //Datos de CLiente     
        int id= c.getId();
        String nombre = c.getNombre();
        boolean estado= c.isEstado();

        //Se crea la sentencia de actualización
        String update
                = "UPDATE Programa SET Nombre = '" + nombre + "', Estado=" + (estado?1:0) + ",CodFunIngreso='"+1+"',FechaIngreso="+"getdate()"+",CodFunEdito='"+1+"',FechaEdito="+"getDate()"+",IdCoordinador="+1+" where Id = " + id + ";";
        //Se ejecuta la sentencia SQL
        accesoDatos.ejecutaSQL(update);
    }
    
       public LinkedList<Programa> moTodo() throws SNMPExceptions, SQLException{
        String select= " ";
        LinkedList<Programa> listaCand= new LinkedList<Programa>();
        
        try{
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos= new AccesoDatos();
            
            //Se crea la sentencia de Busqueda
            select=
                    "Select Id,Nombre,Estado from Programa";
            //se ejecuta la sentencia sql
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos
            while(rsPA.next()){
                
                int id= rsPA.getInt("Id");
                String nombre = rsPA.getString("Nombre");
                boolean estado = rsPA.getBoolean("Estado");

               
                
                //se construye el objeto.
                Programa perCandidato= new Programa(id,nombre,estado);
                
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
