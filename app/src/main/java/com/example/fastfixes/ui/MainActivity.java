package com.example.fastfixes.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fastfixes.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Asegúrate de que el nombre de tu layout sea correcto

        // Botón Iniciar Sesión
        Button iniciarSesionBtn = findViewById(R.id.Iniciosesion);
        iniciarSesionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicia el Activity de Login
                Intent loginIntent = new Intent(MainActivity.this, Login.class);
                startActivity(loginIntent);
            }
        });

        // Botón Registrarse
        Button registroBtn = findViewById(R.id.Registro);
        registroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicia el Activity de Registro
                Intent registroIntent = new Intent(MainActivity.this, Registro.class);
                startActivity(registroIntent);
            }
        });
    }
}