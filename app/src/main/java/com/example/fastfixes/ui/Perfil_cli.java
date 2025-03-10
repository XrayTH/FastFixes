package com.example.fastfixes.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fastfixes.R;

public class Perfil_cli extends AppCompatActivity {

    TextView nombreUsuario;  // Para mostrar el nombre de usuario
    TextView tipoUsuario;    // Para mostrar el tipo de usuario

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil_cli);

        // Referencias a los TextViews en la interfaz
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

        // Referencia al botón "publicar1"
        Button publicar1 = findViewById(R.id.publicar1);

        // Configurar el clic del botón "Publicar"
        publicar1.setOnClickListener(v -> {
            // Crear un Intent para ir a la actividad Formulario
            Intent intentFormulario = new Intent(Perfil_cli.this, Formulario.class);

            // Pasar los datos del usuario y tipoUsuario al formulario
            intentFormulario.putExtra("usuario", usuario);
            intentFormulario.putExtra("tipoUsuario", tipo);

            // Iniciar la actividad de formulario
            startActivity(intentFormulario);
            finish(); // Finalizar la actividad actual para evitar regresar
        });

        // Referencia al botón "salir2"
        Button salir2 = findViewById(R.id.salir2);

        // Configurar el clic del botón "Salir"
        salir2.setOnClickListener(v -> {
            Intent intentSalir = new Intent(Perfil_cli.this, MainActivity.class);
            startActivity(intentSalir);
            finish(); // Finalizar la actividad actual para evitar regresar
        });

        // Referencia al botón "btnHistorial"
        Button btnHistorial = findViewById(R.id.btnHistorial);

        // Configurar el clic del botón "Historial"
        btnHistorial.setOnClickListener(v -> {
            // Crear un Intent para ir a la actividad Historial
            Intent intentHistorial = new Intent(Perfil_cli.this, Historial.class);

            // Pasar los datos del usuario y tipoUsuario a la actividad Historial
            intentHistorial.putExtra("usuario", usuario);
            intentHistorial.putExtra("tipoUsuario", tipo);

            // Iniciar la actividad Historial
            startActivity(intentHistorial);
            finish(); // Finalizar la actividad actual para evitar regresar
        });
    }
}


