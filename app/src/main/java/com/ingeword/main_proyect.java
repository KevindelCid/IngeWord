package com.ingeword;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ingeword.fragments.introFragment;

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
                    showSelectedFragment(new introFragment());
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
    private void showSelectedFragment(Fragment fragment){

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
    }
}