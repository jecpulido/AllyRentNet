package com.allyrent.entidades;

import com.allyrent.entidades.Marca;
import com.allyrent.entidades.Vehiculo;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-09-18T13:05:07")
@StaticMetamodel(Modelo.class)
public class Modelo_ { 

    public static volatile SingularAttribute<Modelo, Integer> idModelo;
    public static volatile SingularAttribute<Modelo, Marca> idMarca;
    public static volatile SingularAttribute<Modelo, String> nombre;
    public static volatile CollectionAttribute<Modelo, Vehiculo> vehiculoCollection;

}