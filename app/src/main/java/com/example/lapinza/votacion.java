package com.example.lapinza;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class votacion extends AppCompatActivity implements View.OnClickListener {

    ArrayList<String> participantes;
    Button btSiguienteMC, btAnteriorMC;
    Button bt0, bt05, bt1, bt15, bt2, bt25, bt3, bt35, bt4, btplus0, btplus05, btplus1;
    TextView tvSiguienteMC, tvAnteriorMC, tvActualMC;
    int nIntervencion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votacion);

        inicializar();
        actualizaOrden();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btSiguienteMC.getId()) {

        }
        else if(v.getId() == btAnteriorMC.getId()){

        } else {
            mensaje("otro");
        }
    }

    public void actualizaOrden () {
        //mezclarArray()
        Collections.shuffle(participantes);
        tvAnteriorMC.setText("--");
        tvActualMC.setText(participantes.get(0));
        tvSiguienteMC.setText(participantes.get(1));
    }

    public void cargaArrayList () {
        participantes.add("Chuty");
        participantes.add("Khan");
        participantes.add("Babi");
        participantes.add("Cixer");
    }

    public void inicializar () {
        nIntervencion = 0;

        participantes = new ArrayList<String>();
        cargaArrayList();

        btSiguienteMC = findViewById(R.id.btSiguienteMC);
        btAnteriorMC = findViewById(R.id.btAnteriorMC);
        bt0 = findViewById(R.id.bt0);
        bt05 = findViewById(R.id.bt05);
        bt1 = findViewById(R.id.bt1);
        bt15 = findViewById(R.id.bt15);
        bt2 = findViewById(R.id.bt2);
        bt25 = findViewById(R.id.bt25);
        bt3 = findViewById(R.id.bt3);
        bt35 = findViewById(R.id.bt35);
        bt4 = findViewById(R.id.bt4);
        btplus0 = findViewById(R.id.btplus0);
        btplus05 = findViewById(R.id.btplus05);
        btplus1 = findViewById(R.id.btplus1);

        btSiguienteMC.setOnClickListener(this);
        btAnteriorMC.setOnClickListener(this);
        bt0.setOnClickListener(this);
        bt05.setOnClickListener(this);
        bt1.setOnClickListener(this);
        bt15.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt25.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt35.setOnClickListener(this);
        bt4.setOnClickListener(this);
        btplus0.setOnClickListener(this);
        btplus05.setOnClickListener(this);
        btplus1.setOnClickListener(this);

        tvAnteriorMC = findViewById(R.id.tvAnteriorMC);
        tvSiguienteMC = findViewById(R.id.tvSiguienteMC);
        tvActualMC = findViewById(R.id.tvActualMC);
    }

    public void mensaje (String m) {
        Toast.makeText(this, m, Toast.LENGTH_LONG).show();
    }
}