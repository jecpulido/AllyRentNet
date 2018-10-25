/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allyrent.DTO;

import com.allyrent.entidades.Publicacion;
import com.allyrent.entidades.Reaccion;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author Jorge
 */
public class PublicacionDTO {

    private Integer idPublicacion;

    private String publicacion;

    private Date fechaInicio;

    private Date fechaFin;

    private Date fechaPublicacion;

    private UsuarioDTO usuario;

    private VehiculoDTO vehiculo;

    private int idTipoPublicacion;

    private String nombreTipoPublicacion;

    private int like;

    private int disLike;

    private String reaccion;
    
    private String ubicacion;

    public PublicacionDTO() {
    }

    public PublicacionDTO(Publicacion entity) {
        this.idPublicacion = entity.getIdPublicacion();
        this.publicacion = entity.getPublicacion();
        this.fechaInicio = entity.getFechaInicio();
        this.fechaFin = entity.getFechaFin();
        this.fechaPublicacion = entity.getFechaPublicacion();
        this.ubicacion = entity.getUbicacion();
        if (entity.getIdUsuario() != null) {
            this.usuario = new UsuarioDTO(entity.getIdUsuario());
        }
        if (entity.getIdTipoPublicacion() != null) {
            this.idTipoPublicacion = entity.getIdTipoPublicacion().getIdDataType();
            this.nombreTipoPublicacion = entity.getIdTipoPublicacion().getNombreDataType();
        }
        if (entity.getIdVehiculo() != null) {
            this.vehiculo = new VehiculoDTO();
            this.vehiculo.setIdVehiculo(entity.getIdVehiculo());
        }
    }

    public Integer getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(Integer idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public String getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(String publicacion) {
        this.publicacion = publicacion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public VehiculoDTO getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(VehiculoDTO vehiculo) {
        this.vehiculo = vehiculo;
    }

    public int getIdTipoPublicacion() {
        return idTipoPublicacion;
    }

    public void setIdTipoPublicacion(int idTipoPublicacion) {
        this.idTipoPublicacion = idTipoPublicacion;
    }

    public String getNombreTipoPublicacion() {
        return nombreTipoPublicacion;
    }

    public void setNombreTipoPublicacion(String nombreTipoPublicacion) {
        this.nombreTipoPublicacion = nombreTipoPublicacion;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDisLike() {
        return disLike;
    }

    public void setDisLike(int disLike) {
        this.disLike = disLike;
    }

    public String getReaccion() {
        return reaccion;
    }

    public void setReaccion(String reaccion) {
        this.reaccion = reaccion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    
    
}
