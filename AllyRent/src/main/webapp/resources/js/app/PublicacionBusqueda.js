/// <reference path="../knockout-2.3.0.debug.js" />

var PublicacionHomeViewModel = function () {
    self = this;
    self.idUsuario = ko.observable();
    self.nombreUsuario = ko.observable();
    self.correoElectronico = ko.observable();
    self.idMarca = ko.observable();
    self.idModelo = ko.observable();
    self.placa = ko.observable();
    self.ano = ko.observable();
    self.idDpto = ko.observable();
    self.idCiudad = ko.observable();
    self.idTPublicacion = ko.observable();
    self.fechaPublicacion = ko.observable();
    self.fechaInicio = ko.observable();
    self.fechaFin = ko.observable();

    //Listas
    self.tPublicacionList = ko.observableArray();// array y tambien obserbable
    self.dptoList = ko.observableArray();// array y tambien obserbable
    self.ciudadList = ko.observableArray();// array y tambien obserbable    
    self.marcaList = ko.observableArray();// array y tambien obserbable    
    self.modeloList = ko.observableArray();// array y tambien obserbable    

    self.publicacionesList = ko.observableArray();// array y tambien obserbable
    self.usuariosList = ko.observableArray();// array y tambien obserbable
    self.vehiculosList = ko.observableArray();// array y tambien obserbable

    self.getAll = function () {
        $.getJSON('/AllyRent/api/general/findType/' + 6, function (data) {
            self.tPublicacionList(data);
        });
        $.getJSON('/AllyRent/api/vehiculos/marca', function (data) {
            self.marcaList(data);
        });
        $.getJSON('/AllyRent/api/general/departments', function (data) {
            self.dptoList(data);
        });
    },
            self.getObjectBusqueda = function () {
                var BusquedaDTO = {
                    "idUsuario": self.idUsuario(),
                    "nombreUsuario": self.nombreUsuario(),
                    "correoElectronico": self.correoElectronico(),
                    "idMarca": self.idMarca(),
                    "idModelo": self.idModelo(),
                    "placa": self.placa(),
                    "ano": self.ano(),
                    "idDpto": self.idDpto(),
                    "idCiudad": self.idCiudad(),
                    "idTPublicacion": self.idTPublicacion(),
                    "fechaPublicacion": self.fechaPublicacion() + "T00:00:00-05:00",
                    "fechaInicio": self.fechaInicio() + "T00:00:00-05:00",
                    "fechaFin": self.fechaFin() + "T00:00:00-05:00"
                };
                return BusquedaDTO;
            },
            self.compararValores = function (item1, item2) {
                console.log(item1);
                console.log(item2);
                return item1.toString() == item2.toString();
            },
            self.getModelo = function () {
                $.getJSON('/AllyRent/api/vehiculos/modelo/' + self.idMarca(), function (data) {
                    self.modeloList(data);
                });
            },
            self.getCiudad = function () {
                $.getJSON('/AllyRent/api/general/city/' + self.idDpto(), function (data) {
                    self.ciudadList(data);
                });
            },
            self.insertSolicitud = function (id) {
                var url = '/AllyRent/api/solicitudes/ToggleSolicitud';
                var solicitud = {
                    "estado": 1,
                    "idPublicacion": {
                        "idPublicacion": id
                    },
                    "idUsuario": {
                        "idUsuario": self.idUsuario()
                    }
                };

                $.ajax({
                    url: url,
                    type: 'POST',
                    data: ko.toJSON(solicitud),
                    contentType: "application/json;chartset=utf-8",
                    statusCode: {
                        200: function (data) {
                            if (data.responseText === "OK") {
                                alert('Solicitud realizada');
                            } else if (data.responseText === "DELETE") {
                                alert('Solicitud Cancelada');
                            }
                        },
                        204: function () {
                            alert('Error');
                        }
                    }
                });
            },
            self.seguirUsuario = function (idUsuario2) {
                var url = '/AllyRent/api/solicitudes/Seguir';
                var relacion = {
                    "usuario": {
                        "idUsuario": self.idUsuario()
                    },
                    "usuario1": {
                        "idUsuario": idUsuario2
                    }
                };

                $.ajax({
                    url: url,
                    type: 'POST',
                    data: ko.toJSON(relacion),
                    contentType: "application/json;chartset=utf-8",
                    statusCode: {
                        200: function (data) {
                            if (data.responseText === "OK") {
                                alert('OK');
                                self.buscarSugerencias();
                                self.getAllPost();
                            }
                        },
                        204: function () {
                            alert('Error');
                        }
                    }
                });
            },
            self.search = function () {
                var url = '/AllyRent/api/publicaciones/searchAdvance';
                $.ajax({
                    url: url,
                    type: 'POST',
                    data: ko.toJSON(self.getObjectBusqueda()),
                    contentType: "application/json;chartset=utf-8",
                    statusCode: {
                        200: function (data) {
                            console.log(data.publicacion);
                            console.log(data.usuario);
                            console.log(data.vehiculo);

                            self.publicacionesList(data.publicacion);
                            self.usuariosList(data.usuario);
                            self.vehiculosList(data.vehiculo);


                        },
                        204: function () {
                            alert('Error');
                        }
                    }
                });
            };

};
$(document).ready(function () {
    var postvm = new PublicacionHomeViewModel();
    ko.applyBindings(postvm);
    postvm.idUsuario(sessionStorage.idusuario);
    postvm.getAll();
});