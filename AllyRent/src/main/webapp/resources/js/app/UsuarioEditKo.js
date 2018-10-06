var UsuarioViewModel = function () {
    self = this;
    self.idUsuario = ko.observable();
    self.nombre = ko.observable();
    self.apellido = ko.observable();
    self.idTipoDocumento = ko.observable();
    self.dni = ko.observable();
    self.telefono = ko.observable();
    self.fechaNacimiento = ko.observable();
    self.idSexo = ko.observable();
    self.idRol = ko.observable();
    self.idDpto = ko.observable();
    self.idCiudad = ko.observable();
    self.imagen = ko.observable();

    self.correo = ko.observable();

    self.tipoDocumentoList = ko.observableArray();
    self.tipoSexoList = ko.observableArray();
    self.dptoList = ko.observableArray();
    self.ciudadList = ko.observableArray();

    self.getAll = function () {
        $.getJSON('/AllyRent/api/general/findType/1', function (data) {
            self.tipoDocumentoList(data);
        });

        $.getJSON('/AllyRent/api/general/findType/2', function (data) {
            self.tipoSexoList(data);
        });
        $.getJSON('/AllyRent/api/general/departments', function (data) {
            self.dptoList(data);
        });
    },
            self.getCiudad = function () {
                $.getJSON('/AllyRent/api/general/city/' + self.idDpto(), function (data) {
                    self.ciudadList(data);
                });
            },
            self.UpdateUser = function () {

            },
            self.loadUser = function (id) {
                $.getJSON('/AllyRent/api/usuarios/' + id, function (data) {
                    self.nombre(data.nombre);
                    self.apellido(data.apellido);
                    self.idTipoDocumento(data.idTipoDocumento);
                    self.dni(data.dni);
                    self.telefono(data.telefono);
                    self.fechaNacimiento(data.fechaNacimiento);
                    self.idSexo(data.idSexo);
                    self.idRol(data.idRol);
                    self.idDpto(data.idCiudad);
                    self.idCiudad(data.idCiudad);
                });
            };


};

$(document).ready(function () {
    var uservm = new UsuarioViewModel();
    ko.applyBindings(uservm);
    console.log(sessionStorage.idusuario);
    console.log(getParameterByName("id"));
    if (sessionStorage.idusuario.toString() != getParameterByName("id").toString()) {
        window.location.href = "http://localhost:8080/AllyRent/home.html";
    }
    ;
    self.idUsuario(sessionStorage.idusuario);
    self.loadUser(getParameterByName("id"));
    self.getAll();
});

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
            results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}