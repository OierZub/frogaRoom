package com.example.frogaRoom.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.frogaRoom.data.entities_relations.ErosketaZerrendaProduktuakCrossRef;

import java.util.List;

@Dao
public interface ErosketaZerrendaProduktuakDao {
    @Insert
    void insertShoppingListProduct(ErosketaZerrendaProduktuakCrossRef shoppingListProduct);

    // Zerrenda bateko produktu guztiak eskuratzeko.
    @Query("SELECT * FROM erosketa_zerrenda_produktuak WHERE zerrendaId = :listId")
    List<ErosketaZerrendaProduktuakCrossRef> getProductsInList(int listId);

    // Zerrenda baten produktu bat eskuratzeko. Kopurua edukitzeko.
    @Query("SELECT * FROM erosketa_zerrenda_produktuak WHERE zerrendaId = :listId and produktuId = :prodId")
    ErosketaZerrendaProduktuakCrossRef getProductByIdFromZerrenda(int listId, int prodId);

    // Zerrendako produktu guztien izenak eskuratzeko. Hau 2.5.0 room bertsioa behar da.
    /*
    @Query("SELECT produktuak FROM products " +
            "INNER JOIN shopping_list_products ON products.productId = shopping_list_products.productId " +
            "WHERE shopping_list_products.listId = :listId")
    List<String> getProductsInShoppingList(int listId);
    */


    // Eguneratzeko kopurua
    @Query("UPDATE erosketa_zerrenda_produktuak SET kopurua= :kop WHERE zerrendaId = :listId and produktuId = :prodId")
    void updateProductKop(int listId, int prodId, int kop);
}
