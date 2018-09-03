package com.allyrent.entidades;

import com.allyrent.entidades.Vehiculo;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-09-03T00:34:16")
@StaticMetamodel(Tarifa.class)
public class Tarifa_ { 

    public static volatile SingularAttribute<Tarifa, Float> porHora;
    public static volatile SingularAttribute<Tarifa, Float> porDia;
    public static volatile SingularAttribute<Tarifa, Integer> idTarifa;
    public static volatile CollectionAttribute<Tarifa, Vehiculo> vehiculoCollection;

}