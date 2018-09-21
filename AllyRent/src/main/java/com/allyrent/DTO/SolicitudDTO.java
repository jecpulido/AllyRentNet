/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allyrent.DTO;

import com.allyrent.entidades.Solicitud;
import java.util.Date;

/**
 *
 * @author pulidoje
 */
public class SolicitudDTO {

    private Integer estado;

    private Integer idSolicitud;

    private Date fechaSolicitud;

    private PublicacionDTO idPublicacion;

    private UsuarioDTO idUsuario;

    public SolicitudDTO() {
    }

    public SolicitudDTO(Solicitud entity) {
        this.estado = entity.getEstado();
        this.idSolicitud = entity.getIdSolicitud();
        this.fechaSolicitud = entity.getFechaSolicitud();
        if (entity.getIdPublicacion() != null) {
            this.idPublicacion = new PublicacionDTO(entity.getIdPublicacion());
        }
        if (entity.getIdUsuario() != null) {
            this.idUsuario = new UsuarioDTO(entity.getIdUsuario());
        }
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public PublicacionDTO getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(PublicacionDTO idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public UsuarioDTO getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UsuarioDTO idUsuario) {
        this.idUsuario = idUsuario;
    }
    
}
