package com.example.fastfixes.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fastfixes.models.Cliente;

import java.util.List;

@Dao
public interface ClienteDao {
    @Insert
    void insertar(Cliente cliente);

    @Update
    void actualizar(Cliente cliente);

    @Query("SELECT * FROM clientes")
    List<Cliente> obtenerTodos();

    @Query("SELECT * FROM clientes WHERE id = :id")
    Cliente obtenerPorId(int id);
}

