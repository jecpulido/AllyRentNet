/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allyrent.resources;

import com.allyrent.DTO.FotoDTO;
import com.allyrent.bean.*;
import com.allyrent.entidades.*;
import com.allyrent.utility.ImageToArray;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Jorge
 */
@Path("vehiculos")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class VehiculoResource {

    @EJB
    VehiculoFacade _vehicleFacade;

    @EJB
    MarcaFacade _marcaFacade;

    @EJB
    ModeloFacade _modeloFacade;

    @EJB
    TarifaFacade _tarifaFacade;

    @EJB
    FotosFacade _fotosFacade;

    @GET
    @Path("/{idUsuario}")
    @Consumes({MediaType.APPLICATION_JSON})
    public List<Vehiculo> ListVehiclesByUsuario(@PathParam("idUsuario") int idUsuario) {
        List<Vehiculo> vehiculos = _vehicleFacade.ListVehiclesByUsuario(idUsuario);
        return vehiculos;
    }

    @GET
    @Path("/marca")
    public List<Marca> ListAllMarca() {
        List<Marca> listaMarca = _marcaFacade.findAll();
        return listaMarca;
    }

    @GET
    @Path("/modelo/{idMarca}")
    public List<Modelo> findModelByMarca(@PathParam("idMarca") int idMarca) {
        List<Modelo> listaModelo = _modeloFacade.findModelByMarca(idMarca);
        return listaModelo;
    }

    @POST
    @Path("/create")
    public Vehiculo CreateVehicle(Vehiculo vehiculo) {
        try {
            if (vehiculo != null) {
                _vehicleFacade.create(vehiculo);
            }
            return vehiculo;
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
        return null;
    }

    @GET
    @Path("/findVehicle/{idVehiculo}")
    public Vehiculo FindVehicle(@PathParam("idVehiculo") int idVehiculo) {
        Vehiculo veh = _vehicleFacade.find(idVehiculo);
        return veh;
    }

    @GET
    @Path("/tarifas")
    public List<Tarifa> ListAllTarifas() {
        List<Tarifa> listaTarifas = _tarifaFacade.findAll();
        return listaTarifas;
    }

    @GET
    @Path("/fotos")
    public List<Fotos> listarFoto() {
        List<Fotos> fotos = _fotosFacade.findAll();
        return fotos;
    }

    @POST
    @Path("/upload")
    public void uploadImages(List<FotoDTO> listFotos) throws IOException {
        if (listFotos != null) {
            for (FotoDTO f : listFotos) {
                if (f.getFoto() != null) {
                    byte[] imageInByteArray = ImageToArray.convertStringToImageByteArray(f.getFoto());
                    Fotos foto = new Fotos();
                    foto.setFoto(imageInByteArray);
                    foto.setIdVehiculo(new Vehiculo(f.getIdVehiculo()));
                    foto.setFechaCarga(new Date());
                    _fotosFacade.create(foto);
                } 
            }
        }
    }

}
