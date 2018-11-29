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
public class BarrioDB {
    public LinkedList<Barrio> seleccionarBarrios(int IdCanton,int IdProvincia,int IdDistrito) throws SNMPExceptions{
        String select = "";
        LinkedList<Barrio> listaBarrio = new LinkedList<>();
        try {
            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select = "SELECT * FROM Barrio where IdProvincia=";
            select += IdProvincia + " and IdCanton =" + IdCanton + " and IdDistrito =" + IdDistrito;
            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {
                int id = rsPA.getInt("Id");
                String nombre = rsPA.getString("Descripcion");
               
                Barrio ba = new Barrio(id, IdCanton, IdDistrito, IdDistrito, nombre);
                listaBarrio.add(ba);
            }
            rsPA.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (SNMPExceptions | ClassNotFoundException | NamingException e) {
            System.out.println(e.getMessage());
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {
        }
        return listaBarrio;
    }
}
