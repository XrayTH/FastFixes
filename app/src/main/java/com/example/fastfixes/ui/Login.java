package com.example.fastfixes.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fastfixes.R;
import com.example.fastfixes.data.ClienteDao;
import com.example.fastfixes.data.ProfesionalDao;
import com.example.fastfixes.data.AppDatabase;
import com.example.fastfixes.models.Cliente;
import com.example.fastfixes.models.Profesional;

import java.util.List;

public class Login extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private AppDatabase database;
    private ClienteDao clienteDao;
    private ProfesionalDao profesionalDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // Inicializamos las vistas
        etUsername = findViewById(R.id.etcorreo);
        etPassword = findViewById(R.id.etcontra);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegresar = findViewById(R.id.btnregresar);

        // Inicializamos la base de datos
        database = AppDatabase.getInstance(this);
        clienteDao = database.clienteDao();
        profesionalDao = database.profesionalDao();

        // Configuramos el botón de login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Login.this, "Por favor ingresa usuario y contraseña", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Hacer la verificación en segundo plano
                new LoginTask().execute(username, password);
            }
        });

        // Configuramos el botón de regresar
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    // AsyncTask para manejar la verificación en segundo plano
    private class LoginTask extends AsyncTask<String, Void, Boolean> {
        private String tipoUsuario;
        private String usuarioNombre;

        @Override
        protected Boolean doInBackground(String... params) {
            String username = params[0];
            String password = params[1];

            // Verificamos si el usuario es un Cliente o Profesional
            boolean loginExitoso = verificarLogin(username, password);
            return loginExitoso;
        }

        @Override
        protected void onPostExecute(Boolean loginExitoso) {
            super.onPostExecute(loginExitoso);
            if (loginExitoso) {
                Toast.makeText(Login.this, "Login exitoso", Toast.LENGTH_SHORT).show();

                // Enviar datos a la actividad Muro
                Intent intent = new Intent(Login.this, Muro.class);
                intent.putExtra("usuario", usuarioNombre);
                intent.putExtra("tipoUsuario", tipoUsuario);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(Login.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        }

        // Método para verificar si el usuario es un Cliente o Profesional
        private boolean verificarLogin(String username, String password) {
            // Verificamos en la tabla de Clientes
            List<Cliente> clientes = clienteDao.obtenerTodos();
            for (Cliente cliente : clientes) {
                if (cliente.getUsuario().equals(username) && cliente.getContrasena().equals(password)) {
                    Log.d("Login", "Usuario encontrado en clientes");
                    tipoUsuario = "Cliente";
                    usuarioNombre = cliente.getUsuario();
                    return true;
                }
            }

            // Verificamos en la tabla de Profesionales
            List<Profesional> profesionales = profesionalDao.obtenerTodos();
            for (Profesional profesional : profesionales) {
                if (profesional.getUsuario().equals(username) && profesional.getContrasena().equals(password)) {
                    Log.d("Login", "Usuario encontrado en profesionales");
                    tipoUsuario = "Profesional";
                    usuarioNombre = profesional.getUsuario();
                    return true;
                }
            }

            // Si no encontramos coincidencias en ninguna tabla
            return false;
        }
    }
}

