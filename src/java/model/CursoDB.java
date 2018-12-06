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
import java.util.Date;
import java.util.LinkedList;
import javax.naming.NamingException;

/**
 *
 * @author Pablo
 */
public class CursoDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public CursoDB(Connection conn) {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public CursoDB() {
        super();
    }

    public LinkedList<Curso> consultarCurso() throws SNMPExceptions, SQLException {
        String select;
        LinkedList<Curso> listaCurso = new LinkedList<>();

        try {
            //open();
            //Se instancia la clase de acceso a datos
            accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT Id,Descripcion,Estado,IdPrograma from Curso";

            //Se llena el arryaList con los catálogos
            try ( //Se ejecuta la sentencia SQL
                    ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select)) {
                //Se llena el arryaList con los catálogos
                while (rsPA.next()) {
                    
                    int id = rsPA.getInt("Id");
                    String descripcion = rsPA.getString("Descripcion");
                    boolean estado = rsPA.getBoolean("Estado");
                    int idPrograma = rsPA.getInt("IdPrograma");
                    
                    Curso dep = new Curso(id, descripcion, estado, idPrograma);
                    listaCurso.add(dep);
                }
            }

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (SNMPExceptions | ClassNotFoundException | NamingException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        } finally {

        }
        return listaCurso;
    }
    
    public LinkedList<Curso> buscarCurso(String idp) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String select = "";
        LinkedList<Curso> listaCurso = new LinkedList<Curso>();

        try {
            //open();
            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT Descripcion,Estado,IdPrograma from Curso where Id ="+idp;

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los catálogos   
            while (rsPA.next()) {
                     int id = rsPA.getInt("Id");
                    String descripcion = rsPA.getString("Descripcion");
                    boolean estado = rsPA.getBoolean("Estado");
                    int idPrograma = rsPA.getInt("IdPrograma");
                
                    Curso dep = new Curso(id, descripcion, estado, idPrograma);
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

    public void mvRegitroCurso(Curso pvoCurso,Funcionario fun)
            throws SNMPExceptions, SQLException {
        String strSQL;
        try {
//Se obtienen los valores del objeto 
            Curso cur = pvoCurso;
            strSQL = "INSERT  INTO Curso(Id,Descripcion,Estado,CodFunIngreso,FechaIngreso,CodFunEdito,FechaEdito,IdPrograma) VALUES("
                    + cur.getId() + ",'"
                    + cur.getDescripcion() + "',"
                    + (cur.isEstado()?1:0) + ",'"
                    + fun.getCodFunIngreso()+ "',"
                    + fun.getFechaIngreso()+ ",'"
                    + 1 + "',"
                    + "getDate()" + ","
                    + cur.getIdPrograma() + ")";
//Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQL/*, sqlBitacora*/);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (SNMPExceptions | ClassNotFoundException | NamingException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        } finally {
        }
    }

    public void actualizarCurso(Curso cursop,Funcionario fun) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        //Se obtienen los valores del objeto Cliente
        Curso c = cursop;
        Funcionario f=fun;
        //Datos de CLiente         
        int id = c.getId();
        String descripcion = c.getDescripcion();
        boolean estado = c.isEstado();
        int idPrograma = c.getIdPrograma();

        //Se crea la sentencia de actualización
        String update = "UPDATE Curso SET Descripcion = '"+ descripcion+ "', Estado="+ (estado?1:0) +  ",CodFunEdito='" + f.getCodFunEdito() + "',FechaEdito="  + f.getFechaEdito()  + ",IdPrograma=" + idPrograma  + "where Id = " + id + ";";
        //Se ejecuta la sentencia SQL
        accesoDatos.ejecutaSQL(update);
    }
    
    public LinkedList<Curso> moTodo() throws SNMPExceptions, SQLException{
        String select= " ";
        LinkedList<Curso> listaCand= new LinkedList<Curso>();
        
        try{
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos= new AccesoDatos();
            
            //Se crea la sentencia de Busqueda
            select=
                    "Select Id,Descripcion,Estado,IdPrograma from Curso";
            //se ejecuta la sentencia sql
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos
            while(rsPA.next()){
                
                int id= rsPA.getInt("Id");
                String nombre = rsPA.getString("Descripcion");
                boolean estado = rsPA.getBoolean("Estado");
                int idPrograma=rsPA.getInt("IdPrograma");

               
                
                //se construye el objeto.
                Curso perCandidato= new Curso(id,nombre,estado,idPrograma);
                
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
