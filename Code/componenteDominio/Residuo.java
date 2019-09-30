/**
 * Residuo.java
 * Creado Domingo 07 de Abril de 2019 a las 3:34PM
 */
package Dominio;

import Excepciones.ResiduosException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Clase que representa a un Residuo
 *
 * @author Equipo 1
 */
public class Residuo {
    private String IDResiduo;
    private String nombreResiduo;
    private ArrayList<ElementoQuimico> elementosQuimicos;
    private ArrayList<EmpresaProductora> empresasProductoras;
    
    public Residuo(String codigoResiduo, String nombreResiduo, ArrayList<ElementoQuimico> elementosQuimicos) throws ResiduosException {
        this.IDResiduo = codigoResiduo;
        if(!this.IDResiduo.matches("^\\d{6}$")){
            throw new ResiduosException("El codigo del residuo no es númerico de 6 posiciones");
        }
        this.nombreResiduo = nombreResiduo;
        this.elementosQuimicos = elementosQuimicos;
        this.empresasProductoras = new ArrayList<>();
    }

    public Residuo(String codigoResiduo, String nombreResiduo) {
        this.IDResiduo = codigoResiduo;
        this.nombreResiduo = nombreResiduo;
    }

    public String getIDResiduo() {
        return IDResiduo;
    }

    public void setIDResiduo(String IDResiduo) {
        this.IDResiduo = IDResiduo;
    }

    public String getNombreResiduo() {
        return nombreResiduo;
    }

    public void setNombreResiduo(String nombreResiduo) {
        this.nombreResiduo = nombreResiduo;
    }

    public ArrayList<ElementoQuimico> getElementosQuimicos() {
        return elementosQuimicos;
    }

    public void setElementosQuimicos(ArrayList<ElementoQuimico> elementosQuimicos) {
        this.elementosQuimicos = elementosQuimicos;
    }

    public ArrayList<EmpresaProductora> getEmpresasProductoras() {
        return empresasProductoras;
    }

    public void setEmpresasProductoras(ArrayList<EmpresaProductora> empresasProductoras) {
        this.empresasProductoras = empresasProductoras;
    }
           
    public boolean addEmpresaProductora(EmpresaProductora empresa){
        if(this.empresasProductoras.contains(empresa)){
            return false;
        }else{
            this.empresasProductoras.add(empresa);
            return true;
        }
    }
    
    public void remEmpresaProductora(EmpresaProductora empresa){
        this.empresasProductoras.remove(empresa);
    }
    
    public boolean isEmpresaProductoraIn(EmpresaProductora empresa){
        return empresasProductoras.contains(empresa);
    }
    
    public boolean mismosElementosQuimicos(Object obj){
        final Residuo other = (Residuo) obj;

        if (this.getElementosQuimicos().size() == other.getElementosQuimicos().size()) {
            for (int i = 0; i < other.getElementosQuimicos().size(); i++) {
                if (!other.getElementosQuimicos().contains(this.getElementosQuimicos().get(i))) {
                    return false;
                }
            }
        }else{
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.IDResiduo);
        hash = 97 * hash + Objects.hashCode(this.nombreResiduo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Residuo other = (Residuo) obj;

        if (Objects.equals(this.IDResiduo, other.IDResiduo) || Objects.equals(this.nombreResiduo, other.nombreResiduo)) {
            return true;
        }

        if (this.getElementosQuimicos().size() == other.getElementosQuimicos().size()) {
            for (int i = 0; i < other.getElementosQuimicos().size(); i++) {
                if (!other.getElementosQuimicos().contains(this.getElementosQuimicos().get(i))) {
                    return false;
                }
            }
        }else{
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.IDResiduo + " - " + this.nombreResiduo + " - " + this.elementosQuimicos.toString();
    }
}