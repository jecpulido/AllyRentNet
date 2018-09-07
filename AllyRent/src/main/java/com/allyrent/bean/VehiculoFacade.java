/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allyrent.bean;

import com.allyrent.entidades.Vehiculo;
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
public class VehiculoFacade extends AbstractFacade<Vehiculo> {

    @PersistenceContext(unitName = "com.AllyRent_AllyRent_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VehiculoFacade() {
        super(Vehiculo.class);
    }
    
    public List<Vehiculo> ListVehiclesByUsuario(int idUsuario){
         try {
            Query q = em.createNamedQuery("Vehiculo.findByUsuario").
                    setParameter("idUsuario", idUsuario);
            List<Vehiculo> vehiculos = q.getResultList();

            if (!vehiculos.isEmpty()) {
                return vehiculos;
            }
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
        return null;
    }
    
}
