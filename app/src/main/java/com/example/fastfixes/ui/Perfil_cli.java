package com.example.fastfixes.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fastfixes.R;

public class Perfil_cli extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil_cli);

        // Referencia al botón "publicar1" después de inflar la vista
        Button publicar1 = findViewById(R.id.publicar1);

        // Configurar el clic del botón
        publicar1.setOnClickListener(v -> {
            Intent intent = new Intent(Perfil_cli.this, Formulario.class);
            startActivity(intent);
            finish(); // Finalizar actividad actual
        });

        Button salir2 = findViewById(R.id.salir2);

        // Configurar el clic del botón
        salir2.setOnClickListener(v -> {
            Intent intent = new Intent(Perfil_cli.this, MainActivity.class);
            startActivity(intent);
            finish(); // Finalizar actividad actual
        });


    }
}
