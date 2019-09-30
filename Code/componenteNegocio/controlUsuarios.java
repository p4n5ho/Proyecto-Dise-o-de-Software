/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Dominio.EmpresaProductora;
import Dominio.EmpresaTransporte;
import Excepciones.ResiduosException;
import Datos.FabricaDatos;
import Datos.IFacadeDatos;
import Dominio.Usuario;

/**
 *
 * @author Equipo 1
 */
class controlUsuarios {
    
    private IFacadeDatos facade = FabricaDatos.getFachada();
    private Usuario usuario;
    
    controlUsuarios(){}
    
    public Usuario getUsuario(){
        return usuario;
    }
    
    boolean verificarSesion(String codigo, String password, int tipo){
        Usuario usuarioLog = new Usuario(codigo, password, tipo);
        Usuario resultado = facade.obtenerUsuario(usuarioLog.getCodigoAcceso());
        
        if(resultado != null){
            if(usuarioLog.getPassword().equals(resultado.getPassword()) && usuarioLog.getTipo() == resultado.getTipo()){
                this.usuario = resultado;
                return true;
            }
        }
        
        return false;
    }
    
    boolean registrarUsuario(String codigo, String password, int tipo, String nombre){
        Usuario usuarioLog = new Usuario(codigo, password, tipo);
        usuarioLog.setNombre(nombre);
        try {
            facade.agregarUsuario(usuarioLog);
            return true;
        } catch (ResiduosException residuosException) {
            return false;
        }
    }
    
    EmpresaProductora obtenerEmpresaProductora(String identificador){
        return facade.obtenerEmpresaProductora(identificador);
    }
    
    EmpresaTransporte obtenerEmpresaTransportadora(String identificador){
        return facade.obtenerEmpresaTransporte(identificador);
    }
}