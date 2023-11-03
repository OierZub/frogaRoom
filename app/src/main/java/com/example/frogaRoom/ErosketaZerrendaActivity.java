package com.example.frogaRoom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.frogaRoom.data.ErosketaZerrendaDatabase;
import com.example.frogaRoom.data.entities.Erabiltzailea;
import com.example.frogaRoom.data.entities.Produktua;
import com.example.frogaRoom.data.entities_relations.ErosketaZerrendaProduktuakCrossRef;

import java.util.ArrayList;
import java.util.List;


public class ErosketaZerrendaActivity extends AppCompatActivity {


    private ErosketaZerrendaDatabase datuBasea; // Declarar tu instancia de la base de datos
    //--
    final String BILDUMA_IZENA = "zerrenda";
    String produktuIzena;
    int kopurua;
    int userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erosketa_zerrenda);

        // Irakurri erabiltzaile ID-a
        Intent intent = getIntent();
        if (intent != null) {
            userId = intent.getIntExtra("userId", 0);
            Toast.makeText(ErosketaZerrendaActivity.this, "UserID: " + userId,
                    Toast.LENGTH_SHORT).show();
        }


        // Datu basea kargatu.
        datuBasea = ErosketaZerrendaDatabase.getDatabase(this);

        // Botoien entzuleak sortu.
        Button bSartu = findViewById(R.id.bSartu);
        bSartu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Prouktuak gorde
                sartuProduktua();
            }
        });

        Button bEzabatuUser = findViewById(R.id.bEzabatuUser);
        bEzabatuUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Erabiultzailea ezabatu.
                ezabatuErabiltzailea();
            }
        });

        Button bEzabatu = findViewById(R.id.bEzabatu);
        bEzabatu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Produktuak ezabatu
                ezabatuProduktua();
            }
        });


        Button bIrakurri = findViewById(R.id.bIrakurri);
        bIrakurri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Produktuak irakurri
                irakurriProduktuak();
            }
        });
    }


    public void sartuProduktua() {

        // Irakurri sartutako datuak.
        getDatuak();
        // Sortu objektua.
        Produktua oProd;
        oProd = datuBasea.produktuaDao().getProductByName(produktuIzena);
        if (oProd == null){ // Produktu berria bada, sartu datu basean.
            oProd = new Produktua(produktuIzena, 1.00);
            datuBasea.produktuaDao().insertProduct(oProd);
            oProd = datuBasea.produktuaDao().getProductByName(produktuIzena);
        }
        int prodId = oProd.getProduktuId();
        // Gorde erosketa zerrendan. KONTUZ!!! Momentuz erabiltzaile bakoitza zerrenda bakarra, gero uderId beharrean zerrendaId erabili.
        ErosketaZerrendaProduktuakCrossRef oProdErosketa  = datuBasea.erosketaZerrendaProduktuakDao().getProductByIdFromZerrenda(userId, prodId);
        if (oProdErosketa != null) {
            //Toast.makeText(ErosketaZerrendaActivity.this, "Ezin da produktua sartu, zerrendan dago jada. ", Toast.LENGTH_SHORT).show();
            datuBasea.erosketaZerrendaProduktuakDao().updateProductKop(userId, prodId, kopurua);

        } else {
            int zerrendaId = userId;
            int produktuId = prodId;
            int kop = kopurua;
            ErosketaZerrendaProduktuakCrossRef shoppingListProduct1 = new ErosketaZerrendaProduktuakCrossRef(zerrendaId, produktuId, kop);
            datuBasea.erosketaZerrendaProduktuakDao().insertShoppingListProduct(shoppingListProduct1);
            Toast.makeText(ErosketaZerrendaActivity.this, "Gehitu da erosketa zerrendan. ",
                    Toast.LENGTH_SHORT).show();
            // Irakurri datuak eguneratzeko pantaila.

        }
        // Eguneratu Erosketa zerrenda.
        irakurriProduktuak();


    }

    private void ezabatuErabiltzailea() {

        Erabiltzailea oUser = datuBasea.erabiltzaileDao().getUserById(userId);
        datuBasea.erabiltzaileDao().deleteUser(oUser);
        Toast.makeText(ErosketaZerrendaActivity.this, "Ezabatu da erabiltzailea: " + oUser.getIzena(),
                Toast.LENGTH_SHORT).show();
        finish();
    }

    private void ezabatuProduktua() {
        // Irakurri sartutako datuak.
        getDatuak();
        // Sortu objektua.
        Produktua oProd;
        oProd = datuBasea.produktuaDao().getProductByName(produktuIzena);
        if (oProd != null) {
            // Ezabatu produktua.
            datuBasea.produktuaDao().deleteProduct(oProd);
            // Eguneratu Erosketa zerrenda.
            irakurriProduktuak();
        } else {
            Toast.makeText(ErosketaZerrendaActivity.this, "Produktu hau ez da existitzen: " + produktuIzena,
                    Toast.LENGTH_SHORT).show();
        }

    }

    private void irakurriProduktuak() {

        int zerrendaId = userId;
        List<ErosketaZerrendaProduktuakCrossRef> prodEroskList =  datuBasea.erosketaZerrendaProduktuakDao().getProductsInList(zerrendaId);

        List<Integer> prodIdList = new ArrayList<>();
        for (int i = 0; i < prodEroskList.size();i++) {
            int prodId = prodEroskList.get(i).getProduktuId();
            // Realiza las operaciones necesarias con los nombres de productos
            prodIdList.add(prodId);
        }
        List<Produktua> produkList = datuBasea.produktuaDao().getProductsByIds(prodIdList);

        String erosketaZerrenda = "";
        for (int i = 0; i < produkList.size();i++) {
            String prokuktuInfo = "";
            prokuktuInfo = produkList.get(i).getIzena() + " Kop: " + prodEroskList.get(i).getKopurua()
                    + " Prezioa: " + produkList.get(i).getPrezioa() + " €";
            erosketaZerrenda += prokuktuInfo + "\n";
        }

        TextView tZerrenda = findViewById(R.id.tZerrenda);
        // KONTUZ!!! Hau asinkronoa da.
        tZerrenda.setText(erosketaZerrenda);

    }


    private void getDatuak() {

        EditText etProduktua = findViewById(R.id.editTextProduktua);
        EditText etKopurua = findViewById(R.id.editTextKopurua);

        // Obtener el texto del EditText
        produktuIzena = etProduktua.getText().toString();
        // Kopurua hutsik badago, 1 ipini.
        String sKopurua = etKopurua.getText().toString();
        if (sKopurua.equals("")) {
            sKopurua = "1";
        }
        // int bihurtu.
        kopurua = Integer.parseInt(sKopurua);
    }


}