/// <reference path="../knockout-2.3.0.debug.js" />

var HomeViewModel = function () {
    self = this;
    self.tipoPublicacion = ko.observable();
    self.idUsuario = ko.observable();
    self.publicacion = ko.observable();
    self.fechaInicio = ko.observable();
    self.fechaFin = ko.observable();
    self.idVehiculo = ko.observable();
    self.txtBuscar = ko.observable();

    //Listas
    self.publicacionesList = ko.observableArray();// array y tambien obserbable
    self.sugerenciasList = ko.observableArray();// array y tambien obserbable


    self.getAllPost = function () {
        $.getJSON('/AllyRent/api/publicaciones/' + self.idUsuario(), function (data) {
            self.publicacionesList(data);
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
            self.createPublicacion = function () {
                var url = '/AllyRent/api/publicaciones/create';
                $.ajax({
                    url: url,
                    type: 'POST',
                    data: ko.toJSON(self.getObjectPublicacion()),
                    contentType: "application/json;chartset=utf-8",
                    statusCode: {
                        200: function (data) {
                            if (data.responseText === "OK") {
                                self.clearForm();
                                alert('Publicacion was created');
                                window.location.href = "http://localhost:8080/AllyRent/";
                            }
                            alert(data.responseText);
                        },
                        204: function () {
                            alert('Error');
                        }
                    }
                });
            },
            self.clearForm = function () {
                self.tipoPublicacion("");
                self.idUsuario("");
                self.publicacion("");
                self.fechaInicio("");
                self.fechaFin("");
            },
            self.getObjectPublicacion = function () {
                var publicacion = {
                    "estado": 1,
                    "fechaSolicitud": "2018-09-07T14:00:00-05:00",
                    "idPublicacion": {
                        "idPublicacion": 1
                    },
                    "idSolicitud": 1,
                    "idUsuario": {
                        "idUsuario": 1
                    }
                };

                return publicacion;
            },
            self.compararValores = function (item1, item2) {
                console.log(item1);
                console.log(item2);
                return item1.toString() == item2.toString();
            },
            self.buscarSugerencias = function () {
                $.getJSON('/AllyRent/api/usuarios/sugerencias/' + self.idUsuario(), function (data) {
                    self.sugerenciasList(data);
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
            self.buscarAmigos = function () {
                var coincidencias = [];
                $.getJSON('/AllyRent/api/publicaciones/' + self.idUsuario(), function (data) {
                    $.each(data, function (i, item) {
                        var nombre = item.usuario.nombre +" " + item.usuario.apellido;
                        nombre = nombre.toLowerCase();
                        var buscar = self.txtBuscar().toLowerCase();
                        if (nombre.indexOf(buscar) != -1){
                            coincidencias.push(item);
                        }
                    });      
                    self.publicacionesList(coincidencias);
                });


            };

};
$(document).ready(function () {
    var homevm = new HomeViewModel();
    ko.applyBindings(homevm);
    homevm.idUsuario(sessionStorage.idusuario);
    homevm.getAllPost();
    homevm.buscarSugerencias();

});