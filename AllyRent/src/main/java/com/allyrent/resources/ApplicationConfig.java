/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allyrent.resources;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Jorge
 */
@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.allyrent.resources.ContratoResource.class);
        resources.add(com.allyrent.resources.GeneralResource.class);
        resources.add(com.allyrent.resources.PublicacionResource.class);
        resources.add(com.allyrent.resources.SolicitudResoruce.class);
        resources.add(com.allyrent.resources.UsuarioResource.class);
        resources.add(com.allyrent.resources.VehiculoResource.class);
    }
    
}
