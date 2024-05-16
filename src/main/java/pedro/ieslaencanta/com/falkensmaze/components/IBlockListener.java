/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pedro.ieslaencanta.com.falkensmaze.components;

/**
 *
 * @author Cristian
 * 
 * Para aquellas clases que implementen esta interfaz forzara a que tengan un onCLicked(un click) y onDoubleClick(doble click)
 */
public interface IBlockListener {
    /**
     * Metodo para cuando se hace un click sobre un bloque
     * @param b 
     */
    public void onClicked(Block b);
    /**
     * Metodo para cuando se hacen dos clicks
     * @param b 
     */
    public void onDoubleClicked(Block b);
}
