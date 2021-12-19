package com.example.lapinza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.lapinza.cifrado.Encode;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        (findViewById(R.id.button_login)).setOnClickListener(v -> {
            Encode.loginToDataBase(((EditText) findViewById(R.id.email_login)).getText().toString(),
                    ((EditText) findViewById(R.id.password_login)).getText().toString(), Login.this);
        });

        (findViewById(R.id.button_goto_register)).setOnClickListener(v -> {
            startActivity(new Intent(Login.this, Registro.class));
        });
    }


}