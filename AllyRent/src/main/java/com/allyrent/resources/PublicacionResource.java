/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allyrent.resources;

import com.allyrent.DTO.BusquedaDTO;
import com.allyrent.DTO.PublicacionDTO;
import com.allyrent.DTO.VehiculoDTO;
import com.allyrent.bean.PublicacionFacade;
import com.allyrent.bean.ReaccionFacade;
import com.allyrent.bean.UsuarioFacade;
import com.allyrent.bean.VehiculoFacade;
import com.allyrent.entidades.Publicacion;
import com.allyrent.entidades.Reaccion;
import com.allyrent.entidades.Usuario;
import com.allyrent.entidades.Vehiculo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
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

    @EJB
    UsuarioFacade _usuarioFacade;

    @EJB
    ReaccionFacade _reaccionFacade;

    /**
     * crear una publicacion
     *
     * @param publicacion
     * @return
     */
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

    /**
     * lista las publiaciones de un solo usuario
     *
     * @param id
     * @return
     */
    @GET
    @Path("/{idUsuario}")
    public List<PublicacionDTO> IndexPublicaciones(@PathParam("idUsuario") int id) {
        try {
            List<Publicacion> publicaciones = _publicacionFacade.indexPublicacion(id);
            List<PublicacionDTO> publicacionesDTO = null;

            if (publicaciones.size() > 0) {
                publicacionesDTO = new ArrayList<>();

                for (Publicacion pub : publicaciones) {
                    PublicacionDTO p = new PublicacionDTO(pub);
                    if (pub.getIdVehiculo() != null) {
                        p.setVehiculo(new VehiculoDTO(_vehiculoFacade.find(pub.getIdVehiculo())));
                        p.getVehiculo().setIdUsuario(null);
                    }
                    List<Reaccion> reacciones = _reaccionFacade.FindReaccionByPublicacion(p.getIdPublicacion());
                    if (reacciones != null) {
                        List<Reaccion> likes = reacciones.stream().filter(r -> r.getBandera() == 1).collect(Collectors.toList());
                        if (likes != null) {
                            p.setLike(likes.size());
                        }

                        List<Reaccion> dislikes = reacciones.stream().filter(r -> r.getBandera() == 0).collect(Collectors.toList());
                        if (dislikes != null) {
                            p.setDisLike(dislikes.size());                            
                        }

                        List<Reaccion> me = reacciones.stream().filter(r
                                -> Objects.equals(r.getIdUsuario().getIdUsuario(), id))
                                .collect(Collectors.toList());
                        if (me != null && me.size() > 0) {
                            String re = me.get(0).getBandera() == 1 ? "like" : "dislike";
                            p.setReaccion(re);
                        }
                    }
                    publicacionesDTO.add(p);
                }
            }
            return publicacionesDTO;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * lista las publiaciones de un solo usuario
     *
     * @param id
     * @return
     */
    @GET
    @Path("/list/{idUsuario}")
    public List<PublicacionDTO> ListPublicacionById(@PathParam("idUsuario") int id) {
        try {
            List<Publicacion> publicaciones = _publicacionFacade.findIdUsuario(id);
            List<PublicacionDTO> publicacionesDTO = null;

            if (publicaciones.size() > 0) {
                publicacionesDTO = new ArrayList<>();

                for (Publicacion pub : publicaciones) {
                    PublicacionDTO p = new PublicacionDTO(pub);
                    if (pub.getIdVehiculo() != null) {
                        p.setVehiculo(new VehiculoDTO(_vehiculoFacade.find(pub.getIdVehiculo())));
                        p.getVehiculo().setIdUsuario(null);
                    }
                    List<Reaccion> reacciones = _reaccionFacade.FindReaccionByPublicacion(p.getIdPublicacion());
                    if (reacciones != null) {
                        List<Reaccion> likes = reacciones.stream().filter(r -> r.getBandera() == 1).collect(Collectors.toList());
                        if (likes != null) {
                            p.setLike(likes.size());
                        }

                        List<Reaccion> dislikes = reacciones.stream().filter(r -> r.getBandera() == 0).collect(Collectors.toList());
                        if (dislikes != null) {
                            p.setLike(dislikes.size());                            
                        }

                        List<Reaccion> me = reacciones.stream().filter(r
                                -> Objects.equals(r.getIdUsuario().getIdUsuario(), id))
                                .collect(Collectors.toList());
                        if (me != null && me.size() > 0) {
                            String re = me.get(0).getBandera() == 1 ? "like" : "dislike";
                            p.setReaccion(re);
                        }
                    }
                    publicacionesDTO.add(p);
                }
            }
            return publicacionesDTO;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Busca una publicacion por su id
     *
     * @param id
     * @return
     */
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

    @POST
    @Path("/searchAdvance")
    public BusquedaDTO SearchAdvance(BusquedaDTO busqueda) {
        try {
            List<Publicacion> publicaciones = null;
            List<Vehiculo> vehiculos = null;
            List<Usuario> usuarios = null;
            BusquedaDTO response;

            publicaciones = _publicacionFacade.busquedaAvanzada(busqueda.getNombreUsuario(), busqueda.getCorreoElectronico(),
                    busqueda.getIdCiudad(), busqueda.getIdModelo(), busqueda.getIdTPublicacion(),
                    busqueda.getFechaPublicacion(), busqueda.getFechaInicio(), busqueda.getFechaFin());

            vehiculos = _vehiculoFacade.busquedaAvanzada(busqueda.getNombreUsuario(),
                    busqueda.getCorreoElectronico(), busqueda.getPlaca(), busqueda.getAño(),
                    busqueda.getIdModelo());

            usuarios = _usuarioFacade.busquedaAvanzada(busqueda.getNombreUsuario(),
                    busqueda.getCorreoElectronico(), busqueda.getIdCiudad());

            response = new BusquedaDTO(publicaciones, usuarios, vehiculos);

            if (response.getPublicacion().size() > 0) {
                for (PublicacionDTO publicacionDTO : response.getPublicacion()) {
                    if (publicacionDTO.getVehiculo() != null) {
                        int idVehiculo = publicacionDTO.getVehiculo().getIdVehiculo();
                        publicacionDTO.setVehiculo(new VehiculoDTO(_vehiculoFacade.find(idVehiculo)));
                    }
                }
            }

            return response;
        } catch (Exception e) {
            return null;
        }
    }

    @POST
    @Path("/LikeDislike")
    public String LikeDislike(Reaccion reaccion) {
        String respuesta;
        try {
            if (reaccion != null) {
                Reaccion rea = _reaccionFacade.ValidateReaccion(reaccion.getIdPublicacion()
                        .getIdPublicacion(), reaccion.getIdUsuario().getIdUsuario());

                if (rea != null) {
                    if (Objects.equals(rea.getBandera(), reaccion.getBandera())) {
                        _reaccionFacade.remove(rea);//Eliminar
                    } else {
                        rea.setBandera(reaccion.getBandera());
                        rea.setFechaReaccion(new Date());
                        _reaccionFacade.edit(rea);//Actualizar
                    }
                } else {
                    reaccion.setFechaReaccion(new Date());
                    _reaccionFacade.create(reaccion);//Crear
                }
            }
            return "OK";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
