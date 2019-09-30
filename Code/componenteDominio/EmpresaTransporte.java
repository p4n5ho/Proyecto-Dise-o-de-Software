/**
 * EmpresaTransporte.java
 * Creado Domingo 07 de Abril de 2019 a las 6:00PM
 */
package Dominio;

import java.util.Objects;

/**
 * Clase que representa a una empresa de transporte
 * @author Equipo 1
 */
public class EmpresaTransporte extends Usuario {
    private String RFC;
    private String nombreTransporte;

    public EmpresaTransporte(String codigoAcceso, String password, int tipo, String nombre) {
        super(codigoAcceso, password, tipo);
        this.nombreTransporte = nombre;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public String getNombreTransporte() {
        return nombreTransporte;
    }

    public void setNombreTransporte(String nombreTransporte) {
        this.nombreTransporte = nombreTransporte;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.RFC);
        hash = 97 * hash + Objects.hashCode(this.nombreTransporte);
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
        final EmpresaTransporte other = (EmpresaTransporte) obj;
        if (!Objects.equals(this.RFC, other.RFC)) {
            return false;
        }
        if (!Objects.equals(this.nombreTransporte, other.nombreTransporte)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EmpresaTransporte{" + "RFC=" + RFC + ", nombreTransporte=" + nombreTransporte + '}';
    }
}