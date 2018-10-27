var VehicleProfileViewModel = function () {
    self = this;
    self.idUsuario = ko.observable();
    self.idVehiculo = ko.observable();
    self.login = ko.observableArray();
    self.usuario = ko.observableArray();
    self.vehiculo = ko.observableArray();
    self.publicaciones = ko.observableArray();
    self.loadUser = function (id) {
        $.getJSON('/AllyRent/api/usuarios/' + id, function (data) {
            self.usuario(data);
            self.login(data.idLogin);
        });
    },
            self.loadVehicle = function (id) {
                $.getJSON('/AllyRent/api/vehiculos/findVehicle/' + id, function (data) {
                    console.log(data);
                    self.vehiculo(data);
                    if (data != null) {
                        self.loadUser(data.idUsuario.idUsuario);
                        self.loadGallery(data.fotosCollection);                    }

                });
            },
            self.loadPublicaciones = function (id) {
                $.getJSON('/AllyRent/api/publicaciones/listByVehiculo/' + id + '/' + self.idUsuario(), function (data) {
                    var dat = [];
                    $.each(data, function (index, element) {
                        if (typeof (element.reaccion) != 'undefined') {
                            dat.push(element);
                        } else {
                            element.reaccion = '';
                            dat.push(element);
                        }
                    });
                    self.publicaciones(dat);
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
            self.likeDislike = function (idPublicacion, bandera) {
                var url = '/AllyRent/api/publicaciones/LikeDislike';
                var reaccion = {
                    "idPublicacion": {
                        "idPublicacion": idPublicacion
                    },
                    "idUsuario": {
                        "idUsuario": self.idUsuario()
                    },
                    "bandera": bandera
                };
                $.ajax({
                    url: url,
                    type: 'POST',
                    data: ko.toJSON(reaccion),
                    contentType: "application/json;chartset=utf-8",
                    statusCode: {
                        200: function (data) {
                            if (data.responseText === "OK") {
                                self.loadPublicaciones(self.idVehiculo());
                            }
                        },
                        204: function () {
                            alert('Error');
                        }
                    }
                });
            },
            self.loadGallery = function (fotos) {
                $.each(fotos, function (index, element) {
                    $("#carouselIndicator").append(
                            "<li data-target='#myCarousel' data-slide-to=" + index + " class=" + (index == 0 ? 'active' : '') + "></li>");
                    $("#carouselImg").append(
                            "<div class='item " + (index == 0 ? 'active' : '') + "'>"
                            + "<img src='" + element.foto + "' alt='" + index + "'>"
                            + "</div>");
                });
            };
};

$(document).ready(function () {
    var vehiclevm = new VehicleProfileViewModel();
    ko.applyBindings(vehiclevm);
    self.idUsuario(sessionStorage.idusuario);
    self.idVehiculo(getParameterByName("id"));
    vehiclevm.loadVehicle(getParameterByName("id"));
    vehiclevm.loadPublicaciones(getParameterByName("id"));
});

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
            results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}