$(document).ready(function () {

    // Consumo de Servicio Login--------------------------------------------------------------------------------------
    $(".login-form").submit(function (e) {

        e.preventDefault();
        ingresar();
    });

    function ingresar() {

        var correo = $('#emailUser').val();
        var clave = md5($('#pwdUser').val());
        var obj = {
            correo: correo,
            contrasena: clave
        };
        var myJSON = JSON.stringify(obj);
        // console.log(hex_md5(clave));
        $.ajax({
            url: "api/usuarios/login",
            data: myJSON,
            method: "post",
            contentType: "application/json"
        })
                .done(function (data) {
                    if (data) {
                        cargarSession(data);
                        window.location.href = "home.html";
                        console.log(data);
                    } else {
                        $('#msj').html('<p style="color: #c1251d;"><i class="fa fa-ban fa-lg"> </i> <b>Usuario Incorrecto </b></p>');
                        $('#msj').animate({
                            fontSize: '20px'
                        }, 'slow');
                        $('#msj').fadeOut(2000);
                        setTimeout(function () {
                            location.reload();
                        }, 1500);
                    }
                })
                .fail(function (data) {
                    console.log("Fallo");
                });

    }

    // Consumo de Servicio Registro-----------------------------------------------------------------------------------

    $(".sign").submit(function (e) {
        let correo =
                e.preventDefault();
        registrar();
    });

    function registrar() {

        var rol = ($('#rol').prop("checked") == true) ? 2 : 3;
        var sexo = ($('#sexo').prop("checked") == true) ? 6 : 7;
        var fotoString = $("#imagen").attr("src");

        var obj = {
            apellido: $('#apellidos').val(),
            dni: $('#documento').val(),
            fechaNacimiento: $('#fechanacimiento').val() + "T00:00:00-05:00",
            idCiudad: $('#ciudad').val(),
            idRol: rol,
            idSexo: sexo,
            idTipoDocumento: $('#tipodoc').val(),
            idUsuario: 1,
            nombre: $('#nombres').val(),
            rutaFoto: fotoString,
            telefono: $('#telefono').val(),
            idLogin: {
                contrasena: md5($('#clave').val()),
                correo: $('#email').val()
            }
        };

        var myJSON = JSON.stringify(obj);
        $.ajax({
            url: "api/usuarios/create",
            data: myJSON,
            method: "post",
            contentType: "application/json",
            success: function (data) {
                console.log(data);
                if (data) {
                    console.log("Usuario Creado");
                    $('#noti').html('<p style="color: green;"><i class="fa fa-check-circle fa-lg"> </i> <b>Usuario Registrado. </b></p>');
                    setTimeout(function () {
                        window.location.href = "index.html";
                    }, 2000);
                } else {
                    $('#noti').html('<p style="color: #c1251d;"><i class="fa fa-ban fa-lg"> </i> <b>Error al crear el Usuario.</b></p>');
                    setTimeout(function () {
                        location.reload();
                    }, 2000);
                }
            },
            error: function (data) {
                console.log("Fallo");
            }

        });
    }

    //Cargar Session
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

    //Carga imagen
    $("#foto").change(function () {
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

    // Carga de Listas--------------------------------------------------------------------------------------

    $.get("http://localhost:8080/AllyRent/api/general/findType/1",
            function (data) {
                $.each(data, function (i, contact) {
                    $("#tipodoc").append(
                            "<option value='" + contact.idDataType + "'>" + contact.nombreDataType + "</option>");
                });
            });

    $.get("http://localhost:8080/AllyRent/api/general/departments",
            function (data) {
                $.each(data, function (i, contact) {
                    $("#departamento").append(
                            "<option value='" + contact.idDepartamento + "'>" + contact.nombreDepartamento + "</option>");
                });
            });

    $('#departamento').change(function () {
        var id = $(this).val();
        $.get("http://localhost:8080/AllyRent/api/general/city/" + id,
                function (data) {
                    $.each(data, function (i, contact) {
                        $("#ciudad").append(
                                "<option value='" + contact.idCiudad + "'>" + contact.nombreCiudad + "</option>");
                    });
                });
    });

});
function onSignIn(googleUser) {
    sessionStorage.clear();
    var profile = googleUser.getBasicProfile();
    console.log(googleUser);
    console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
    console.log('Name: ' + profile.getName());
    console.log('Image URL: ' + profile.getImageUrl());
    console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
    
    //sessionStorage.idusuario = googleUser.getAuthResponse().id_token;
    sessionStorage.idusuario = profile.getId();
    sessionStorage.nombre = profile.getName();
    sessionStorage.correo = profile.getEmail();
    sessionStorage.foto = profile.getImageUrl();
    sessionStorage.isFromRedSocial = true;
    window.location.href = "home.html";
}