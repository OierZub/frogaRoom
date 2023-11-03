package com.example.frogaRoom.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.frogaRoom.data.entities.ErosketaZerrenda;
import com.example.frogaRoom.data.entities.Produktua;

import java.util.List;

@Dao
public interface ProduktuaDao {


        @Insert
        void insertProduct(Produktua product);

        @Query("SELECT * FROM produktuak WHERE product_id = :productId")
        Produktua getProductById(int productId);

        @Query("SELECT * FROM produktuak WHERE izena = :prodIzena")
        Produktua getProductByName(String prodIzena);

        @Query("SELECT * FROM produktuak WHERE product_Id IN (:productIds)")
        List<Produktua> getProductsByIds(List<Integer> productIds);

        @Delete
        void deleteProduct(Produktua product);



}
