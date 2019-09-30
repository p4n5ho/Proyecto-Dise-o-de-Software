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
public class Asignacion {
    private int IDAsignacion;
    private int IDSolicitud;
    private String RFCEmpresaT;
    private String IDResiduo;
    private float cantidadReparto;
    private String medida;
    private boolean estado;

    public Asignacion(int IDAsignacion, int IDSolicitud, String RFCEmpresaT, String IDResiduo, float cantidadReparto, String medida, Boolean estado) {
        this.IDAsignacion = IDAsignacion;
        this.IDSolicitud = IDSolicitud;
        this.RFCEmpresaT = RFCEmpresaT;
        this.IDResiduo = IDResiduo;
        this.cantidadReparto = cantidadReparto;
        this.medida = medida;
        this.estado = estado;
    }

    public int getIDAsignacion() {
        return IDAsignacion;
    }

    public void setIDAsignacion(int IDAsignacion) {
        this.IDAsignacion = IDAsignacion;
    }

    public int getIDSolicitud() {
        return IDSolicitud;
    }

    public void setIDSolicitud(int IDSolicitud) {
        this.IDSolicitud = IDSolicitud;
    }

    public String getRFCEmpresaT() {
        return RFCEmpresaT;
    }

    public void setRFCEmpresaT(String RFCEmpresaT) {
        this.RFCEmpresaT = RFCEmpresaT;
    }

    public String getIDResiduo() {
        return IDResiduo;
    }

    public void setIDResiduo(String IDResiduo) {
        this.IDResiduo = IDResiduo;
    }

    public float getCantidadReparto() {
        return cantidadReparto;
    }

    public void setCantidadReparto(float cantidadReparto) {
        this.cantidadReparto = cantidadReparto;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.IDAsignacion;
        hash = 79 * hash + this.IDSolicitud;
        hash = 79 * hash + Objects.hashCode(this.RFCEmpresaT);
        hash = 79 * hash + Objects.hashCode(this.IDResiduo);
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.cantidadReparto) ^ (Double.doubleToLongBits(this.cantidadReparto) >>> 32));
        hash = 79 * hash + Objects.hashCode(this.medida);
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
        final Asignacion other = (Asignacion) obj;
        if (this.IDAsignacion != other.IDAsignacion) {
            return false;
        }
        if (this.IDSolicitud != other.IDSolicitud) {
            return false;
        }
        if (Double.doubleToLongBits(this.cantidadReparto) != Double.doubleToLongBits(other.cantidadReparto)) {
            return false;
        }
        if (!Objects.equals(this.RFCEmpresaT, other.RFCEmpresaT)) {
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
        return "Asignacion{" + "IDAsignacion=" + IDAsignacion + ", IDSolicitud=" + IDSolicitud + ", RFCEmpresaT=" + RFCEmpresaT + ", IDResiduo=" + IDResiduo + ", cantidadReparto=" + cantidadReparto + ", medida=" + medida + '}';
    }
}