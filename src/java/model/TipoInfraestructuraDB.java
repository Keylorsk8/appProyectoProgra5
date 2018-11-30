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
public class TipoInfraestructuraDB {
   private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    private LinkedList<TipoInfraestructura> listaP = new LinkedList<TipoInfraestructura>();

    public TipoInfraestructuraDB(Connection conn) {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public TipoInfraestructuraDB() {
        super();
    }
     
     public LinkedList<TipoInfraestructura> moTodo() throws SNMPExceptions, SQLException{
        String select= " ";
        LinkedList<TipoInfraestructura> listaCand= new LinkedList<TipoInfraestructura>();
        
        try{
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos= new AccesoDatos();
            
            //Se crea la sentencia de Busqueda
            select=
                    "Select Id,Nombre from TipoInfraestructura";
            //se ejecuta la sentencia sql
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos
            while(rsPA.next()){
                
                int id= rsPA.getInt("Id");
                String nombre = rsPA.getString("Nombre");
                

               
                
                //se construye el objeto.
                TipoInfraestructura perCandidato= new TipoInfraestructura(id,nombre);
                
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
