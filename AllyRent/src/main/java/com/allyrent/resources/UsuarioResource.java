/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allyrent.resources;

import com.allyrent.DTO.UsuarioDTO;
import com.allyrent.bean.*;
import com.allyrent.entidades.*;
import com.allyrent.utility.ImageToArray;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Jorge
 */
@Path("usuarios")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class UsuarioResource {

    @EJB
    UsuarioFacade _user;

    @EJB
    LoginFacade _login;

    @EJB
    RelacionesFacade _relaciones;

    @POST
    @Path("/create")
    public Usuario createUser(UsuarioDTO user) {
        Usuario usuario = new Usuario();
        Login login = new Login();
        try {
            if (user != null) {
                if (user.getIdLogin() != null) {
                    login.setContrasena(user.getIdLogin().getContrasena());
                    login.setCorreo(user.getIdLogin().getCorreo());
                    login.setUltimaConexion(new Date());
                    _login.create(login);
                    if (login.getIdLogin() != null) {
                        usuario.setIdLogin(login);
                        usuario.setNombre(user.getNombre());
                        usuario.setApellido(user.getApellido());
                        usuario.setTelefono(user.getTelefono());
                        usuario.setDni(user.getDni());
                        usuario.setFechaNacimiento(user.getFechaNacimiento());
                        usuario.setIdCiudad(new Ciudad(user.getIdCiudad()));
                        usuario.setIdRol(new Rol(user.getIdRol()));
                        usuario.setIdSexo(new Datatype(user.getIdSexo()));
                        usuario.setIdTipoDocumento(new Datatype(user.getIdTipoDocumento()));
                        byte[] foto = ImageToArray.convertStringToImageByteArray(user.getRutaFoto());
                        usuario.setRutaFoto(foto);
                        _user.create(usuario);
                        if (usuario.getIdUsuario() != null) {
                            Relaciones relacion = new Relaciones(usuario.getIdUsuario(), usuario.getIdUsuario());
                            relacion.setRelacionesPK(new RelacionesPK(usuario.getIdUsuario(), usuario.getIdUsuario()));
                            relacion.setFechaRelacion(new Date());
                            _relaciones.create(relacion);
                        }
                        return usuario;
                    }
                }
            }

        } catch (Exception e) {
            if (usuario.getIdUsuario() == null) {
                _login.remove(login);
            }
        }
        return null;
    }

    @POST
    @Path("/login")
    public UsuarioDTO LoginPost(Login login) {
        try {
            String correo = login.getCorreo();
            String password = login.getContrasena();
            Usuario user = _user.Login(correo, password);
            if (user != null) {
                UsuarioDTO userDTO = new UsuarioDTO(user);
                userDTO.setNumeroPublicaciones((int) _user.countPublicaciones(userDTO.getIdUsuario()));
                userDTO.setNumeroVehiculos((int) _user.countVehiculos(userDTO.getIdUsuario()));
                return userDTO;
            }
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
        return null;
    }

    @GET
    @Path("/sugerencias/{id}")
    public List<UsuarioDTO> buscarSugerencias(@PathParam("id") int idUsuario) {
        try {
            List<Usuario> usuarios = _user.buscarSugerencias(idUsuario);
            List<UsuarioDTO> usuariosDTO = new ArrayList<>();
            for (Usuario usuario : usuarios) {
                usuariosDTO.add(new UsuarioDTO(usuario));
            }
            return usuariosDTO;
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
        return null;
    }

    @GET
    @Path("/{idUsuario}")
    public UsuarioDTO buscarUsuario(@PathParam("idUsuario") int idUsuario) {
        try {
            Usuario user = _user.find(idUsuario);
            if (user != null) {
                UsuarioDTO userDTO = new UsuarioDTO(user);
                userDTO.setNumeroPublicaciones((int) _user.countPublicaciones(userDTO.getIdUsuario()));
                userDTO.setNumeroVehiculos((int) _user.countVehiculos(userDTO.getIdUsuario()));
                return userDTO;
            }
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
        return null;
    }

    @PUT
    @Path("/update/{idUsuario}")
    public Usuario actualizarUsuario(UsuarioDTO user, @PathParam("idUsuario") int idUsuario) {
        Usuario usuario = null;
        Login login = null;
        try {
            usuario = _user.find(idUsuario);

            if (usuario == null) {
                return null;
            }

            login = _login.find(usuario.getIdLogin().getIdLogin());

            if (login != null) {
                if (user != null) {

                    if (!login.getCorreo().equals(user.getIdLogin().getCorreo())) {
                        login.setCorreo(user.getIdLogin().getCorreo());
                        _login.edit(login);
                    }
                    usuario.setIdLogin(login);
                    if (!usuario.getNombre().equals(user.getNombre())) {
                        usuario.setNombre(user.getNombre());
                    }
                    if (!usuario.getApellido().equals(user.getApellido())) {
                        usuario.setApellido(user.getApellido());
                    }
                    if (!usuario.getTelefono().equals(user.getTelefono())) {
                        usuario.setTelefono(user.getTelefono());
                    }
                    if (!usuario.getDni().equals(user.getDni())) {
                        usuario.setDni(user.getDni());
                    }
                    if (!usuario.getFechaNacimiento().equals(user.getFechaNacimiento())) {
                        usuario.setFechaNacimiento(user.getFechaNacimiento());
                    }
                    if (!usuario.getIdCiudad().getIdCiudad().equals(user.getIdCiudad())) {
                        usuario.setIdCiudad(new Ciudad(user.getIdCiudad()));
                    }
                    if (!usuario.getIdRol().getIdRol().equals(user.getIdRol())) {
                        usuario.setIdRol(new Rol(user.getIdRol()));
                    }
                    if (!usuario.getIdSexo().getIdDataType().equals(user.getIdSexo())) {
                        usuario.setIdSexo(new Datatype(user.getIdSexo()));
                    }
                    if (!usuario.getIdTipoDocumento().getIdDataType().equals(user.getIdTipoDocumento())) {
                        usuario.setIdTipoDocumento(new Datatype(user.getIdTipoDocumento()));
                    }
                    //byte[] foto = ImageToArray.convertStringToImageByteArray(user.getRutaFoto());
                    //usuario.setRutaFoto(foto);
                    _user.edit(usuario);
//                    if (usuario.getIdUsuario() != null) {
//                        Relaciones relacion = new Relaciones(usuario.getIdUsuario(), usuario.getIdUsuario());
//                        relacion.setRelacionesPK(new RelacionesPK(usuario.getIdUsuario(), usuario.getIdUsuario()));
//                        relacion.setFechaRelacion(new Date());
//                        _relaciones.create(relacion);
//                    }
                    return usuario;

                }
            }

        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
