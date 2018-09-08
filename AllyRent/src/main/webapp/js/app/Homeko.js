/// <reference path="../knockout-2.3.0.debug.js" />

var HomeViewModel = function () {
    self = this;
    self.tipoPublicacion = ko.observable();
    self.idUsuario = ko.observable();
    self.publicacion = ko.observable();
    self.fechaInicio = ko.observable();
    self.fechaFin = ko.observable();
    self.idVehiculo = ko.observable();

    //Listas
    self.publicacionesList = ko.observableArray();// array y tambien obserbable


    self.getAllPost = function () {
        $.getJSON('/AllyRent/api/publicaciones/list/' + self.idUsuario(), function (data) {
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
                            }else if (data.responseText === "DELETE") {
                                alert('Solicitud Cancelada');
                            }
                        },
                        204: function () {
                            alert('Error');
                        }
                    }
                });
            },
            self.deletePersonById = function () {
                var url = '/api/person/' + self.id();
                $.ajax({
                    url: url,
                    type: 'DELETE',
                    contentType: "application/json;chartset=utf-8",
                    statusCode: {
                        200: function () {
                            self.getAll();
                            self.clearForm();
                            alert('Person with id= ' + self.id() + ' was deleted');
                        },
                        404: function () {
                            alert('Person with id= ' + self.id() + ' was not found');
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
            };

};


$(document).ready(function () {
    var homevm = new HomeViewModel();
    ko.applyBindings(homevm);
    homevm.idUsuario(1);
    homevm.getAllPost();
//    if ((sessionStorage.idUsuario === undefined) && (sessionStorage.idLogin === undefined)) {
//        alert("Por favor inicie sesion");    
//        window.location.href = "http://localhost:8080/AllyRent/index.html";
//    }

});
