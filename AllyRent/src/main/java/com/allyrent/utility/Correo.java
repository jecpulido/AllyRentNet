///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.allyrent.utility;
//
//import java.util.Properties;
//import javax.mail.Transport;
//
///**
// *
// * @author Jorge
// */
//public class Correo {
//    
//Properties props = new Properties();
//
//// Nombre del host de correo, es smtp.gmail.com
//props.setProperty("mail.smtp.host", "smtp.gmail.com");
//
//// TLS si está disponible
//props.setProperty("mail.smtp.starttls.enable", "true");
//
//// Puerto de gmail para envio de correos
//props.setProperty("mail.smtp.port","587");
//
//// Nombre del usuario
//props.setProperty("mail.smtp.user", "ejemplo@gmail.com");
//
//// Si requiere o no usuario y password para conectarse.
//props.setProperty("mail.smtp.auth", "true");
//
//Session session = Session.getDefaultInstance(props);
//
//// Para obtener un log de salida más extenso
//session.setDebug(true);
//
//
//MimeMessage message = new MimeMessage(session);
//
//// Quien envia el correo
//message.setFrom(new InternetAddress("ejemplo@gmail.com"));
//
//// A quien va dirigido
//message.addRecipient(Message.RecipientType.TO, new InternetAddress("destinatario@dominio.com"));
//
//message.setSubject("Asunto del mensaje");
//message.setText("Texto del mensaje");
//
//
//Transport t = session.getTransport("smtp");
//
//// Aqui usuario y password de gmail
//t.connect("chuidiang@gmail.com","la password");
//t.sendMessage(message,message.getAllRecipients());
//t.close();
//}
