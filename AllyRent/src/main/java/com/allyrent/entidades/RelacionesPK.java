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
public class RelacionesPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idUsuario1")
    private int idUsuarioOcupante;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idUsuario2")
    private int idUsuarioPropietario;

    public RelacionesPK() {
    }

    public RelacionesPK(int idUsuarioOcupante, int idUsuarioPropietario) {
        this.idUsuarioOcupante = idUsuarioOcupante;
        this.idUsuarioPropietario = idUsuarioPropietario;
    }

    public int getIdUsuarioOcupante() {
        return idUsuarioOcupante;
    }

    public void setIdUsuarioOcupante(int idUsuarioOcupante) {
        this.idUsuarioOcupante = idUsuarioOcupante;
    }

    public int getIdUsuarioPropietario() {
        return idUsuarioPropietario;
    }

    public void setIdUsuarioPropietario(int idUsuarioPropietario) {
        this.idUsuarioPropietario = idUsuarioPropietario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUsuarioOcupante;
        hash += (int) idUsuarioPropietario;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RelacionesPK)) {
            return false;
        }
        RelacionesPK other = (RelacionesPK) object;
        if (this.idUsuarioOcupante != other.idUsuarioOcupante) {
            return false;
        }
        if (this.idUsuarioPropietario != other.idUsuarioPropietario) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.allyrent.entidades.RelacionesPK[ idUsuarioOcupante=" + idUsuarioOcupante + ", idUsuarioPropietario=" + idUsuarioPropietario + " ]";
    }
    
}
