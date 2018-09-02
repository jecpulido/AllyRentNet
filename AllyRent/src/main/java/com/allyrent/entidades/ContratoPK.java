/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allyrent.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jorge
 */
@Embeddable
public class ContratoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idPropietario")
    private int idPropietario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idOcupante")
    private int idOcupante;

    public ContratoPK() {
    }

    public ContratoPK(int idPropietario, int idOcupante) {
        this.idPropietario = idPropietario;
        this.idOcupante = idOcupante;
    }

    public int getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(int idPropietario) {
        this.idPropietario = idPropietario;
    }

    public int getIdOcupante() {
        return idOcupante;
    }

    public void setIdOcupante(int idOcupante) {
        this.idOcupante = idOcupante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPropietario;
        hash += (int) idOcupante;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContratoPK)) {
            return false;
        }
        ContratoPK other = (ContratoPK) object;
        if (this.idPropietario != other.idPropietario) {
            return false;
        }
        if (this.idOcupante != other.idOcupante) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.allyrent.entidades.ContratoPK[ idPropietario=" + idPropietario + ", idOcupante=" + idOcupante + " ]";
    }
    
}
