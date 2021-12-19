package com.example.lapinza.cifrado;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Encode {
    private static final String URL = "http://lapinza.club/login.php";
    private static String PRIVATE_KEY = "nolesabesaltrap1";

    public static void checkUser(String user, String password, Context context){
        if (user.length() < 0 || password.length() < 0) {
            Toast.makeText(context, "No dejes campos vacios", Toast.LENGTH_LONG).show();
            return;
        }

        sendToDataBase(encode(user), encode(password), context);
    }

    private static void sendToDataBase(String user, String password, Context context) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Base de datos", response);
                switch (response){
                    case "true":
                        //Modifica
                        break;
                    case "false":
                        break;
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "El servidor/tu conexion no estan disponibles", Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("nickname", user);
                params.put("password", password);

                return params;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    private static String encode(String data) {
        //Generamos la key secreta
        SecretKey secretKey = getSecretKey();

        try {
            //ciframos los datos
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            return Base64.encodeToString(cipher.doFinal(data.getBytes()),0);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        return "";
    }

    private static SecretKey getSecretKey(){
        return new SecretKeySpec(PRIVATE_KEY.getBytes(), "AES");
    }

}