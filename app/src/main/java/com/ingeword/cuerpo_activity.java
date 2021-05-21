package com.ingeword;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class cuerpo_activity extends AppCompatActivity {


    EditText edte;
    Button btnintro, cu, co;



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
        setContentView(R.layout.activity_cuerpo_activity);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        EditText texto;
        texto = (EditText) findViewById(R.id.TextoCuerpo1);
        Button boton;
        try {
            texto.setText(recibirTexto());
        }catch (Exception e) {}



        edte = (EditText)findViewById(R.id.TextoINTRO);
        btnintro = (Button ) findViewById(R.id.intro);
        cu = (Button ) findViewById(R.id.cuerpo);
        co = (Button ) findViewById(R.id.conclucion);
        cu.setEnabled(false);
        cu.setBackgroundColor(Color.GRAY);
        String archivos [] =fileList();


        if(ArchivoExiste(archivos,"cuerpo.txt")){
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("cuerpo.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String introduccion = "";

                while(linea != null){
                    introduccion = introduccion + linea +"\n";
                    linea = br.readLine();

                }
                br.close();
                archivo.close();
                edte.setText(introduccion);

            }catch (IOException e){

            }
            try {
                edte.setText(recibirTexto());
            }catch (Exception e) {}


        }




        Button bmanual = (Button) findViewById(R.id.btm);


        bmanual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                EditText texto;
                texto = (EditText) findViewById(R.id.TextoINTRO);
                String tt = texto.getText().toString();

                Intent intl = new Intent(getApplicationContext(), editar_cuerpo_activity.class);

                intl.putExtra("texto", tt);

                startActivity(intl);
                Toast.makeText(getApplicationContext(),
                        "Espera unos segundos. Cargando...", Toast.LENGTH_LONG).show();

            }
        });

    }


    private boolean ArchivoExiste(String archivos [], String NombreArchivo){

        for(int i =0; i<archivos.length; i++)
            if(NombreArchivo.equals(archivos[i]))
                return true;
        return false;


    }
    public void guardar(View view){
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(
                    openFileOutput("cuerpo.txt", Activity.MODE_PRIVATE));
            archivo.write(edte.getText().toString());
            archivo.flush();
            archivo.close();

        }catch (IOException e){

        }
        Toast.makeText(this,"Cuerpo Guardado", Toast.LENGTH_SHORT).show();

    }

    public void guarda(){
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(
                    openFileOutput("cuerpo.txt", Activity.MODE_PRIVATE));
            archivo.write(edte.getText().toString());
            archivo.flush();
            archivo.close();

        }catch (IOException e){

        }
        Toast.makeText(this,"Cuerpo Guardado", Toast.LENGTH_SHORT).show();

    }



    public void introbb(View view){

        Intent intl = new Intent(getApplicationContext(), introduccion_activity.class);

        startActivity(intl);


    }
    public void cuerpobb (View view){

        Intent intl = new Intent(getApplicationContext(), cuerpo_activity.class);
        guarda();
        startActivity(intl);


    }
    public void conclubb (View view){

        Intent intl = new Intent(getApplicationContext(), conclusion_activity.class);
        guarda();
        startActivity(intl);


    }




}