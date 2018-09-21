/// <reference path="../knockout-2.3.0.debug.js" />
if ((sessionStorage.idusuario === undefined) && (sessionStorage.nombre === undefined)) {
    window.location.href = "http://localhost:8080/AllyRent/index.html";
} else {
    // $("#iImagen").attr("src",sessionStorage.foto);
    $("#hNombre").text(sessionStorage.nombre);
    $("#sPropietario").text(sessionStorage.rol);
    $("#sCorreo").text(sessionStorage.correo);
    $("#sTelefono").text(sessionStorage.telefono);
    $("#sCiudad").text(sessionStorage.ciudad);
    $("#sPublicaciones").text(sessionStorage.publicaciones);
    $("#sVehiculos").text(sessionStorage.vehiculos);


    let nombre = sessionStorage.nombre.split(" ");
    $("#linkProfile").html("<i class='fa fa-user'></i> " + nombre[0]);
    $("#nomProfile").html("<i class='fa fa-car'></i> " + sessionStorage.nombre);
    $("#rolProfile").text(sessionStorage.rol);
    $("#ciuProfile").html("<strong>Origen: </strong>" + sessionStorage.ciudad);
    $("#vehProfile").html("<strong>N° de Vehículos: </strong> Este usuario posee " + sessionStorage.vehiculos + " vehiculos registrados.");
    console.log(sessionStorage.foto);
    $("#pubProfile").text(sessionStorage.publicaciones);
    $("#imgProfile").attr("src", "../resources/" + sessionStorage.foto);


}
$(document).ready(function () {

});
