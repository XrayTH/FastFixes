package com.example.fastfixes.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.fastfixes.R;
import com.example.fastfixes.models.Publicacion;
import com.example.fastfixes.data.AppDatabase;
import com.example.fastfixes.data.PublicacionDao;
import com.example.fastfixes.utils.ImageUtils;

import java.io.IOException;

public class Formulario extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText etTitulo, etDescripcion, etFecha;
    private ImageView ivImagenSeleccionada;
    private Button btnSeleccionarImagen, btnGuardar, btnLleveAVerPublicacion;

    private String imagenBase64 = ""; // Almacena la imagen en Base64
    private AppDatabase db; // Instancia de la base de datos
    private PublicacionDao publicacionDao; // Dao para acceder a las publicaciones

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario);

        // Inicializar los campos del formulario
        etTitulo = findViewById(R.id.etTitulo);
        etDescripcion = findViewById(R.id.etDescripcion);
        etFecha = findViewById(R.id.etFecha);
        ivImagenSeleccionada = findViewById(R.id.ivImagenSeleccionada);
        btnSeleccionarImagen = findViewById(R.id.btnSeleccionarImagen);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnLleveAVerPublicacion = findViewById(R.id.btnLleveAVerPublicacion);

        // Configurar la base de datos y Dao
        db = AppDatabase.getInstance(this); // Obtener la instancia de la base de datos
        publicacionDao = db.publicacionDao(); // Obtener el Dao para Publicaciones

        // Configurar botón para seleccionar una imagen
        btnSeleccionarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirSelectorImagen();
            }
        });

        // Configurar botón para guardar la publicación
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarPublicacion();
            }
        });

        // Configurar botón para ir al Muro
        btnLleveAVerPublicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irAlMuro();
            }
        });
    }

    private void abrirSelectorImagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Selecciona una imagen"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            try {
                // Cargar la imagen seleccionada en el ImageView usando Glide
                Glide.with(this).load(imageUri).into(ivImagenSeleccionada);

                // Convertir la imagen a Bitmap
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);

                // Convertir el Bitmap a Base64
                imagenBase64 = ImageUtils.bitmapToBase64(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error al cargar la imagen", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void guardarPublicacion() {
        if (imagenBase64.isEmpty()) {
            Toast.makeText(this, "Por favor, selecciona una imagen", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear una nueva publicación a partir de los datos del formulario
        Publicacion nuevaPublicacion = new Publicacion();
        nuevaPublicacion.setTitulo(etTitulo.getText().toString());
        nuevaPublicacion.setDescripcion(etDescripcion.getText().toString());
        nuevaPublicacion.setEstado("Solicitado");
        nuevaPublicacion.setImagen(imagenBase64); // Almacenar la imagen como Base64
        nuevaPublicacion.setFecha(etFecha.getText().toString());

        // Guardar la publicación en la base de datos en un hilo secundario
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Intentar insertar la publicación en la base de datos
                    publicacionDao.insertar(nuevaPublicacion); // Insertamos la publicación en la base de datos

                    // Actualizamos la UI en el hilo principal en caso de éxito
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // Mostrar mensaje de éxito
                            Toast.makeText(Formulario.this, "Publicación guardada exitosamente", Toast.LENGTH_SHORT).show();
                            limpiarCampos(); // Limpiar los campos después de guardar
                        }
                    });

                } catch (Exception e) {
                    // En caso de error al guardar
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Formulario.this, "Error al guardar la publicación"+e, Toast.LENGTH_SHORT).show();
                        }
                    });
                    e.printStackTrace();
                    Log.e("Formulario", "Error al guardar la publicación", e);  // Agregar un log de error más detallado
                }
            }
        }).start();
    }

    private void limpiarCampos() {
        etTitulo.setText("");
        etDescripcion.setText("");
        etFecha.setText("");
        ivImagenSeleccionada.setImageResource(android.R.color.transparent); // Limpiar el ImageView
        imagenBase64 = ""; // Limpiar la imagen Base64
    }

    // Método para ir al Muro (Activity)
    private void irAlMuro() {
        Intent intent = new Intent(Formulario.this, Muro.class);
        startActivity(intent);
    }
}


