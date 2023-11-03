package com.example.frogaRoom.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.frogaRoom.data.dao.ErabiltzaileDao;
import com.example.frogaRoom.data.dao.ErosketaZerrendaDao;
import com.example.frogaRoom.data.dao.ErosketaZerrendaProduktuakDao;
import com.example.frogaRoom.data.dao.ProduktuaDao;
import com.example.frogaRoom.data.entities.Erabiltzailea;
import com.example.frogaRoom.data.entities.ErosketaZerrenda;
import com.example.frogaRoom.data.entities.Produktua;
import com.example.frogaRoom.data.entities_relations.ErosketaZerrendaProduktuakCrossRef;

@Database(entities = {Erabiltzailea.class, ErosketaZerrenda.class, Produktua.class, ErosketaZerrendaProduktuakCrossRef.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class) // Si necesitas convertidores personalizados para tipos de datos
public abstract class ErosketaZerrendaDatabase extends RoomDatabase {

    // Define los DAOs para cada entidad
    public abstract ErabiltzaileDao erabiltzaileDao();
    public abstract ErosketaZerrendaDao erosketaZerrendaDao();
    public abstract ProduktuaDao produktuaDao();
    public abstract ErosketaZerrendaProduktuakDao erosketaZerrendaProduktuakDao();

    // Puedes crear una instancia única de la base de datos (Patrón Singleton)
    private static volatile ErosketaZerrendaDatabase INSTANCE;

    public static ErosketaZerrendaDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ErosketaZerrendaDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ErosketaZerrendaDatabase.class, "erosketazerrenda_database").allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
