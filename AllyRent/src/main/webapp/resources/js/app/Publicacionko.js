/// <reference path="../knockout-2.3.0.debug.js" />

var PublicacionViewModel = function () {
    self = this;
    self.tipoPublicacion = ko.observable();
    self.idUsuario = ko.observable();
    self.publicacion = ko.observable();
    self.fechaInicio = ko.observable();
    self.fechaFin = ko.observable();
    self.idVehiculo = ko.observable();

    //Listas
    self.vehiculoList = ko.observableArray();// array y tambien obserbable
    self.tipoPublicacionList = ko.observableArray();// array y tambien obserbable
    self.solicitudesList = ko.observableArray();

    self.getAll = function () {
        $.getJSON('/AllyRent/api/vehiculos/' + self.idUsuario(), function (data) {
            self.vehiculoList(data);
        });
        $.getJSON('/AllyRent/api/general/findType/' + 6, function (data) {
            self.tipoPublicacionList(data);
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
                                sessionStorage.publicaciones = parseInt(sessionStorage.publicaciones) + 1;
                                window.location.href = "http://localhost:8080/AllyRent/home.html";
                            }
                            alert(data.responseText);
                        },
                        204: function () {
                            alert('Error');
                        }
                    }
                });
            },
            self.getSolicitudesList = function () {
                $.getJSON('/AllyRent/api/solicitudes/ListSolicitudes/' + self.idUsuario(), function (data) {
                    self.solicitudesList(data);
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
                    "fechaFin": self.fechaFin() + ':00-05:00',
                    "fechaInicio": self.fechaInicio() + ':00-05:00    ',
                    "fechaPublicacion": "",
                    "idPublicacion": 1,
                    "idTipoPublicacion": {
                        "idDataType": self.tipoPublicacion()
                    },
                    "idUsuario": {
                        "idUsuario": self.idUsuario()
                    },
                    "idVehiculo": self.idVehiculo(),
                    "publicacion": self.publicacion()
                };
                return publicacion;
            },
            self.updateState = function (dataU, fechaSolicitud, idSolicitud) {
                var url = '/AllyRent/api/solicitudes/updateState';
                var solicitud = {
                    "estado": 0,
                    "fechaSolicitud": fechaSolicitud,
                    "idPublicacion": {
                        "idPublicacion": dataU.idPublicacion
                    },
                    "idSolicitud": idSolicitud,
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
                                alert('Solicitud denegada');
                            }
                            self.getSolicitudesList();
                        },
                        204: function () {
                            alert('Error');
                        }
                    }
                });
            };

};

$(document).ready(function () {
    var publiacionvm = new PublicacionViewModel();
    ko.applyBindings(publiacionvm);
    publiacionvm.idUsuario(sessionStorage.idusuario);
    publiacionvm.getAll();
    publiacionvm.getSolicitudesList();
    ValidateRol();
    ObtenerUbicacion();
});

function ValidateRol() {

    if (sessionStorage.rol == "Ocupante") {
        $("#divVehiculo").hide();
    } else {
        $("#divVehiculo").show();
    }
}

function ObtenerUbicacion() {
    if (navigator.geolocation) {
        $("#msgError").hide();
        navigator.geolocation.getCurrentPosition(localizacion, error);
    } else {
        $("#divUbicacion").hide();
        $("#msgError").show();
        $("#msgError").text("Navegador NO soporta Geolocalizacion")
    }

    function localizacion(posicion) {
        var latitud = posicion.coords.latitude;
        var longitud = posicion.coords.longitude;
        $("#msgError").hide();
        $("#divUbicacion").show();
        $("#lblUbicacion").text(latitud + "," + longitud);
    }
    function error() {
        $("#msgError").show();
        $("#divUbicacion").hide();
        $("#msgError").text("No se pudo obtener ubicacion");
    }
}

