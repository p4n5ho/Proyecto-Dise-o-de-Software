/**
 * EmpresaProductora.java
 * Creado Domingo 07 de Abril de 2019 a las 5:43PM
 */
package Dominio;

import java.util.Objects;

/**
 * Clase que representa a una empresa productora
 * @author Equipo 1
 */
public class EmpresaProductora extends Usuario{
    private String RFC;
    private String nombreProductora;

    public EmpresaProductora(String codigoAcceso, String password, int tipo, String nombre) {
        super(codigoAcceso, password, tipo);
        this.RFC = codigoAcceso;
        this.nombreProductora = nombre;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public String getNombreProductora() {
        return nombreProductora;
    }

    public void setNombreProductora(String nombreProductora) {
        this.nombreProductora = nombreProductora;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.RFC);
        hash = 29 * hash + Objects.hashCode(this.nombreProductora);
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
        final EmpresaProductora other = (EmpresaProductora) obj;
        if (!Objects.equals(this.RFC, other.RFC)) {
            return false;
        }
        if (!Objects.equals(this.nombreProductora, other.nombreProductora)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "' " + RFC + "-" + nombreProductora + " ' ";
    }
}