package com.example.lapinza;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


public class MainActivity extends AppCompatActivity implements OnClickListener {

    private Button btEnviar;
    private EditText etUser, etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btEnviar = findViewById(R.id.btenviar);
        etUser = findViewById(R.id.etuser);
        etPass = findViewById(R.id.etpass);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btEnviar.getId()) {
            String username = etUser.getText().toString();
            String password = etPass.getText().toString();
            new DescargarImagen(MainActivity.this).execute(username,password);
        }
    }

    public static class DescargarImagen extends AsyncTask<String, Void, String> {
        private WeakReference<Context> context;

        public DescargarImagen(Context context) {
            this.context = new WeakReference<>(context);
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        protected String doInBackground(String... params) {
            String registrarURL = "http://lapinza.club/conexion.php";
            String resultado;
            int control = 0;

            try {
                URL url = new URL(registrarURL);
                control = 1;
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                control = 2;
                httpURLConnection.setRequestMethod("POST");
                control = 3;
                httpURLConnection.setDoOutput(true);
                control = 4;
                OutputStream outputStream = httpURLConnection.getOutputStream();
                control = 5;
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
                control = 6;

                String nickname = params[0];
                String password = params[1];

                String data = URLEncoder.encode("nickname","UTF-8") + "=" + URLEncoder.encode(nickname,"UTF-8")
                        + "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

                bufferedWriter.write(data);
                control = 7;
                bufferedWriter.flush();
                control = 8;
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                StringBuilder stringBuilder = new StringBuilder();

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                resultado = stringBuilder.toString();

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
            }
            catch (Exception e) {
                Log.d("Mia","Error");
                resultado = "Se ha producido un error " + control;
            }
            return resultado;
        }

        protected void onPostExecute(String resultado) {
            Toast.makeText(context.get(), resultado, Toast.LENGTH_LONG).show();
        }
    }

}