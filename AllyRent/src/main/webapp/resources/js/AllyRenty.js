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
    $("#aMensajes").attr("href", "http://localhost:8080/AllyRent/home.html");
    $("#aSolicitudes").attr("href", "http://localhost:8080/AllyRent/Publicacion/solicitudes.html");
    $("#aContratos").attr("href", "http://localhost:8080/AllyRent/home.htmll");
});

function CompleteRegister(){
    $("#mostrarmodal").modal("show");
    CargarControles();
    
}
function CargarControles(){
        $.get("/AllyRent/api/general/findType/1",
            function (data) {
                $.each(data, function (i, contact) {
                    $("#sltTipoDocumento").append(
                            "<option value='" + contact.idDataType + "'>" + contact.nombreDataType + "</option>");
                });
            });

    $.get("/AllyRent/api/general/departments",
            function (data) {
                $.each(data, function (i, contact) {
                    $("#sltDepartamento").append(
                            "<option value='" + contact.idDepartamento + "'>" + contact.nombreDepartamento + "</option>");
                });
            });

    $('#departamento').change(function () {
        var id = $(this).val();
        $.get("/AllyRent/api/general/city/" + id,
                function (data) {
                    $.each(data, function (i, contact) {
                        $("#ciudad").append(
                                "<option value='" + contact.idCiudad + "'>" + contact.nombreCiudad + "</option>");
                    });
                });
    });
    
}