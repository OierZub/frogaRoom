package com.example.frogaRoom.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.frogaRoom.data.entities_aux.ProduktuaInfo;
import com.example.frogaRoom.data.entities_relations.ErosketaZerrendaProduktuakCrossRef;

import java.util.List;

@Dao
public interface ErosketaZerrendaProduktuakDao {
    @Insert
    void insertShoppingListProduct(ErosketaZerrendaProduktuakCrossRef shoppingListProduct);

    // Zerrenda bateko produktu guztiak eskuratzeko.
    @Query("SELECT * FROM erosketa_zerrenda_produktuak WHERE zerrendaId = :listId")
    List<ErosketaZerrendaProduktuakCrossRef> getProductsInList(long listId);

    // Zerrenda baten produktu bat eskuratzeko. Kopurua edukitzeko.
    @Query("SELECT * FROM erosketa_zerrenda_produktuak WHERE zerrendaId = :listId and produktuId = :prodId")
    ErosketaZerrendaProduktuakCrossRef getProductByIdFromZerrenda(long listId, long prodId);

    // Zerrendako produktu guztien izenak eskuratzeko. Hau 2.5.0 room bertsioa behar da.

    @Query("SELECT produktuak.product_id, produktuak.izena, produktuak.prezioa, erosketa_zerrenda_produktuak.kopurua FROM produktuak " +
            "INNER JOIN erosketa_zerrenda_produktuak ON produktuak.product_id = erosketa_zerrenda_produktuak.produktuId " +
            "WHERE erosketa_zerrenda_produktuak.zerrendaId = :listId")
    List<ProduktuaInfo> getZerrendakoProduktuInfoList(long listId);



    // Eguneratzeko kopurua
    @Query("UPDATE erosketa_zerrenda_produktuak SET kopurua= :kop WHERE zerrendaId = :listId and produktuId = :prodId")
    void updateProductKop(long listId, long prodId, int kop);
}
