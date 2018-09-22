var ContratoViewModel = function () {
    self = this;
    self.idUsuario = ko.observable();
    self.login = ko.observableArray();
    self.usuario = ko.observableArray();
    self.publicaciones = ko.observableArray();

    self.loadUser = function (id) {
        $.getJSON('/AllyRent/api/usuarios/' + id, function (data) {
            self.usuario(data);
            self.login(data.idLogin);
            console.log(self.usuario());
        });
    },
            self.loadPublicaciones = function (id) {
                $.getJSON('/AllyRent/api/publicaciones/list/' + id, function (data) {
                    self.publicaciones(data);
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
            };
};

$(document).ready(function () {
    var uservm = new ContratoViewModel();
    ko.applyBindings(uservm);
    self.idUsuario(sessionStorage.idusuario)
    uservm.loadUser(getParameterByName("id"));
    uservm.loadPublicaciones(getParameterByName("id"));
});

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
            results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}