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
public class DireccionDB {

    AccesoDatos accesoDatos = new AccesoDatos();

    public Direccion seleccionarDireccion(int idFuncionario) {
        Direccion dir = null;
        String select;
        try {
            select = "SELECT [Id]\n"
                    + "      ,[IdProvincia]\n"
                    + "      ,[IdCanton]\n"
                    + "      ,[IdDistrito]\n"
                    + "      ,[IdBarrio]\n"
                    + "      ,[Otras_Senias]\n"
                    + "  FROM [dbo].[Direccion] where Id = " + idFuncionario;
           ResultSet rsPA =  accesoDatos.ejecutaSQLRetornaRS(select);
           while(rsPA.next()){
               int provincia = rsPA.getInt("IdProvincia");
               int canton = rsPA.getInt("IdCanton");
               int distrito = rsPA.getInt("IdDistrito");
               int barrio = rsPA.getInt("IdBarrio");
               String otrasSenias = rsPA.getString("Otras_Senias");
               dir = new Direccion(idFuncionario, provincia, canton, distrito, barrio, otrasSenias);
           }
        } catch (SNMPExceptions | ClassNotFoundException | SQLException | NamingException e) {
        }finally{
        }
        return dir;
    }
}
