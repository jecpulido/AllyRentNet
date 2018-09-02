/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allyrent.bean;

import com.allyrent.entidades.Ciudad;
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
public class CiudadFacade extends AbstractFacade<Ciudad> {

    @PersistenceContext(unitName = "com.AllyRent_AllyRent_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CiudadFacade() {
        super(Ciudad.class);
    }
    
    public List<Ciudad> findByDepartamento(int idDepartamento){
        try {
            Query q = em.createNamedQuery("Ciudad.findByIidDepartamento").setParameter("idDepartamento", idDepartamento);
            List<Ciudad> listado = q.getResultList();

            if (!listado.isEmpty()) {
                return listado;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("" + e.getMessage());
            return null;
        }
        
    }
    
}
