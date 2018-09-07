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


    self.getAll = function () {
        $.getJSON('/AllyRent/api/vehiculos/' + self.idUsuario(), function (data) {
           self.vehiculoList(data);
        });
        $.getJSON('/AllyRent/api/general/findType/' + 6, function (data) {
            self.tipoPublicacionList(data);
        });
        
    },
            self.getPersonById = function () {
                var url = '/api/person/' + self.id();
                $.getJSON(url)
                        .done(function (data) {
                            self.name(data.Name);
                            self.lastname(data.LastName);
                            self.twitter(data.Twitter);
                        })
                        .fail(function (erro) {
                            self.clearForm();
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
            self.updatePerson = function () {
                var url = '/api/person/' + self.id();
                $.ajax({
                    url: url,
                    type: 'PUT',
                    data: ko.toJSON(self),
                    contentType: "application/json;chartset=utf-8",
                    statusCode: {
                        200: function () {
                            self.getAll();
                            self.clearForm();
                            alert('Person with id= ' + self.id() + ' was updated');
                        },
                        404: function () {
                            self.clearForm();
                            alert('Person with id= ' + self.id() + ' was not found');
                        },
                        400: function () {
                            self.clearForm();
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
                            if (data.responseText === "OK"){
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
                    "fechaFin": self.fechaFin()+':00-05:00',
                    "fechaInicio": self.fechaInicio()+':00-05:00    ',
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
            };

};

$(document).ready(function () {
//    if ((sessionStorage.idUsuario === undefined) && (sessionStorage.idLogin === undefined)) {
//        alert("Por favor inicie sesion");    
//        window.location.href = "http://localhost:8080/AllyRent/index.html";
//    }
    var publiacionvm = new PublicacionViewModel();
    ko.applyBindings(publiacionvm);
    publiacionvm.idUsuario(1);
    publiacionvm.getAll();
});




