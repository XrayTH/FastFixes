package com.example.fastfixes.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Formulario extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int MAX_IMAGE_SIZE_MB = 1; // Tamaño máximo en MB
    private static final int MAX_IMAGE_DIMENSION = 1000; // Dimensión máxima (ancho o alto)

    private EditText etTitulo, etDescripcion, etFecha;
    private ImageView ivImagenSeleccionada;
    private Button btnSeleccionarImagen, btnGuardar, btnLleveAVerPublicacion;

    private String imagenBase64 = ""; // Almacena la imagen en Base64
    private AppDatabase db; // Instancia de la base de datos
    private PublicacionDao publicacionDao; // Dao para acceder a las publicaciones

    private String usuario; // Almacena el usuario recibido
    private String tipoUsuario; // Almacena el tipo de usuario recibido

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario);

        // Obtener el usuario y tipoUsuario del Intent
        Intent intent = getIntent();
        usuario = intent.getStringExtra("usuario");
        tipoUsuario = intent.getStringExtra("tipoUsuario");

        // Inicializar los campos del formulario
        etTitulo = findViewById(R.id.etTitulo);
        etDescripcion = findViewById(R.id.etDescripcion);
        etFecha = findViewById(R.id.etFecha);
        ivImagenSeleccionada = findViewById(R.id.ivImagenSeleccionada);
        btnSeleccionarImagen = findViewById(R.id.btnSeleccionarImagen);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnLleveAVerPublicacion = findViewById(R.id.btnLleveAVerPublicacion);

        // Configurar la base de datos y Dao
        db = AppDatabase.getInstance(this);
        publicacionDao = db.publicacionDao();

        // Configurar botón para seleccionar una imagen
        btnSeleccionarImagen.setOnClickListener(v -> abrirSelectorImagen());

        // Configurar botón para guardar la publicación
        btnGuardar.setOnClickListener(v -> guardarPublicacion());

        // Configurar botón para ir al Muro
        btnLleveAVerPublicacion.setOnClickListener(v -> irAlMuro());
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
                // Convertir la imagen seleccionada a Bitmap
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);

                // Verificar las dimensiones de la imagen
                if (bitmap.getWidth() > MAX_IMAGE_DIMENSION || bitmap.getHeight() > MAX_IMAGE_DIMENSION) {
                    Toast.makeText(this, "La imagen excede las dimensiones máximas de 1000x1000 píxeles.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Convertir el Bitmap a un arreglo de bytes
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageBytes = baos.toByteArray();

                // Verificar el tamaño del archivo
                if (imageBytes.length > MAX_IMAGE_SIZE_MB * 1024 * 1024) {
                    Toast.makeText(this, "El tamaño de la imagen excede 1 MB. Por favor selecciona otra.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Convertir la imagen a Base64
                imagenBase64 = Base64.encodeToString(imageBytes, Base64.DEFAULT);

                // Cargar la imagen seleccionada en el ImageView usando Glide
                Glide.with(this).load(imageUri).into(ivImagenSeleccionada);

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

        if (usuario == null || usuario.isEmpty()) {
            Toast.makeText(this, "El usuario no está disponible", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear una nueva publicación a partir de los datos del formulario
        Publicacion nuevaPublicacion = new Publicacion();
        nuevaPublicacion.setTitulo(etTitulo.getText().toString());
        nuevaPublicacion.setDescripcion(etDescripcion.getText().toString());
        nuevaPublicacion.setEstado("Solicitado");
        nuevaPublicacion.setImagen(imagenBase64);
        nuevaPublicacion.setFecha(etFecha.getText().toString());
        nuevaPublicacion.setCliente(usuario); // Asignamos el usuario como cliente

        // Guardar la publicación en la base de datos en un hilo secundario
        new Thread(() -> {
            try {
                publicacionDao.insertar(nuevaPublicacion);

                runOnUiThread(() -> {
                    Toast.makeText(Formulario.this, "Publicación guardada exitosamente", Toast.LENGTH_SHORT).show();
                    limpiarCampos();
                });
            } catch (Exception e) {
                runOnUiThread(() -> Toast.makeText(Formulario.this, "Error al guardar la publicación", Toast.LENGTH_SHORT).show());
                Log.e("Formulario", "Error al guardar la publicación", e);
            }
        }).start();
    }

    private void limpiarCampos() {
        etTitulo.setText("");
        etDescripcion.setText("");
        etFecha.setText("");
        ivImagenSeleccionada.setImageResource(android.R.color.transparent);
        imagenBase64 = "";
    }

    private void irAlMuro() {
        Intent intentMuro = new Intent(Formulario.this, Muro.class);
        intentMuro.putExtra("usuario", usuario);
        intentMuro.putExtra("tipoUsuario", tipoUsuario);
        startActivity(intentMuro);
    }
}





