package com.gabriel.whatsapp_clone.utils;

import android.util.Base64;

import java.nio.charset.StandardCharsets;

public class Base64Utils {

    public static String codificarBase64(String s){
        return Base64.encodeToString(s.getBytes(), Base64.DEFAULT ).replaceAll("(\\n|\\r)","");
    }
    public static String decodificarBase64(String s){
        return new String(Base64.decode(s, Base64.DEFAULT ));
    }
}
