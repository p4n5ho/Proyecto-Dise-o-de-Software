/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Dominio.ElementoQuimico;
import Dominio.EmpresaProductora;
import Dominio.Residuo;
import Excepciones.ResiduosException;
import Datos.FabricaDatos;
import Datos.IFacadeDatos;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alejandro Galindo
 */
class controlResiduos {

    private IFacadeDatos facade = FabricaDatos.getFachada();

    controlResiduos() {
    }

    void agregarResiduo(String codigo, String nombre, List<ElementoQuimico> elementos, EmpresaProductora empresaP) throws ResiduosException {
        try {
            Residuo residuoAgregar = new Residuo(codigo, nombre, (ArrayList<ElementoQuimico>) elementos);
            List<Residuo> residuosBD = facade.getListaResiduos();
            String mensajeFinal = "";

            for (Residuo residuo : residuosBD) {
                if (residuo.getIDResiduo().equals(codigo)) {
                    mensajeFinal += "El codigo de residuo '" + codigo + "' ya existe en el catalogo de residuos. '" + residuo.getNombreResiduo() + "' es el residuo encontrado en el catalogo.\n";
                    break;
                }
            }

            for (Residuo residuo : residuosBD) {
                if (residuo.getNombreResiduo().equalsIgnoreCase(nombre)) {
                    mensajeFinal += "El nombre de residuo '" + nombre + "' ya existe en el catalogo de residuos. '" + residuo.getIDResiduo() + "' es el ID del Residuo encontrado en el catalogo.\n";
                    break;
                }
            }

            for (Residuo residuo : residuosBD) {
                if (residuo.mismosElementosQuimicos(residuoAgregar)) {
                    mensajeFinal += "Existe un residuo con la misma composición de elementos en el catalogo de residuos. '" + residuo.getNombreResiduo() + "' es el residuo encontrado en el catalogo.\n";
                    break;
                }
            }

            if (mensajeFinal.equalsIgnoreCase("")) {
                residuoAgregar.addEmpresaProductora(empresaP);
                facade.agregarResiduo(residuoAgregar);
            } else {
                throw new ResiduosException(mensajeFinal);
            }
        } catch (ResiduosException residuosException) {
            throw residuosException;
        }
    }
    
    Residuo obtenerResiduo(String identificador){
        return facade.obtenerResiduo(identificador);
    }
}
