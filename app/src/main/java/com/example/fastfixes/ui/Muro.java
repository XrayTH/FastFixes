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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fastfixes.R;
import com.example.fastfixes.models.Publicacion;

import java.util.ArrayList;

public class Muro extends AppCompatActivity {

    private RecyclerView rvPublicaciones;
    private PublicacionAdapter publicacionAdapter;
    private ArrayList<Publicacion> publicaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_muro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Referencia al botón "perfil"
        Button btn_perfil = findViewById(R.id.btn_perfil);
        btn_perfil.setOnClickListener(v -> {
            Intent intent = new Intent(Muro.this, Perfil_cli.class);
            startActivity(intent);
            finish(); // Finalizar actividad actual
        });

        // Referencia al botón "salir"
        Button btnsalir = findViewById(R.id.btn_salir);
        btnsalir.setOnClickListener(v -> {
            Intent intent = new Intent(Muro.this, MainActivity.class);
            startActivity(intent);
            finish(); // Finalizar actividad actual
        });

        // Inicializar RecyclerView
        rvPublicaciones = findViewById(R.id.rvPublicaciones);
        rvPublicaciones.setLayoutManager(new LinearLayoutManager(this));

        // Obtener publicaciones del formulario
        publicaciones = Formulario.getPublicaciones();

        // Configurar el adaptador
        publicacionAdapter = new PublicacionAdapter(this, publicaciones);
        rvPublicaciones.setAdapter(publicacionAdapter);
    }
}
