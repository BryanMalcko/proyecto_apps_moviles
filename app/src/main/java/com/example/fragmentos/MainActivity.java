package com.example.fragmentos;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        BottomNavigationView BottomNavigationView = findViewById(R.id.menu_principal);
        BottomNavigationView.setOnNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameContenedor, new Frag3()).commit();
        BottomNavigationView.setSelectedItemId(R.id.login_menu);


        getSupportFragmentManager().beginTransaction().replace(R.id.frameContenedor, new Frag1()).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem Item) {
        int itemid = Item.getItemId();
        if(itemid == R.id.info_cliente_menu){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameContenedor, new Frag3()).commit();
        }
        else if (itemid == R.id.registro_menu) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameContenedor, new Frag2()).commit();
        }
        else if (itemid == R.id.login_menu) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameContenedor, new login()).commit();
        }

        return true;
    }
}