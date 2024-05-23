package com.example.fragmentos;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Usuario extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_usuario);

        BottomNavigationView BottomNavigationView = findViewById(R.id.menu_usuario);
        BottomNavigationView.setOnNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameContenedor_adm, new Frag3()).commit();
        BottomNavigationView.setSelectedItemId(R.id.login_menu);


        getSupportFragmentManager().beginTransaction().replace(R.id.frameContenedor_adm, new pantalla_P_admin()).commit();

    }

    public boolean onNavigationItemSelected(@NonNull MenuItem Item) { 
        int itemid = Item.getItemId();
        if (itemid == R.id.Registro_cliente) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameContenedor_adm, new Frag2()).commit();
        } else if (itemid == R.id.p_info_cliente) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameContenedor_adm, new info_admin_frag()).commit();
        }
        else if (itemid == R.id.modificar_cliente) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameContenedor_adm, new ManageUsersActivity()).commit();
        }
        else if (itemid == R.id.cerrar_sesion) {
            // Aquí cambiamos el fragmento por una actividad
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish(); // Opcional: cierra la actividad actual si no quieres que el usuario regrese a ella
        }
        return true;
    }
}
