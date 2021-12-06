package com.example.lapinza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class intro extends AppCompatActivity implements OnClickListener {

    Button btInicio, btRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        btInicio = findViewById(R.id.btInico);
        btRegistro = findViewById(R.id.btRegitro);
        btRegistro.setOnClickListener(this);
        btInicio.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btInicio.getId()) {
//            mensaje("Inicando sesion");
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
        else if (v.getId() == btRegistro.getId()) {
            mensaje("registrandose");
        }
    }

    public void mensaje (String m) {
        Toast.makeText(this, m, Toast.LENGTH_LONG).show();
    }

}