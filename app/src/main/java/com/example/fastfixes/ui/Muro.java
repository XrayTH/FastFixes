package com.example.fastfixes.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fastfixes.R;
import com.example.fastfixes.models.Publicacion;

import java.util.ArrayList;

public class Muro extends AppCompatActivity {

    private RecyclerView rvPublicaciones;
    private PublicacionAdapter publicacionAdapter;
    private ArrayList<Publicacion> publicaciones;
    private TextView tvBienvenida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_muro);

        // Ajuste de márgenes para la barra de estado
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar el TextView donde se mostrará el mensaje de bienvenida
        tvBienvenida = findViewById(R.id.bienvenida);

        // Obtener los datos del usuario desde el Intent
        Intent intent = getIntent();
        String usuario = intent.getStringExtra("usuario");
        String tipoUsuario = intent.getStringExtra("tipoUsuario");

        // Verificar que los datos no sean nulos antes de usarlos
        if (usuario != null && tipoUsuario != null) {
            // Mostrar el mensaje de bienvenida
            tvBienvenida.setText("Bienvenido, " + usuario + " | Tipo: " + tipoUsuario);
        } else {
            // Si no se reciben los datos correctamente, mostrar un mensaje predeterminado
            tvBienvenida.setText("Bienvenido, Usuario | Tipo: Desconocido");
        }

        // Configurar el botón para ir al perfil
        Button btn_perfil = findViewById(R.id.btn_perfil);
        btn_perfil.setOnClickListener(v -> {
            Intent perfilIntent = new Intent(Muro.this, Perfil_cli.class);
            startActivity(perfilIntent);
            finish(); // Finalizar la actividad actual para evitar el regreso
        });

        // Configurar el botón de salir
        Button btnsalir = findViewById(R.id.btn_salir);
        btnsalir.setOnClickListener(v -> {
            Intent salirIntent = new Intent(Muro.this, MainActivity.class);
            startActivity(salirIntent);
            finish(); // Finalizar la actividad actual
        });

        // Inicializar RecyclerView para las publicaciones
        rvPublicaciones = findViewById(R.id.rvPublicaciones);
        rvPublicaciones.setLayoutManager(new LinearLayoutManager(this));

        // Obtener las publicaciones (este método depende de cómo estés obteniendo las publicaciones)
        publicaciones = Formulario.getPublicaciones(); // Este método debe ser correcto según tu implementación

        // Configurar el adaptador para RecyclerView
        publicacionAdapter = new PublicacionAdapter(this, publicaciones);
        rvPublicaciones.setAdapter(publicacionAdapter);
    }
}

