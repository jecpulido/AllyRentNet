/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allyrent.resources;

import com.allyrent.bean.ContratoFacade;
import com.allyrent.entidades.Contrato;
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
@Path("contratos")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class ContratoResource {
    
    @EJB
    ContratoFacade _contratoFacade;
    
    @POST
    @Path("/create")    
    public String createContrato(Contrato contrato){
        try {
            if (contrato == null){
                return "Datos Incorrectos";
            }
            _contratoFacade.create(contrato);
            return "OK";
        } catch (Exception e) {
            return "";
        }
    }
    
    @GET
    @Path("/{idPropietario}/{idOcupante}")    
    public Contrato findContrato(@PathParam("idPropietario") int idPropietario,
            @PathParam("idOcupante") int idOcupante){
        try {            
            Contrato contrato =_contratoFacade.FindSolicitudByUsuario(idPropietario, idOcupante);
            return contrato;
        } catch (Exception e) {
            return null;
        }
    }
    
}
