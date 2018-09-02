/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allyrent.bean;

import com.allyrent.entidades.Datatype;
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
public class DatatypeFacade extends AbstractFacade<Datatype> {

    @PersistenceContext(unitName = "com.AllyRent_AllyRent_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DatatypeFacade() {
        super(Datatype.class);
    }

    public List<Datatype> ListarTipos(int idDataMaster) {
        try {
            Query q = em.createNamedQuery("Datatype.findByIdDataMaster").setParameter("idDataMaster", idDataMaster);
            List<Datatype> listado = q.getResultList();

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
