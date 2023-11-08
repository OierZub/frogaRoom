package com.example.frogaRoom.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.frogaRoom.data.entities.Erabiltzailea;
import com.example.frogaRoom.data.entities.Produktua;

@Dao
public interface ErabiltzaileDao {


        @Insert
        long insertUser(Erabiltzailea user);

        @Query("SELECT * FROM Erabiltzaileak WHERE user_id = :userId")
        Erabiltzailea getUserById(long userId);

        @Query("SELECT * FROM Erabiltzaileak WHERE email = :email")
        Erabiltzailea getUserByEmail(String email);

        @Delete
        void deleteUser(Erabiltzailea user);

}
