package com.example.fastfixes.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fastfixes.R;

public class Perfil_pro extends AppCompatActivity {

    TextView nombreUsuario;  // Para mostrar el nombre de usuario
    TextView tipoUsuario;    // Para mostrar el tipo de usuario

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil_pro);

        // Ajuste de márgenes para la barra de estado
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar los TextViews para mostrar el nombre y tipo de usuario
        nombreUsuario = findViewById(R.id.txtUsuario);
        tipoUsuario = findViewById(R.id.txtTipoUsuario);

        // Obtener los datos del Intent
        Intent intent = getIntent();
        String usuario = intent.getStringExtra("usuario");
        String tipo = intent.getStringExtra("tipoUsuario");

        // Mostrar los datos en los TextViews
        if (usuario != null && tipo != null) {
            nombreUsuario.setText("Usuario: " + usuario);
            tipoUsuario.setText("Tipo: " + tipo);
        } else {
            nombreUsuario.setText("Usuario no disponible");
            tipoUsuario.setText("Tipo no disponible");
        }

        // Referencia al botón "muro1"
        Button muro1 = findViewById(R.id.muro1);

        // Configurar el clic del botón para regresar a Muro
        muro1.setOnClickListener(v -> {
            Intent intentMuro = new Intent(Perfil_pro.this, Muro.class);
            startActivity(intentMuro);
            finish(); // Finalizar la actividad actual para evitar volver con "atrás"
        });

        // Referencia al botón "salir1"
        Button salir1 = findViewById(R.id.salir1);

        // Configurar el clic del botón "Salir"
        salir1.setOnClickListener(v -> {
            Intent intentSalir = new Intent(Perfil_pro.this, MainActivity.class);
            startActivity(intentSalir);
            finish(); // Finalizar la actividad actual para evitar volver con "atrás"
        });
    }
}
