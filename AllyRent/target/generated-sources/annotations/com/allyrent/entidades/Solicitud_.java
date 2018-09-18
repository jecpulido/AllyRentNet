package com.allyrent.entidades;

import com.allyrent.entidades.Publicacion;
import com.allyrent.entidades.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-09-17T13:47:48")
@StaticMetamodel(Solicitud.class)
public class Solicitud_ { 

    public static volatile SingularAttribute<Solicitud, Integer> estado;
    public static volatile SingularAttribute<Solicitud, Date> fechaSolicitud;
    public static volatile SingularAttribute<Solicitud, Usuario> idUsuario;
    public static volatile SingularAttribute<Solicitud, Integer> idSolicitud;
    public static volatile SingularAttribute<Solicitud, Publicacion> idPublicacion;

}