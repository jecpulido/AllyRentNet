/// <reference path="../knockout-2.3.0.debug.js" />
var ContratoViewModel = function () {
    self = this;
    self.estado = ko.observable();
    self.valor = ko.observable();
    self.idPropietario = ko.observable();
    self.idOcupante = ko.observable();
    self.idVehiculo = ko.observable();

    self.propietario = ko.observableArray();
    self.loginProp = ko.observableArray();
    self.ocupante = ko.observableArray();
    self.loginOcup = ko.observableArray();
    self.vehiculo = ko.observableArray();
    self.publicacion = ko.observableArray();

    self.findSolicitud = function (idSolicitud) {
        $.getJSON('/AllyRent/api/solicitudes/' + idSolicitud, function (data) {
            self.propietario(data.idPublicacion.vehiculo.idUsuario);
            self.idPropietario(self.propietario().idUsuario);
            self.loginProp(data.idPublicacion.vehiculo.idUsuario.idLogin);

            self.ocupante(data.idUsuario);
            self.idOcupante(self.ocupante().idUsuario);

            self.loginOcup(data.idUsuario.idLogin);
            self.vehiculo(data.idPublicacion.vehiculo);
            self.idVehiculo(self.vehiculo().idVehiculo);
            self.publicacion(data.idPublicacion);

            var fecha1 = moment(self.publicacion().fechaInicio);
            var fecha2 = moment(self.publicacion().fechaFin);
            var dias = fecha2.diff(fecha1, 'days');
            if (dias > 0) {
                self.valor(dias * self.vehiculo().dia);
            } else {
                var horas = fecha2.diff(fecha1, 'days');
                self.valor(horas * self.vehiculo().hora);
            }

        });
    }, self.createContrato = function () {
        var obj = {
            'fechaInicio': $("#txtFInicio").val(),
            'fechaFin': $("#txtFFin").val(),
            'valor': $("#txtValor").val(),
            'usuario': {
                'idUsuario': self.idPropietario()
            },
            'usuario1': {
                'idUsuario': self.idOcupante()
            },
            'idVehiculo': {
                'idVehiculo': self.idVehiculo()
            }
        };

        $.ajax({
            url: "/AllyRent/api/contratos/create",
            type: 'POST',
            data: ko.toJSON(obj),
            contentType: "application/json;chartset=utf-8",
            statusCode: {
                200: function (data) {
                    if (data.responseText === "OK") {
                        alert('Contrato realizada');
                    }
                },
                204: function () {
                    alert('Error');
                }
            }
        });

    };

};


$(document).ready(function () {
    var Contratovm = new ContratoViewModel();
    ko.applyBindings(Contratovm);
    Contratovm.findSolicitud(getParameterByName("idSolicitud"));
});

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
            results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}
