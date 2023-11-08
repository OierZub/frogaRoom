package com.example.frogaRoom.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.frogaRoom.data.entities.ErosketaZerrenda;

@Dao
public interface ErosketaZerrendaDao {


        @Insert
        void insertList(ErosketaZerrenda erosketaZerrenda);

        @Query("SELECT * FROM erosketa_zerrenda WHERE zerrenda_id = :listId")
        ErosketaZerrenda getListById(long listId);

        // Otros métodos para consultar o modificar listas de compras


}
