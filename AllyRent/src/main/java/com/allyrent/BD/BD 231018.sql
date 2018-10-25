CREATE TABLE reaccion(
	idReaccion int AUTO_INCREMENT primary key,
	idPublicacion int,
	idUsuario int,
	bandera int,
	fechaReaccion datetime,
	foreign key (idPublicacion) references Publicacion (idPublicacion),
	foreign key (idUsuario) references Usuario (idUsuario) 
);

ALTER TABLE publicacion ADD COLUMN ubicacion varchar(100);