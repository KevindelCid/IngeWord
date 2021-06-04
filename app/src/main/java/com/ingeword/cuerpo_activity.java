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
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class cuerpo_activity extends AppCompatActivity {


    EditText edte;
    Button btnintro, cu, co;
    TextView name, printro;
    @Override
    public void onBackPressed(){




        Intent intl = new Intent(getApplicationContext(), introduccion_activity.class);
        guarda();
        intl.putExtra("nombreProyecto", proyectname());
        startActivity(intl);


    }


    private String recibirPivote(){

        Bundle ex = getIntent().getExtras();
        String texto = ex.getString("pivote");



        return texto;
    }

    private String recibirTexto(){
        Bundle ex = getIntent().getExtras();
        String texto = ex.getString("texto");


        return texto;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuerpo_activity);






        printro = findViewById(R.id.printroc);


        printro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intl = new Intent(cuerpo_activity.this, antescuerpo.class);
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
        try {
            texto.setText(recibirTexto());
        }catch (Exception e) {}



        edte = (EditText)findViewById(R.id.TextoINTRO);
        btnintro = (Button ) findViewById(R.id.intro);
        cu = (Button ) findViewById(R.id.cuerpo);
        co = (Button ) findViewById(R.id.conclucion);
        name = (TextView) findViewById(R.id.namecu);
        name.setText(proyectname());
        cu.setEnabled(false);
        cu.setBackgroundColor(Color.GRAY);
        String archivos [] =fileList();
        String p ;
        p = recibirPivote();


        if(p != null) {
            edte.setText(recibirTexto());

        }else {


            if (ArchivoExiste(archivos, proyectname() + "_cuerpo.txt")) {
                try {
                    InputStreamReader archivo = new InputStreamReader(openFileInput(proyectname() + "_cuerpo.txt"));
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


            }


        }

        Button bmanual = (Button) findViewById(R.id.btm);


        bmanual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                EditText texto;
                texto = (EditText) findViewById(R.id.TextoINTRO);
                String tt = texto.getText().toString();

                Intent intl = new Intent(getApplicationContext(), editar_cuerpo_activity.class);

                intl.putExtra("nombreProyecto", proyectname());
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
                    openFileOutput(proyectname()+"_cuerpo.txt", Activity.MODE_PRIVATE));
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
                    openFileOutput(proyectname()+"_cuerpo.txt", Activity.MODE_PRIVATE));
            archivo.write(edte.getText().toString());
            archivo.flush();
            archivo.close();

        }catch (IOException e){

        }
//        Toast.makeText(this,"Cuerpo Guardado", Toast.LENGTH_SHORT).show();

    }



    private String proyectname(){
        Bundle ex = getIntent().getExtras();
        String texto = ex.getString("nombreProyecto");

//        Toast.makeText(getApplicationContext(),
//                "EL PARRAFO DICE: "+texto, Toast.LENGTH_SHORT).show();

        return texto;
    }

    public void introbb(View view){

        Intent intl = new Intent(getApplicationContext(), introduccion_activity.class);
        guarda();
        intl.putExtra("nombreProyecto", proyectname());
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
    public void guardar_referencia (View view) {

        try {
            OutputStreamWriter archivo = new OutputStreamWriter(
                    openFileOutput(proyectname() + "_ref_cuerpo.txt", Activity.MODE_PRIVATE));
            archivo.write(edte.getText().toString());
            archivo.flush();
            archivo.close();

            Toast.makeText(this, "Se guardó el punto de referencia", Toast.LENGTH_SHORT).show();


        } catch (IOException e) {

        }
    }


}