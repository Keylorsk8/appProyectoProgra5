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
import javax.faces.model.SelectItem;
import javax.naming.NamingException;

/**
 *
 * @author KeylorSk8
 */
public class CantonDB {
    
    public LinkedList<Canton> seleccionarCantones(int idProvincia) throws SNMPExceptions{
        String select = "";
        LinkedList<Canton> listaCantones = new LinkedList<>();
        try {
            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select = "SELECT * FROM Canton where IdProvincia =";
            select += idProvincia;
            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los proyectos   
            while (rsPA.next()) {
                int id = rsPA.getInt("Id");
                String nombre = rsPA.getString("nombre");
          
                Canton can = new Canton(id,idProvincia,nombre);
                listaCantones.add(can);
            }
            rsPA.close();

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (SNMPExceptions | ClassNotFoundException | NamingException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {
        }
        return listaCantones;
    }
}
