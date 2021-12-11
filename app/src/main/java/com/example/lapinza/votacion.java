package com.example.lapinza;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class votacion extends AppCompatActivity implements View.OnClickListener {

    ArrayList<String> participantes;
    double [] notas;
    Button btSiguienteMC, btAnteriorMC;
    Button bt0, bt05, bt1, bt15, bt2, bt25, bt3, bt35, bt4, btplus0, btplus05, btplus1;
    TextView tvSiguienteMC, tvAnteriorMC, tvActualMC, tvVar;
    int nIntervencion, turno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votacion);

        inicializar();
        inicializaOrden();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btSiguienteMC.getId()) {
            adelantar();
            muestraDatos();
        }
        else if(v.getId() == btAnteriorMC.getId()){
            retroceder();
            muestraDatos();
        } else {
            mensaje("otro");
        }
    }

    public void inicializaOrden () {
        Collections.shuffle(participantes); //barajea los participantes

        tvAnteriorMC.setText("--");
        tvActualMC.setText(participantes.get(0));
        tvSiguienteMC.setText(participantes.get(1));
    }

    public void muestraDatos () {
        tvVar.setText("turno: " + turno);
        tvActualMC.setText(participantes.get(turno));
        tvAnteriorMC.setText(dameAnteriorMC());
        tvSiguienteMC.setText(dameSiguienteMC());
    }

    public String dameSiguienteMC () {
        String dev = "";
        tvVar.setText("turno: " + turno);
        if (turno == (participantes.size() - 1)) {
            dev = participantes.get(0);
        } else {
            dev = participantes.get(turno + 1);
            tvVar.setText("turno: " + turno + "  sigui: " + (turno+1) + " sizeA: " + participantes.size());
            Log.e("Siguiente ", String.valueOf(turno));
        }

        return dev;
    }

    public String dameAnteriorMC () {
        String dev = "";
        if (turno == 0) {
            dev = participantes.get(participantes.size() - 1);
        } else {
            dev = participantes.get(turno - 1);
        }
        return dev;
    }

    public void cargaArrayList () {
        participantes.add("Chuty");
        participantes.add("Khan");
        participantes.add("Babi");
        participantes.add("Cixer");
    }

    public void retroceder () {
        if (turno == 0) {
            turno = (participantes.size() - 1);
        } else {
            turno -= 1;
        }
    }

    public void adelantar () {
        if (turno == participantes.size() - 1) {
            turno = 0;
        } else {
            turno += 1;
        }
    }

    public void inicializar () {
        nIntervencion = 0;
        turno = 0;

        participantes = new ArrayList<String>();
        mensaje("participantes size: " + participantes.size());
        notas = new double[participantes.size()];

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
        tvVar = findViewById(R.id.tvVar);
    }

    public void mensaje (String m) {
        Toast.makeText(this, m, Toast.LENGTH_LONG).show();
    }
}