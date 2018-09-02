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
@Table(name = "mensajes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mensajes.findAll", query = "SELECT m FROM Mensajes m")
    , @NamedQuery(name = "Mensajes.findByIdUsuarioRecibe", query = "SELECT m FROM Mensajes m WHERE m.mensajesPK.idUsuarioRecibe = :idUsuarioRecibe")
    , @NamedQuery(name = "Mensajes.findByIdUsuarioEnvia", query = "SELECT m FROM Mensajes m WHERE m.mensajesPK.idUsuarioEnvia = :idUsuarioEnvia")
    , @NamedQuery(name = "Mensajes.findByFechaEnvia", query = "SELECT m FROM Mensajes m WHERE m.fechaEnvia = :fechaEnvia")
    , @NamedQuery(name = "Mensajes.findByMensaje", query = "SELECT m FROM Mensajes m WHERE m.mensaje = :mensaje")
    , @NamedQuery(name = "Mensajes.findByEstado", query = "SELECT m FROM Mensajes m WHERE m.estado = :estado")})
public class Mensajes implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MensajesPK mensajesPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaEnvia")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEnvia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5000)
    @Column(name = "mensaje")
    private String mensaje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado;
    @JoinColumn(name = "idUsuarioRecibe", referencedColumnName = "idUsuario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "idUsuarioEnvia", referencedColumnName = "idUsuario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario1;

    public Mensajes() {
    }

    public Mensajes(MensajesPK mensajesPK) {
        this.mensajesPK = mensajesPK;
    }

    public Mensajes(MensajesPK mensajesPK, Date fechaEnvia, String mensaje, boolean estado) {
        this.mensajesPK = mensajesPK;
        this.fechaEnvia = fechaEnvia;
        this.mensaje = mensaje;
        this.estado = estado;
    }

    public Mensajes(int idUsuarioRecibe, int idUsuarioEnvia) {
        this.mensajesPK = new MensajesPK(idUsuarioRecibe, idUsuarioEnvia);
    }

    public MensajesPK getMensajesPK() {
        return mensajesPK;
    }

    public void setMensajesPK(MensajesPK mensajesPK) {
        this.mensajesPK = mensajesPK;
    }

    public Date getFechaEnvia() {
        return fechaEnvia;
    }

    public void setFechaEnvia(Date fechaEnvia) {
        this.fechaEnvia = fechaEnvia;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
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
        hash += (mensajesPK != null ? mensajesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mensajes)) {
            return false;
        }
        Mensajes other = (Mensajes) object;
        if ((this.mensajesPK == null && other.mensajesPK != null) || (this.mensajesPK != null && !this.mensajesPK.equals(other.mensajesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.allyrent.entidades.Mensajes[ mensajesPK=" + mensajesPK + " ]";
    }
    
}
