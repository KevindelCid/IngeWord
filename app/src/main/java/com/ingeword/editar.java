package com.ingeword;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class editar extends AppCompatActivity {



    private final String USER_AGENT = "Mozilla/5.0";

    private TextView textview1;
    private Button button1;
    static int numBotones = 0;
    static String parrafo = "";

    private String nuevoparrafo="";
    private Intent inten = new Intent();
    private Button boton;
    int cantidadpalabras = 0;
    String temp[];

    private String buscar_sinonimos(String palabra) throws Exception {

        String sinonims1 = null;
        sinonims1 = searchSynonym(palabra);
        return sinonims1;
    }


    private String searchSynonym(String wordToSearch) throws Exception {
        System.out.println("Sending request...");
        String cadena="";
        String url = " http://sesat.fdi.ucm.es:8080/servicios/rest/sinonimos/json/" + wordToSearch;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending request to: " + url);

        System.out.println("JSON Response: " + responseCode + "\n");


        // ordering the response
        StringBuilder response;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            response = new StringBuilder();
            // aqui vamos a mostrar la url mediante in.readLine()

            String json = in.readLine();

            //ahora haremos un analisis lexico omitiendo las palabras (sinonimos, sinonimo, :, "," " ")

            String[] sin = json.split("\"");
            // los sinonimos se encuentran en las posiciones desde el 5 + 4 cada poscion 5,9,13,17...


            // si una palabra no cuenta con un solo sinonimo solo se tomara la palabta normal y se agrefara a la cadena

            if(sin[5] == null){
//                Toast.makeText(getApplicationContext(),
//                        "La palabra no tiene sinonimos", Toast.LENGTH_SHORT).show();

                cadena = wordToSearch;
            }else{

                cadena =sin[5];
            }








        }


        catch (IOException e) {
            e.getMessage();
        }
        return cadena;

    }

    private void cabio(int index, String palabra){
        //aqui wa obtener la palabra que se esta usando y el numero del array en el que se va a almacenar para que siga teniendo coherencia
        //lo escrito
        temp[index-1] = palabra;

    }
    private void guardar(){

        //aqui voy a tomar el temp como esté actualmente y pasarlo al main activity.


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




        String pa ="";
        pa = recibirTexto();
        String palabra = null;
        String palabraBase = null;

//		Toast.makeText(getApplicationContext(),
//				"EL PARRAFO DICE: "+pa, Toast.LENGTH_SHORT).show();

        temp = pa.split(" ");
        cantidadpalabras = temp.length;


        //Establecemos el layout main
        setContentView(R.layout.activity_editar);

        //Obtenemos el linear layout donde colocar los botones
        LinearLayout llBotonera = (LinearLayout) findViewById(R.id.llBotonera);

        //Creamos las propiedades de layout que tendrán los botones.
        //Son LinearLayout.LayoutParams porque los botones van a estar en un LinearLayout.
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT );



        for (int i=0; i<cantidadpalabras+1; i++){
            TextView te = new TextView(this);
            TextView tes = new TextView(this);
            Button button = new Button(this);
            //Asignamos propiedades de layout al boton
            te.setLayoutParams(lp);
            tes.setLayoutParams(lp);
            button.setLayoutParams(lp);

            if(i ==0){
                te.setText("Presiona el boton correspondiente a cada palabra para navegar entre el sinonimo y la palabra original");
                button.setText("Confirmar Cambios");
                button.setBackgroundColor(Color.BLUE);
                llBotonera.addView(te);
                llBotonera.addView(button);


            }else{
                try {




                    te.setText(temp[i-1]);

                    palabra = buscar_sinonimos(temp[i-1]);
                    palabraBase = temp[i-1];

                    tes.setText(palabra);

                    button.setText("Haz seleccionado la palabra: "+palabraBase);

                } catch (Exception e) {
                    e.printStackTrace();
                }
//Asignamos Texto al botón


                //Añadimos el botón a la botonera

                llBotonera.addView(te);
                llBotonera.addView(tes);
                llBotonera.addView(button);
            }

            button.setOnClickListener(new ButtonsOnClickListener(this, i, palabra,palabraBase));
        }

    }

    class ButtonsOnClickListener implements View.OnClickListener
    {
        int pin = 0;
        Context context;
        int numButton;
        String palabra;
        String palabraBase;

        public ButtonsOnClickListener(Context context, int numButton, String palabra, String palabraBase) {
            this.context = context;
            this.numButton = numButton;
            this.palabra = palabra;
            this.palabraBase = palabraBase;
        }

        @Override
        public void onClick(View v)
        {

            TextView teee = (TextView) v;
            if(numButton!=0){

                if(pin == 0){
                    pin =1;

                    teee.setText("Haz seleccionado: " + palabra);
//        Toast.makeText(getApplicationContext(),
//                "EL PARRAFO nuevo DICE: ", Toast.LENGTH_SHORT).show();
                    cabio(numButton,palabra);

                }else if(pin == 1){
                    pin =0;
                    teee.setText("Haz seleccionado: " + palabraBase);


                    cabio(numButton,palabraBase);
                    // aqui voy a obtener una funcion que va a recibir los datos de la palabra que estamos modificando es decir el numbutton
                    //en la funcion que aun no esta creada recibiré el index para usarlo con el parrafo original el cual estará segmentado por palabras
                    //mediante un split de esta forma
                    //parrafo[numbutton]
                    //funccion camb(numButton){ parrafo[numButton] =    }

                }

            }else{
                String nuevo ="";


                for(int i = 0; i<temp.length; i++){

                    nuevo = nuevo +temp[i] + " ";

                }
//
//    Toast.makeText(getApplicationContext(),
//            "el texto nuevo es: ", Toast.LENGTH_SHORT).show();
//    Toast.makeText(getApplicationContext(),
//            nuevo, Toast.LENGTH_SHORT).show();

                String tt = nuevo;
//    Toast.makeText(getApplicationContext(),
//            "hhhhhhhhhhhh: "+tt, Toast.LENGTH_SHORT).show();
                Intent intl = new Intent(getApplicationContext(), MainActivity.class);

                intl.putExtra("texto", tt);

                startActivity(intl);



            }







        }
    };
}