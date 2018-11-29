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
public class DistritoDB {
    
    public LinkedList<Distrito> seleccionarDistritos(int idCanton,int idProvincia) throws SNMPExceptions{
        String select = "";
        LinkedList<Distrito> listaDistritos = new LinkedList<>();
        try {
            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select = "SELECT * FROM Distrito where IdProvincia =";
            select += idProvincia + " and IdCanton = " + idCanton; 
            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {
                int id = rsPA.getInt("Id");
                String nombre = rsPA.getString("nombre");
          
                Distrito dis = new Distrito(id, idCanton, idProvincia, nombre);
                listaDistritos.add(dis);
            }
            rsPA.close();

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (SNMPExceptions | ClassNotFoundException | NamingException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {
        }
        return listaDistritos;
    }
}
