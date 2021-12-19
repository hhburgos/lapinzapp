package com.example.lapinza;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.example.lapinza.R;

public class MainActivity extends AppCompatActivity {

    class MainActivityAsyncTask extends AsyncTask<Integer, Integer, String> {

        @Override
        protected void onPreExecute() {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            );
        }

        @Override
        protected String doInBackground(Integer... params) {

            for (int i = 25; i < 101; i += 25){
                try {
                    Thread.sleep(500);
                    ((ProgressBar) findViewById(R.id.datBaseLoadingBar)).setProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Intent intent = new Intent( MainActivity.this, Login.class );
            startActivity(intent);
            finish();

            return "";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            ((ProgressBar) findViewById(R.id.datBaseLoadingBar)).setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            //progressBar.setVisibility( View.GONE);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        // The progress params
        ((ProgressBar) findViewById(R.id.datBaseLoadingBar)).setMax(100);
        ((ProgressBar) findViewById(R.id.datBaseLoadingBar)).setProgress(0);

        // Everything is set, let's put it all to work!!
        new MainActivityAsyncTask().execute();
    }
}