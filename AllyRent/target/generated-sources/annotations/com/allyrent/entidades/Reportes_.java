package com.allyrent.entidades;

import com.allyrent.entidades.ReportesPK;
import com.allyrent.entidades.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-09-17T13:47:48")
@StaticMetamodel(Reportes.class)
public class Reportes_ { 

    public static volatile SingularAttribute<Reportes, Boolean> estado;
    public static volatile SingularAttribute<Reportes, String> causa;
    public static volatile SingularAttribute<Reportes, Usuario> usuario;
    public static volatile SingularAttribute<Reportes, Usuario> usuario1;
    public static volatile SingularAttribute<Reportes, ReportesPK> reportesPK;
    public static volatile SingularAttribute<Reportes, Date> fechaReporte;

}