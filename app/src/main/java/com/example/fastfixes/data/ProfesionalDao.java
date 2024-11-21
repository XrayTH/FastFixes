package com.example.fastfixes.data;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fastfixes.models.Profesional;

import java.util.List;

@Dao
public interface ProfesionalDao {
    @Insert
    void insertar(Profesional profesional);

    @Update
    void actualizar(Profesional profesional);

    @Query("SELECT * FROM profesionales")
    List<Profesional> obtenerTodos();

    @Query("SELECT * FROM profesionales WHERE id = :id")
    Profesional obtenerPorId(int id);
}
