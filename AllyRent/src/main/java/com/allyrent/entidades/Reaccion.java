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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pulidoje
 */
@Entity
@Table(name = "reaccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reaccion.findAll", query = "SELECT r FROM Reaccion r")
    , @NamedQuery(name = "Reaccion.findByIdReaccion", query = "SELECT r FROM Reaccion r WHERE r.idReaccion = :idReaccion")
    , @NamedQuery(name = "Reaccion.findByIdPubIdUsu", query = "SELECT r FROM Reaccion r WHERE r.idPublicacion.idPublicacion = :idPublicacion AND r.idUsuario.idUsuario = :idUsuario")
    , @NamedQuery(name = "Reaccion.findByIdPublicacion", query = "SELECT r FROM Reaccion r WHERE r.idPublicacion.idPublicacion = :idPublicacion")
    , @NamedQuery(name = "Reaccion.findByFechaReaccion", query = "SELECT r FROM Reaccion r WHERE r.fechaReaccion = :fechaReaccion")})
public class Reaccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idReaccion")
    private Integer idReaccion;
    @Column(name = "fechaReaccion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReaccion;
    @JoinColumn(name = "idPublicacion", referencedColumnName = "idPublicacion")
    @ManyToOne
    private Publicacion idPublicacion;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne
    private Usuario idUsuario;
    @Column(name = "bandera")
    private Integer bandera;

    public Reaccion() {
    }

    public Reaccion(Integer idReaccion) {
        this.idReaccion = idReaccion;
    }

    public Integer getIdReaccion() {
        return idReaccion;
    }

    public void setIdReaccion(Integer idReaccion) {
        this.idReaccion = idReaccion;
    }

    public Date getFechaReaccion() {
        return fechaReaccion;
    }

    public void setFechaReaccion(Date fechaReaccion) {
        this.fechaReaccion = fechaReaccion;
    }

    public Publicacion getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(Publicacion idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getBandera() {
        return bandera;
    }

    public void setBandera(Integer bandera) {
        this.bandera = bandera;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReaccion != null ? idReaccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reaccion)) {
            return false;
        }
        Reaccion other = (Reaccion) object;
        if ((this.idReaccion == null && other.idReaccion != null) || (this.idReaccion != null && !this.idReaccion.equals(other.idReaccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.allyrent.entidades.Reaccion[ idReaccion=" + idReaccion + " ]";
    }

}
