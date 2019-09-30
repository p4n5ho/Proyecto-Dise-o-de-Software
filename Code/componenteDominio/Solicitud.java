/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Alejandro Galindo
 */
public class Solicitud {
    private int IDSolicitud;
    private String fecha;
    private String RFCEmpresa;
    private boolean estado;
    private List<DetallesSolicitud> detalles;

    public Solicitud(int IDSolicitud, String fecha, String empresa, boolean estado) {
        this.IDSolicitud = IDSolicitud;
        this.fecha = fecha;
        this.RFCEmpresa = empresa;
        this.estado = estado;
    }

    public int getIDSolicitud() {
        return IDSolicitud;
    }

    public void setIDSolicitud(int IDSolicitud) {
        this.IDSolicitud = IDSolicitud;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getRFCEmpresa() {
        return RFCEmpresa;
    }

    public void setRFCEmpresa(String RFCEmpresa) {
        this.RFCEmpresa = RFCEmpresa;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public List<DetallesSolicitud> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallesSolicitud> detalles) {
        this.detalles = detalles;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.IDSolicitud;
        hash = 59 * hash + Objects.hashCode(this.fecha);
        hash = 59 * hash + Objects.hashCode(this.RFCEmpresa);
        hash = 59 * hash + (this.estado ? 1 : 0);
        hash = 59 * hash + Objects.hashCode(this.detalles);
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
        final Solicitud other = (Solicitud) obj;
        if (this.IDSolicitud != other.IDSolicitud) {
            return false;
        }
        if (this.estado != other.estado) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.RFCEmpresa, other.RFCEmpresa)) {
            return false;
        }
        if (!Objects.equals(this.detalles, other.detalles)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Solicitud{" + "IDSolicitud=" + IDSolicitud + ", fecha=" + fecha + ", RFCEmpresa=" + RFCEmpresa + ", estado=" + estado + ", detalles=" + detalles + '}';
    }
}