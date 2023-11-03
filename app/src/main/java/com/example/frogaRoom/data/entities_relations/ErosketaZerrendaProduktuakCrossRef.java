package com.example.frogaRoom.data.entities_relations;

import androidx.room.Entity;
import androidx.room.ForeignKey;

import com.example.frogaRoom.data.entities.ErosketaZerrenda;

@Entity(tableName = "erosketa_zerrenda_produktuak", primaryKeys = {"zerrendaId", "produktuId"},
        foreignKeys = @ForeignKey(
        entity = ErosketaZerrenda.class,
        parentColumns = "zerrenda_id", // Hau ErosketaZerrenda-ko izena da.
        childColumns = "zerrendaId",    // Hau klase honen izena.
        onDelete = ForeignKey.CASCADE)
)
public class ErosketaZerrendaProduktuakCrossRef {
    private int zerrendaId;      // ID de la lista de compras
    private int produktuId;   // ID del producto en la lista de compras
    private int kopurua;    // Cantidad del producto en la lista de compras


    public ErosketaZerrendaProduktuakCrossRef(int zerrendaId, int produktuId, int kopurua) {
        this.zerrendaId = zerrendaId;
        this.produktuId = produktuId;
        this.kopurua = kopurua;
    }

    public int getZerrendaId() {
        return zerrendaId;
    }

    public void setZerrendaId(int zerrendaId) {
        this.zerrendaId = zerrendaId;
    }

    public int getProduktuId() {
        return produktuId;
    }

    public void setProduktuId(int produktuId) {
        this.produktuId = produktuId;
    }

    public int getKopurua() {
        return kopurua;
    }

    public void setKopurua(int kopurua) {
        this.kopurua = kopurua;
    }
}
