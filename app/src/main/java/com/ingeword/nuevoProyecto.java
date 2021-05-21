package com.ingeword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class nuevoProyecto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_proyecto);









        Button nuevo = (Button) findViewById(R.id.btnnuevop);


        nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {

                Intent intl = new Intent(getApplicationContext(), introduccion_activity.class);

//                intl.putExtra("texto", tt);

                startActivity(intl);
                Toast.makeText(getApplicationContext(),
                        "El boton si está funcionando", Toast.LENGTH_LONG).show();

            }
        });





    }
}