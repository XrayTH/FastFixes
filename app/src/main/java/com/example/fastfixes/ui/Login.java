package com.example.fastfixes.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fastfixes.R;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // login
        Button btnLogin = findViewById(R.id.btnLogin);

        // Configurar el clic del botón para regresar a MainActivity
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Muro.class);
                startActivity(intent);
                finish(); // Finalizar la actividad actual para evitar volver con "atrás"
            }
        });

        // Referencia al botón "Regresar"
            Button btnRegresar = findViewById(R.id.btnregresar);

            // Configurar el clic del botón para regresar a MainActivity
            btnRegresar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Finalizar la actividad actual para evitar volver con "atrás"
                }
        });
    }
}