$(document).ready(function () {

    // Consumo de Servicio Login--------------------------------------------------------------------------------------
    $(".login-form").submit(function (e) {

        e.preventDefault();

        $('#login').click(function () {

            var correo = $('#emailUser').val();
            var clave = hex_md5($('#pwdUser').val());
            var obj = {correo: correo, contrasena: clave};
            var myJSON = JSON.stringify(obj);
            // console.log(hex_md5(clave));
            $.ajax({
                url: "api/usuarios/login",
                data: myJSON,
                method: "post",
                contentType: "application/json"
              })
              .done(function (data) {
                  console.log(data);
                  if (data) {
                      window.location.href = "home.html";
                  } else {
                      $('#msj').html('<p style="color: #c1251d; font-size: <20></20>px""><span class="glyphicon glyphicon-remove-circle"> </span> <b>Usuario Incorrecto </b></p>');
                      $('#msj').animate({fontSize: '15px'}, 'slow');
                  }
              })
              .fail(function (data) {
                  console.log("Fallo");
              });

            });
        });

    });

    // Consumo de Servicio Registro-----------------------------------------------------------------------------------

    // $(".sign").submit(function (e) {
    //
    //     e.preventDefault();
    //
    //     $('#signin').click(function () {
    //
    //         var rol = ($('#rol').prop( "checked") == true)?2:3;
    //         var sexo = ($('#sexo').prop( "checked") == true)?6:7;
    //
    //
    //         var obj = { apellido: $('#apellidos').val(),
    //                     dni: $('#documento').val(),
    //                     fechaNacimiento: $('#fechanacimiento').val() + "T00:00:00-05:00",
    //
    //                     idCiudad: {
    //                         idCiudad: $('#ciudad').val()
    //                     },
    //
    //                     idLogin: {
    //                         contrasena: hex_md5($('#clave').val()),
    //                         correo: $('#email').val()
    //                     },
    //
    //                     idRol: {
    //                         idRol: rol
    //                     },
    //
    //                     idSexo: {
    //                         idDataType: sexo
    //                     },
    //
    //                     idTipoDocumento: {
    //                         idDataType: $('#tipodoc').val()
    //                    },
    //
    //                     idUsuario: 1,
    //                     nombre: $('#nombres').val(),
    //                     rutaFoto: "../default/" + $('#foto').val(),
    //                     telefono: $('#telefono').val()
    //
    //                 };
    //
    //         var myJSON = JSON.stringify(obj);
    //         // console.log(myJSON);
    //         $.ajax({
    //             url: "webresources/User/CreateUser",
    //             data: myJSON,
    //             method: "post",
    //             contentType: "application/json",
    //             success: function (data) {
    //                 if (data) {
    //                     $('#noti').html('<p style="color: green; font-size: <20></20>px""><span class="glyphicon glyphicon-ok-circle"> </span> <b>Usuario Registrado. </b></p>');
    //                     setTimeout(function(){ location.reload();}, 1500);
    //                 } else {
    //                     $('#noti').html('<p style="color: #c1251d; font-size: <20></20>px""><span class="glyphicon glyphicon-remove-circle"> </span> <b>No se pudo crear. </b></p>');
    //                     setTimeout(function(){ location.reload();}, 1500);
    //                 }
    //             },
    //             error: function (data) {
    //                 console.log("Fallo");
    //             }
    //
    //         });
    //     });
    //
    //
    // });
    //
    //
    //
    //
    //
    // // Carga de Listas--------------------------------------------------------------------------------------
    //
    // $.get("http://localhost:8080/ShareCar/webresources/Parameters/FindType/1",
    //         function (data) {
    //             $.each(data, function (i, contact) {
    //                 $("#tipodoc").append(
    //                     "<option value='" + contact.idDataType + "'>" + contact.nombreDataType + "</option>");
    //             });
    //         });
    //
    // $.get("http://localhost:8080/ShareCar/webresources/Parameters/Departments",
    //         function (data) {
    //             $.each(data, function (i, contact) {
    //                 $("#departamento").append(
    //                     "<option value='" + contact.idDepartamento + "'>" + contact.nombreDepartamento + "</option>");
    //             });
    //         });
    //
    // $('#departamento').change(function(){
    //     var id = $(this).val();
    //     $.get("http://localhost:8080/ShareCar/webresources/Parameters/City/"+ id,
    //         function (data) {
    //             $.each(data, function (i, contact) {
    //                 $("#ciudad").append(
    //                     "<option value='" + contact.idCiudad + "'>" + contact.nombreCiudad + "</option>");
    //             });
    //         });
    // });

// });
