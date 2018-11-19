/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author KeylorSk8
 */
@Named(value = "beanIndex")
@SessionScoped
public class beanIndex implements Serializable {

    /**
     * Creates a new instance of beanIndex
     */
    public beanIndex() {
    }
    
}
