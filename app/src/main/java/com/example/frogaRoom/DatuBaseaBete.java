package com.example.frogaRoom;

import com.example.frogaRoom.data.ErosketaZerrendaDatabase;
import com.example.frogaRoom.data.entities.Erabiltzailea;
import com.example.frogaRoom.data.entities.ErosketaZerrenda;
import com.example.frogaRoom.data.entities.Produktua;
import com.example.frogaRoom.data.entities_relations.ErosketaZerrendaProduktuakCrossRef;

import java.util.Date;

public class DatuBaseaBete {






    public static void insertTestData(ErosketaZerrendaDatabase datuBasea) {
        // Aquí puedes realizar inserciones de datos de prueba utilizando los DAOs

        // Por ejemplo, para insertar un usuario de prueba:
        Erabiltzailea user = new Erabiltzailea("oier", "oier@gmail.com", "1234");
        datuBasea.erabiltzaileDao().insertUser(user);

        // Inserta listas de compras de prueba
        //ErosketaZerrenda shoppingList1 = new ErosketaZerrenda("Zerrenda 1", new Date(), user.getUserId());
        ErosketaZerrenda shoppingList1 = new ErosketaZerrenda("Zerrenda 2", new Date(), 1);
        datuBasea.erosketaZerrendaDao().insertList(shoppingList1);

        // Inserta productos de prueba
        Produktua product1 = new Produktua("Esnea", 1.85);

        datuBasea.produktuaDao().insertProduct(product1);

        // Asocia productos a una lista de compras
//        int zerrendaId = shoppingList1.getZerrendaId();
//        int produktuId = product1.getProduktuId();
        int zerrendaId = 2;
        int produktuId = 1;
        int kopurua = 3;
        ErosketaZerrendaProduktuakCrossRef shoppingListProduct1 = new ErosketaZerrendaProduktuakCrossRef(zerrendaId, produktuId, kopurua);

        datuBasea.erosketaZerrendaProduktuakDao().insertShoppingListProduct(shoppingListProduct1);

        // Puedes continuar insertando más datos de prueba según tus necesidades
    }
}
