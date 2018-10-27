/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allyrent.DTO;

import com.allyrent.entidades.Fotos;
import com.allyrent.entidades.Vehiculo;
import com.allyrent.utility.ImageToArray;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class VehiculoDTO {

    private Integer idVehiculo;

    private String placa;

    private int ano;

    private int km;

    private boolean isPlacaPar;

    private UsuarioDTO idUsuario;

    private int idModelo;
    private String nombreModelo;

    private int idCombustible;
    private String nombreCombustible;

    private int idTransmision;
    private String nombreTransmision;

    private int idTarifa;
    private float dia;
    private float hora;

    private int idTipoVehiculo;
    private String nombreTipoVehiculo;

    private String primerFoto;
    private List<FotoDTO> fotosCollection;

    public VehiculoDTO() {

    }

    public VehiculoDTO(Vehiculo veh) {
        this.idVehiculo = veh.getIdVehiculo();
        this.placa = veh.getPlaca();
        this.ano = veh.getAno();
        this.km = veh.getKm();
        this.isPlacaPar = veh.getIsPlacaPar();
        if (veh.getIdUsuario() != null) {
            UsuarioDTO user = new UsuarioDTO(veh.getIdUsuario());
            this.idUsuario = user;
        }
        if (veh.getIdModelo() != null) {
            this.idModelo = veh.getIdModelo().getIdModelo();
            this.nombreModelo = veh.getIdModelo().getNombre();
        }
        if (veh.getIdCombustible() != null) {
            this.idCombustible = veh.getIdCombustible().getIdDataType();
            this.nombreCombustible = veh.getIdCombustible().getNombreDataType();
        }
        if (veh.getIdTransmision() != null) {
            this.idTransmision = veh.getIdTransmision().getIdDataType();
            this.nombreTransmision = veh.getIdTransmision().getNombreDataType();
        }
        if (veh.getIdTarifa() != null) {
            this.idTarifa = veh.getIdTarifa().getIdTarifa();
            this.hora = veh.getIdTarifa().getPorHora();
            this.dia = veh.getIdTarifa().getPorDia();
        }
        if (veh.getIdTipoVehiculo() != null) {
            this.idTipoVehiculo = veh.getIdTipoVehiculo().getIdDataType();
            this.nombreTipoVehiculo = veh.getIdTipoVehiculo().getNombreDataType();
        }
        if (veh.getFotosCollection() != null) {
            List<FotoDTO> fotos = new ArrayList<>();
            for (Fotos fot : veh.getFotosCollection()) {
                fotos.add(new FotoDTO(
                        ImageToArray.converseByteArrayToImage(fot.getFoto()),
                        fot.getIdFoto()));
            }
            if (fotos.size()>0) {
                this.fotosCollection = fotos;
                this.primerFoto=fotos.get(0).getFoto();
            }
        }

    }
//    private Collection<Comentario> comentarioCollection;
//    private Collection<Contrato> contratoCollection;

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

    public boolean isIsPlacaPar() {
        return isPlacaPar;
    }

    public void setIsPlacaPar(boolean isPlacaPar) {
        this.isPlacaPar = isPlacaPar;
    }

    public UsuarioDTO getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UsuarioDTO idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(int idModelo) {
        this.idModelo = idModelo;
    }

    public String getNombreModelo() {
        return nombreModelo;
    }

    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }

    public int getIdCombustible() {
        return idCombustible;
    }

    public void setIdCombustible(int idCombustible) {
        this.idCombustible = idCombustible;
    }

    public String getNombreCombustible() {
        return nombreCombustible;
    }

    public void setNombreCombustible(String nombreCombustible) {
        this.nombreCombustible = nombreCombustible;
    }

    public int getIdTransmision() {
        return idTransmision;
    }

    public void setIdTransmision(int idTransmision) {
        this.idTransmision = idTransmision;
    }

    public String getNombreTransmision() {
        return nombreTransmision;
    }

    public void setNombreTransmision(String nombreTransmision) {
        this.nombreTransmision = nombreTransmision;
    }

    public int getIdTarifa() {
        return idTarifa;
    }

    public void setIdTarifa(int idTarifa) {
        this.idTarifa = idTarifa;
    }

    public float getDia() {
        return dia;
    }

    public void setDia(float dia) {
        this.dia = dia;
    }

    public float getHora() {
        return hora;
    }

    public void setHora(float hora) {
        this.hora = hora;
    }

    public int getIdTipoVehiculo() {
        return idTipoVehiculo;
    }

    public void setIdTipoVehiculo(int idTipoVehiculo) {
        this.idTipoVehiculo = idTipoVehiculo;
    }

    public String getNombreTipoVehiculo() {
        return nombreTipoVehiculo;
    }

    public void setNombreTipoVehiculo(String nombreTipoVehiculo) {
        this.nombreTipoVehiculo = nombreTipoVehiculo;
    }

    public String getPrimerFoto() {
        return primerFoto;
    }

    public void setPrimerFoto(String primerFoto) {
        this.primerFoto = primerFoto;
    }

    public List<FotoDTO> getFotosCollection() {
        return fotosCollection;
    }

    public void setFotosCollection(List<FotoDTO> fotosCollection) {
        this.fotosCollection = fotosCollection;
    }

    
    
}
