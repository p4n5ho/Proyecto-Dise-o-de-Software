package Negocio;

import Dominio.Solicitud;
import Excepciones.ResiduosException;
import Datos.FabricaDatos;
import Datos.IFacadeDatos;
import Dominio.Asignacion;
import Dominio.DetallesSolicitud;
import Dominio.Residuo;
import Dominio.Traslado;
import Dominio.Vehiculo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alejandro Galindo
 */
class controlTraslados {

    private IFacadeDatos facade = FabricaDatos.getFachada();

    controlTraslados() {}

    void registrarSolicitudTraslado(String fecha, String IDEmpresa, List<DetallesSolicitud> detalles) throws ResiduosException {
        String mensajeFinal = "";
        Solicitud solicitud = new Solicitud(0, fecha, IDEmpresa, true);
        solicitud.setDetalles(detalles);
        
        List<Solicitud> solicitudes = facade.getListaSolicitudes();
        List<Solicitud> solicitudesPorFecha = new ArrayList<>();
        List<Residuo> residuosEncontrados = new ArrayList<>();
        List<Residuo> residuosPropios = new ArrayList<>();
        
        for (Solicitud solicitude : solicitudes) {
            if(solicitude.getFecha().equals(fecha)){
                solicitudesPorFecha.add(solicitude);
            }
        }
        
        for (Solicitud solicitude : solicitudes) {
            if(IDEmpresa.equalsIgnoreCase(solicitude.getRFCEmpresa()) && fecha.equals(solicitude.getFecha())){
                List<DetallesSolicitud> detallesSolt = solicitude.getDetalles();
                for (DetallesSolicitud detallesSolicitud : detallesSolt) {
                    residuosEncontrados.add(facade.obtenerResiduo(detallesSolicitud.getIDResiduo()));
                }
            }    
        }
        
        for (DetallesSolicitud detallesRegistro : detalles) {
            residuosPropios.add(facade.obtenerResiduo(detallesRegistro.getIDResiduo()));
        }
        
        if(residuosPropios.size() == residuosEncontrados.size()){
            for (Residuo residuosEn : residuosEncontrados) {
                if(!residuosPropios.contains(residuosEn)){
                    break;
                }
            }
            mensajeFinal += "No se aceptan solicitudes repetidas.\n";
        }
        
        if(solicitudesPorFecha.size() >= 5){
            mensajeFinal += "Ya hay mas de 5 traslados para esa fecha. Intente asignar una fecha distinta.\n";
        }
        
        
        
        if(mensajeFinal.equalsIgnoreCase("")){
            facade.agregarSolicitudTraslado(solicitud);
        }else{
            throw new ResiduosException(mensajeFinal);
        }
    }
    
    void agregarAsignacion(int solicitud, String RFC, String IDResiduo, float cantidadReparto, String medida){
        Asignacion asig = new Asignacion(0, solicitud, RFC, IDResiduo, cantidadReparto, medida, false);
        try {
            facade.asignarTraslado(asig);
        } catch (ResiduosException residuosException) {
            throw residuosException;
        }
    }
    
    Solicitud obtenerSolicitud(String identificador){
        return facade.obtenerSolicitud(identificador);
    }

    void registrarTraslado(int IDAsignacion, String fechaTermino, String tratamiento, List<Vehiculo> vehiculos, float costo, String EmpresaT, float kilometros) {
        try {
            Traslado traslado = new Traslado(0, IDAsignacion, fechaTermino, tratamiento, vehiculos, costo, EmpresaT, kilometros);
            facade.registrarTraslado(traslado);
        } catch (ResiduosException residuosException) {
            throw residuosException;
        }
    }
}
