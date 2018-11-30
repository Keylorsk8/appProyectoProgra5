/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Pablo
 */
public class TipoInfraestructura {
    private int id;
    private String nombre;
    
    public TipoInfraestructura(){
        this.id=0;
        this.nombre=" ";        
    }
    
    public TipoInfraestructura(int idp,String nombrep){
        this.id=idp;
        this.nombre=nombrep;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
