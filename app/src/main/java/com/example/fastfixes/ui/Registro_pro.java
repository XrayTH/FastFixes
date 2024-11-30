package com.example.fastfixes.ui;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fastfixes.R;
import com.example.fastfixes.data.AppDatabase;
import com.example.fastfixes.models.Profesional;

public class Registro_pro extends AppCompatActivity {

    // Campos del formulario
    private EditText etUsuario;
    private EditText etContrasena;
    private EditText etNombre;
    private EditText etApellido;
    private EditText etCiudad;
    private EditText etDireccion;
    private EditText etEspecialidad;
    private EditText etCorreo;
    private EditText etTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_pro);

        // Referencias a los campos de entrada
        etUsuario = findViewById(R.id.etUsuario);
        etContrasena = findViewById(R.id.etContrasena);
        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etCiudad = findViewById(R.id.etCiudad);
        etDireccion = findViewById(R.id.etDireccion);
        etEspecialidad = findViewById(R.id.etProfesion);
        etCorreo = findViewById(R.id.etEmail);
        etTelefono = findViewById(R.id.etTelefono);

        // Referencia al botón "Registrar"
        Button btnRegistrar = findViewById(R.id.btnRegistro2);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarProfesional();
            }
        });

        // Referencia al botón "Regresar"
        Button btnRegresar2 = findViewById(R.id.btnregresar2);
        btnRegresar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registro_pro.this, Registro.class);
                startActivity(intent);
                finish(); // Finalizar la actividad actual para evitar volver con "atrás"
            }
        });
    }

    private void registrarProfesional() {
        // Obtener los valores de los campos
        String usuario = etUsuario.getText().toString().trim();
        String contrasena = etContrasena.getText().toString().trim();
        String nombre = etNombre.getText().toString().trim();
        String apellido = etApellido.getText().toString().trim();
        String ciudad = etCiudad.getText().toString().trim();
        String direccion = etDireccion.getText().toString().trim();
        String especialidad = etEspecialidad.getText().toString().trim();
        String correo = etCorreo.getText().toString().trim();
        String telefono = etTelefono.getText().toString().trim();

        // Validar campos (esto puede personalizarse según tus necesidades)
        if (usuario.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || ciudad.isEmpty() || especialidad.isEmpty() || correo.isEmpty() || telefono.isEmpty()) {
            Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear profesional
        Profesional nuevoProfesional = new Profesional();
        nuevoProfesional.setUsuario(usuario);
        nuevoProfesional.setContrasena(contrasena);
        nuevoProfesional.setNombre(nombre);
        nuevoProfesional.setApellido(apellido);
        nuevoProfesional.setCiudad(ciudad);
        nuevoProfesional.setDireccion(direccion);
        nuevoProfesional.setProfesion(especialidad);
        nuevoProfesional.setCorreo(correo);
        nuevoProfesional.setTelefono(telefono);

        // Guardar profesional en la base de datos (en un hilo secundario)
        new Thread(() -> {
            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            db.profesionalDao().insertar(nuevoProfesional);

            // Mostrar un mensaje en la UI principal
            runOnUiThread(() -> {
                Toast.makeText(Registro_pro.this, "Profesional registrado exitosamente", Toast.LENGTH_SHORT).show();
                // Opcionalmente, limpiar los campos
                etUsuario.setText("");
                etContrasena.setText("");
                etNombre.setText("");
                etApellido.setText("");
                etCiudad.setText("");
                etDireccion.setText("");
                etEspecialidad.setText("");
                etCorreo.setText("");
                etTelefono.setText("");
            });
        }).start();
    }
}
