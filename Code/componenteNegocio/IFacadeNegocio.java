/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Dominio.*;
import Excepciones.ResiduosException;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author Alejandro Galindo
 */
public interface IFacadeNegocio {
    
    //Login
    boolean verificarSesion(String usuario, String password, int tipo);
    boolean registrarUsuario(String RFC, String passsword, int tipo, String nombre);
    Usuario obtenerUsuarioLogueado();
    
    //Agregar residuos
    void agregarResiduo(String codigo, String nombre, List<ElementoQuimico> elementos, EmpresaProductora empresa) throws ResiduosException;
    
    //Solicitud Traslado
    void agregarSolicitudTraslado(String fecha, String IDEmpresa, List<DetallesSolicitud> detalles) throws ResiduosException;
    
    //Asignacion Traslado
    EmpresaProductora obtenerEmpresaProductora(String identificador) throws ResiduosException;
    Residuo obtenerResiduo(String identificador) throws ResiduosException;
    Solicitud obtenerSolicitud(String identificador) throws ResiduosException;
    void agregarAsignacion(int solicitud, String RFC, String IDResiduo, float cantidadReparto, String medida) throws ResiduosException;
    
    //RegistroTraslado
    EmpresaTransporte obtenerEmpresaTransportadora(String identificador) throws ResiduosException;
    void registrarTraslado(int IDAsignacion, String fechaTermino, String tratamiento, List<Vehiculo> vehiculos, float costo, String EmpresaT, float kilometros) throws ResiduosException;
    
    
    //Listas
    List<Residuo> obtenerListaResiduos() throws ResiduosException;
    List<Residuo> obtenerListaResiduosEmpresa(EmpresaProductora empresa) throws ResiduosException;
    List<ElementoQuimico> obtenerListaElementosQuimicos() throws ResiduosException;
    List<Traslado> obtenerListaTraslados() throws ResiduosException;
    List<Solicitud> obtenerListaSolicitudesNoAtendidas() throws ResiduosException;
    List<EmpresaTransporte> obtenerListaEmpresasTransporte() throws ResiduosException;
    List<Asignacion> obtenerAsignacionesEmpresa(String identificador) throws ResiduosException;
    List<Vehiculo> obtenerVehiculosEmpresa(String identificador) throws ResiduosException;
}
