package com.example.ahorcadokm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText palabra;
    String clave;
    Button continuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        continuar = (Button) findViewById(R.id.btnContinuar);
        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                enviarDatos();
            }
        });


    }


    public void enviarDatos(){
        palabra = (EditText)findViewById(R.id.txtpalabra);
        clave = palabra.getText().toString();

        if(clave.length()<4){
            Toast.makeText(this, "Debes escribir al menos cuatro letras", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(getApplicationContext(),AdivinaActivity.class);
            intent.putExtra("word", clave);
            startActivity(intent);

        }


    }


}
