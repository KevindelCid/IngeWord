package com.ingeword;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class antesconclusion extends AppCompatActivity {

    private TextView edtex;

    private String proyectname(){
        Bundle ex = getIntent().getExtras();
        String texto = ex.getString("nombreProyecto");


        return texto;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antesconclusion);
        DisplayMetrics medidaventana = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidaventana);


        String archivos[] = fileList();
        int ancho = medidaventana.widthPixels;
        int alto = medidaventana.heightPixels;

        getWindow().setLayout((int) (ancho * 0.85), (int) (alto * 0.5));

        edtex = findViewById(R.id.textorefcon);

        if (ArchivoExiste(archivos, proyectname() + "_ref_conclusion.txt")) {
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput(proyectname() + "_ref_conclusion.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String introduccion = "";

                while (linea != null) {
                    introduccion = introduccion + linea + "\n";
                    linea = br.readLine();

                }
                br.close();
                archivo.close();
                edtex.setText(introduccion);


            } catch (IOException e) {

            }

        }
    }
    private boolean ArchivoExiste(String archivos [], String NombreArchivo){

        for(int i =0; i<archivos.length; i++)
            if((archivos[i]).equals(proyectname()+"_ref_conclusion.txt"))
                return true;
        return false;


    }

}