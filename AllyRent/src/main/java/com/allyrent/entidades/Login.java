/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allyrent.entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "login")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Login.findAll", query = "SELECT l FROM Login l")
    , @NamedQuery(name = "Login.findByIdLogin", query = "SELECT l FROM Login l WHERE l.idLogin = :idLogin")
    , @NamedQuery(name = "Login.findByCorreo", query = "SELECT l FROM Login l WHERE l.correo = :correo")
    , @NamedQuery(name = "Login.findByContrasena", query = "SELECT l FROM Login l WHERE l.contrasena = :contrasena")
    , @NamedQuery(name = "Login.findByUltimaConexion", query = "SELECT l FROM Login l WHERE l.ultimaConexion = :ultimaConexion")
    , @NamedQuery(name = "Login.login", query = "SELECT l FROM Login l WHERE l.correo = :correo AND l.contrasena = :contrasena")
    , @NamedQuery(name = "Login.findByEstado", query = "SELECT l FROM Login l WHERE l.estado = :estado")})
public class Login implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLogin")
    private Integer idLogin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "contrasena")
    private String contrasena;
    @Column(name = "ultimaConexion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaConexion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLogin")
    private Collection<Usuario> usuarioCollection;

    public Login() {
    }

    public Login(Integer idLogin) {
        this.idLogin = idLogin;
    }

    public Login(Integer idLogin, String correo, String contrasena, boolean estado) {
        this.idLogin = idLogin;
        this.correo = correo;
        this.contrasena = contrasena;
        this.estado = estado;
    }

    public Integer getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(Integer idLogin) {
        this.idLogin = idLogin;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Date getUltimaConexion() {
        return ultimaConexion;
    }

    public void setUltimaConexion(Date ultimaConexion) {
        this.ultimaConexion = ultimaConexion;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLogin != null ? idLogin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Login)) {
            return false;
        }
        Login other = (Login) object;
        if ((this.idLogin == null && other.idLogin != null) || (this.idLogin != null && !this.idLogin.equals(other.idLogin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.allyrent.entidades.Login[ idLogin=" + idLogin + " ]";
    }
    
}
