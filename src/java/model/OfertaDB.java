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
public class OfertaDB {
    
     private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public OfertaDB(Connection conn) {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public OfertaDB() {
        super();
    }
    
    
    public LinkedList<Oferta> consultarOferta() throws SNMPExceptions, SQLException {
        String select;
        String estadop="";
        LinkedList<Oferta> listaOferta = new LinkedList<>();

        try {
            //open();
            //Se instancia la clase de acceso a datos
            accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "select id,descripcion,estado,fechainicio,fechafinal,horainicio,horafinal,idcurso,IdInfraestructura,IdPeriodo from OfertaAcademica";

            //Se llena el arryaList con los catálogos
            try ( //Se ejecuta la sentencia SQL
                    ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select)) {
                //Se llena el arryaList con los catálogos
                while (rsPA.next()) {                   
                    int id = rsPA.getInt("id");
                    String descripcion = rsPA.getString("descripcion");
                    boolean estado = rsPA.getBoolean("estado");
                    String fechaInicio= rsPA.getString("fechainicio").substring(0,10);
                    String fechaFinal = rsPA.getString("fechafinal").substring(0,10);
                    int horaInicio = rsPA.getInt("horainicio");
                    int horaFinal = rsPA.getInt("horafinal");
                    int idCurso = rsPA.getInt("idcurso");
                    int idInfraestructura = rsPA.getInt("IdInfraestructura");
                    int idPeriodo = rsPA.getInt("IdPeriodo");                   
                                                    
                    Oferta dep = new Oferta(id, descripcion, estado,fechaInicio,fechaFinal,horaInicio,horaFinal,idCurso,idInfraestructura,idPeriodo );
                    listaOferta.add(dep);
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
        return listaOferta;
    }
    
    
     public LinkedList<Oferta> buscarOferta(int idp) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String select = "";
        String estadop=" ";
        LinkedList<Oferta> listaOferta = new LinkedList<Oferta>();

        try {
            //open();
            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "select id,descripcion,estado,fechainicio,fechafinal,horainicio,horafinal,idcurso,IdInfraestructura,IdPeriodo from OfertaAcademica where id="+idp;

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los catálogos   
            while (rsPA.next()) {
                   int id = rsPA.getInt("Id");
                    String descripcion = rsPA.getString("Descripcion");
                    boolean estado = rsPA.getBoolean("Estado");
                    
                    String fechaInicio= rsPA.getString("fechainicio").substring(0,10);
                    String fechaFinal = rsPA.getString("fechaFinal").substring(0,10);
                    int horaInicio = rsPA.getInt("horaInicio");
                    int horaFinal = rsPA.getInt("horaFinal");
                    int idCurso = rsPA.getInt("idCurso");
                    int idInfraestructura = rsPA.getInt("IdInfraestructura");
                    int idPeriodo = rsPA.getInt("IdPeriodo");
                       
                    Oferta dep = new Oferta(id, descripcion, estado,fechaInicio,fechaFinal,horaInicio,horaFinal,idCurso,idInfraestructura,idPeriodo );
                    listaOferta.add(dep);
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
        return listaOferta;
    }
     
      public void mvRegitroOferta(Oferta pvoOferta,Funcionario fun)
            throws SNMPExceptions, SQLException {
        String strSQL;
        try {
//Se obtienen los valores del objeto 
            Oferta cur = pvoOferta;
            strSQL = "INSERT  INTO OfertaAcademica(descripcion,estado,fechainicio,fechafinal,horainicio,horafinal,CodFunIngreso,FechaIngreso,CodFunEdito,FechaEdito, idcurso,IdInfraestructura,IdPeriodo) VALUES('"                   
                    + cur.getDescripcion() + "',"
                    + (cur.isEstadov()?1:0) + ",'"
                    + cur.getFechaInicio() + "','"
                    + cur.getFechaFinal() + "',"
                    + cur.getHoraInicio()+ ","
                    + cur.getHoraFinal() + ",'"
                    + fun.getId()+ "',"
                    + "getDate()"+ ",'"
                    + 1 + "',"
                    + "getDate()" + ","
                    + cur.getIdCurso()+ ","
                    + cur.getIdInfraestructura() + ","
                    + cur.getIdPeriodo()+")";
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
      
       public void actualizarOferta(Oferta cursop,Funcionario fun) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        //Se obtienen los valores del objeto Cliente
        Oferta c = cursop;
        Funcionario f=fun;
        //Datos de CLiente         
        int id = c.getId();
        String descripcion = c.getDescripcion();
        boolean estado = c.isEstadov();
        String fechaInicio = c.getFechaInicio();
        String fechaFinal = c.getFechaFinal();
        int horaInicio = c.getHoraInicio();
        int horaFinal = c.getHoraFinal();
        int codFunEdito = f.getId();
        int idCurso = c.getIdCurso();
        int idInfraestructura = c.getIdInfraestructura();
        int idPeriodo = c.getIdPeriodo();
        
        //Se crea la sentencia de actualización
        String update = "UPDATE OfertaAcademica SET Descripcion = '"+ descripcion+ "', Estado="+ (estado?1:0) + ",FechaInicio='"+fechaInicio +"',FechaFinal='"+fechaFinal+"',horaInicio="+horaInicio+",HoraFinal="+horaFinal +",CodFunEdito='" + codFunEdito + "',FechaEdito="  + "getDate()" +",idCurso="+idCurso+",IdInfraestructura="+idInfraestructura+",IdPeriodo="+idPeriodo+ "where Id = " + id + ";";
        //Se ejecuta la sentencia SQL
        accesoDatos.ejecutaSQL(update);
    }
       
//       public LinkedList<Oferta> moTodo() throws SNMPExceptions, SQLException{
//        String select= " ";
//        LinkedList<Oferta> listaOferta= new LinkedList<Oferta>();
//        
//        try{
//            //Se intancia la clase de acceso a datos
//            AccesoDatos accesoDatos= new AccesoDatos();
//            
//            //Se crea la sentencia de Busqueda
//            select=
//                    "select id,descripcion,estado,fechainicio,fechafinal,horainicio,horafinal,idcurso,IdInfraestructura,IdPeriodo from OfertaAcademica ";
//            //se ejecuta la sentencia sql
//            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
//            //se llama el array con los proyectos
//            while(rsPA.next()){
//                
//                int id = rsPA.getInt("Id");
//                    String descripcion = rsPA.getString("Descripcion");
//                    boolean estado = rsPA.getBoolean("Estado");
//                    String fechaInicio= rsPA.getString("fechainicio");
//                    String fechaFinal = rsPA.getString("fechaFinal");
//                    int horaInicio = rsPA.getInt("horaInicio");
//                    int horaFinal = rsPA.getInt("horaFinal");
//                    int idCurso = rsPA.getInt("idCurso");
//                    int idInfraestructura = rsPA.getInt("IdInfraestructura");
//                    int idPeriodo = rsPA.getInt("IdPeriodo");
//                    
//                    
//                                     
//                    Oferta dep = new Oferta(id, descripcion, estado,fechaInicio,fechaFinal,horaInicio,horaFinal,idCurso,idInfraestructura,idPeriodo );
//                    listaOferta.add(dep);
//            }
//            rsPA.close();//se cierra el ResultSeat.
//            
//        }catch(SQLException e){
//            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
//                                     e.getMessage(),e.getErrorCode());
//        }catch(SNMPExceptions | ClassNotFoundException | NamingException e){
//            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
//        }finally{
//            
//        }
//        return listaOferta;
//    } 
}
