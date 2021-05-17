package com.ingeword;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class main_proyect extends AppCompatActivity {

    BottomNavigationView bottomView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_proyect);

        bottomView = (BottomNavigationView) findViewById(R.id.bottomnav);
        bottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.menu_intro){
                    Toast.makeText(getApplicationContext(),
                        "Estas en la ventana de la introduccion", Toast.LENGTH_SHORT).show(); }
                if(item.getItemId() == R.id.menu_body){

                    Toast.makeText(getApplicationContext(),
                            "Estas en la ventana del cuerpo", Toast.LENGTH_SHORT).show();
                }
                if(item.getItemId() == R.id.menu_conclu){
                    Toast.makeText(getApplicationContext(),
                            "Estas en la ventana de la conclusion", Toast.LENGTH_SHORT).show();

                }

                return true;
            }
        });

    }
}