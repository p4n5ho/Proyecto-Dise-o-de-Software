/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.util.Objects;

/**
 *
 * @author Alejandro Galindo
 */
public class DetallesSolicitud {
    private int IDSolicitud;
    private String IDResiduo;
    private float cantidad;
    private String medida;
    
    public DetallesSolicitud(int IDSolicitud, String IDResiduo, float cantidad, String medida) {
        this.IDSolicitud = IDSolicitud;
        this.IDResiduo = IDResiduo;
        this.cantidad = cantidad;
        this.medida = medida;
    }

    public int getIDSolicitud() {
        return IDSolicitud;
    }

    public void setIDSolicitud(int IDSolicitud) {
        this.IDSolicitud = IDSolicitud;
    }

    public String getIDResiduo() {
        return IDResiduo;
    }

    public void setIDResiduo(String IDResiduo) {
        this.IDResiduo = IDResiduo;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.IDSolicitud;
        hash = 97 * hash + Objects.hashCode(this.IDResiduo);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.cantidad) ^ (Double.doubleToLongBits(this.cantidad) >>> 32));
        hash = 97 * hash + Objects.hashCode(this.medida);
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
        final DetallesSolicitud other = (DetallesSolicitud) obj;
        if (this.IDSolicitud != other.IDSolicitud) {
            return false;
        }
        if (Double.doubleToLongBits(this.cantidad) != Double.doubleToLongBits(other.cantidad)) {
            return false;
        }
        if (!Objects.equals(this.IDResiduo, other.IDResiduo)) {
            return false;
        }
        if (!Objects.equals(this.medida, other.medida)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DetallesSolicitud{" + "IDSolicitud=" + IDSolicitud + ", IDResiduo=" + IDResiduo + ", cantidad=" + cantidad + ", medida=" + medida + '}';
    }
}
