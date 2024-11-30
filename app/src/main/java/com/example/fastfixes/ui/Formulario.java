package com.example.fastfixes.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import com.example.fastfixes.utils.ImageUtils;

import java.io.IOException;
import java.util.ArrayList;

public class Formulario extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText etTitulo, etDescripcion, etEstado, etFecha, etFechaTerminado;
    private ImageView ivImagenSeleccionada;
    private Button btnSeleccionarImagen, btnGuardar, btnLleveAVerPublicacion;

    private String imagenBase64 = ""; // Almacena la imagen en Base64
    private static ArrayList<Publicacion> publicaciones = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario);

        // Inicializar los campos del formulario
        etTitulo = findViewById(R.id.etTitulo);
        etDescripcion = findViewById(R.id.etDescripcion);
        etEstado = findViewById(R.id.etEstado);
        etFecha = findViewById(R.id.etFecha);
        etFechaTerminado = findViewById(R.id.etFechaTerminado);
        ivImagenSeleccionada = findViewById(R.id.ivImagenSeleccionada);
        btnSeleccionarImagen = findViewById(R.id.btnSeleccionarImagen);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnLleveAVerPublicacion = findViewById(R.id.btnLleveAVerPublicacion);

        // Configurar botón para seleccionar una imagen
        btnSeleccionarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirSelectorImagen();
            }
        });

        // Configurar botón para publicar daños
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
        nuevaPublicacion.setEstado(etEstado.getText().toString());
        nuevaPublicacion.setImagen(imagenBase64); // Almacenar la imagen como Base64
        nuevaPublicacion.setFecha(etFecha.getText().toString());
        nuevaPublicacion.setFechaTerminado(etFechaTerminado.getText().toString());

        // Agregar la nueva publicación al array
        publicaciones.add(nuevaPublicacion);

        // Mostrar un mensaje de confirmación
        Toast.makeText(this, "Publicación exitosa", Toast.LENGTH_SHORT).show();

        // Limpiar los campos del formulario
        limpiarCampos();
    }

    private void limpiarCampos() {
        etTitulo.setText("");
        etDescripcion.setText("");
        etEstado.setText("");
        etFecha.setText("");
        etFechaTerminado.setText("");
        ivImagenSeleccionada.setImageResource(android.R.color.transparent); // Limpiar el ImageView
        imagenBase64 = ""; // Limpiar la imagen Base64
    }

    // Método para ir al Muro (Activity)
    private void irAlMuro() {
        Intent intent = new Intent(Formulario.this, Muro.class);
        startActivity(intent);
    }

    // Método para obtener el array de publicaciones (opcional)
    public static ArrayList<Publicacion> getPublicaciones() {
        return publicaciones;
    }
}
