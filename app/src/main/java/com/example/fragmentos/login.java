package com.example.fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class login extends Fragment {

    private EditText emailEditText, passwordEditText;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_frag4, container, false);

        // Inicializar Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        emailEditText = view.findViewById(R.id.email);
        passwordEditText = view.findViewById(R.id.password);
        Button loginButton = view.findViewById(R.id.login);

        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (!isValidEmail(email)) {
                emailEditText.setError("Correo electrónico no válido.");
                emailEditText.requestFocus();
            } else if (!isValidPassword(password)) {
                passwordEditText.setError("La contraseña debe tener al menos 6 caracteres.");
                passwordEditText.requestFocus();
            } else {
                signIn(email, password);
            }
        });

        return view;
    }

    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 8;
    }

    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(Objects.requireNonNull(getActivity()), task -> {
                    if (task.isSuccessful()) {
                        // Inicio de sesión exitoso
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(getActivity(), "Inicio de sesión exitoso.", Toast.LENGTH_SHORT).show();
                        // Redirigir a MainActivity o cualquier otra actividad
                        Intent intent = new Intent(getActivity(), Usuario.class);
                        startActivity(intent);
                        getActivity().finish(); // Opcional: Finaliza la actividad actual
                    } else {
                        // Si el inicio de sesión falla
                        Toast.makeText(getActivity(), "Inicio de sesión fallido: " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
