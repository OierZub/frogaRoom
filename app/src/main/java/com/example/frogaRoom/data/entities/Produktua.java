package com.example.frogaRoom.data.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity (tableName = "produktuak", indices = {@Index(value = "izena", unique = true)})
public class Produktua {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "product_id")
    public long produktuId;

    @NonNull
    public String izena;
    public double prezioa;


    public Produktua(String psIzena, double prezioa){
        this.izena   = psIzena;
        this.prezioa = prezioa;
    }

    public Produktua() {
    }

    public long getProduktuId() {
        return produktuId;
    }

    public void setProduktuId(long produktuId) {
        this.produktuId = produktuId;
    }

    @NonNull
    public String getIzena() {
        return izena;
    }

    public void setIzena(@NonNull String izena) {
        this.izena = izena;
    }

    public double getPrezioa() {
        return prezioa;
    }

    public void setPrezioa(double prezioa) {
        this.prezioa = prezioa;
    }
}
