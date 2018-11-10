/// <reference path="../knockout-2.3.0.debug.js" />
if ((sessionStorage.idusuario === undefined) && (sessionStorage.nombre === undefined)) {
    window.location.href = "http://localhost:8080/AllyRent/index.html";
} else {
    $("#iImagen").attr("src", sessionStorage.foto);
    $("#hNombre").text(sessionStorage.nombre);
    $("#sPropietario").text(sessionStorage.rol);
    $("#sCorreo").text(sessionStorage.correo);
    $("#sTelefono").text(sessionStorage.telefono);
    $("#sCiudad").text(sessionStorage.ciudad);
    $("#sPublicaciones").text(sessionStorage.publicaciones);
    if (sessionStorage.rol == "Ocupante") {
        $("#sVehiculos").parent().parent().hide();
    } else {
        $("#sVehiculos").text(sessionStorage.vehiculos);
    }

}

$(document).ready(function () {
    if (sessionStorage.isFromRedSocial !== undefined) {
        if (sessionStorage.isFromRedSocial === "true") {
            if (sessionStorage.rol === undefined) {
                CompleteRegister();
            }
        }
    }
    $("#aProfile").attr('href', 'http://localhost:8080/AllyRent/Usuario/profile.html?id=' + sessionStorage.idusuario);
    $("#ahappy").attr('href', 'http://localhost:8080/AllyRent/Usuario/profile.html?id=' + sessionStorage.idusuario);
    $("#aInicio").attr("href", "http://localhost:8080/AllyRent/home.html");
    $("#aMensajes").attr("href", "http://localhost:8080/AllyRent/Chat/chat.html");
    $("#aSolicitudes").attr("href", "http://localhost:8080/AllyRent/Publicacion/solicitudes.html");
    $("#aContratos").attr("href", "http://localhost:8080/AllyRent/home.html");
    $("#aPublicaciones").attr("href", "http://localhost:8080/AllyRent/Publicacion/new.html");
    $("#aVehiculos").attr("href", "http://localhost:8080/AllyRent/Vehiculo/index.html");
});

function CompleteRegister() {
    var obj = {
        contrasena: md5(sessionStorage.idusuario),
        correo: sessionStorage.correo
    };
    $.ajax({
        url: "api/usuarios/login",
        data: JSON.stringify(obj),
        method: "post",
        contentType: "application/json",
        statusCode: {
            200: function (data) {
                cargarSession(data);
                location.reload();
            },
            204: function () {
                CargarControles();
            }
        }
    });
}
function CargarControles() {
    $("#mostrarmodal").modal("show");
    $("#txtNombre").val(sessionStorage.nombre);
    $("#sltTipoDocumento").append("<option value='-1'>--Seleccione--</option>");
    $("#sltGenero").append("<option value='-1'>--Seleccione--</option>");
    $("#sltRol").append("<option value='-1'>--Seleccione--</option>");
    $("#sltDepartamento").append("<option value='-1'>--Seleccione--</option>");
    $("#sltCiudad").append("<option value='-1'>--Seleccione--</option>");
    $.get("/AllyRent/api/general/findType/1",
            function (data) {
                $.each(data, function (i, contact) {
                    $("#sltTipoDocumento").append(
                            "<option value='" + contact.idDataType + "'>" + contact.nombreDataType + "</option>");
                });
            });
    $.get("/AllyRent/api/general/findType/2",
            function (data) {
                $.each(data, function (i, contact) {
                    $("#sltGenero").append(
                            "<option value='" + contact.idDataType + "'>" + contact.descripcionDataType + "</option>");
                });
            });
    $.get("/AllyRent/api/general/roles",
            function (data) {
                $.each(data, function (i, contact) {
                    if (contact.nombreRol !== "Admin") {
                        $("#sltRol").append(
                                "<option value='" + contact.idRol + "'>" + contact.nombreRol + "</option>");
                    }

                });
            });
    $.get("/AllyRent/api/general/departments",
            function (data) {
                $.each(data, function (i, contact) {
                    $("#sltDepartamento").append(
                            "<option value='" + contact.idDepartamento + "'>" + contact.nombreDepartamento + "</option>");
                });
            });
    $('#sltDepartamento').change(function () {
        var id = $(this).val();
        $.get("/AllyRent/api/general/city/" + id,
                function (data) {
                    $.each(data, function (i, contact) {
                        $("#sltCiudad").append(
                                "<option value='" + contact.idCiudad + "'>" + contact.nombreCiudad + "</option>");
                    });
                });
    });
}
//Carga imagen
$("#subirImagen").change(function () {
    let file = this;
    let data = '';
    if (file.files && file.files[0]) {
        let reader = new FileReader();
        reader.onload = function (e) {
            data = e.target.result;
            $("#imagen").attr('src', data);
            $("#imagen").show();
        }.bind(this);
        reader.readAsDataURL(file.files[0]);
    }
});
$("#btnUpdate").click(function () {
    var fotoString = $("#imagen").attr("src");
    var obj = {
        apellido: $('#txtApellido').val(),
        dni: $('#txtNDocumento').val(),
        fechaNacimiento: $('#txtFechaNacimiento').val() + "T00:00:00-05:00",
        idCiudad: $('#sltCiudad').val(),
        idRol: $('#sltRol').val(),
        idSexo: $('#sltGenero').val(),
        idTipoDocumento: $('#sltTipoDocumento').val(),
        idUsuario: 1,
        nombre: $('#txtNombre').val(),
        rutaFoto: fotoString,
        telefono: $('#txtTelefono').val(),
        idLogin: {
            contrasena: md5(sessionStorage.idusuario),
            correo: sessionStorage.correo
        }
    };
    var myJSON = JSON.stringify(obj);
    $.ajax({
        url: "api/usuarios/create",
        data: myJSON,
        method: "post",
        contentType: "application/json",
        statusCode: {
            200: function (data) {
                if (data) {
                    alert("Usuario Actualizado");
                } else {
                    alert("Error al actualizar Usuario");
                }
            },
            204: function () {
                alert('Error');
            }
        }
    });
});

function cargarSession(data) {
    sessionStorage.clear();
    sessionStorage.idusuario = data.idUsuario;
    sessionStorage.nombre = data.nombre + ' ' + data.apellido;
    sessionStorage.correo = data.idLogin.correo;
    sessionStorage.ultimaConexion = data.idLogin.UltimaConexion;
    sessionStorage.fechaNacimiento = data.fechaNacimiento;
    sessionStorage.telefono = data.telefono;
    sessionStorage.rol = data.nombreRol;
    sessionStorage.sexo = data.nombreSexo;
    sessionStorage.foto = data.rutaFoto;
    sessionStorage.ciudad = data.nombreCiudad;
    sessionStorage.publicaciones = data.numeroPublicaciones;
    sessionStorage.vehiculos = data.numeroVehiculos;
    sessionStorage.idRol = data.idRol;

}
$("#logout").click(function(){
   sessionStorage.clear();
   window.location.href = "http://localhost:8080/AllyRent/index.html";
});