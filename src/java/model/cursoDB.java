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

/**
 *
 * @author Pablo
 */
public class cursoDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    private LinkedList<curso> listaP = new LinkedList<curso>();

    public cursoDB(Connection conn) {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public cursoDB() {
        super();
    }

    public LinkedList<curso> consultarCurso() throws SNMPExceptions, SQLException {
        String select = "";
        LinkedList<curso> listaCurso = new LinkedList<curso>();

        try {
            //open();
            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT Id,Descripcion,Estado,CodFunIngreso,FechaIngreso,CodFunEdito,FechaEdito,IdPrograma from Curso";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los catálogos   
            while (rsPA.next()) {

                int id = rsPA.getInt("Id");
                String descripcion = rsPA.getString("Descripcion");
                boolean estado= rsPA.getBoolean("esyado");
                String codFunIngreso = rsPA.getString("CodFunIngreso");
                Date fechaIngreso = rsPA.getDate("FechaIngreso");
                String codFunEdito = rsPA.getString("CodFunEdito");
                Date fechaEdito = rsPA.getDate("FechaEdito");
                int idPrograma = rsPA.getInt("IdPrograma");

                curso dep = new curso(id,descripcion,estado,codFunIngreso,fechaIngreso,codFunEdito,fechaEdito,idPrograma);
                listaCurso.add(dep);
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
        return listaCurso;
    }

    public void mvRegitroCurso(curso pvoCurso)
            throws SNMPExceptions, SQLException {
        String strSQL = "";
        try {
//Se obtienen los valores del objeto 
            curso cur = new curso();
            cur = pvoCurso;
            strSQL
                    = "INSERT  INTO Curso(Id,Descripcion,Estado,CodFunIngreso,FechaIngreso,CodFunEdito,FechaEdito,IdPrograma) VALUES("
                    + cur.getId() + ",'"
                    + cur.getDescripcion() + "',"
                    + cur.isEstado()+ ",'"
                    + cur.getCodFunIngreso()+ "',"
                    + cur.getFechaIngreso()+ ",'"
                    + cur.getCodFunEdito()+ "',"
                    + cur.getFechaEdito()+ ","
                    + cur.getIdPrograma()+ ")";
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

    public void actualizarCurso(curso cursop) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        //Se obtienen los valores del objeto Cliente
        curso c = new curso();
        c = cursop;

        //Datos de CLiente         
       int id= c.getId();
       String descripcion = c.getDescripcion();
       boolean estado = c.isEstado();
       String codFunIngreso = c.getCodFunIngreso();
       Date fechaIngreso = c.getFechaIngreso();
       String codFunEdito = c.getCodFunEdito();
       Date fechaEdito = c.getFechaEdito();
       int idPrograma = c.getIdPrograma();

        //Se crea la sentencia de actualización
        String update
                = "UPDATE Curso SET Descripcion = '" + descripcion + "', Estado='" + estado + "',CodFunIngreso="+codFunIngreso+"',FechaIngreso="+fechaIngreso+",CodFunEdito='"+codFunEdito +"',FechaEdito="+fechaEdito+",IdPrograma="+idPrograma+"where Id = '" + id + "';";
        //Se ejecuta la sentencia SQL
        accesoDatos.ejecutaSQL(update);
    }
    
    
}
