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
    private EditText etContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resgistro_cli);

        // Referencias a los campos de entrada
        etUsuario = findViewById(R.id.etUsuario);
        etContrasena = findViewById(R.id.etContrasena);

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
        String contrasena = etContrasena.getText().toString().trim();

        // Crear cliente
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setUsuario(usuario);
        nuevoCliente.setContrasena(contrasena);

        // Guardar cliente en la base de datos (en un hilo secundario)
        new Thread(() -> {
            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            db.clienteDao().insertar(nuevoCliente);

            // Mostrar un mensaje en la UI principal
            runOnUiThread(() -> {
                Toast.makeText(Resgistro_cli.this, "Cliente registrado exitosamente", Toast.LENGTH_SHORT).show();
                // Opcionalmente, limpiar los campos
                etUsuario.setText("");
                etContrasena.setText("");
            });
        }).start();
    }
}

