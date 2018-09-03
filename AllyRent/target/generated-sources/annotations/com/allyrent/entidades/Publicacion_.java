package com.allyrent.entidades;

import com.allyrent.entidades.Datatype;
import com.allyrent.entidades.Solicitud;
import com.allyrent.entidades.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-09-03T00:34:16")
@StaticMetamodel(Publicacion.class)
public class Publicacion_ { 

    public static volatile SingularAttribute<Publicacion, Date> fechaInicio;
    public static volatile CollectionAttribute<Publicacion, Solicitud> solicitudCollection;
    public static volatile SingularAttribute<Publicacion, Usuario> idUsuario;
    public static volatile SingularAttribute<Publicacion, Datatype> idTipoPublicacion;
    public static volatile SingularAttribute<Publicacion, Date> fechaPublicacion;
    public static volatile SingularAttribute<Publicacion, Integer> idPublicacion;
    public static volatile SingularAttribute<Publicacion, Date> fechaFin;
    public static volatile SingularAttribute<Publicacion, String> publicacion;

}