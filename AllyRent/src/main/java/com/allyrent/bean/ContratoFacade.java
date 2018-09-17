/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allyrent.bean;

import com.allyrent.entidades.Contrato;
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
public class ContratoFacade extends AbstractFacade<Contrato> {

    @PersistenceContext(unitName = "com.AllyRent_AllyRent_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContratoFacade() {
        super(Contrato.class);
    }
    
     public Contrato FindSolicitudByUsuario(int idPropietario, int idOcupante) {
        try {
            Query q = em.createNamedQuery("Contrato.findByPropietarioOcupante").
                    setParameter("idPropietario", idPropietario)
                    .setParameter("idOcupante", idOcupante);
            List<Contrato> listado = q.getResultList();
            return listado.get(0);

        } catch (Exception e) {
        }
        return null;
    }
    
}
