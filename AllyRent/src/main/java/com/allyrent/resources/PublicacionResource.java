/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allyrent.resources;

import com.allyrent.DTO.PublicacionDTO;
import com.allyrent.DTO.VehiculoDTO;
import com.allyrent.bean.PublicacionFacade;
import com.allyrent.bean.VehiculoFacade;
import com.allyrent.entidades.Publicacion;
import java.util.ArrayList;
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

/**
 *
 * @author Jorge
 */
@Path("publicaciones")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class PublicacionResource {

    @EJB
    PublicacionFacade _publicacionFacade;
    
    @EJB
    VehiculoFacade _vehiculoFacade;

    @POST
    @Path("/create")
    public String CrearPublicacion(Publicacion publicacion) {
        try {
            if (publicacion != null) {
                if (publicacion.getFechaPublicacion() == null) {
                    publicacion.setFechaPublicacion(new Date());
                }
                _publicacionFacade.create(publicacion);
                return "OK";
            }
            return "Se presento un problema";
        } catch (Exception e) {
            return "Error en publicacion";
        }
    }

    @GET
    @Path("/list/{idUsuario}")
    public List<PublicacionDTO> ListPublicacionById(@PathParam("idUsuario") int id) {
        try {
            List<Publicacion> publicaciones = _publicacionFacade.findAll();
            List<PublicacionDTO> publicacionesDTO = null;
            
            if (publicaciones.size() > 0) {
                publicacionesDTO = new ArrayList<>();
                
                for (Publicacion pub : publicaciones) {
                    PublicacionDTO p = new PublicacionDTO(pub);
                    p.setVehiculo(new VehiculoDTO(_vehiculoFacade.find(pub.getIdVehiculo())));
                    p.getVehiculo().setIdUsuario(null);
                    publicacionesDTO.add(p);
                }
            }
            return publicacionesDTO;
        } catch (Exception e) {
            return null;
        }
    }

    @GET
    @Path("/find/{idPublicacion}")
    public Publicacion FindPublicacionById(@PathParam("idPublicacion") int id) {
        try {
            Publicacion publicacion = _publicacionFacade.find(id);
            return publicacion;
        } catch (Exception e) {
            return null;
        }
    }

}
