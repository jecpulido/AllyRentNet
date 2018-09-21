/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allyrent.DTO;

import com.allyrent.entidades.Contrato;
import java.util.Date;

/**
 *
 * @author pulidoje
 */
public class ContratoDTO {

    private Integer idContrato;

    private Date fechaContrato;

    private Date fechaInicio;

    private Date fechaFin;

    private float valor;

    private UsuarioDTO usuario;

    private UsuarioDTO usuario1;
    
    private VehiculoDTO vehiculo;

    public ContratoDTO() {
    }

    public ContratoDTO(Contrato entity) {
        this.idContrato = entity.getIdContrato();
        this.fechaContrato = entity.getFechaContrato();
        this.fechaInicio = entity.getFechaInicio();
        this.fechaFin = entity.getFechaFin();
        this.valor = entity.getValor();
        if (entity.getUsuario() != null){
            this.usuario = new UsuarioDTO(entity.getUsuario()) ;
        }
        if (entity.getUsuario1() != null){
            this.usuario1 =  new UsuarioDTO(entity.getUsuario1()) ;
        }
        if (entity.getIdVehiculo()!= null){
            this.vehiculo = new VehiculoDTO(entity.getIdVehiculo());
        }        
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Integer idContrato) {
        this.idContrato = idContrato;
    }

    public Date getFechaContrato() {
        return fechaContrato;
    }

    public void setFechaContrato(Date fechaContrato) {
        this.fechaContrato = fechaContrato;
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

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public UsuarioDTO getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(UsuarioDTO usuario1) {
        this.usuario1 = usuario1;
    }

    public VehiculoDTO getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(VehiculoDTO vehiculo) {
        this.vehiculo = vehiculo;
    }
    
    
    

}
