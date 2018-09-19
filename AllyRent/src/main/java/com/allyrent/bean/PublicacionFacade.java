/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allyrent.bean;

import com.allyrent.entidades.Publicacion;
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
public class PublicacionFacade extends AbstractFacade<Publicacion> {

    @PersistenceContext(unitName = "com.AllyRent_AllyRent_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PublicacionFacade() {
        super(Publicacion.class);
    }
    
    public List<Publicacion> findIdUsuario(int idUsuario){
    try {
            Query q = em.createNamedQuery("Publicacion.finByIdUsuario").
                    setParameter("idUsuario", idUsuario);
            List<Publicacion> publicaciones = q.getResultList();

            return publicaciones;
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
        return null;
    }
    
}
