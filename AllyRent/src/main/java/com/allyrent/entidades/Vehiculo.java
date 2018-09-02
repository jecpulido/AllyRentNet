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
@Table(name = "vehiculo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vehiculo.findAll", query = "SELECT v FROM Vehiculo v")
    , @NamedQuery(name = "Vehiculo.findByIdVehiculo", query = "SELECT v FROM Vehiculo v WHERE v.idVehiculo = :idVehiculo")
    , @NamedQuery(name = "Vehiculo.findByPlaca", query = "SELECT v FROM Vehiculo v WHERE v.placa = :placa")
    , @NamedQuery(name = "Vehiculo.findByAno", query = "SELECT v FROM Vehiculo v WHERE v.ano = :ano")
    , @NamedQuery(name = "Vehiculo.findByKm", query = "SELECT v FROM Vehiculo v WHERE v.km = :km")
    , @NamedQuery(name = "Vehiculo.findByIsPlacaPar", query = "SELECT v FROM Vehiculo v WHERE v.isPlacaPar = :isPlacaPar")})
public class Vehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idVehiculo")
    private Integer idVehiculo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "placa")
    private String placa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ano")
    private int ano;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Km")
    private int km;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isPlacaPar")
    private boolean isPlacaPar;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vEHICULOidVehiculo")
    private Collection<Contrato> contratoCollection;
    @JoinColumn(name = "idModelo", referencedColumnName = "idModelo")
    @ManyToOne(optional = false)
    private Modelo idModelo;
    @JoinColumn(name = "idCombustible", referencedColumnName = "idDataType")
    @ManyToOne(optional = false)
    private Datatype idCombustible;
    @JoinColumn(name = "idTransmision", referencedColumnName = "idDataType")
    @ManyToOne(optional = false)
    private Datatype idTransmision;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;
    @JoinColumn(name = "idTarifa", referencedColumnName = "idTarifa")
    @ManyToOne(optional = false)
    private Tarifa idTarifa;
    @JoinColumn(name = "idTipoVehiculo", referencedColumnName = "idDataType")
    @ManyToOne(optional = false)
    private Datatype idTipoVehiculo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idVehiculo")
    private Collection<Comentario> comentarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idVehiculo")
    private Collection<Fotos> fotosCollection;

    public Vehiculo() {
    }

    public Vehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public Vehiculo(Integer idVehiculo, String placa, int ano, int km, boolean isPlacaPar) {
        this.idVehiculo = idVehiculo;
        this.placa = placa;
        this.ano = ano;
        this.km = km;
        this.isPlacaPar = isPlacaPar;
    }

    public Integer getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public boolean getIsPlacaPar() {
        return isPlacaPar;
    }

    public void setIsPlacaPar(boolean isPlacaPar) {
        this.isPlacaPar = isPlacaPar;
    }

    @XmlTransient
    public Collection<Contrato> getContratoCollection() {
        return contratoCollection;
    }

    public void setContratoCollection(Collection<Contrato> contratoCollection) {
        this.contratoCollection = contratoCollection;
    }

    public Modelo getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Modelo idModelo) {
        this.idModelo = idModelo;
    }

    public Datatype getIdCombustible() {
        return idCombustible;
    }

    public void setIdCombustible(Datatype idCombustible) {
        this.idCombustible = idCombustible;
    }

    public Datatype getIdTransmision() {
        return idTransmision;
    }

    public void setIdTransmision(Datatype idTransmision) {
        this.idTransmision = idTransmision;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Tarifa getIdTarifa() {
        return idTarifa;
    }

    public void setIdTarifa(Tarifa idTarifa) {
        this.idTarifa = idTarifa;
    }

    public Datatype getIdTipoVehiculo() {
        return idTipoVehiculo;
    }

    public void setIdTipoVehiculo(Datatype idTipoVehiculo) {
        this.idTipoVehiculo = idTipoVehiculo;
    }

    @XmlTransient
    public Collection<Comentario> getComentarioCollection() {
        return comentarioCollection;
    }

    public void setComentarioCollection(Collection<Comentario> comentarioCollection) {
        this.comentarioCollection = comentarioCollection;
    }

    @XmlTransient
    public Collection<Fotos> getFotosCollection() {
        return fotosCollection;
    }

    public void setFotosCollection(Collection<Fotos> fotosCollection) {
        this.fotosCollection = fotosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVehiculo != null ? idVehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vehiculo)) {
            return false;
        }
        Vehiculo other = (Vehiculo) object;
        if ((this.idVehiculo == null && other.idVehiculo != null) || (this.idVehiculo != null && !this.idVehiculo.equals(other.idVehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.allyrent.entidades.Vehiculo[ idVehiculo=" + idVehiculo + " ]";
    }
    
}
