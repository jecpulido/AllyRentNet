/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allyrent.utility;

import com.allyrent.entidades.Fotos;
import com.allyrent.entidades.Vehiculo;
import java.util.Date;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Jorge
 */
public class ImageToArray {

    public static byte[] convertStringToImageByteArray(String imageString) {
        byte[] imageInByteArray = null;
        try {
            imageString = imageString.substring(imageString.indexOf(',') + 1, imageString.length());
            imageInByteArray = Base64.decodeBase64(imageString);
        } catch (Exception e) {
            System.out.println("ee" + e.getMessage());
        }
        return imageInByteArray;

    }

    public static String converseByteArrayToImage(byte[] imageByte) {
        String imagen = "";
        try {
            imagen = "data:image/jpeg;base64,";
            imagen += Base64.encodeBase64String(imageByte);
        } catch (Exception e) {
            System.out.println("ee" + e.getMessage());
        }
        return imagen;
    }
}
