/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.falkensmaze.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * 
 * @author Cristian
 * 
 * Clase que representa a los bloques
 * 
 */
@XmlRootElement
public class Block implements  Serializable {
    private String value;
    /**
     * Constructor predeterminado
     */
    public Block(){
        this.value=null;
    }
    /**
     * Metodo para obtener lo que vale el objeto block
     * @return el valor de block
     */
    public String getValue(){
        return this.value;
    }
    /**
     * Metodo para asignar un valor al objeto block
     * @param value
     */
    public void setValue(String value){
        this.value=value;
    }
    /**
     * Metodo para comprobar si el objeto esta vacio
     * @return true si esta vacio y false si contiene informacion
     */
    public boolean isEmpty(){
        return this.value==null;
    }
}
