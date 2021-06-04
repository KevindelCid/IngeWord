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

public class nuevoProyecto extends AppCompatActivity {

    EditText proyectname,nombreus,contacto,catedratico,titulotrab;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_proyecto);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();




        proyectname = (EditText)findViewById(R.id.proyectname);
        nombreus = (EditText)findViewById(R.id.nombreus);
        contacto = (EditText)findViewById(R.id.contacto);
        catedratico = (EditText)findViewById(R.id.catedratico);
        titulotrab = (EditText)findViewById(R.id.titulotrab);

        String archivos [] =fileList();







//        Button bmanual = (Button) findViewById(R.id.edmanual);
//
//
//        bmanual.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View _view) {
//                EditText texto;
//                texto = (EditText) findViewById(R.id.TextoINTRO);
//                String tt = texto.getText().toString();
//
//                Intent intl = new Intent(getApplicationContext(), editar_intro_activity.class);
//
//                intl.putExtra("texto", tt);
//
//                startActivity(intl);
//                Toast.makeText(getApplicationContext(),
//                        "Espera unos segundos. Cargando...", Toast.LENGTH_LONG).show();
//
//            }
//        });








        Button nuevo = (Button) findViewById(R.id.btnnuevop);


        nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {


                if (proyectname.getText().toString().equals("")) {

                    Toast.makeText(getApplicationContext(),
                            "Debes asignarle un nombre al Proyecto...", Toast.LENGTH_LONG).show();

                } else {



                    guardar();
                  // proyectostxt();
                    Intent intl = new Intent(getApplicationContext(), introduccion_activity.class);

                    intl.putExtra("nombreProyecto", "Proyecto_" + proyectname.getText().toString());

                    startActivity(intl);
//                    Toast.makeText(getApplicationContext(),
//                            "El boton si está funcionando", Toast.LENGTH_LONG).show();

                }
            }
        });





    }


    public void guardar(){

        // creamos la cadena de info que irá dentro del archivo del proyecto
        // **Titilodeltrabajo**    --nombreproyectp--  ((nombreus)) {{catedratico}} ##contacto##

        String cadenaProyecto = ",,,"+titulotrab.getText().toString()+
                ",,,"+ proyectname.getText().toString()+",,,"+nombreus.getText().toString()+
                ",,,"+ catedratico.getText().toString()+",,,"+contacto.getText().toString()+",,,";


        try {
            OutputStreamWriter archivo = new OutputStreamWriter(
                    openFileOutput("Proyecto_"+proyectname.getText().toString()+".txt", Activity.MODE_PRIVATE));
            archivo.write(cadenaProyecto);
            archivo.flush();
            archivo.close();

            Toast.makeText(this,"Se ha creado el proyecto: "+proyectname.getText().toString(), Toast.LENGTH_LONG).show();

        }catch (IOException e){

        }
//        Toast.makeText(this,"aqui es cuando se crea el proyecto "+"Proyecto_"+proyectname.getText().toString()+".txt"
//                +"con los valores: "+cadenaProyecto, Toast.LENGTH_SHORT).show();


        try {
            OutputStreamWriter archivo = new OutputStreamWriter(
                    openFileOutput("Proyectos1.txt", Activity.MODE_APPEND));
            archivo.write(proyectname.getText().toString()+",");
            archivo.flush();
            archivo.close();

        }catch (IOException e){

        }



    }


    public void proyectostxt(){




        String archivos [] =fileList();


        String cadena = "";
        if(ArchivoExiste(archivos,"Proyectos1.txt")){
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("Proyectos1.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
//                Toast.makeText(this, "se ha encontrado Proyectos1.txt" , Toast.LENGTH_SHORT).show();

                while(linea != null){
                    cadena = cadena + linea +"\n";
                    linea = br.readLine();

                }
                br.close();
                archivo.close();


            }catch (IOException e){

            }





//            Toast.makeText(this, "el valor de la cadena aqui en medio es: "+cadena , Toast.LENGTH_SHORT).show();






        // creamos la cadena de info que irá dentro del archivo del proyecto
        // **Titilodeltrabajo**    --nombreproyectp--  ((nombreus)) {{catedratico}} ##contacto##


    }
    }



    private boolean ArchivoExiste(String archivos [], String NombreArchivo){

        for(int i =0; i<archivos.length; i++)
            if(NombreArchivo.equals(archivos[i]))
                return true;
        return false;


    }
}