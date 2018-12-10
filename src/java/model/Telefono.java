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
public class Telefono {

    private int tipoTelefono;
    private int numero;

    public Telefono() {
    }

    public Telefono(int tipoTelefono, int numero) {
        this.tipoTelefono = tipoTelefono;
        this.numero = numero;
    }
    
    /**
     * @return the tipoTelefono
     */
    public int getTipoTelefono() {
        return tipoTelefono;
    }

    /**
     * @param tipoTelefono the tipoTelefono to set
     */
    public void setTipoTelefono(int tipoTelefono) {
        this.tipoTelefono = tipoTelefono;
    }

    /**
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }
}
