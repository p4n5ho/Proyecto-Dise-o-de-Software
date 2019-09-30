/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

/**
 *
 * @author Alejandro Galindo
 */
public class FabricaNegocio {
    public static IFacadeNegocio getFachada(){
        return new facadeNegocio();
    }
}
