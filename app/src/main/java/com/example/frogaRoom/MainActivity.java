package com.example.frogaRoom;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.frogaRoom.data.ErosketaZerrendaDatabase;
import com.example.frogaRoom.data.entities.Erabiltzailea;
import com.example.frogaRoom.data.entities.ErosketaZerrenda;

import java.util.Date;


public class MainActivity extends AppCompatActivity {



    private ErosketaZerrendaDatabase datuBasea; // Declarar tu instancia de la base de datos

    final private static String TAG = "DatuBasea";
    private SharedPreferences sharedPreferences;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Datu basea kargatu.
        datuBasea = ErosketaZerrendaDatabase.getDatabase(this);
        // Bete datuekin datu basea.
        //DatuBaseaBete.insertTestData(datuBasea);

        // Botoien entzuleak sortu.
        Button bErregistratu = findViewById(R.id.bErregistratu);

        bErregistratu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // erregistratu erabiltzaile berria
                erregistratu();
            }
        });

        Button bLogeatu = findViewById(R.id.bLogeatu);
        bLogeatu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // logeatu erabiltzailea
                logeatu();
            }
        });

        // SharedPreferences abiarazi.
        sharedPreferences = getSharedPreferences("LoginDatuak", Context.MODE_PRIVATE);

    }

    @Override
    public void onStart() {
        super.onStart();

        irakurriLoginDatuak();

    }



    private void erregistratu() {
        EditText etEmail = findViewById(R.id.editTextEmail);
        EditText etPass = findViewById(R.id.editTextPasahitza);

        // Obtener el texto del EditText
        String email = etEmail.getText().toString();
        String password = etPass.getText().toString();

        Erabiltzailea oUser = datuBasea.erabiltzaileDao().getUserByEmail(email);

        if (oUser == null ) {
            Log.d(TAG, "Erregistroa:success");
            // Sortu erabiltzailea
            String izena = email.split("@")[0];
            oUser = new Erabiltzailea(izena, email, password);

            long userId = datuBasea.erabiltzaileDao().insertUser(oUser);
            Log.d(TAG, "ZZZZZZZZZZZZZZZZZ   userId: " + userId);
            oUser.setUserId(userId);
            //oUser = datuBasea.erabiltzaileDao().getUserByEmail(email); // Irakurri id-a eskuratzeko.
            // Sortu erosketa zerrenda.
            ErosketaZerrenda shoppingList1 = new ErosketaZerrenda("Zerrenda " + izena, new Date(), oUser.getUserId());
            datuBasea.erosketaZerrendaDao().insertList(shoppingList1);
            // Mezua aurkeztu.
            Toast.makeText(MainActivity.this, "Erregistratu da: " + oUser.getIzena(),
                    Toast.LENGTH_SHORT).show();
            datuakGorde();
            // Deitu ErosketaZerrenda activity-ra.
            Intent intent = new Intent(MainActivity.this, ErosketaZerrendaActivity.class);
            intent.putExtra("userId", oUser.getUserId());
            startActivity(intent);
        } else {
            Log.d(TAG, "Erregistroa:Failed");
            Toast.makeText(MainActivity.this, "Email hori erregistratuta dago jada.",
                    Toast.LENGTH_SHORT).show();
        }
    }


    private void logeatu() {
        EditText etEmail = findViewById(R.id.editTextEmail);
        EditText etPass = findViewById(R.id.editTextPasahitza);

        // Obtener el texto del EditText
        String email = etEmail.getText().toString();
        String password = etPass.getText().toString();

        Erabiltzailea oUser = datuBasea.erabiltzaileDao().getUserByEmail(email);

        if (oUser != null && oUser.getPassword().equals(password)) {
            Log.d(TAG, "Login:success");
            Toast.makeText(MainActivity.this, "Logeatu da: " + oUser.getIzena(),
                    Toast.LENGTH_SHORT).show();
            datuakGorde();
            // Deitu ErosketaZerrenda activity-ra.
            Intent intent = new Intent(MainActivity.this, ErosketaZerrendaActivity.class);
            intent.putExtra("userId", oUser.getUserId());
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, "Erabiltzailea eta/edo pasahitza ez da zuzena",
                    Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Login:error");
        }




    }


    private void irakurriLoginDatuak() {

        String email = sharedPreferences.getString("Email", "");
        String password = sharedPreferences.getString("Pasahitza", "");

        EditText etEmail = findViewById(R.id.editTextEmail);
        EditText etPass = findViewById(R.id.editTextPasahitza);

        etEmail.setText(email);
        etPass.setText(password);

    }


    private void datuakGorde() {
        EditText etEmail = findViewById(R.id.editTextEmail);
        EditText etPass = findViewById(R.id.editTextPasahitza);

        // Obtener el texto del EditText
        String email = etEmail.getText().toString();
        String password = etPass.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Email", email);
        editor.putString("Pasahitza", password);
        editor.apply();
    }

}