package com.example.fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

public class carga extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carga);

        final int DURACION = 1000;

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(carga.this, MainActivity.class);
            startActivity(intent);
            finish();
        }, DURACION);
    }
}