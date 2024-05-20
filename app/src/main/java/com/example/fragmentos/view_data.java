package com.example.fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class view_data extends Fragment {

    private TextView dataTextView;

    public view_data() {
        // Constructor vacío obligatorio
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflar el diseño para este fragmento
        View view = inflater.inflate(R.layout.frag_view_data, container, false);

        dataTextView = view.findViewById(R.id.dataTextView);

        // Inicializar la referencia a la base de datos
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("your_node");

        // Agregar un listener para obtener los datos
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    StringBuilder data = new StringBuilder();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        // Asume que los datos son Strings, cambia esto según tu estructura de datos
                        String value = snapshot.getValue(String.class);
                        data.append(value).append("\n");
                    }
                    dataTextView.setText(data.toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Manejo de errores
                dataTextView.setText("Error: " + databaseError.getMessage());
            }
        });

        return view;
    }
}
