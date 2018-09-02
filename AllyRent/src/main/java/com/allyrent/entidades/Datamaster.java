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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "datamaster")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Datamaster.findAll", query = "SELECT d FROM Datamaster d")
    , @NamedQuery(name = "Datamaster.findByIdDataMaster", query = "SELECT d FROM Datamaster d WHERE d.idDataMaster = :idDataMaster")
    , @NamedQuery(name = "Datamaster.findByNombreDataMaster", query = "SELECT d FROM Datamaster d WHERE d.nombreDataMaster = :nombreDataMaster")
    , @NamedQuery(name = "Datamaster.findByDescripcionDataMaster", query = "SELECT d FROM Datamaster d WHERE d.descripcionDataMaster = :descripcionDataMaster")})
public class Datamaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDataMaster")
    private Integer idDataMaster;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombreDataMaster")
    private String nombreDataMaster;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "descripcionDataMaster")
    private String descripcionDataMaster;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDataMaster")
    private Collection<Datatype> datatypeCollection;

    public Datamaster() {
    }

    public Datamaster(Integer idDataMaster) {
        this.idDataMaster = idDataMaster;
    }

    public Datamaster(Integer idDataMaster, String nombreDataMaster, String descripcionDataMaster) {
        this.idDataMaster = idDataMaster;
        this.nombreDataMaster = nombreDataMaster;
        this.descripcionDataMaster = descripcionDataMaster;
    }

    public Integer getIdDataMaster() {
        return idDataMaster;
    }

    public void setIdDataMaster(Integer idDataMaster) {
        this.idDataMaster = idDataMaster;
    }

    public String getNombreDataMaster() {
        return nombreDataMaster;
    }

    public void setNombreDataMaster(String nombreDataMaster) {
        this.nombreDataMaster = nombreDataMaster;
    }

    public String getDescripcionDataMaster() {
        return descripcionDataMaster;
    }

    public void setDescripcionDataMaster(String descripcionDataMaster) {
        this.descripcionDataMaster = descripcionDataMaster;
    }

    @XmlTransient
    public Collection<Datatype> getDatatypeCollection() {
        return datatypeCollection;
    }

    public void setDatatypeCollection(Collection<Datatype> datatypeCollection) {
        this.datatypeCollection = datatypeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDataMaster != null ? idDataMaster.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Datamaster)) {
            return false;
        }
        Datamaster other = (Datamaster) object;
        if ((this.idDataMaster == null && other.idDataMaster != null) || (this.idDataMaster != null && !this.idDataMaster.equals(other.idDataMaster))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.allyrent.entidades.Datamaster[ idDataMaster=" + idDataMaster + " ]";
    }
    
}
