/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author KeylorSk8
 */
public class Direccion {

    private int id;
    private int idProvincia;
    private int idCanton;
    private int idDistrito;
    private int idBarrio;
    private String otrasSenias;

    public Direccion(){
        
    }
    
    public Direccion(int id, int idProvincia, int idCanton, int idDistrito, int idBarrio, String otrasSenias) {
        this.id = id;
        this.idProvincia = idProvincia;
        this.idCanton = idCanton;
        this.idDistrito = idDistrito;
        this.idBarrio = idBarrio;
        this.otrasSenias = otrasSenias;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the idProvincia
     */
    public int getIdProvincia() {
        return idProvincia;
    }

    /**
     * @param idProvincia the idProvincia to set
     */
    public void setIdProvincia(int idProvincia) {
        this.idProvincia = idProvincia;
    }

    /**
     * @return the idCanton
     */
    public int getIdCanton() {
        return idCanton;
    }

    /**
     * @param idCanton the idCanton to set
     */
    public void setIdCanton(int idCanton) {
        this.idCanton = idCanton;
    }

    /**
     * @return the idDistrito
     */
    public int getIdDistrito() {
        return idDistrito;
    }

    /**
     * @param idDistrito the idDistrito to set
     */
    public void setIdDistrito(int idDistrito) {
        this.idDistrito = idDistrito;
    }

    /**
     * @return the idBarrio
     */
    public int getIdBarrio() {
        return idBarrio;
    }

    /**
     * @param idBarrio the idBarrio to set
     */
    public void setIdBarrio(int idBarrio) {
        this.idBarrio = idBarrio;
    }

    /**
     * @return the otrasSenias
     */
    public String getOtrasSenias() {
        return otrasSenias;
    }

    /**
     * @param otrasSenias the otrasSenias to set
     */
    public void setOtrasSenias(String otrasSenias) {
        this.otrasSenias = otrasSenias;
    }
}
