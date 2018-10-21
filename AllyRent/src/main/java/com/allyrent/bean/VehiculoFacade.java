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
    
    public List<Vehiculo> busquedaAvanzada(String nombreUsuario, String correoElectronico,
            String placa,int ano,int idModelo) {
        try {                
             String query = "SELECT v FROM Vehiculo v WHERE 1=1 ";
                    
            if (nombreUsuario != null) {
                query += " AND UPPER(CONCAT(v.idUsuario.nombre,' ',v.idUsuario.apellido)) LIKE UPPER(:nombreUsuario)";
            }

            if (correoElectronico != null) {
                query += " AND u.idLogin.correo LIKE :correoElectronico";
            }

            if (placa != null) {
                query +=" AND UPPER(v.placa) LIKE UPPER(:placa)";
            }
            
            if (ano != 0) {
                query += " AND v.ano = :ano";
            }
            
            if (idModelo != 0) {
                query += " AND v.idModelo.idMarca = :idModelo";
            }
            
            Query q = em.createQuery(query);
            
            if (nombreUsuario != null) {
                q.setParameter("nombreUsuario", "%" + nombreUsuario + "%");
            }

            if (correoElectronico != null) {
                q.setParameter("correoElectronico", "%" + correoElectronico +"%");
            }
            
            if (placa != null) {
                q.setParameter("placa", placa);
            }
            
            if (ano != 0) {
                q.setParameter("ano", ano);
            }

            if (idModelo != 0) {
                q.setParameter("idModelo", idModelo);
            }      

            List<Vehiculo> listado = q.getResultList();

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
