package com.example.ahorcadokm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Arrays;

public class AdivinaActivity extends AppCompatActivity {

    String clave;
    char[] caracteres;
    String [] guiones;
    TextView display;
    TextView acertadas;
    EditText letra;
    TextView vidas;



    int intentos =5;
    int puntos=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adivina);

        recibirDatos();
        setDisplay(); //aqui convierto en guiones
        setCaracteres();
        puntos =0;


    }

    public void recibirDatos(){
        Bundle extras = getIntent().getExtras();
        clave = extras.getString("word", clave);

    }




    public void setDisplay(){
        display = (TextView)findViewById(R.id.txtdisplay);
        guiones = new String[clave.length()];
        String txt = new String();

        for(int x=0; x<clave.length(); x++){
          guiones[x] = "-";
        }


        txt = Arrays.toString(guiones);

        display.setText(txt);
        System.out.println(txt);
    }

    public void setCaracteres(){

        caracteres = new char[clave.length()];

        for(int x=0; x<clave.length(); x++){
            caracteres[x] = clave.charAt(x);
        }
    }


     //Aqui inicia el proceso de  all the game

    public void juego(View view){


     letra = (EditText) findViewById(R.id.txtletra);
     acertadas = (TextView)findViewById(R.id.txtpuntos);

    String palabraAdivinar = letra.getText().toString();

    //VALIDACION DE QUE SEA SOLAMENTE UNA LETRA
        if(palabraAdivinar.length()==1){
            int bandera =0;


            //SI EL USUARIO LLEGA A GANAR LO MANDA A OTRO ACTIVITY
            if(puntos >= (clave.length())-1){
                Toast.makeText(getApplicationContext(),"Has ganado!!!!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),GanasteActivity.class);
                intent.putExtra("win", clave);
                startActivity(intent);

            }
            else{

                for(int x=0; x<caracteres.length; x++){

                    if(palabraAdivinar.equals(String.valueOf(caracteres[x]))){
                        bandera = 1;
                        guiones[x] = palabraAdivinar;

                        //VOY A QUEMAR EL CARACTER PARA QUE YA NO SE PUEDA UTILIZAR PARA GANAR
                        caracteres[x] = ')'; //Le puse una ) porque dificilmente habra una palabra así
                        puntos++;
                    }
                }

                if(bandera==1){
                    Toast.makeText(getApplicationContext(),"Has acertado",Toast.LENGTH_SHORT).show();
                }

                if (bandera==0){
                    intentos--;
                    Toast.makeText(getApplicationContext(),"Letra equivocada",Toast.LENGTH_SHORT).show();
                }

                vidas = (TextView)findViewById(R.id.txtvidas);
                vidas.setText("Intentos = "+intentos);

                acertadas.setText("Puntos = "+puntos);


                //convertimos
                String txt = Arrays.toString(guiones);
                display.setText(txt);



                //condicion por si pierde ()
                if (intentos <= 0){
                    Intent intent = new Intent(getApplicationContext(),PerdisteActivity.class);
                    intent.putExtra("loose", clave);
                    startActivity(intent);
                }

            } //the ELSE ends

            //THE WHILE ENDS, IT MEANS THE PLAYER HAS WON


        }//validacion de que solo sea una letra
        else{
            Toast.makeText(getApplicationContext(),"Solamente se acepta una letra",Toast.LENGTH_SHORT).show();
        }




        } //TERMINA EL JUEGO





}
