package com.example.frogaRoom.data.entities_aux;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;


public class ProduktuaInfo {


    public long product_id;
    public String izena;
    public double prezioa;
    public int kopurua;

    public ProduktuaInfo( long product_id,String izena,double prezioa, int kopurua) {
        this.product_id = product_id;
        this.izena = izena;
        this.prezioa = prezioa;
        this.kopurua = kopurua;
    }

    public ProduktuaInfo() {
    }

    public long getProduktuId() {
        return product_id;
    }

    public void setProduktuId(long product_id) {
        this.product_id = product_id;
    }

    public String getIzena() {
        return izena;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public double getPrezioa() {
        return prezioa;
    }

    public void setPrezioa(double prezioa) {
        this.prezioa = prezioa;
    }

    public int getKopurua() {
        return kopurua;
    }

    public void setKopurua(int kopurua) {
        this.kopurua = kopurua;
    }
}
