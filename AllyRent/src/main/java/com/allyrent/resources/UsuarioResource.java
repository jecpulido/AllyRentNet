/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allyrent.resources;

import com.allyrent.bean.*;
import com.allyrent.entidades.*;
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
    public Usuario createUser(Usuario user) {
        try {
            if (user != null) {
                Login login = user.getIdLogin();
                if(login != null){
                    _login.create(login);
                }
                user.setIdLogin(login);

                _user.create(user);
            }

            return user;
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
        return null;
    }

    @POST
    @Path("/login")
    public Usuario LoginPost(Login login) {
        try {
            String correo = login.getCorreo();
            String password = login.getContrasena();
            Usuario user = _user.Login(correo, password);
                if (user != null) {
                return user;
            }
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
        return null;
    }

}
