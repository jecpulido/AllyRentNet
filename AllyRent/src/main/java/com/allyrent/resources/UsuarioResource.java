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
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
                        return usuario;
                    }
                }
            }

        } catch (Exception e) {
            if (usuario.getIdUsuario() == null){
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
                userDTO.setNumeroPublicaciones((int)_user.countPublicaciones(userDTO.getIdUsuario()));
                userDTO.setNumeroVehiculos((int)_user.countVehiculos(userDTO.getIdUsuario()));
                return userDTO;
            }
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
        return null;
    }

}
