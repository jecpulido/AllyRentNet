package com.allyrent.entidades;

import com.allyrent.entidades.ContratoPK;
import com.allyrent.entidades.Usuario;
import com.allyrent.entidades.Vehiculo;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-09-17T13:47:48")
@StaticMetamodel(Contrato.class)
public class Contrato_ { 

    public static volatile SingularAttribute<Contrato, Date> fechaInicio;
    public static volatile SingularAttribute<Contrato, Float> valor;
    public static volatile SingularAttribute<Contrato, Usuario> usuario;
    public static volatile SingularAttribute<Contrato, Usuario> usuario1;
    public static volatile SingularAttribute<Contrato, Vehiculo> vEHICULOidVehiculo;
    public static volatile SingularAttribute<Contrato, ContratoPK> contratoPK;
    public static volatile SingularAttribute<Contrato, Date> fechaFin;

}