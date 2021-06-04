package com.ingeword;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

int contador = 0;
    @Override
    public void onBackPressed(){


        if(contador ==0) {
            Toast.makeText(getApplicationContext(),
                    "Presiona atrás de nuevo para salir", Toast.LENGTH_SHORT).show();
            contador++;
        }else{
            finish();

        }

        new CountDownTimer(3000,1000){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                contador =0;
            }
        }.start();

    }

    private String recibirTexto(){
        Bundle ex = getIntent().getExtras();
        String texto = ex.getString("texto");

//        Toast.makeText(getApplicationContext(),
//                "EL PARRAFO DICE: "+texto, Toast.LENGTH_SHORT).show();

        return texto;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        EditText texto;
        texto = (EditText) findViewById(R.id.TextoCuerpo1);
        Button boton;
        try {
            texto.setText(recibirTexto());
        }catch (Exception e) {}



        Button nuevo = (Button) findViewById(R.id.btnnp);


        nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {

                Intent intl = new Intent(getApplicationContext(), nuevoProyecto.class);

                //intl.putExtra("texto", tt);

                startActivity(intl);

            }
        });






        Button bmanual = (Button) findViewById(R.id.btnmanual);


        bmanual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                EditText texto;
                texto = (EditText) findViewById(R.id.TextoCuerpo1);
                String tt = texto.getText().toString();

                Intent intl = new Intent(getApplicationContext(), editar.class);

                intl.putExtra("texto", tt);

                startActivity(intl);
                Toast.makeText(getApplicationContext(),
                        "Espera unos segundos. Cargando...", Toast.LENGTH_LONG).show();

            }
        });




    }

    public void abrir(View view){

        Intent intl = new Intent(getApplicationContext(), open_proyect.class);

        startActivity(intl);


    }
}