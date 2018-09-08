/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allyrent.resources;

import com.allyrent.bean.SolicitudFacade;
import com.allyrent.entidades.Solicitud;
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
@Path("solicitudes")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class SolicitudResoruce {

    @EJB
    SolicitudFacade _solicitudFacade;

    @POST
    @Path("/ToggleSolicitud")
    public String TogglePost(Solicitud solicitud) {
        try {
            if (solicitud != null) {
                Solicitud _solicitud = _solicitudFacade.FindSolicitudByUsuario(
                        solicitud.getIdUsuario().getIdUsuario(),
                        solicitud.getIdPublicacion().getIdPublicacion());

                if (_solicitud == null) {
                    if (solicitud.getFechaSolicitud() == null) {
                        solicitud.setFechaSolicitud(new Date());
                    }
                    _solicitudFacade.create(solicitud);
                    return "OK";
                } else {
                    _solicitudFacade.remove(_solicitud);
                    return "DELETE";
                }

            }
            return "Incorrect Data!";
        } catch (Exception e) {
            return "Se presento un error";
        }
    }

    @GET
    @Path("/FindSolicitudes/{idUsuario}")
    public List<Solicitud> FindSolicitudList(@PathParam("idUsuario") int idUsuario) {
        try {
            List<Solicitud> solicitudes = _solicitudFacade.FindSolicitudList(idUsuario);
            return solicitudes;
        } catch (Exception e) {
            return null;
        }
    }

    @GET
    @Path("/ListSolicitudes/{idUsuario}")
    public List<Solicitud> FindSolicitudByUsuarioCreate(@PathParam("idUsuario") int idUsuario) {
        try {
            List<Solicitud> solicitudes = _solicitudFacade.FindSolicitudByUsuarioCreate(idUsuario);
            return solicitudes;
        } catch (Exception e) {
            return null;
        }

    }

    @POST
    @Path("/updateState")
    public String UpdateStateRequest(Solicitud solicitud) {
        try {
            if (solicitud != null) {
                Solicitud _solicitud = _solicitudFacade.FindSolicitudByUsuario(
                        solicitud.getIdUsuario().getIdUsuario(),
                        solicitud.getIdPublicacion().getIdPublicacion());
                _solicitudFacade.edit(solicitud);
                return "OK";
            }
            return "Incorrect Data!";
        } catch (Exception e) {
            return "Se presento un error";
        }
    }
    
    @GET
    @Path("/{idSolicitud}")
    public Solicitud FindSolicitud(@PathParam("idSolicitud") int idSolicitud) {
        try {
            Solicitud solicitud = _solicitudFacade.find(idSolicitud);
            return solicitud;
        } catch (Exception e) {
            return null;
        }

    }

}