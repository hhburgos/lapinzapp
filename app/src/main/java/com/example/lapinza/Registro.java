package com.example.lapinza;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lapinza.cifrado.Encode;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);



        (findViewById(R.id.button_regsiter)).setOnClickListener(v -> {

            String name = ((EditText) findViewById(R.id.name_register)).getText().toString();
            String lastname = ((EditText) findViewById(R.id.lastname_register)).getText().toString();
            String email = ((EditText) findViewById(R.id.email_register)).getText().toString();
            String nickname = ((EditText) findViewById(R.id.nickname_register)).getText().toString();
            boolean passwordEquals = ((EditText) findViewById(R.id.password_register)).getText().toString()
                    .equals(((EditText) findViewById(R.id.password_registerConfirm)).getText().toString());

            if (!passwordEquals){
                Toast.makeText(Registro.this, "Las contraseñas deben coincidir y no estar vacias", Toast.LENGTH_SHORT).show();
                return;
            }

            Encode.sendDataToRegister(name, lastname, nickname, email, ((EditText) findViewById(R.id.password_registerConfirm)).getText().toString(), Registro.this);
        });

        (findViewById(R.id.buttonRegister_back)).setOnClickListener(v -> {
            finish();
        });
    }
}