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
import com.example.fastfixes.models.Cliente;

public class Resgistro_cli extends AppCompatActivity {

    // Campos del formulario
    private EditText etUsuario;
    private EditText etNombre;
    private EditText etApellido;
    private EditText etCiudad;
    private EditText etDireccion;
    private EditText etCorreo;
    private EditText etTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resgistro_cli);

        // Referencias a los campos de entrada
        etUsuario = findViewById(R.id.etUsuario);
        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etCiudad = findViewById(R.id.etCiudad);
        etDireccion = findViewById(R.id.etDireccion);
        etCorreo = findViewById(R.id.etEmail);
        etTelefono = findViewById(R.id.etTelefono);

        // Referencia al botón "Registrar"
        Button btnRegistrar = findViewById(R.id.btnRegistro2);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarCliente();
            }
        });

        // Referencia al botón "Regresar"
        Button btnRegresar2 = findViewById(R.id.btnregresar2);
        btnRegresar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Resgistro_cli.this, Registro.class);
                startActivity(intent);
                finish(); // Finalizar la actividad actual para evitar volver con "atrás"
            }
        });
    }

    private void registrarCliente() {
        // Obtener los valores de los campos
        String usuario = etUsuario.getText().toString().trim();
        String nombre = etNombre.getText().toString().trim();
        String apellido = etApellido.getText().toString().trim();
        String ciudad = etCiudad.getText().toString().trim();
        String direccion = etDireccion.getText().toString().trim();
        String correo = etCorreo.getText().toString().trim();
        String telefono = etTelefono.getText().toString().trim();

        // Validar campos (esto puede personalizarse según tus necesidades)
        if (usuario.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || ciudad.isEmpty() || direccion.isEmpty() || correo.isEmpty() || telefono.isEmpty()) {
            Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear cliente
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setUsuario(usuario);
        nuevoCliente.setNombre(nombre);
        nuevoCliente.setApellido(apellido);
        nuevoCliente.setCiudad(ciudad);
        nuevoCliente.setDireccion(direccion);
        nuevoCliente.setCorreo(correo);
        nuevoCliente.setTelefono(telefono);

        // Guardar cliente en la base de datos (en un hilo secundario)
        new Thread(() -> {
            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            db.clienteDao().insertar(nuevoCliente);

            // Mostrar un mensaje en la UI principal
            runOnUiThread(() -> {
                Toast.makeText(Resgistro_cli.this, "Cliente registrado exitosamente", Toast.LENGTH_SHORT).show();
                // Opcionalmente, limpiar los campos
                etUsuario.setText("");
                etNombre.setText("");
                etApellido.setText("");
                etCiudad.setText("");
                etDireccion.setText("");
                etCorreo.setText("");
                etTelefono.setText("");
            });
        }).start();
    }
}

