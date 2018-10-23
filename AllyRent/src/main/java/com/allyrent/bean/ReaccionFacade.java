/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allyrent.bean;

import com.allyrent.entidades.Reaccion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author pulidoje
 */
@Stateless
public class ReaccionFacade extends AbstractFacade<Reaccion> {

    @PersistenceContext(unitName = "com.AllyRent_AllyRent_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReaccionFacade() {
        super(Reaccion.class);
    }

    public Reaccion ValidateReaccion(int idPublicacion, int idUsuario) {
        try {
            List<Reaccion> reacciones;
            Query q = em.createNamedQuery("Reaccion.findByIdPubIdUsu")
                    .setParameter("idPublicacion", idPublicacion)
                    .setParameter("idUsuario", idUsuario);
            reacciones = q.getResultList();
            if (!reacciones.isEmpty()) {
                if (reacciones.size() > 0) {
                    return reacciones.get(0);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Reaccion> FindReaccionByPublicacion(int idPublicacion) {
        try {
            List<Reaccion> reacciones;
            Query q = em.createNamedQuery("Reaccion.findByIdPublicacion")
                    .setParameter("idPublicacion", idPublicacion);
            reacciones = q.getResultList();
            if (!reacciones.isEmpty()) {
                return reacciones;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
