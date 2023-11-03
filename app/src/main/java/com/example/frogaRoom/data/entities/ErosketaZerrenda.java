package com.example.frogaRoom.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.*;

import java.util.Date;
// , onDelete = CASCADE  errorea ematen du
@Entity(tableName = "erosketa_zerrenda", foreignKeys = @ForeignKey(entity = Erabiltzailea.class, parentColumns = "user_id", childColumns = "user_id", onDelete = ForeignKey.CASCADE))
public class ErosketaZerrenda {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "zerrenda_id")
    public int zerrendaId;
    public String zerrendaIzena;
    public Date sortzeData;
    @ColumnInfo(name = "user_id")
    public int userId; // Erabiltzaile jabearen id-a.

    public ErosketaZerrenda(String zerrendaIzena, Date sortzeData, int userId) {

        this.zerrendaIzena = zerrendaIzena;
        this.sortzeData = sortzeData;
        this.userId = userId;
    }

    public ErosketaZerrenda() {
    }

    public int getZerrendaId() {
        return zerrendaId;
    }

    public void setZerrendaId(int zerrendaId) {
        this.zerrendaId = zerrendaId;
    }

    public String getZerrendaIzena() {
        return zerrendaIzena;
    }

    public void setZerrendaIzena(String zerrendaIzena) {
        this.zerrendaIzena = zerrendaIzena;
    }

    public Date getSortzeData() {
        return sortzeData;
    }

    public void setSortzeData(Date sortzeData) {
        this.sortzeData = sortzeData;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    // Getters y setters

}
