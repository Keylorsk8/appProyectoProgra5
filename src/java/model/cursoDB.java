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
                    = "SELECT IdCurso,Descripcion,IdPrograma from Curso";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los catálogos   
            while (rsPA.next()) {

                String codigoCurso = rsPA.getString("IdCurso");
                String descripcion = rsPA.getString("Descripcion");
                String codigoPrograma = rsPA.getString("IdPrograma");

                curso dep = new curso(codigoCurso, descripcion, codigoPrograma);
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
                    = "INSERT  INTO Curso(IdCurso,Descripcion,IdPrograma) VALUES('"
                    + cur.getCodigoCurso() + "','"
                    + cur.getDescripcion() + "','"
                    + cur.getCodigoPrograma() + "')";
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
        String codigoCurso = c.getCodigoCurso();
        String descripcion = c.getDescripcion();
        String codigoPrograma = c.getCodigoPrograma();

        //Se crea la sentencia de actualización
        String update
                = "UPDATE Curso SET Descripcion = '" + descripcion + "', IdPrograma='" + codigoPrograma + "' where IdCurso = '" + codigoCurso + "';";
        //Se ejecuta la sentencia SQL
        accesoDatos.ejecutaSQL(update);
    }
}
