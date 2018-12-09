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
public class PeriodoDB {
    
     private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    private LinkedList<Periodo> listaP = new LinkedList<Periodo>();

    public PeriodoDB(Connection conn) {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public PeriodoDB() {
        super();
    }
    
    public LinkedList<Periodo> consultarPeriodo() throws SNMPExceptions, SQLException {
        String select = "";
         
        LinkedList<Periodo> listaPeriodo = new LinkedList<Periodo>();
        try {
            //open();
            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();
            //Se crea la sentencia de búsqueda
            select
                    = "SELECT Id,Nombre,FechaInicio,FechaFinal,Anio from Periodo";
            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los catálogos   
            while (rsPA.next()) {
                int id = rsPA.getInt("Id");
                String nombre = rsPA.getString("Nombre");
                String fechaInicio= rsPA.getString("FechaInicio");
                String fechaFinal = rsPA.getString("FechaFinal");
                int annio = rsPA.getInt("Anio");
                
                //Programa dep = new Programa(id, nombre, estado);
                Periodo dep = new Periodo(id, nombre,fechaInicio,fechaFinal,annio);
                listaPeriodo.add(dep);
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
        return listaPeriodo;
    }
    
    
    public void mvRegitroPeriodo(Periodo pvoPeriodo)
            throws SNMPExceptions, SQLException {
        String strSQL = "";
        try {
            Periodo cur = new Periodo();
            cur = pvoPeriodo;
            strSQL
                    = "INSERT  INTO Periodo(Nombre,FechaInicio,FechaFinal,anio) VALUES('"
                    + cur.getNombre() + "','"
                    + cur.getFechaInicio() + "','" 
                    + cur.getFechaFinal() + "'," 
                    + cur.getAnio()  +");";
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
    
     public LinkedList<Periodo> buscarID(int idp) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String select = "";
        String estadop=" ";
        LinkedList<Periodo> listaPeriodo = new LinkedList<Periodo>();
            try {
                //open();
                //Se instancia la clase de acceso a datos
                AccesoDatos accesoDatos = new AccesoDatos();
                //Se crea la sentencia de búsqueda
                select
                = "select * from Periodo where id=" +idp;
                //Se ejecuta la sentencia SQL
                ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
                //Se llena el arryaList con los catálogos   
                while (rsPA.next()) {
                    int id = rsPA.getInt("Id");
                    String nombre = rsPA.getString("Nombre");
                    String fechaInicio = rsPA.getString("FechaInicio");
                    String fechaFinal = rsPA.getString("FechaFinal");
                    int anio = rsPA.getInt("Anio");    
                     Periodo dep = new Periodo(id, nombre,fechaInicio,fechaFinal,anio);
                    listaPeriodo.add(dep);
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
            return listaPeriodo;       
    }
     
     public LinkedList<Periodo> buscarPeriodo(String idp) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String select = "";
        
        LinkedList<Periodo> listaPeriodo = new LinkedList<Periodo>();
        try{
             try {
                int num= Integer.parseInt(idp);
                //open();
                //Se instancia la clase de acceso a datos
                AccesoDatos accesoDatos = new AccesoDatos();
                //Se crea la sentencia de búsqueda
                select
                = "Select * from Periodo where id="+num;
                //Se ejecuta la sentencia SQL
                ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
                //Se llena el arryaList con los catálogos   
                while (rsPA.next()) {
                  int id = rsPA.getInt("Id");
                    String nombre = rsPA.getString("Nombre");
                    String fechaInicio = rsPA.getString("FechaInicio");
                    String fechaFinal = rsPA.getString("FechaFinal");
                    int anio = rsPA.getInt("Anio");    
                     Periodo dep = new Periodo(id, nombre,fechaInicio,fechaFinal,anio);
                    listaPeriodo.add(dep);
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
            return listaPeriodo;
        }catch(Exception ex) {
             try {
                
                //open();
                //Se instancia la clase de acceso a datos
                AccesoDatos accesoDatos = new AccesoDatos();
                //Se crea la sentencia de búsqueda
                select
                = "Select * from Periodo where Nombre like '%"+idp+"%'";
                //Se ejecuta la sentencia SQL
                ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
                //Se llena el arryaList con los catálogos   
                while (rsPA.next()) {
                   int id = rsPA.getInt("Id");
                    String nombre = rsPA.getString("Nombre");
                    String fechaInicio = rsPA.getString("FechaInicio");
                    String fechaFinal = rsPA.getString("FechaFinal");
                    int anio = rsPA.getInt("Anio");    
                     Periodo dep = new Periodo(id, nombre,fechaInicio,fechaFinal,anio);
                    listaPeriodo.add(dep);
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
            return listaPeriodo;
        }
           
       
    }
    
    public void actualizarPeriodo(Periodo periodop) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        //Se obtienen los valores del objeto Cliente
        Periodo c = new Periodo();
        c = periodop;        
        
        //Datos de CLiente     
        int id= c.getId();
        String nombre = c.getNombre();
        String fechaInicio = c.getFechaInicio();
        String fechaFinal = c.getFechaFinal();
        int anio= c.getAnio();
        
        int annoS = Integer.parseInt(fechaInicio.substring(0, 4));
        int diaS = Integer.parseInt(fechaInicio.substring(8, 10));
        int mesS = Integer.parseInt(fechaInicio.substring(5, 7));
        fechaInicio=annoS + "/"+diaS+"/"+mesS;
            
        int anno = Integer.parseInt(fechaFinal.substring(0, 4));       
        int dia = Integer.parseInt(fechaFinal.substring(8, 10));
        int mes = Integer.parseInt(fechaFinal.substring(5, 7));
        fechaFinal= anno+"/"+dia+"/"+mes;
        //Se crea la sentencia de actualización
        String update
                = "UPDATE Periodo SET Nombre = '" + nombre + 
                                      "',"+"FechaInicio='"+ fechaInicio+
                                      "',"+"FechaFinal='"+fechaFinal+
                                      "',"+"Anio="+ anio+
                                      " where Id = " + id + ";";
        //Se ejecuta la sentencia SQL
        accesoDatos.ejecutaSQL(update);
    }
    
     public LinkedList<Periodo> moTodo() throws SNMPExceptions, SQLException{
        String select= " ";
       
        LinkedList<Periodo> listaPeriodo= new LinkedList<Periodo>();       
        try{
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos= new AccesoDatos();            
            //Se crea la sentencia de Busqueda
            select=
                    "Select * from Periodo";
            //se ejecuta la sentencia sql
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos
            while(rsPA.next()){                
                int id = rsPA.getInt("Id");
                    String nombre = rsPA.getString("Nombre");
                    String fechaInicio = rsPA.getString("FechaInicio");
                    String fechaFinal = rsPA.getString("FechaFinal");
                    int anio = rsPA.getInt("Anio");    
                     Periodo dep = new Periodo(id, nombre,fechaInicio,fechaFinal,anio);
                    listaPeriodo.add(dep);
            }
            rsPA.close();//se cierra el ResultSeat.
            
        }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(SNMPExceptions | ClassNotFoundException | NamingException e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }finally{
            
        }
        return listaPeriodo;
       }
    
    
}
