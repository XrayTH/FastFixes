package com.example.fastfixes.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fastfixes.R;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        // Referencia al botón "cliente"
        Button btncliente = findViewById(R.id.btncliente);
        // Referencia al botón "profesional"
        Button btnvolver1 = findViewById(R.id.btnvolver1);

        // Configurar el clic del botón para abrir Resgistro_cli
        btncliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registro.this, Resgistro_cli.class);
                startActivity(intent);
                finish(); // Finalizar la actividad actual para evitar volver con "atrás"
            }
        });

        // Referencia al botón "profesional"
        Button btnprofesional = findViewById(R.id.btnprofesional);

        // Configurar el clic del botón para abrir Registro_pro
        btnprofesional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registro.this, Registro_pro.class);
                startActivity(intent);
                finish(); // Finalizar la actividad actual para evitar volver con "atrás"
            }
        });



        // Configurar el clic del botón para abrir Registro_pro
        btnvolver1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registro.this, MainActivity.class);
                startActivity(intent);
                finish(); // Finalizar la actividad actual para evitar volver con "atrás"
            }
        });
    }
}
