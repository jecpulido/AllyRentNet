/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allyrent.resources;

import com.allyrent.bean.*;
import com.allyrent.entidades.*;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Jorge
 */
@Path("general")
@Produces({MediaType.APPLICATION_JSON})
public class GeneralResource {

    @EJB
    DatatypeFacade _Datatype;

    @EJB
    DatamasterFacade _Datamaster;

    @EJB
    DepartamentoFacade _Department;

    @EJB
    CiudadFacade _Ciudad;
    
    @EJB
    RolFacade _Rol;
    
    @GET
    @Path("/findType/{idParameter}")
    public List<Datatype> GetSearchList(@PathParam("idParameter") int idParameter) {
        try {
            List<Datatype> lista = _Datatype.ListarTipos(idParameter);
            if (lista != null) {
                return lista;
            }
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
        return null;
    }

    @GET
    @Path("/parametersMaster")
    public List<Datamaster> ListParameters() {
        try {
            List<Datamaster> lista = _Datamaster.findAll();
            if (lista != null) {
                return lista;
            }
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
        return null;
    }

    @GET
    @Path("/parametersType")
    public List<Datatype> ListAllParameters() {
        try {
            List<Datatype> lista = _Datatype.findAll();
            if (lista != null) {
                return lista;
            }
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
        return null;
    }

    @GET
    @Path("/departments")
    public List<Departamento> ListDepartments() {
        try {
            List<Departamento> lista = _Department.findAll();
            if (lista != null) {
                return lista;
            }
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
        return null;
    }

    @GET
    @Path("/city/{idDepartamento}")
    public List<Ciudad> FindCities(@PathParam("idDepartamento") int idDepartamento) {
        try {
            List<Ciudad> lista = _Ciudad.findByDepartamento(idDepartamento);
            if (lista != null) {
                return lista;
            }
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
        return null;
    }

    @GET
    @Path("/roles")
    public List<Rol> ListRoles() {
        try {
            List<Rol> lista = _Rol.findAll();
            if (lista != null) {
                return lista;
            }
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
        return null;
    }
}
