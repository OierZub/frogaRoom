package com.example.frogaRoom.data.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

// Queremos que el email sea único.
@Entity (tableName = "Erabiltzaileak", indices = {@Index(value = "email", unique = true)})
public class Erabiltzailea {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    public long userId;
    @NonNull
    public String izena;
    @NonNull
    public String email;
    @NonNull
    public String password;

    public Erabiltzailea (@NonNull String izena, @NonNull String email, @NonNull String pass) {
        this.izena = izena;
        this.email = email;
        this.password = pass;
    }

    public Erabiltzailea() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getIzena() {
        return izena;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }
}
