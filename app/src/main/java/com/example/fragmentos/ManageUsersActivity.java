package com.example.fragmentos;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class ManageUsersActivity extends Fragment {

    private EditText emailEditText, oldPasswordEditText, newPasswordEditText, passwordEditText;
    private Button updateEmailButton, updatePasswordButton, deleteButton;
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_manage_users, container, false);

        emailEditText = view.findViewById(R.id.emailEditText);
        oldPasswordEditText = view.findViewById(R.id.old_password);
        newPasswordEditText = view.findViewById(R.id.new_password);
        passwordEditText = view.findViewById(R.id.passwordEditText);
        updateEmailButton = view.findViewById(R.id.Button_Update_user);
        updatePasswordButton = view.findViewById(R.id.updateButton);
        deleteButton = view.findViewById(R.id.deleteButton);

        // Inicializar Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        updateEmailButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            if (!TextUtils.isEmpty(email)) {
                updateEmail(email);
            } else {
                Toast.makeText(getActivity(), "Por favor, ingrese un correo electrónico válido.", Toast.LENGTH_SHORT).show();
            }
        });

        updatePasswordButton.setOnClickListener(v -> {
            String oldPassword = oldPasswordEditText.getText().toString().trim();
            String newPassword = newPasswordEditText.getText().toString().trim();
            String confirmPassword = passwordEditText.getText().toString().trim();

            if (!TextUtils.isEmpty(oldPassword) && !TextUtils.isEmpty(newPassword) && newPassword.equals(confirmPassword)) {
                reauthenticateAndChangePassword(oldPassword, newPassword);
            } else {
                Toast.makeText(getActivity(), "Por favor, asegúrese de que las contraseñas coincidan y que los campos no estén vacíos.", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(getActivity(), "Email actualizado correctamente.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Fallo en actualizar el email: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void reauthenticateAndChangePassword(String oldPassword, String newPassword) {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), oldPassword);
            user.reauthenticate(credential)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            user.updatePassword(newPassword)
                                    .addOnCompleteListener(passwordTask -> {
                                        if (passwordTask.isSuccessful()) {
                                            Toast.makeText(getActivity(), "Contraseña cambiada correctamente.", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(getActivity(), "Fallo en actualizar la contraseña: " + passwordTask.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        } else {
                            Toast.makeText(getActivity(), "Fallo en reautenticar: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(getActivity(), "Usuario borrado correctamente.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Fallo en borrar usuario: " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
