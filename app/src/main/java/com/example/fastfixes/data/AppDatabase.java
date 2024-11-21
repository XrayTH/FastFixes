package com.example.fastfixes.data;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.example.fastfixes.models.Cliente;
import com.example.fastfixes.models.Profesional;
import com.example.fastfixes.models.Publicacion;

@Database(entities = {Cliente.class, Profesional.class, Publicacion.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ClienteDao clienteDao();
    public abstract ProfesionalDao profesionalDao();
    public abstract PublicacionDao publicacionDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class, "mi_base_de_datos"
                    ).fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}

