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

public class Perfil_pro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil_pro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Referencia al botón "muro"
        Button muro1 = findViewById(R.id.muro1);

        // Configurar el clic del botón para regresar a MainActivity
        muro1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perfil_pro.this, Muro.class);
                startActivity(intent);
                finish(); // Finalizar la actividad actual para evitar volver con "atrás"
            }
        });

        // Referencia al botón "pendiete"
        Button pendiente = findViewById(R.id.pendiente);

        // Configurar el clic del botón para regresar a MainActivity
        pendiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perfil_pro.this, Historial.class);
                startActivity(intent);
                finish(); // Finalizar la actividad actual para evitar volver con "atrás"
            }
        });

        // Referencia al botón "Regresar"
        Button salir1 = findViewById(R.id.salir1);

        // Configurar el clic del botón para regresar a MainActivity
        salir1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perfil_pro.this, MainActivity.class);
                startActivity(intent);
                finish(); // Finalizar la actividad actual para evitar volver con "atrás"
            }
        });
    }
}