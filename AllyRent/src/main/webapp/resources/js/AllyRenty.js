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
            CompleteRegister();
        }
    }
    $("#aProfile").attr('href', 'http://localhost:8080/AllyRent/Usuario/profile.html?id=' + sessionStorage.idusuario);
    $("#ahappy").attr('href', 'http://localhost:8080/AllyRent/Usuario/profile.html?id=' + sessionStorage.idusuario);
    $("#aInicio").attr("href", "http://localhost:8080/AllyRent/home.html");
    $("#aMensajes").attr("href", "http://localhost:8080/AllyRent/Chat/chat.html");
    $("#aSolicitudes").attr("href", "http://localhost:8080/AllyRent/Publicacion/solicitudes.html");
    $("#aContratos").attr("href", "http://localhost:8080/AllyRent/home.htmll");
});

function CompleteRegister() {
    $("#mostrarmodal").modal("show");
    CargarControles();

}
function CargarControles() {

    $("#sltTipoDocumento").append("<option value='-1'>--Seleccione--</option>");
    $("#sltGenero").append("<option value='-1'>--Seleccione--</option>");
    $("#sltRol").append("<option value='-1'>--Seleccione--</option>");
    $("#sltDepartamento").append("<option value='-1'>--Seleccione--</option>");
    $("#sltCiudad").append("<option value='-1'>--Seleccione--</option>");
    $('#txtFechaNacimiento').datetimepicker({format: "dd.mm.yyyy"});

    $("#btnRegister").click(GuardarUsuario());


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

function GuardarUsuario() {


}
