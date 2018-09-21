    let nombre = sessionStorage.nombre.split(" ");
    $("#linkProfile").html("<i class='fa fa-user'></i> " + nombre[0]);
    $("#nomProfile").html("<i class='fa fa-car'></i> " + sessionStorage.nombre);
    $("#rolProfile").text(sessionStorage.rol);
    $("#ciuProfile").html("<strong>Origen: </strong>" + sessionStorage.ciudad);
    $("#vehProfile").html("<strong>N° de Vehículos: </strong> Este usuario posee " + sessionStorage.vehiculos + " vehiculos registrados.");
    console.log(sessionStorage.foto);
    $("#pubProfile").text(sessionStorage.publicaciones);
    $("#imgProfile").attr("src", sessionStorage.foto);