package com.ingeword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class open_proyect extends AppCompatActivity {

    ListView lista;
    String temp[];
    EditText edtex;
    String cadenitawaa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_proyect);

edtex = (EditText)findViewById(R.id.edtex);

        String archivos[] = fileList();


        if(ArchivoExiste(archivos,"Proyectos1.txt")){
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("Proyectos1.txt"));
                BufferedReader br = new BufferedReader(archivo);

                String linea = br.readLine();
                String introduccion = "";

                while(linea != null){
                    introduccion = introduccion + linea +"\n";
                    linea = br.readLine();

                }
                br.close();
                archivo.close();

//                Toast.makeText(this, "eee"+ introduccion, Toast.LENGTH_SHORT).show();
                edtex.setText(introduccion);

            }catch (IOException e){

            }
//            try {
//                edte.setText(recibirTexto());
//            }catch (Exception e) {}
//

        }else{

            Toast.makeText(this, "Crea un proyecto y aparecerá aquí" , Toast.LENGTH_SHORT).show();

        }









temp = edtex.getText().toString().split(",");


//        Toast.makeText(this, ""+ edtex.getText().toString(), Toast.LENGTH_SHORT).show();




        lista = (ListView) findViewById(R.id.listview);





                ArrayList<String> listaproyectos = new ArrayList<>();





                for (int i=0; i<temp.length; i++){
                    listaproyectos.add(temp[i]);

                }



                ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaproyectos);

                lista.setAdapter(adaptador);


              //  }










            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Toast.makeText(parent.getContext(), "Ingresando al proyecto "+parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();

                    Intent intl = new Intent(getApplicationContext(), introduccion_activity.class);

                    intl.putExtra("nombreProyecto", "Proyecto_" + parent.getItemAtPosition(position).toString());

                    startActivity(intl);
//                    Toast.makeText(getApplicationContext(),
//                            "El boton si está funcionando", Toast.LENGTH_LONG).show();
                }
            });


    }

        private boolean ArchivoExiste (String archivos [],String NombreArchivo){

            for (int i = 0; i < archivos.length; i++)
                if ((archivos[i]).equals("Proyectos1.txt"))
                    return true;
            return false;


        }


    }
