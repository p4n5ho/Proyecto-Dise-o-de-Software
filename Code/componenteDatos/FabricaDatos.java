/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

/**
 *
 * @author Alejandro Galindo
 */
public class FabricaDatos {
    public static IFacadeDatos getFachada(){
        return new facadeDatos();
    }
}
