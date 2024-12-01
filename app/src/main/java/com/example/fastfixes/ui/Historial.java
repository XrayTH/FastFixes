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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fastfixes.R;
import com.example.fastfixes.models.Publicacion;
import com.example.fastfixes.data.AppDatabase;
import com.example.fastfixes.data.PublicacionDao;

import java.util.ArrayList;

public class Historial extends AppCompatActivity {

    private RecyclerView rvPublicaciones;
    private PublicacionAdapter publicacionAdapter;
    private ArrayList<Publicacion> publicaciones;

    private AppDatabase db; // Instancia de la base de datos
    private PublicacionDao publicacionDao; // Dao para acceder a las publicaciones

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_historial);

        // Ajuste de márgenes para la barra de estado
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar la base de datos y el Dao
        db = AppDatabase.getInstance(this); // Instanciamos la base de datos
        publicacionDao = db.publicacionDao(); // Obtenemos el Dao para Publicacion

        // Obtener los datos del usuario desde el Intent
        Intent intent = getIntent();
        String usuario = intent.getStringExtra("usuario");
        String tipoUsuario = intent.getStringExtra("tipoUsuario");

        // Verificar que los datos no sean nulos antes de usarlos
        if (usuario != null && tipoUsuario != null) {
            // Llamar a la función para obtener las publicaciones filtradas
            obtenerPublicaciones(usuario, tipoUsuario);
        }

        // Configurar el botón "volver"
        Button volver = findViewById(R.id.volver);
        volver.setOnClickListener(v -> {
            Intent volverIntent = new Intent(Historial.this, Perfil_pro.class);
            volverIntent.putExtra("usuario", usuario);
            volverIntent.putExtra("tipoUsuario", tipoUsuario);
            startActivity(volverIntent);
            finish(); // Finalizar actividad actual
        });

        // Inicializar RecyclerView para las publicaciones
        rvPublicaciones = findViewById(R.id.rvPublicaciones);
        rvPublicaciones.setLayoutManager(new LinearLayoutManager(this));
    }

    public void obtenerPublicaciones(String usuario, String tipoUsuario) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Obtener todas las publicaciones con estado "Solicitado"
                publicaciones = (ArrayList<Publicacion>) publicacionDao.obtenerPorEstado("Solicitado");

                if (publicaciones != null && !publicaciones.isEmpty()) {
                    // Filtrar las publicaciones por usuario (cliente o profesional)
                    ArrayList<Publicacion> publicacionesFiltradas = new ArrayList<>();
                    for (Publicacion publicacion : publicaciones) {
                        if (usuario.equals(publicacion.getCliente()) || usuario.equals(publicacion.getProfesional())) {
                            publicacionesFiltradas.add(publicacion);
                        }
                    }

                    // Si encontramos publicaciones, las invertimos (si es necesario)
                    if (!publicacionesFiltradas.isEmpty()) {
                        java.util.Collections.reverse(publicacionesFiltradas);
                    }

                    // Actualizar el RecyclerView en el hilo principal
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            publicacionAdapter = new PublicacionAdapter(Historial.this, publicacionesFiltradas, usuario, tipoUsuario, publicacionDao);
                            rvPublicaciones.setAdapter(publicacionAdapter);
                        }
                    });
                }
            }
        }).start();
    }
}




