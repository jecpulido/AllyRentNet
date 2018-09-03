/// <reference path="../knockout-2.3.0.debug.js" />

var VehiculoViewModel = function () {
    self = this;
    self.idMarca = ko.observable();
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
    self.createPerson = function () {
        var url = '/api/person/';
        $.ajax({
            url: url,
            type: 'POST',
            data: ko.toJSON(self),
            contentType: "application/json;chartset=utf-8",
            statusCode: {
                201: function () {
                    self.getAll();
                    self.clearForm();
                    alert('Person was created');
                },
                400: function () {
                    self.clearForm();
                    alert('Error');
                }
            }
        });
    },
    self.clearForm = function () {
        self.id('');
        self.name('');
        self.lastname('');
        self.twitter('');
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
    self.getModelo= function (){
        console.log(self.idMarca());
        $.getJSON('/AllyRent/api/vehiculos/modelo/'+self.idMarca(), function (data) {  
            console.log(data);
            self.modeloList(data);
        });
    };
};

$(document).ready(function () {
    if ((sessionStorage.idUsuario === undefined) && (sessionStorage.idLogin === undefined)) {
        alert("Por favor inicie sesion");    
        window.location.href = "http://localhost:8080/AllyRent/index.html";
    }
    var vehiculovm = new VehiculoViewModel();
    ko.applyBindings(vehiculovm);
    vehiculovm.getAll(1);
    vehiculovm.getTarifas();
    vehiculovm.getTipoVehiculo();
    vehiculovm.getTipoCombustible();
    vehiculovm.getTipoTransmision();
    vehiculovm.getTipoMarca();

    $("#vehicleForm").submit(function (e) {
        guardarVehicule();
        e.preventDefault(); // avoid to execute the actual submit of the form.
    });
});

function guardarVehicule() {
    var isPar = $('#chkPar').is(":checked");

    var vehicle = {"ano": $("#txtAño").val(),
        "idCombustible": {
            "idDataType": $("#sltCombustible").val()
        },
        "idModelo": {
            "idModelo": $("#sltModelo").val()
        },
        "idTarifa": {
            "idTarifa": $("#sltTarifa").val()
        },
        "idTipoVehiculo": {
            "idDataType": $("#sltTipoVehiculo").val()
        },
        "idTransmision": {
            "idDataType": $("#sltTransmision").val()
        },
        "idUsuario": {
            "idUsuario": 1
        },
        "isPlacaPar": isPar,
        "km": $("#txtKM").val(),
        "placa": $("#txtPlaca").val()
    };
    var myJSON = JSON.stringify(vehicle);
    // console.log(myJSON);    
    $.ajax({
        url: "http://localhost:8080/ShareCar/webresources/Vehicle/CreateVehicle",
        data: myJSON,
        method: "post",
        contentType: "application/json",
        success: function (data) {
            if (data) {
                $('#noti').html('<p style="color: green; font-size: <20></20>px""><span class="glyphicon glyphicon-ok-circle"> </span> <b>Usuario Registrado. </b></p>');
                setTimeout(function () {
                    location.reload();
                }, 1500);
            } else {
                $('#noti').html('<p style="color: #c1251d; font-size: <20></20>px""><span class="glyphicon glyphicon-remove-circle"> </span> <b>No se pudo crear. </b></p>');
                setTimeout(function () {
                    location.reload();
                }, 1500);
            }
        },
        error: function (data) {
            console.log("Fallo");
        }

    });

}



