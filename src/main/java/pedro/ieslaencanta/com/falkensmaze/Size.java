/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.falkensmaze;

import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Clase que gestiona el tamaño del laberinto
 * @author Cristian
 * 
 * 
 */
@XmlRootElement
public class Size implements Cloneable, Comparable<Size>, Serializable {

    private int width;
    private int height;

    /**
     * Constructor por defecto
     */
    public Size() {
    }

    /**
     * Constructor sobrecargado con la anchura y altura
     * 
     * @param width
     * @param height
     */
    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Metodo para clonar un objeto
     * @return devuelve el objeto clonado
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Size(this.getWidth(), this.getHeight());
    }

    /** 
     * Metodo para comprobar si dos objetos son iguales
     * @return devuelve true si es igual y false si no son iguales
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Size)) {
            return false;
        }
        if (this.getWidth() == ((Size) (o)).getWidth() && this.getHeight() == ((Size) (o)).getHeight()) {
            return true;
        } else {
            return false;
        }

    }
/**
 * Metodo para comparar dos objetos size y ver si son iguales
 * @return devuelve 0 si los dos size son iguales, -1 si el objeto es menor que el del parámetro y 1 si el objeto es mayor que el parametro
 */
    @Override
    public int compareTo(Size o) {
        if (this.getWidth() == o.getWidth() && this.getHeight() == o.getHeight()) {
            return 0;
        }
        if (this.getWidth() < o.getWidth()) {
            return -1;
        } else {
            return 1;
        }
    }
/**
 * Metodo para devolver por pantalla el tamaño
 * @return devuelve el objeto size para poder mostrarse por pantalla
 */
    public String toString() {
        return "W:" + this.width + " H:" + this.height;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

}
