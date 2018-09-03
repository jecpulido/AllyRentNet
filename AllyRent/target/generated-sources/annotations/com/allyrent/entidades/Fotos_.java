package com.allyrent.entidades;

import com.allyrent.entidades.Vehiculo;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-09-03T00:34:16")
@StaticMetamodel(Fotos.class)
public class Fotos_ { 

    public static volatile SingularAttribute<Fotos, Integer> idFoto;
    public static volatile SingularAttribute<Fotos, Date> fechaCarga;
    public static volatile SingularAttribute<Fotos, Vehiculo> idVehiculo;
    public static volatile SingularAttribute<Fotos, byte[]> foto;

}