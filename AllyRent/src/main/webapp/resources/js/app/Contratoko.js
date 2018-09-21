/// <reference path="../knockout-2.3.0.debug.js" />

var ContratoViewModel = function () {
    self = this;
    self.estado = ko.observable();
    self.usuarioProLst = ko.observableArray();
    self.usuarioSolLst = ko.observableArray();
    self.infoGeneral = ko.observableArray();
    
    self.findSolicitud = function (idSolicitud) {
        $.getJSON('/AllyRent/api/solicitudes/' + idSolicitud, function (data) {
            console.log(data);
            self.usuarioProLst(data.idPublicacion);
            self.usuarioSolLst(data.idUsuario);
            self.infoGeneral(data);
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
