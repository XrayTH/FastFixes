package com.example.fastfixes.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fastfixes.R;

public class Registro_pro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_pro);

        // Referencia al botón "Ir al Login"
        Button btnRegistro2 = findViewById(R.id.btnRegistro2);

        // Configurar el clic del botón para ir al Login
        btnRegistro2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registro_pro.this, Login.class);
                startActivity(intent);
                finish(); // Finalizar la actividad actual para evitar volver con "atrás"
            }
        });

        // Referencia al botón "Regresar"
        Button btnRegresar2 = findViewById(R.id.btnregresar2);

        // Configurar el clic del botón para regresar a Registro
        btnRegresar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registro_pro.this, Registro.class);
                startActivity(intent);
                finish(); // Finalizar la actividad actual para evitar volver con "atrás"
            }
        });
    }
}
