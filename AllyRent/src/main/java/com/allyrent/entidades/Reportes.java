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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "reportes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reportes.findAll", query = "SELECT r FROM Reportes r")
    , @NamedQuery(name = "Reportes.findByIdUsuarioReporta", query = "SELECT r FROM Reportes r WHERE r.reportesPK.idUsuarioReporta = :idUsuarioReporta")
    , @NamedQuery(name = "Reportes.findByDUsuarioReportado", query = "SELECT r FROM Reportes r WHERE r.reportesPK.dUsuarioReportado = :dUsuarioReportado")
    , @NamedQuery(name = "Reportes.findByCausa", query = "SELECT r FROM Reportes r WHERE r.causa = :causa")
    , @NamedQuery(name = "Reportes.findByFechaReporte", query = "SELECT r FROM Reportes r WHERE r.fechaReporte = :fechaReporte")
    , @NamedQuery(name = "Reportes.findByEstado", query = "SELECT r FROM Reportes r WHERE r.estado = :estado")})
public class Reportes implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReportesPK reportesPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "Causa")
    private String causa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaReporte")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReporte;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado;
    @JoinColumn(name = "idUsuarioReporta", referencedColumnName = "idUsuario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "dUsuarioReportado", referencedColumnName = "idUsuario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario1;

    public Reportes() {
    }

    public Reportes(ReportesPK reportesPK) {
        this.reportesPK = reportesPK;
    }

    public Reportes(ReportesPK reportesPK, String causa, Date fechaReporte, boolean estado) {
        this.reportesPK = reportesPK;
        this.causa = causa;
        this.fechaReporte = fechaReporte;
        this.estado = estado;
    }

    public Reportes(int idUsuarioReporta, int dUsuarioReportado) {
        this.reportesPK = new ReportesPK(idUsuarioReporta, dUsuarioReportado);
    }

    public ReportesPK getReportesPK() {
        return reportesPK;
    }

    public void setReportesPK(ReportesPK reportesPK) {
        this.reportesPK = reportesPK;
    }

    public String getCausa() {
        return causa;
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }

    public Date getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
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
        hash += (reportesPK != null ? reportesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reportes)) {
            return false;
        }
        Reportes other = (Reportes) object;
        if ((this.reportesPK == null && other.reportesPK != null) || (this.reportesPK != null && !this.reportesPK.equals(other.reportesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.allyrent.entidades.Reportes[ reportesPK=" + reportesPK + " ]";
    }
    
}
