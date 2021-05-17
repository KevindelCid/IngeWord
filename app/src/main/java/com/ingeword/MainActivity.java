package com.ingeword;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {




    private String recibirTexto(){
        Bundle ex = getIntent().getExtras();
        String texto = ex.getString("texto");

        Toast.makeText(getApplicationContext(),
                "EL PARRAFO DICE: "+texto, Toast.LENGTH_SHORT).show();

        return texto;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

//                Intent intl = new Intent(getApplicationContext(), nuevoProyecto.class);
//
//                //intl.putExtra("texto", tt);
//
//                startActivity(intl);
//                Toast.makeText(getApplicationContext(),
//                        "El boton si está funcionando", Toast.LENGTH_LONG).show();

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
}