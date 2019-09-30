/**
 * Traslado.java
 * Creado Domingo 07 de Abril de 2019 a las 7:55PM
 */
package Dominio;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

/**
 * Clase que representa a un Traslado
 * @author Equipo 1
 */
public class Traslado {
    private int IDTraslado; 
    private int IDAsignacion;
    private String fechaTermino;
    private String tratamiento;
    private String destino = "Planta de Tratamiento";
    private List<Vehiculo> vehiculos;
    private float costo;
    private String IDEmpresaT;
    private float kilometros;

    public Traslado(int IDTraslado, int IDAsignacion, String fechaTermino, String tratamiento, List<Vehiculo> vehiculos, float costo, String EmpresaT, float kilometros) {
        this.IDTraslado = IDTraslado;
        this.IDAsignacion = IDAsignacion;
        this.fechaTermino = fechaTermino;
        this.tratamiento = tratamiento;
        this.vehiculos = vehiculos;
        this.costo = costo;
        this.IDEmpresaT = EmpresaT;
        this.kilometros = kilometros;
    }

    public int getIDTraslado() {
        return IDTraslado;
    }

    public void setIDTraslado(int IDTraslado) {
        this.IDTraslado = IDTraslado;
    }

    public int getIDAsignacion() {
        return IDAsignacion;
    }

    public void setIDAsignacion(int IDAsignacion) {
        this.IDAsignacion = IDAsignacion;
    }

    public String getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(String fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public String getIDEmpresaT() {
        return IDEmpresaT;
    }

    public void setIDEmpresaT(String IDEmpresaT) {
        this.IDEmpresaT = IDEmpresaT;
    }

    public float getKilometros() {
        return kilometros;
    }

    public void setKilometros(float kilometros) {
        this.kilometros = kilometros;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.IDTraslado;
        hash = 11 * hash + this.IDAsignacion;
        hash = 11 * hash + Objects.hashCode(this.fechaTermino);
        hash = 11 * hash + Objects.hashCode(this.tratamiento);
        hash = 11 * hash + Objects.hashCode(this.destino);
        hash = 11 * hash + (int) (Double.doubleToLongBits(this.costo) ^ (Double.doubleToLongBits(this.costo) >>> 32));
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
        final Traslado other = (Traslado) obj;
        if (this.IDTraslado != other.IDTraslado) {
            return false;
        }
        if (this.IDAsignacion != other.IDAsignacion) {
            return false;
        }
        if (Double.doubleToLongBits(this.costo) != Double.doubleToLongBits(other.costo)) {
            return false;
        }
        if (!Objects.equals(this.tratamiento, other.tratamiento)) {
            return false;
        }
        if (!Objects.equals(this.destino, other.destino)) {
            return false;
        }
        if (!Objects.equals(this.fechaTermino, other.fechaTermino)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Traslado{" + "IDTraslado=" + IDTraslado + ", IDAsignacion=" + IDAsignacion + ", fechaTermino=" + fechaTermino + ", tratamiento=" + tratamiento + ", destino=" + destino + ", vehiculos=" + vehiculos + ", costo=" + costo + '}';
    }
}