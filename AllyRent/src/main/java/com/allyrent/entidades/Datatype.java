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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "datatype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Datatype.findAll", query = "SELECT d FROM Datatype d")
    , @NamedQuery(name = "Datatype.findByIdDataType", query = "SELECT d FROM Datatype d WHERE d.idDataType = :idDataType")
    , @NamedQuery(name = "Datatype.findByNombreDataType", query = "SELECT d FROM Datatype d WHERE d.nombreDataType = :nombreDataType")
    , @NamedQuery(name = "Datatype.findByIdDataMaster", query = "SELECT d FROM Datatype d WHERE d.idDataMaster.idDataMaster = :idDataMaster")
    , @NamedQuery(name = "Datatype.findByDescripcionDataType", query = "SELECT d FROM Datatype d WHERE d.descripcionDataType = :descripcionDataType")})
public class Datatype implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDataType")
    private Integer idDataType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombreDataType")
    private String nombreDataType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "descripcionDataType")
    private String descripcionDataType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCombustible")
    private Collection<Vehiculo> vehiculoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTransmision")
    private Collection<Vehiculo> vehiculoCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoVehiculo")
    private Collection<Vehiculo> vehiculoCollection2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoPublicacion")
    private Collection<Publicacion> publicacionCollection;
    @JoinColumn(name = "idDataMaster", referencedColumnName = "idDataMaster")
    @ManyToOne(optional = false)
    private Datamaster idDataMaster;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSexo")
    private Collection<Usuario> usuarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoDocumento")
    private Collection<Usuario> usuarioCollection1;

    public Datatype() {
    }

    public Datatype(Integer idDataType) {
        this.idDataType = idDataType;
    }

    public Datatype(Integer idDataType, String nombreDataType, String descripcionDataType) {
        this.idDataType = idDataType;
        this.nombreDataType = nombreDataType;
        this.descripcionDataType = descripcionDataType;
    }

    public Integer getIdDataType() {
        return idDataType;
    }

    public void setIdDataType(Integer idDataType) {
        this.idDataType = idDataType;
    }

    public String getNombreDataType() {
        return nombreDataType;
    }

    public void setNombreDataType(String nombreDataType) {
        this.nombreDataType = nombreDataType;
    }

    public String getDescripcionDataType() {
        return descripcionDataType;
    }

    public void setDescripcionDataType(String descripcionDataType) {
        this.descripcionDataType = descripcionDataType;
    }

    @XmlTransient
    public Collection<Vehiculo> getVehiculoCollection() {
        return vehiculoCollection;
    }

    public void setVehiculoCollection(Collection<Vehiculo> vehiculoCollection) {
        this.vehiculoCollection = vehiculoCollection;
    }

    @XmlTransient
    public Collection<Vehiculo> getVehiculoCollection1() {
        return vehiculoCollection1;
    }

    public void setVehiculoCollection1(Collection<Vehiculo> vehiculoCollection1) {
        this.vehiculoCollection1 = vehiculoCollection1;
    }

    @XmlTransient
    public Collection<Vehiculo> getVehiculoCollection2() {
        return vehiculoCollection2;
    }

    public void setVehiculoCollection2(Collection<Vehiculo> vehiculoCollection2) {
        this.vehiculoCollection2 = vehiculoCollection2;
    }

    @XmlTransient
    public Collection<Publicacion> getPublicacionCollection() {
        return publicacionCollection;
    }

    public void setPublicacionCollection(Collection<Publicacion> publicacionCollection) {
        this.publicacionCollection = publicacionCollection;
    }

    public Datamaster getIdDataMaster() {
        return idDataMaster;
    }

    public void setIdDataMaster(Datamaster idDataMaster) {
        this.idDataMaster = idDataMaster;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection1() {
        return usuarioCollection1;
    }

    public void setUsuarioCollection1(Collection<Usuario> usuarioCollection1) {
        this.usuarioCollection1 = usuarioCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDataType != null ? idDataType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Datatype)) {
            return false;
        }
        Datatype other = (Datatype) object;
        if ((this.idDataType == null && other.idDataType != null) || (this.idDataType != null && !this.idDataType.equals(other.idDataType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.allyrent.entidades.Datatype[ idDataType=" + idDataType + " ]";
    }
 
}
