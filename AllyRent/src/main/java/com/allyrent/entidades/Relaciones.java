/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allyrent.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "relaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Relaciones.findAll", query = "SELECT r FROM Relaciones r")
    , @NamedQuery(name = "Relaciones.findByIdUsuarioOcupante", query = "SELECT r FROM Relaciones r WHERE r.relacionesPK.idUsuarioOcupante = :idUsuarioOcupante")
    , @NamedQuery(name = "Relaciones.findByIdUsuarioPropietario", query = "SELECT r FROM Relaciones r WHERE r.relacionesPK.idUsuarioPropietario = :idUsuarioPropietario")
    , @NamedQuery(name = "Relaciones.findByFechaRelacion", query = "SELECT r FROM Relaciones r WHERE r.fechaRelacion = :fechaRelacion")})
public class Relaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RelacionesPK relacionesPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaRelacion")
    @Temporal(TemporalType.DATE)
    private Date fechaRelacion;
    @JoinColumn(name = "idUsuarioOcupante", referencedColumnName = "idUsuario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "idUsuarioPropietario", referencedColumnName = "idUsuario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario1;

    public Relaciones() {
    }

    public Relaciones(RelacionesPK relacionesPK) {
        this.relacionesPK = relacionesPK;
    }

    public Relaciones(RelacionesPK relacionesPK, Date fechaRelacion) {
        this.relacionesPK = relacionesPK;
        this.fechaRelacion = fechaRelacion;
    }

    public Relaciones(int idUsuarioOcupante, int idUsuarioPropietario) {
        this.relacionesPK = new RelacionesPK(idUsuarioOcupante, idUsuarioPropietario);
    }

    public RelacionesPK getRelacionesPK() {
        return relacionesPK;
    }

    public void setRelacionesPK(RelacionesPK relacionesPK) {
        this.relacionesPK = relacionesPK;
    }

    public Date getFechaRelacion() {
        return fechaRelacion;
    }

    public void setFechaRelacion(Date fechaRelacion) {
        this.fechaRelacion = fechaRelacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(Usuario usuario1) {
        this.usuario1 = usuario1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (relacionesPK != null ? relacionesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Relaciones)) {
            return false;
        }
        Relaciones other = (Relaciones) object;
        if ((this.relacionesPK == null && other.relacionesPK != null) || (this.relacionesPK != null && !this.relacionesPK.equals(other.relacionesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.allyrent.entidades.Relaciones[ relacionesPK=" + relacionesPK + " ]";
    }
    
}
