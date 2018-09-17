/// <reference path="../knockout-2.3.0.debug.js" />

var VehiculoViewModel = function () {
    self = this;
    self.idTipoVehiculo = ko.observable();
    self.idTransmision = ko.observable();
    self.idCombustible = ko.observable();
    self.idTarifa = ko.observable();
    self.idMarca = ko.observable();
    self.idModelo = ko.observable();
    self.placa = ko.observable();
    self.ano = ko.observable();
    self.km = ko.observable();
    self.isPlacaPar = ko.observable();
    self.idUsuario = ko.observable();

    //Listas
    self.vehiculoList = ko.observableArray();// array y tambien obserbable
    self.tarifasList = ko.observableArray();// array y tambien obserbable
    self.tipoVehiculoList = ko.observableArray();// array y tambien obserbable
    self.tipoCombustibleList = ko.observableArray();// array y tambien obserbable
    self.tipoTransmisionList = ko.observableArray();// array y tambien obserbable
    self.marcaList = ko.observableArray();// array y tambien obserbable
    self.modeloList = ko.observableArray();// array y tambien obserbable

    self.getAll = function (id) {
        $.getJSON('/AllyRent/api/vehiculos/' + id, function (data) {
            self.vehiculoList(data);
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
            self.createVehiculo = function () {
                var url = '/AllyRent/api/vehiculos/create';
                $.ajax({
                    url: url,
                    type: 'POST',
                    data: ko.toJSON(self.getObjectVehicule()),
                    contentType: "application/json;chartset=utf-8",
                    statusCode: {
                        200: function (data) {
                            self.saveImages(data.idVehiculo);
                            self.clearForm();
                            sessionStorage.vehiculos = parseInt(sessionStorage.vehiculos) + 1;
                            window.location.href = "http://localhost:8080/AllyRent/Vehiculo/";
                        },
                        204: function () {
                            alert('Error');
                        }
                    }
                });
            },
            self.saveImages = function (idVehiculo) {
                var fotos = [];
                $("#imagenes img").each(function () {
                    var obj = {
                        "foto": $(this).attr('src'),
                        "idVehiculo": idVehiculo
                    };
                    fotos.push(obj);
                });
                $.ajax({
                    url: "/AllyRent/api/vehiculos/upload",
                    type: 'POST',
                    data: ko.toJSON(fotos),
                    contentType: "application/json;chartset=utf-8",
                    success: function (data, textStatus, jqXHR) {
                        
                    }
                });
            },
            self.clearForm = function () {
                self.idTipoVehiculo("");
                self.idTransmision("");
                self.idCombustible("");
                self.idTarifa("");
                self.idMarca("");
                self.idModelo("");
                self.placa("");
                self.ano("");
                self.km("");
                self.isPlacaPar("");
                self.idUsuario("");
            },
            //Opciones del formulario
            self.getTarifas = function () {
                $.getJSON('/AllyRent/api/vehiculos/tarifas', function (data) {
                    self.tarifasList(data);
                });
            },
            self.getTipoVehiculo = function () {
                $.getJSON('/AllyRent/api/general/findType/5', function (data) {
                    self.tipoVehiculoList(data);
                });
            },
            self.getTipoCombustible = function () {
                $.getJSON('/AllyRent/api/general/findType/4', function (data) {
                    self.tipoCombustibleList(data);
                });
            },
            self.getTipoTransmision = function () {
                $.getJSON('/AllyRent/api/general/findType/3', function (data) {
                    self.tipoTransmisionList(data);
                });
            },
            self.getTipoMarca = function () {
                $.getJSON('/AllyRent/api/vehiculos/marca', function (data) {
                    self.marcaList(data);
                });
            },
            self.getModelo = function () {
                console.log(self.idMarca());
                $.getJSON('/AllyRent/api/vehiculos/modelo/' + self.idMarca(), function (data) {
                    self.modeloList(data);
                });
            },
            self.getObjectVehicule = function () {
                var vehicle = {"ano": self.ano(),
                    "idCombustible": {
                        "idDataType": self.idCombustible()
                    },
                    "idModelo": {
                        "idModelo": self.idModelo()
                    },
                    "idTarifa": {
                        "idTarifa": self.idTarifa()
                    },
                    "idTipoVehiculo": {
                        "idDataType": self.idTipoVehiculo()
                    },
                    "idTransmision": {
                        "idDataType": self.idTransmision()
                    },
                    "idUsuario": {
                        "idUsuario": self.idUsuario()
                    },
                    "isPlacaPar": self.isPlacaPar(),
                    "km": self.km(),
                    "placa": self.placa()
                };
                return vehicle;
            };

};

$(document).ready(function () {
//    if ((sessionStorage.idUsuario === undefined) && (sessionStorage.idLogin === undefined)) {
//        alert("Por favor inicie sesion");    
//        window.location.href = "http://localhost:8080/AllyRent/index.html";
//    }
    var vehiculovm = new VehiculoViewModel();
    ko.applyBindings(vehiculovm);
    vehiculovm.getAll(sessionStorage.idusuario);
    vehiculovm.getTarifas();
    vehiculovm.getTipoVehiculo();
    vehiculovm.getTipoCombustible();
    vehiculovm.getTipoTransmision();
    vehiculovm.getTipoMarca();
    vehiculovm.idUsuario(sessionStorage.idusuario);
    $("#vehicleForm").submit(function (e) {
        guardarVehicule();
        e.preventDefault(); // avoid to execute the actual submit of the form.
    });
});
$("#subirImagen").change(function () {
    let file = this;
    let data = '';
    if (file.files && file.files[0]) {
        let reader = new FileReader();
        reader.onload = function (e) {
            data = e.target.result;
            mostrarImagen(data);
        }.bind(this);
        reader.readAsDataURL(file.files[0]);
    }
    $("#subirImagen").val("");
});
function mostrarImagen(data) {
    imagen = $("<img>");
    imagen.addClass("img-vehiculo");
    imagen.attr('src', data);
    imagen.show();
    $("#imagenes").append(imagen);
}




