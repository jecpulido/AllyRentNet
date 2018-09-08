/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allyrent.bean;

import com.allyrent.entidades.Solicitud;
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
public class SolicitudFacade extends AbstractFacade<Solicitud> {

    @PersistenceContext(unitName = "com.AllyRent_AllyRent_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SolicitudFacade() {
        super(Solicitud.class);
    }

    public List<Solicitud> FindSolicitudList(int idUsuario) {
        try {
            Query q = em.createNamedQuery("Solicitud.findByIdUsuario").
                    setParameter("idUsuario", idUsuario);
            List<Solicitud> listado = q.getResultList();
            return listado;

        } catch (Exception e) {
        }
        return null;
    }

    public Solicitud FindSolicitudByUsuario(int idUsuario, int idPublicacion) {
        try {
            Query q = em.createNamedQuery("Solicitud.findByIdUsuarioIdPublicacion").
                    setParameter("idUsuario", idUsuario)
                    .setParameter("idPublicacion", idPublicacion);
            List<Solicitud> listado = q.getResultList();
            return listado.get(0);

        } catch (Exception e) {
        }
        return null;
    }

    public List<Solicitud> FindSolicitudByUsuarioCreate(int idUsuario) {
        try {
            Query q = em.createNamedQuery("Solicitud.findByIdUsuarioCreate").
                    setParameter("idUsuario", idUsuario);
            List<Solicitud> listado = q.getResultList();
            return listado;

        } catch (Exception e) {
            System.out.println(""+e.getMessage());
        }
        return null;
    }
}
