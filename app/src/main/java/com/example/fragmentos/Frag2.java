package com.example.fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Frag2 extends Fragment {

    private EditText editTextNombre;
    private EditText editTextEdad;
    private EditText editTextUsuario;
    private EditText editTextContraseña;
    private Button btnRegistrar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pantalla_reg_frag2, container, false);

        // Inicializar los EditText y Button
        editTextNombre = view.findViewById(R.id.editTextNombre);
        editTextEdad = view.findViewById(R.id.editTextEdad);
        editTextUsuario = view.findViewById(R.id.editTextUsr);
        editTextContraseña = view.findViewById(R.id.editTextPssw);
        btnRegistrar = view.findViewById(R.id.btn_acceder);

        // Configurar el listener del botón
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();
            }
        });

        return view;
    }

    private void registrarUsuario() {
        // Obtener los datos de los EditText
        String nombre = editTextNombre.getText().toString();
        String edad = editTextEdad.getText().toString();
        String usuario = editTextUsuario.getText().toString();
        String contraseña = editTextContraseña.getText().toString();

        // Aquí puedes agregar la lógica para guardar estos datos en una base de datos o lo que requieras
        Log.d("RegistroUsuario", "Nombre: " + nombre);
        Log.d("RegistroUsuario", "Edad: " + edad);
        Log.d("RegistroUsuario", "Usuario: " + usuario);
        Log.d("RegistroUsuario", "Contraseña: " + contraseña);

        // Por ejemplo, podrías llamar aquí a una función que guarde estos datos en una base de datos SQLite
    }
}
