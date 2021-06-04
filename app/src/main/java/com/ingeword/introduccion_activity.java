package com.ingeword;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class introduccion_activity extends AppCompatActivity {

    EditText edte ;
    Button btnintro, cu, co;
TextView name;
int contador =0;


private TextView printro;
    @Override
    public void onBackPressed(){


        if(contador ==0) {
            Toast.makeText(getApplicationContext(),
                    "Presiona atrás de nuevo para ir al inicio", Toast.LENGTH_SHORT).show();
            contador++;
        }else{

            Intent intl = new Intent(getApplicationContext(), MainActivity.class);

            intl.putExtra("nombreProyecto", proyectname());
            guarda();
            startActivity(intl);

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



        return texto;
    }

    private String recibirPivote(){

        Bundle ex = getIntent().getExtras();
        String texto = ex.getString("pivote");



        return texto;
    }

    private String proyectname(){
        Bundle ex = getIntent().getExtras();
        String texto = ex.getString("nombreProyecto");


        return texto;
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduccion_activity);


        printro = findViewById(R.id.printro);


        printro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intl = new Intent(introduccion_activity.this,antesIntro.class);
                intl.putExtra("nombreProyecto", proyectname());
               startActivity(intl);
//
//                startActivity(new Intent(introduccion_activity.this,antesIntro.class));


            }
        });

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        EditText texto;
        texto = (EditText) findViewById(R.id.TextoCuerpo1);
        Button boton;
//        try {
//            texto.setText(recibirTexto());
//        }catch (Exception e) {}



        edte = (EditText)findViewById(R.id.TextoINTRO);
        btnintro = (Button ) findViewById(R.id.intro);
        cu = (Button ) findViewById(R.id.cuerpo);
        co = (Button ) findViewById(R.id.conclucion);
        btnintro.setEnabled(false);
        btnintro.setBackgroundColor(Color.GRAY);
        name = (TextView) findViewById(R.id.name);
        name.setText(proyectname());
        String archivos [] =fileList();
        String p ;
        p = recibirPivote();


            if(p != null) {
                if (ArchivoExiste(archivos, proyectname() + "_Intro.txt")) {

                    edte.setText(recibirTexto());
                    guardarr(recibirTexto());
                                    }
        }else{

            if (ArchivoExiste(archivos, proyectname() + "_Intro.txt")) {
                try {
                    InputStreamReader archivo = new InputStreamReader(openFileInput(proyectname() + "_Intro.txt"));
                    BufferedReader br = new BufferedReader(archivo);
                    String linea = br.readLine();
                    String introduccion = "";

                    while (linea != null) {
                        introduccion = introduccion + linea + "\n";
                        linea = br.readLine();

                    }
                    br.close();
                    archivo.close();
                    edte.setText(introduccion);


                } catch (IOException e) {

                }
//            try {
//                edte.setText(recibirTexto());
//            }catch (Exception e) {}
//

            }

        }






        Button bmanual = (Button) findViewById(R.id.edmanual);


        bmanual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                EditText texto;
                texto = (EditText) findViewById(R.id.TextoINTRO);
                String tt = texto.getText().toString();

                Intent intl = new Intent(getApplicationContext(), editar_intro_activity.class);
                intl.putExtra("nombreProyecto", proyectname());
                intl.putExtra("texto", tt);

                startActivity(intl);
                Toast.makeText(getApplicationContext(),
                        "Espera unos segundos. Cargando...", Toast.LENGTH_LONG).show();

            }
        });


    }


    private String recibirTextos(){
        Bundle ex = getIntent().getExtras();
        String texto = ex.getString("texto");

//        Toast.makeText(getApplicationContext(),
//                "EL PARRAFO DICE: "+texto, Toast.LENGTH_SHORT).show();

        return texto;
    }
    private boolean ArchivoExiste(String archivos [], String NombreArchivo){

        for(int i =0; i<archivos.length; i++)
            if((archivos[i]).equals(proyectname()+"_Intro.txt"))
                return true;
            return false;


    }
    public void guardar(View view){
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(
                    openFileOutput(proyectname()+"_Intro.txt", Activity.MODE_PRIVATE));
            archivo.write(edte.getText().toString());
            archivo.flush();
            archivo.close();

        }catch (IOException e){

        }
        Toast.makeText(this,"Intoducción Guardada", Toast.LENGTH_SHORT).show();

        }

    public void guarda(){
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(
                    openFileOutput(proyectname()+"_Intro.txt", Activity.MODE_PRIVATE));
            archivo.write(edte.getText().toString());
            archivo.flush();
            archivo.close();

        }catch (IOException e){

        }
//        Toast.makeText(this,"Intoducción Guardada", Toast.LENGTH_SHORT).show();






    }

    public void guardarr(String cad){
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(
                    openFileOutput(proyectname()+"_Intro.txt", Activity.MODE_PRIVATE));
            archivo.write(cad);
            archivo.flush();
            archivo.close();

        }catch (IOException e){

        }
//        Toast.makeText(this,"Intoducción Guardada", Toast.LENGTH_SHORT).show();






    }




    public void introbb(View view){

        Intent intl = new Intent(getApplicationContext(), introduccion_activity.class);

        intl.putExtra("nombreProyecto", proyectname());
        guarda();
        startActivity(intl);


    }
    public void cuerpobb (View view){

        Intent intl = new Intent(getApplicationContext(), cuerpo_activity.class);
        intl.putExtra("nombreProyecto", proyectname());
            guarda();
        startActivity(intl);


    }
    public void conclubb (View view){

        Intent intl = new Intent(getApplicationContext(), conclusion_activity.class);
        intl.putExtra("nombreProyecto", proyectname());
        guarda();
        startActivity(intl);


    }
    public void guardar_referencia (View view){

        try {
            OutputStreamWriter archivo = new OutputStreamWriter(
                    openFileOutput(proyectname()+"_ref_Intro.txt", Activity.MODE_PRIVATE));
            archivo.write(edte.getText().toString());
            archivo.flush();
            archivo.close();

            Toast.makeText(this,"Se guardó el punto de referencia", Toast.LENGTH_SHORT).show();



        }catch (IOException e){

        }


    }


}