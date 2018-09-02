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
public class MensajesPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idUsuarioRecibe")
    private int idUsuarioRecibe;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idUsuarioEnvia")
    private int idUsuarioEnvia;

    public MensajesPK() {
    }

    public MensajesPK(int idUsuarioRecibe, int idUsuarioEnvia) {
        this.idUsuarioRecibe = idUsuarioRecibe;
        this.idUsuarioEnvia = idUsuarioEnvia;
    }

    public int getIdUsuarioRecibe() {
        return idUsuarioRecibe;
    }

    public void setIdUsuarioRecibe(int idUsuarioRecibe) {
        this.idUsuarioRecibe = idUsuarioRecibe;
    }

    public int getIdUsuarioEnvia() {
        return idUsuarioEnvia;
    }

    public void setIdUsuarioEnvia(int idUsuarioEnvia) {
        this.idUsuarioEnvia = idUsuarioEnvia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUsuarioRecibe;
        hash += (int) idUsuarioEnvia;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MensajesPK)) {
            return false;
        }
        MensajesPK other = (MensajesPK) object;
        if (this.idUsuarioRecibe != other.idUsuarioRecibe) {
            return false;
        }
        if (this.idUsuarioEnvia != other.idUsuarioEnvia) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.allyrent.entidades.MensajesPK[ idUsuarioRecibe=" + idUsuarioRecibe + ", idUsuarioEnvia=" + idUsuarioEnvia + " ]";
    }
    
}
