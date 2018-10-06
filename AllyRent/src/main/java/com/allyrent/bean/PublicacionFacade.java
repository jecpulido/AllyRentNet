/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allyrent.bean;

import com.allyrent.entidades.Publicacion;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

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

    public List<Publicacion> findIdUsuario(int idUsuario) {
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

    public List<Publicacion> indexPublicacion(int idUsuario) {
        try {
            Query q = em.createNamedQuery("Publicacion.findIndexPublicaciones").
                    setParameter("idUsuario", idUsuario);
            List<Publicacion> publicaciones = q.getResultList();

            return publicaciones;
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
        return null;
    }

    public List<Publicacion> busquedaAvanzada(String nombreUsuario, String correoElectronico, int idCiudad,
            int idModelo, int idTPublicacion, Date fechaPublicacion, Date fechaInicio, Date fechaFin) {
        try {
            String query = "SELECT p FROM Publicacion p WHERE 1 = 1";

            if (nombreUsuario != null) {
                query += " AND UPPER(CONCAT(p.idUsuario.nombre,' ',p.idUsuario.apellido)) LIKE UPPER(:nombreUsuario)";
            }

            if (correoElectronico != null) {
                query += " AND UPPER(p.idUsuario.idLogin.correo) LIKE UPPER(:correoElectronico)";
            }

            if (idCiudad != 0) {
                query += " AND p.idUsuario.idCiudad.idCiudad = :idCiudad";
            }

            if (idModelo != 0) {
                query += " AND (p.idVehiculo IN (SELECT v.idVehiculo FROM Vehiculo v "
                        + "WHERE v.idModelo.idModelo = :idModelo)) ";
            }

            if (idTPublicacion != 0) {
                query += " AND p.idTipoPublicacion.idDataType = :idTPublicacion";
            }

            if (fechaPublicacion != null) {
                query += " AND p.fechaPublicacion >= :fechaPublicacion";
            }

            if (fechaInicio != null && fechaFin != null) {
                query += " AND (p.fechaInicio <= :fechaInicio AND p.fechaFin >= :fechaFin)";
            }

            Query q = em.createQuery(query);
            if (nombreUsuario != null) {
                q.setParameter("nombreUsuario", "%" + nombreUsuario + "%");
            }
            
            if (correoElectronico != null) {
                q.setParameter("correoElectronico", "%" + correoElectronico + "%");
            }
            if (idCiudad != 0) {
                q.setParameter("idCiudad", idCiudad);
            }

            if (idModelo != 0) {
                q.setParameter("idModelo", idModelo);
            }

            if (idTPublicacion != 0) {
                q.setParameter("idTPublicacion", idTPublicacion);
            }

            if (fechaPublicacion != null) {
                q.setParameter("fechaPublicacion", fechaPublicacion, TemporalType.DATE);
            }

            if (fechaInicio != null && fechaFin != null) {
                q.setParameter("fechaInicio", fechaInicio, TemporalType.DATE);
                q.setParameter("fechaFin", fechaFin, TemporalType.DATE);
            }

            List<Publicacion> listado = q.getResultList();

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
