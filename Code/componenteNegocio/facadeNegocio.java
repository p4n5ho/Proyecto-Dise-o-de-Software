/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Dominio.Asignacion;
import Dominio.DetallesSolicitud;
import Dominio.ElementoQuimico;
import Dominio.EmpresaProductora;
import Dominio.EmpresaTransporte;
import Dominio.Residuo;
import Dominio.Solicitud;
import Dominio.Traslado;
import Dominio.Usuario;
import Dominio.Vehiculo;
import Excepciones.ResiduosException;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author Alejandro Galindo
 */
class facadeNegocio implements IFacadeNegocio {
    
    private controlResiduos controlResiduos = new controlResiduos();
    private controlTraslados controlTraslados = new controlTraslados();
    private controlUsuarios controlUsuarios = new controlUsuarios();
    private controlListas controlListas = new controlListas();

    @Override
    public boolean verificarSesion(String usuario, String password, int tipo) {
        return controlUsuarios.verificarSesion(usuario, password, tipo);
    }
    
    @Override
    public boolean registrarUsuario(String codigo, String password, int tipo, String nombre){
        return controlUsuarios.registrarUsuario(codigo, password, tipo, nombre);
    }
    
    @Override
    public Usuario obtenerUsuarioLogueado(){
        return controlUsuarios.getUsuario();
    }
    
    @Override
    public void agregarResiduo(String codigo, String nombre, List<ElementoQuimico> elementos, EmpresaProductora empresa) throws ResiduosException {
        try {
            controlResiduos.agregarResiduo(codigo, nombre, elementos, empresa);
        } catch (ResiduosException residuosException) {
            throw residuosException;
        }
    }
    
    @Override
    public void agregarSolicitudTraslado(String fecha, String IDEmpresa, List<DetallesSolicitud> detalles) throws ResiduosException {
        try{
            controlTraslados.registrarSolicitudTraslado(fecha, IDEmpresa, detalles);
        } catch (ResiduosException residuosException){
            throw residuosException;
        }
    }

    @Override
    public List<Residuo> obtenerListaResiduos() throws ResiduosException {
        return controlListas.obtenerListaResiduos();
    }
    
    @Override
    public List<Residuo> obtenerListaResiduosEmpresa(EmpresaProductora empresa) throws ResiduosException{
        return controlListas.obtenerListaResiduosDeEmpresa(empresa);
    }

    @Override
    public List<ElementoQuimico> obtenerListaElementosQuimicos() throws ResiduosException {
        return controlListas.obtenerListaElementosQuimicos();
    }

    @Override
    public List<Traslado> obtenerListaTraslados() throws ResiduosException {
        return controlListas.obtenerListaTraslados();
    }

    @Override
    public List<Solicitud> obtenerListaSolicitudesNoAtendidas() throws ResiduosException {
        return controlListas.obtenerListasSolicitudesNoAtendidas();
    }

    @Override
    public EmpresaProductora obtenerEmpresaProductora(String identificador) throws ResiduosException {
        return controlUsuarios.obtenerEmpresaProductora(identificador);
    }

    @Override
    public Residuo obtenerResiduo(String identificador) throws ResiduosException {
        return controlResiduos.obtenerResiduo(identificador);
    }

    @Override
    public List<EmpresaTransporte> obtenerListaEmpresasTransporte() throws ResiduosException {
        return controlListas.obtenerListaEmpresasTransportadoras();
    }

    @Override
    public Solicitud obtenerSolicitud(String identificador) throws ResiduosException {
        return controlTraslados.obtenerSolicitud(identificador);
    }

    @Override
    public void agregarAsignacion(int solicitud, String RFC, String IDResiduo, float cantidadReparto, String medida) throws ResiduosException {
        controlTraslados.agregarAsignacion(solicitud, RFC, IDResiduo, cantidadReparto, medida);
    }

    @Override
    public List<Asignacion> obtenerAsignacionesEmpresa(String identificador) throws ResiduosException {
        return controlListas.obtenerAsignacionesEmpresa(identificador);
    }

    @Override
    public EmpresaTransporte obtenerEmpresaTransportadora(String identificador) {
        return controlUsuarios.obtenerEmpresaTransportadora(identificador);
    }

    @Override
    public List<Vehiculo> obtenerVehiculosEmpresa(String identificador) throws ResiduosException {
        return controlListas.obtenerVehiculosEmpresa(identificador);
    }

    @Override
    public void registrarTraslado(int IDAsignacion, String fechaTermino, String tratamiento, List<Vehiculo> vehiculos, float costo, String EmpresaT, float kilometros) throws ResiduosException {
        controlTraslados.registrarTraslado(IDAsignacion, fechaTermino, tratamiento, vehiculos, costo, EmpresaT, kilometros);
    }
}