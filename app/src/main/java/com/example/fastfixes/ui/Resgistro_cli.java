package com.example.fastfixes.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fastfixes.R;

public class Resgistro_cli extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resgistro_cli);

        // Referencia al botón "Regresar"
        Button btnRegistro1= findViewById(R.id.btnRegistro2);

        // Configurar el clic del botón para regresar a MainActivity
        btnRegistro1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Resgistro_cli.this, Login.class);
                startActivity(intent);
                finish(); // Finalizar la actividad actual para evitar volver con "atrás"
            }
        });

            // Referencia al botón "Regresar"
            Button btnRegresar2 = findViewById(R.id.btnregresar2);

            // Configurar el clic del botón para regresar a MainActivity
            btnRegresar2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Resgistro_cli.this, Registro.class);
                    startActivity(intent);
                    finish(); // Finalizar la actividad actual para evitar volver con "atrás"
                }
        });
    }
}