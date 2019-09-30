package Dominio;

import java.util.Objects;

/**
 *
 * @author Equipo Dinamita
 */
public class Usuario {
    private String codigoAcceso;
    private String password;
    private int tipo;
    private String nombre;

    public Usuario(String codigoAcceso, String password, int tipo) {
        this.codigoAcceso = codigoAcceso;
        this.password = password;
        this.tipo = tipo;
    }

    public String getCodigoAcceso() {
        return codigoAcceso;
    }

    public void setCodigoAcceso(String codigoAcceso) {
        this.codigoAcceso = codigoAcceso;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.codigoAcceso);
        hash = 67 * hash + Objects.hashCode(this.password);
        hash = 67 * hash + this.tipo;
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
        final Usuario other = (Usuario) obj;
        if (this.tipo != other.tipo) {
            return false;
        }
        if (!Objects.equals(this.codigoAcceso, other.codigoAcceso)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "codigoAcceso=" + codigoAcceso + ", password=" + password + ", tipo=" + tipo + '}';
    }
}