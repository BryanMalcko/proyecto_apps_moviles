package com.example.fragmentos;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ManageUsersActivity extends Fragment {

    private EditText emailEditText, passwordEditText;
    private Button updateButton, deleteButton;
    private TextView statusTextView;
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_manage_users, container, false);

        emailEditText = view.findViewById(R.id.emailEditText);
        passwordEditText = view.findViewById(R.id.passwordEditText);
        updateButton = view.findViewById(R.id.updateButton);
        deleteButton = view.findViewById(R.id.deleteButton);

        // Inicializar Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        updateButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (!TextUtils.isEmpty(email)) {
                updateEmail(email);
            }
            if (!TextUtils.isEmpty(password)) {
                updatePassword(password);
            }
        });

        deleteButton.setOnClickListener(v -> deleteUser());

        return view;
    }

    private void updateEmail(String email) {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            user.updateEmail(email)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            statusTextView.setText("Email Actualizado.");
                        } else {
                            statusTextView.setText("Fallo en actualizar Email: " + task.getException().getMessage());
                        }
                    });
        }
    }

    private void updatePassword(String password) {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            user.updatePassword(password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            statusTextView.setText("Contraseña Cambiada.");
                        } else {
                            statusTextView.setText("Fallo en actualizar la contraseña: " + task.getException().getMessage());
                        }
                    });
        }
    }

    private void deleteUser() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            user.delete()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            statusTextView.setText("Usuario Borrado.");
                        } else {
                            statusTextView.setText("Fallo en borrar usuario: " + task.getException().getMessage());
                        }
                    });
        }
    }
}
