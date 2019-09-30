/**
 * Vehiculo.java
 * Creado Domingo 07 de Abril de 2019 a las 7:51PM
 */
package Dominio;

import java.util.Objects;

/**
 * Clase que representa a un Vehiculo
 * @author Equipo 1
 */
public class Vehiculo {
    private String matricula;
    private String tipoVehiculo;
    private String RFCEmpresaP;

    public Vehiculo(String matricula, String tipoVehiculo, String RFCEmpresaP) {
        this.matricula = matricula;
        this.tipoVehiculo = tipoVehiculo;
        this.RFCEmpresaP = RFCEmpresaP;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getRFCEmpresaP() {
        return RFCEmpresaP;
    }

    public void setRFCEmpresaP(String RFCEmpresaP) {
        this.RFCEmpresaP = RFCEmpresaP;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.matricula);
        hash = 47 * hash + Objects.hashCode(this.tipoVehiculo);
        hash = 47 * hash + Objects.hashCode(this.RFCEmpresaP);
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
        final Vehiculo other = (Vehiculo) obj;
        if (!Objects.equals(this.matricula, other.matricula)) {
            return false;
        }
        if (!Objects.equals(this.tipoVehiculo, other.tipoVehiculo)) {
            return false;
        }
        if (!Objects.equals(this.RFCEmpresaP, other.RFCEmpresaP)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "matricula=" + matricula + ", tipoVehiculo=" + tipoVehiculo + ", RFCEmpresaP=" + RFCEmpresaP + '}';
    }
}