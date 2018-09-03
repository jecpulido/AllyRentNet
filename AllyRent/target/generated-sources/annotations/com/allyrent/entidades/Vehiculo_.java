package com.allyrent.entidades;

import com.allyrent.entidades.Comentario;
import com.allyrent.entidades.Contrato;
import com.allyrent.entidades.Datatype;
import com.allyrent.entidades.Fotos;
import com.allyrent.entidades.Modelo;
import com.allyrent.entidades.Tarifa;
import com.allyrent.entidades.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-09-03T00:34:16")
@StaticMetamodel(Vehiculo.class)
public class Vehiculo_ { 

    public static volatile SingularAttribute<Vehiculo, Datatype> idTipoVehiculo;
    public static volatile SingularAttribute<Vehiculo, Integer> km;
    public static volatile SingularAttribute<Vehiculo, Integer> idVehiculo;
    public static volatile SingularAttribute<Vehiculo, Integer> ano;
    public static volatile SingularAttribute<Vehiculo, Datatype> idTransmision;
    public static volatile SingularAttribute<Vehiculo, Usuario> idUsuario;
    public static volatile SingularAttribute<Vehiculo, Boolean> isPlacaPar;
    public static volatile CollectionAttribute<Vehiculo, Fotos> fotosCollection;
    public static volatile SingularAttribute<Vehiculo, Tarifa> idTarifa;
    public static volatile SingularAttribute<Vehiculo, Datatype> idCombustible;
    public static volatile SingularAttribute<Vehiculo, Modelo> idModelo;
    public static volatile CollectionAttribute<Vehiculo, Contrato> contratoCollection;
    public static volatile CollectionAttribute<Vehiculo, Comentario> comentarioCollection;
    public static volatile SingularAttribute<Vehiculo, String> placa;

}