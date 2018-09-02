/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allyrent.entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "tarifa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tarifa.findAll", query = "SELECT t FROM Tarifa t")
    , @NamedQuery(name = "Tarifa.findByIdTarifa", query = "SELECT t FROM Tarifa t WHERE t.idTarifa = :idTarifa")
    , @NamedQuery(name = "Tarifa.findByPorHora", query = "SELECT t FROM Tarifa t WHERE t.porHora = :porHora")
    , @NamedQuery(name = "Tarifa.findByPorDia", query = "SELECT t FROM Tarifa t WHERE t.porDia = :porDia")})
public class Tarifa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTarifa")
    private Integer idTarifa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "porHora")
    private float porHora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "porDia")
    private float porDia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTarifa")
    private Collection<Vehiculo> vehiculoCollection;

    public Tarifa() {
    }

    public Tarifa(Integer idTarifa) {
        this.idTarifa = idTarifa;
    }

    public Tarifa(Integer idTarifa, float porHora, float porDia) {
        this.idTarifa = idTarifa;
        this.porHora = porHora;
        this.porDia = porDia;
    }

    public Integer getIdTarifa() {
        return idTarifa;
    }

    public void setIdTarifa(Integer idTarifa) {
        this.idTarifa = idTarifa;
    }

    public float getPorHora() {
        return porHora;
    }

    public void setPorHora(float porHora) {
        this.porHora = porHora;
    }

    public float getPorDia() {
        return porDia;
    }

    public void setPorDia(float porDia) {
        this.porDia = porDia;
    }

    @XmlTransient
    public Collection<Vehiculo> getVehiculoCollection() {
        return vehiculoCollection;
    }

    public void setVehiculoCollection(Collection<Vehiculo> vehiculoCollection) {
        this.vehiculoCollection = vehiculoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTarifa != null ? idTarifa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tarifa)) {
            return false;
        }
        Tarifa other = (Tarifa) object;
        if ((this.idTarifa == null && other.idTarifa != null) || (this.idTarifa != null && !this.idTarifa.equals(other.idTarifa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.allyrent.entidades.Tarifa[ idTarifa=" + idTarifa + " ]";
    }
    
}
