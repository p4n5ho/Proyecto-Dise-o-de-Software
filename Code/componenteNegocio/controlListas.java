/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Dominio.*;
import Datos.FabricaDatos;
import Datos.IFacadeDatos;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alejandro Galindo
 */
class controlListas {
    private IFacadeDatos facade = FabricaDatos.getFachada();
    

    controlListas(){}
    
    List<Residuo> obtenerListaResiduos(){
        return facade.getListaResiduos();
    }
    
    List<Residuo> obtenerListaResiduosDeEmpresa(EmpresaProductora empresa){
        EmpresaProductora empresaFinal = facade.obtenerEmpresaProductora(empresa.getRFC());
        
        List<Residuo> residuos = facade.getListaResiduos();
        List<Residuo> residuosPorEmpresa = new ArrayList<>();
        
        for (Residuo residuo : residuos) {
            if(residuo.getEmpresasProductoras().contains(empresaFinal)){
                residuosPorEmpresa.add(residuo);
            }
        }
        
        return residuosPorEmpresa;
    }
    
    List<ElementoQuimico> obtenerListaElementosQuimicos() {
        return facade.getListaElementosQuimicos();
    }
    
    List<Traslado> obtenerListaTraslados(){
        return facade.getListaTraslados();
    }
    
    List<Solicitud> obtenerListasSolicitudesNoAtendidas(){
        List<Solicitud> solicitudes = facade.getListaSolicitudes();
        List<Solicitud> solicitudesNoAtendidas = new ArrayList<>();
        
        for (Solicitud solt : solicitudes) {
            if(!solt.isEstado()){
                solicitudesNoAtendidas.add(solt);
            }
        }
        
        return solicitudesNoAtendidas;
    }
    
    List<EmpresaTransporte> obtenerListaEmpresasTransportadoras(){
        return facade.getListaEmpresasTransportes();
    }
    
    List<Asignacion> obtenerAsignacionesEmpresa(String identificador) {
        List<Asignacion> asignaciones = facade.getListaAsignaciones();
        List<Asignacion> asignacionesEmpresa = new ArrayList<>();
        
        for (Asignacion asignacione : asignaciones) {
            if(asignacione.getRFCEmpresaT().equalsIgnoreCase(identificador) && !asignacione.isEstado()){
                asignacionesEmpresa.add(asignacione);
            }
        }
        return asignacionesEmpresa;
    }

    List<Vehiculo> obtenerVehiculosEmpresa(String identificador) {
        List<Vehiculo> vehiculos = facade.getListaVehiculos();
        List<Vehiculo> vehiculosEmp = new ArrayList<>();
        
        for (Vehiculo vehiculo : vehiculos) {
            if(vehiculo.getRFCEmpresaP().equalsIgnoreCase(identificador)){
                vehiculosEmp.add(vehiculo);
            }
        }
        
        return vehiculosEmp;
    }
}
