package com.example.fragmentos;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ManageUsersActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button updateButton, deleteButton;
    private TextView statusTextView;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_manage_users);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);
        statusTextView = findViewById(R.id.statusTextView);

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
