/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allyrent.bean;

import com.allyrent.entidades.Login;
import com.allyrent.entidades.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Jorge
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "com.AllyRent_AllyRent_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public Usuario Login(String correo, String pass) {
        try {
            Query q = em.createNamedQuery("Login.login").
                    setParameter("correo", correo)
                    .setParameter("contrasena", pass);
            List<Login> listado = q.getResultList();

            if (!listado.isEmpty()) {
                for (Login login : listado) {
                    if (correo.equals(login.getCorreo()) && pass.equals(login.getContrasena())) {
                        Usuario user = this.findLogin(login.getIdLogin());
                        if (user != null) {
                            return user;
                        }
                    }
                }

            }
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
        return null;
    }

    public Usuario findLogin(int idLogin) {
        try {
            Query q = em.createNamedQuery("Usuario.findByIdLogin").
                    setParameter("idLogin", idLogin);
            List<Usuario> usuarios = q.getResultList();

            if (!usuarios.isEmpty()) {
                return usuarios.get(0);
            }
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
        return null;
    }

    public long countVehiculos(int idUsuario) {
        try {
            Query q = em.createNamedQuery("Vehiculo.Count").
                    setParameter("idUsuario", idUsuario);
            long vehiculos = (long) q.getSingleResult();

            return vehiculos;
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
        return 0;
    }

    public long countPublicaciones(int idUsuario) {
        try {
            Query q = em.createNamedQuery("Publicacion.Count").
                    setParameter("idUsuario", idUsuario);
            long publicaciones = (long) q.getSingleResult();

            return publicaciones;
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
        return 0;
    }

}
