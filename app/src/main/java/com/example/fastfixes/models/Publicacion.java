package com.example.fastfixes.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;

@Entity(tableName = "publicaciones")
public class Publicacion {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String cliente;
    private String imagen; //Podrías usar un URI o base64 si lo deseas.
    private String titulo;
    private String descripcion;
    private String estado;
    private String profesional;
    private String fecha;
    private String fechaTerminado;

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getProfesional() { return profesional; }
    public void setProfesional(String profesional) { this.profesional = profesional; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getFechaTerminado() { return fechaTerminado; }
    public void setFechaTerminado(String fechaTerminado) { this.fechaTerminado = fechaTerminado; }
}
