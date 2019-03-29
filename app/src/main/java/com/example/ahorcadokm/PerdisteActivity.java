package com.example.ahorcadokm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PerdisteActivity extends AppCompatActivity {

    String palabra;
    TextView word;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perdiste);

        recibirDatos();

    }

    public void recibirDatos(){
        Bundle extras = getIntent().getExtras();
        palabra = extras.getString("loose", palabra);

        word = (TextView) findViewById(R.id.txtinfo);
        word.setText("La palabra era: "+palabra);
    }
}
