/**
 * facadeDatos.java
 * Creado el 09 de abril de 2019
 */
package Datos;

import Dominio.*;
import Excepciones.ResiduosException;
import java.util.List;

/**
 *
 * @author Alejandro Galindo
 */
class facadeDatos implements IFacadeDatos {

    private DAOResiduos<Usuario> accesoUs = new UsuarioDAO<>();
    private DAOResiduos<Residuo> accesoRes = new ResiduoDAO<>();
    private DAOResiduos<Solicitud> accesoSolt = new SolicitudDAO<>();
    private DAOResiduos<EmpresaProductora> accesoEP = new EmpresaProductoraDAO<>();
    private DAOResiduos<EmpresaTransporte> accesoET = new EmpresaTransporteDAO<>();   
    private DAOResiduos<ElementoQuimico> accesoEQ = new ElementoQuimicoDAO<>();
    private DAOResiduos<Asignacion> accesoAsg = new AsignacionDAO<>();
    private DAOResiduos<Vehiculo> accesoVh = new VehiculoDAO<>();
    private DAOResiduos<Traslado> accesoTras = new TrasladoDAO<>();
    
    public void facadeDatos() {}

    @Override
    public void agregarResiduo(Residuo residuo) throws ResiduosException {
        Residuo residuoBuscado = accesoRes.obtener(residuo.getIDResiduo());
        if (residuoBuscado != null) {
            throw new ResiduosException("Residuo repetido");
        }
        accesoRes.insertar(residuo);
    }

    @Override
    public Residuo obtenerResiduo(String identificador) throws ResiduosException {
        try {
            Residuo residuoObtenido;
            residuoObtenido = accesoRes.obtener(identificador);

            return residuoObtenido;
        } catch (ResiduosException exception) {
            throw new ResiduosException("No se pudo obtener el residuo", exception);
        }
    }
    
    @Override
    public void agregarSolicitudTraslado(Solicitud solicitud) throws ResiduosException {
        try{
            accesoSolt.insertar(solicitud);
        }catch(ResiduosException exception){
            throw exception;
        }
    }

    @Override
    public EmpresaProductora obtenerEmpresaProductora(String identificador) throws ResiduosException {
        return accesoEP.obtener(identificador);
    }

    @Override
    public EmpresaTransporte obtenerEmpresaTransporte(String identificador) throws ResiduosException {
        return accesoET.obtener(identificador);
    }

    @Override
    public List<Residuo> getListaResiduos() throws ResiduosException {
        List<Residuo> obtenidos = accesoRes.obtenerTodos();
        return obtenidos;
    }

    @Override
    public List<Traslado> getListaTraslados() throws ResiduosException {
        return null;
    }

    @Override
    public List<ElementoQuimico> getListaElementosQuimicos() throws ResiduosException {
        return accesoEQ.obtenerTodos();

    }

    @Override
    public List<EmpresaProductora> getListaEmpresasProductoras() throws ResiduosException {
        return accesoEP.obtenerTodos();
    }

    @Override
    public List<EmpresaTransporte> getListaEmpresasTransportes() throws ResiduosException {
        return accesoET.obtenerTodos();
    }

    @Override
    public Usuario obtenerUsuario(String identificador) throws ResiduosException {
        return accesoUs.obtener(identificador);
    }

    @Override
    public void agregarUsuario(Usuario usuario) throws ResiduosException {
        accesoUs.insertar(usuario);
    }

    @Override
    public List<Solicitud> getListaSolicitudes() throws ResiduosException {
        return accesoSolt.obtenerTodos();
    }

    @Override
    public void asignarTraslado(Asignacion asignacion) throws ResiduosException {
        accesoAsg.insertar(asignacion);
        accesoSolt.actualizar(accesoSolt.obtener(String.valueOf(asignacion.getIDSolicitud())));
    }

    @Override
    public Solicitud obtenerSolicitud(String identificador) throws ResiduosException {
        return accesoSolt.obtener(identificador);
    }

    @Override
    public List<Asignacion> getListaAsignaciones() throws ResiduosException {
        return accesoAsg.obtenerTodos();
    }

    @Override
    public List<Vehiculo> getListaVehiculos() throws ResiduosException {
        return accesoVh.obtenerTodos();
    }

    @Override
    public void registrarTraslado(Traslado traslado) throws ResiduosException {
        accesoTras.insertar(traslado);
    }
}