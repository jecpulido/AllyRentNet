/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allyrent.DTO;

import com.allyrent.entidades.Publicacion;
import com.allyrent.entidades.Usuario;
import com.allyrent.entidades.Vehiculo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author pulidoje
 */
public class BusquedaDTO {

    private String nombreUsuario;

    private String correoElectronico;

    private int idMarca;

    private int idModelo;

    private int idDpto;

    private int idCiudad;

    private int idTPublicacion;

    private Date fechaPublicacion;

    private Date fechaInicio;

    private Date fechaFin;
    
    private String placa;

    private int ano;

    private List<PublicacionDTO> publicacion;

    private List<UsuarioDTO> usuario;

    private List<VehiculoDTO> vehiculo;

    public BusquedaDTO() {

    }

    public BusquedaDTO(List<Publicacion> publicaciones, List<Usuario> usuarios, List<Vehiculo> vehiculos) {

        this.publicacion = new ArrayList<>();
        if (publicaciones != null) {
            for (Publicacion post : publicaciones) {
                this.publicacion.add(new PublicacionDTO(post));
            }
        }

        this.usuario = new ArrayList<>();
        if (usuarios != null) {
            for (Usuario user : usuarios) {
                this.usuario.add(new UsuarioDTO(user));
            }
        }

        this.vehiculo = new ArrayList<>();
        if (vehiculos != null) {
            for (Vehiculo veh : vehiculos) {
                this.vehiculo.add(new VehiculoDTO(veh));
            }
        }
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public int getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(int idModelo) {
        this.idModelo = idModelo;
    }

    public int getIdDpto() {
        return idDpto;
    }

    public void setIdDpto(int idDpto) {
        this.idDpto = idDpto;
    }

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public int getIdTPublicacion() {
        return idTPublicacion;
    }

    public void setIdTPublicacion(int idTPublicacion) {
        this.idTPublicacion = idTPublicacion;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
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

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getAño() {
        return ano;
    }

    public void setAño(int ano) {
        this.ano = ano;
    }
    
    

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<PublicacionDTO> getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(List<PublicacionDTO> publicacion) {
        this.publicacion = publicacion;
    }

    public List<UsuarioDTO> getUsuario() {
        return usuario;
    }

    public void setUsuario(List<UsuarioDTO> usuario) {
        this.usuario = usuario;
    }

    public List<VehiculoDTO> getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(List<VehiculoDTO> vehiculo) {
        this.vehiculo = vehiculo;
    }

}
