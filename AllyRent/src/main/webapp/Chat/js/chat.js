var config = {
   apiKey: "AIzaSyCjTgj3Pk5Us7Qu4JvlsvX4gwvgK8IiV_k",
   authDomain: "allrent-1c552.firebaseapp.com",
   databaseURL: "https://allrent-1c552.firebaseio.com",
   projectId: "allrent-1c552",
   storageBucket: "allrent-1c552.appspot.com",
   messagingSenderId: "1062389559821"
};
firebase.initializeApp(config);
var TablaDeBaseDatos= firebase.database().ref('chat');

$('#btnEnviar').click(function(){
  var formatDate = new Date();
  var d = formatDate.getUTCDate();
  var m = formatDate.getMonth() + 1;
  var y = formatDate.getFullYear();
  var h = formatDate.getHours();
  var min = formatDate.getMinutes();
  var fecha = d + "/" + m + "/" + y + " " + h + ":" + min;

  Fecha= d+"/"+m+"/"+y+" "+h+":"+min;

  TablaDeBaseDatos.push({
      Nombre: sessionStorage.nombre,
      Mensaje:$("#message").val(),
      Fecha:Fecha
   });
})

$(document).ready(function(){
   TablaDeBaseDatos.on('value',function(snapshot){

      $(".msg_history").html(""); // Limpiamos todo el contenido del chat

      // Leer todos los mensajes en firebase
      snapshot.forEach(function(e){
         var objeto=e.val(); // Asignar todos los valores a un objeto

         // Validar datos nulos y agregar contenido en forma de lista etiqueta <li>
         if((objeto.Mensaje!=null)&&(objeto.Nombre!=null)){
            // $(".msg_history").html(objeto.Nombre + " / " + objeto.Mensaje + " / " + objeto.Fecha)
            // Copia el contenido al template y luego lo inserta en el chat
            $( ".outgoing_msg" ).clone().appendTo ( ".msg_history" );
            // $('.msg_history .outgoing_msg').show(10);
            // $('.msg_history #incoming_msg #name-data').html("HOLAAAAAAAAAA");
            // $('.msg_history #incoming_msg #message-data').html("HOLAAAAAAAAAA");
            // $('.msg_history #incoming_msg #date-data').html("HOLAAAAAAAAAA");
         }else{
            console.log("Paila")
         }

      });
   });
})
