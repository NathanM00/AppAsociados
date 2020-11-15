package com.pdg.appasociados;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {


    BottomNavigationView nav_bar;
    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nav_bar = findViewById(R.id.main_nav);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contenedor,
                new HomeFragment()).commit();

        barraDeNavegacion();

    }

  private void barraDeNavegacion(){

        nav_bar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                Fragment fragment = null;

                switch (menuItem.getItemId()) {

                    case R.id.nav_menu:
                        fragment = new HomeFragment();

                        break;

                    case R.id.nav_benef:
                        fragment = new BeneficeFragment();


                        break;

                    case R.id.nav_notis:
                        fragment = new NoticiasFragment();

                        break;

                    case R.id.nav_ayuda:
                        fragment = new HelpFragment();

                        break;

                    case R.id.nav_perfil:
                        fragment = new PerfilFragment();

                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contenedor,
                        fragment).commit();
                return true;
            }

        });

    }

}