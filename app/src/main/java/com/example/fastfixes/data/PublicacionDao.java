package com.example.fastfixes.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fastfixes.models.Publicacion;

import java.util.List;

@Dao
public interface PublicacionDao {
    @Insert
    void insertar(Publicacion publicacion);

    @Update
    void actualizar(Publicacion publicacion);

    @Query("SELECT * FROM publicaciones")
    List<Publicacion> obtenerTodas();

    @Query("SELECT * FROM publicaciones WHERE id = :id")
    Publicacion obtenerPorId(int id);

    @Query("SELECT * FROM publicaciones WHERE estado = :estado")
    List<Publicacion> obtenerPorEstado(String estado);
}

