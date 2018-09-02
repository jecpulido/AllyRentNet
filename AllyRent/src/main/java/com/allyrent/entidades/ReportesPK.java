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
public class ReportesPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idUsuarioReporta")
    private int idUsuarioReporta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dUsuarioReportado")
    private int dUsuarioReportado;

    public ReportesPK() {
    }

    public ReportesPK(int idUsuarioReporta, int dUsuarioReportado) {
        this.idUsuarioReporta = idUsuarioReporta;
        this.dUsuarioReportado = dUsuarioReportado;
    }

    public int getIdUsuarioReporta() {
        return idUsuarioReporta;
    }

    public void setIdUsuarioReporta(int idUsuarioReporta) {
        this.idUsuarioReporta = idUsuarioReporta;
    }

    public int getDUsuarioReportado() {
        return dUsuarioReportado;
    }

    public void setDUsuarioReportado(int dUsuarioReportado) {
        this.dUsuarioReportado = dUsuarioReportado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUsuarioReporta;
        hash += (int) dUsuarioReportado;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReportesPK)) {
            return false;
        }
        ReportesPK other = (ReportesPK) object;
        if (this.idUsuarioReporta != other.idUsuarioReporta) {
            return false;
        }
        if (this.dUsuarioReportado != other.dUsuarioReportado) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.allyrent.entidades.ReportesPK[ idUsuarioReporta=" + idUsuarioReporta + ", dUsuarioReportado=" + dUsuarioReportado + " ]";
    }
    
}
