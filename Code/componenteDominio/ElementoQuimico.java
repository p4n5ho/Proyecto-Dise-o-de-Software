/**
 * ElementoQuimico.java
 * Creado Domingo 07 de Abril de 2019 a las 4:00PM
 */
package Dominio;

import java.util.Objects;

/**
 * Clase que representa a un elemento Quimico
 * @author Equipo 1
 */
public class ElementoQuimico {
    private int IDElemento;
    private String nombreElemento;
    private String simbolo;
    private int nAtomico;

    public ElementoQuimico(int codigoEQ, String nombreElemento, String simbolo, int nAtomico) {
        this.IDElemento = codigoEQ;
        this.nombreElemento = nombreElemento;
        this.simbolo = simbolo;
        this.nAtomico = nAtomico;
    }

    public ElementoQuimico(String nombreElemento) {
        this.nombreElemento = nombreElemento;
    }
        
    public int getIDElemento() {
        return IDElemento;
    }

    public void setIDElemento(int IDElemento) {
        this.IDElemento = IDElemento;
    }

    public String getNombreElemento() {
        return nombreElemento;
    }

    public void setNombreElemento(String nombreElemento) {
        this.nombreElemento = nombreElemento;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public int getnAtomico() {
        return nAtomico;
    }

    public void setnAtomico(int nAtomico) {
        this.nAtomico = nAtomico;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.nombreElemento);
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
        final ElementoQuimico other = (ElementoQuimico) obj;
        if (!Objects.equals(this.nombreElemento, other.nombreElemento)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nombreElemento;
    }
}