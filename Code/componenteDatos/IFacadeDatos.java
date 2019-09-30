/**
 * IFacadeDatos.java
 * Creado el 09 de Abril de 2019
 */
package Datos;

import Excepciones.ResiduosException;
import Dominio.*;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author Alejandro Galindo
 */
public interface IFacadeDatos {
    
    //Registrar Residuo
    void agregarResiduo(Residuo residuo) throws ResiduosException;
    Residuo obtenerResiduo(String identificador) throws ResiduosException;
    
    //Solicitar traslado
    void agregarSolicitudTraslado(Solicitud solicitud) throws ResiduosException;
    
    //Asignar traslado a empresas
    void asignarTraslado(Asignacion asignacion) throws ResiduosException;
    Solicitud obtenerSolicitud(String identificador) throws ResiduosException;
    
    //Registrar Traslado
    void registrarTraslado(Traslado traslado) throws ResiduosException;
    
    void agregarUsuario(Usuario usuario) throws ResiduosException;
    EmpresaProductora obtenerEmpresaProductora(String identificador) throws ResiduosException;
    EmpresaTransporte obtenerEmpresaTransporte(String identificador) throws ResiduosException;
    
    
    //Listas
    List<Residuo> getListaResiduos() throws ResiduosException;
    List<Traslado> getListaTraslados() throws ResiduosException;
    List<ElementoQuimico> getListaElementosQuimicos() throws ResiduosException;
    List<EmpresaProductora> getListaEmpresasProductoras() throws ResiduosException;
    List<EmpresaTransporte> getListaEmpresasTransportes() throws ResiduosException;
    List<Solicitud> getListaSolicitudes() throws ResiduosException;
    List<Asignacion> getListaAsignaciones() throws ResiduosException;
    List<Vehiculo> getListaVehiculos() throws ResiduosException;
     
    Usuario obtenerUsuario(String identificador) throws ResiduosException;
}