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
public class TelefonoDB {

    AccesoDatos accesoDatos = new AccesoDatos();

    public LinkedList<Telefono> seleccionarTelefonos(int idFuncionario) {
        LinkedList<Telefono> telefonos = new LinkedList<>();
        String select;
        try {
            select = "Select Id,Numero,TipoTelefono from Telefono te inner Join Funcionario_Telefono ft\n"
                    + "on te.Id = ft.IdTelefono\n"
                    + "where ft.IdFuncionario = " + idFuncionario;
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            while(rsPA.next()){
                int id = rsPA.getInt("Id");
                int numero = rsPA.getInt("Numero");
                int tipoTelefono = rsPA.getInt("TipoTelefono");
                Telefono te = new Telefono(tipoTelefono, numero);
                telefonos.add(te);
            }
        } catch (SNMPExceptions | ClassNotFoundException | SQLException | NamingException e) {
        }
        return telefonos;
    }

    public void insertarTelefono(Telefono tel, Funcionario fun) {
        String insert1;
        String iden = "";
        String insert2;
        try {
            insert1 = "INSERT INTO [dbo].[Telefono]\n"
                    + "([Numero]\n"
                    + ",[TipoTelefono])\n"
                    + "VALUES\n"
                    + "('"
                    + tel.getNumero() + "',"
                    + tel.getTipoTelefono() + ");";
            accesoDatos.ejecutaSQL(insert1);
            iden = "select Count(*) as iden from Telefono;";
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(iden);
            while (rsPA.next()) {
                iden = rsPA.getString("iden");
            }
            insert2 = "INSERT INTO [dbo].[Funcionario_Telefono]\n"
                    + "           ([IdFuncionario]\n"
                    + "           ,[IdTelefono])\n"
                    + "     VALUES\n"
                    + "           ("
                    + fun.getId() + ","
                    + iden + ")";
            accesoDatos.ejecutaSQL(insert2);
        } catch (SNMPExceptions | ClassNotFoundException | SQLException | NamingException e) {
            System.out.println(e.getMessage());
        } finally {
        }
    }
}
