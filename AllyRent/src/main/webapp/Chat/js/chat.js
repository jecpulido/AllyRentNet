$(document).ready(function(){
   $('#username').text(sessionStorage.nombre);
   $('#rol').text(sessionStorage.rol);
})


var config = {
   apiKey: "AIzaSyCjTgj3Pk5Us7Qu4JvlsvX4gwvgK8IiV_k",
   authDomain: "allrent-1c552.firebaseapp.com",
   databaseURL: "https://allrent-1c552.firebaseio.com",
   projectId: "allrent-1c552",
   storageBucket: "allrent-1c552.appspot.com",
   messagingSenderId: "1062389559821"
};
firebase.initializeApp(config);
var user = 'chat';
firebase.database()
 .ref('chat')
 .orderByChild('chat')
 .equalTo(user)
 .on('value', function(snapshot) {
    console.log(snapshot.exists() ? 'user exist' : 'user non existent');
   }, function(error) {
     console.log(error);
   });
var TablaDeBaseDatos= firebase.database().ref(user);

$('#btnEnviar').click(function(){

  Fecha= dateAct();
  Hora = hourAct();

  TablaDeBaseDatos.push({
      Nombre: sessionStorage.nombre,
      Mensaje:$("#message").val(),
      Fecha:Fecha,
      Hora: Hora
   });

   $("#message").val("");
})

$(document).ready(function(){
   TablaDeBaseDatos.on('value',function(snapshot){

      $(".msg_history").html(""); // Limpiamos todo el contenido del chat

      // Leer todos los mensajes en firebase
      snapshot.forEach(function(e){
         var objeto=e.val(); // Asignar todos los valores a un objeto
            
         // Validar datos nulos y agregar contenido en forma de lista etiqueta <li>
         if((objeto.Mensaje!=null)&&(objeto.Nombre!=null)){
            let div = "HOLAAAA";
            let nombre = objeto.Nombre.split(" ");
            let fecha = (objeto.Fecha == dateAct()) ? "Hoy " : objeto.Fecha + " | ";

            if(objeto.Nombre == sessionStorage.nombre){
               div = '<div class="outgoing_msg"><div class="sent_msg"><span><small>'+ ucWords(nombre[0]) +' dice: </small></span><p>' + objeto.Mensaje + '</p><span class="time_date">' + fecha + objeto.Hora + '</span></div></div>';
            }else{
               div = '<div class="incoming_msg" id="plantilla"><div class="incoming_msg_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"></div><div class="received_msg"><div class="received_withd_msg"><span><small id="name-data">'+ ucWords(nombre[0]) +' dice:</small></span><p id="message-data">' + objeto.Mensaje + '</p><span class="time_date" id="date-data" style="text-align: right;">'+fecha + objeto.Hora +'</span></div></div></div><br>';
            }

            $(div).appendTo ( ".msg_history" );

         }else{
            console.log("Ocurrio un error al conectar con firebase.")
         }

      });
   });
})


function dateAct(){
   var formatDate = new Date();
   var d = formatDate.getUTCDate();
   var m = formatDate.getMonth() + 1;
   var y = formatDate.getFullYear();

   return d+"/"+m+"/"+y;
}

function hourAct(){
   var formatDate = new Date();
   var h = formatDate.getHours();
   var min = formatDate.getMinutes();

   return  h+":"+min;
}

function ucWords(string){
 var arrayWords;
 var returnString = "";
 var len;
 arrayWords = string.split(" ");
 len = arrayWords.length;
 for(i=0;i < len ;i++){
  if(i != (len-1)){
   returnString = returnString+ucFirst(arrayWords[i])+" ";
  }
  else{
   returnString = returnString+ucFirst(arrayWords[i]);
  }
 }
 return returnString;
}

function ucFirst(string){
 return string.substr(0,1).toUpperCase()+string.substr(1,string.length).toLowerCase();
}
