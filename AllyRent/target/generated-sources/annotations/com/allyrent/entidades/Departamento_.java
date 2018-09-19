package com.allyrent.entidades;

import com.allyrent.entidades.Ciudad;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-09-18T13:05:07")
@StaticMetamodel(Departamento.class)
public class Departamento_ { 

    public static volatile SingularAttribute<Departamento, Integer> idDepartamento;
    public static volatile SingularAttribute<Departamento, String> nombreDepartamento;
    public static volatile CollectionAttribute<Departamento, Ciudad> ciudadCollection;

}