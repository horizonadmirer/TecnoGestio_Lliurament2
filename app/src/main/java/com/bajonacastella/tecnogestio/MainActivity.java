package com.bajonacastella.tecnogestio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Classe Main en la qual mostrarem 6 botons per a les 6 categories amb un resultat bàsic.
 * M07-UF1 Lliurament 4
 * Marc Bajona i Ester Castellà
 */

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    String[] argsCategoria;
    ImageButton btnCat1,btnCat2,btnCat3,btnCat4,btnCat5,btnCat6;
    TextView txtDescripcioProducte1, txtNomProducte1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        argsCategoria = new String[1];
        txtNomProducte1 = findViewById(R.id.txtNomProducte1);
        txtDescripcioProducte1 = findViewById(R.id.txtDescripcioProducte1);
        btnCat1 = findViewById(R.id.categoria1);
        btnCat2 = findViewById(R.id.categoria2);
        btnCat3 = findViewById(R.id.categoria3);
        btnCat4 = findViewById(R.id.categoria4);
        btnCat5 = findViewById(R.id.categoria5);
        btnCat6 = findViewById(R.id.categoria6);

        // Obrim la nostra base de dades creada amb DB Browser i penjada al nostre mòbil en format només lectura.
        db = SQLiteDatabase.openDatabase(
                "/data/data/com.bajonacastella.tecnogestio/databases/db_tecnogestio.db",
                null,
                SQLiteDatabase.OPEN_READONLY
        );


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_buscar:
                Log.i("ActionBar", "Buscar!");
                startActivity(new Intent(this, CercaAvancada.class));

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Mètode associat a tots els botons de les categories 1-6.
     * Executarà una query simple a la base de dades i mostrarà
     * el primer resultat en la part inferior de la pantalla del mòbil.
     * @param v
     */
    public void botonsCategories (View v) {
        if(v == btnCat1) {
            argsCategoria[0] = "1";
            Cursor c = db.query("productes", null, "categoria=?", argsCategoria, null, null, null);
            c.moveToNext();
            Log.e("BASE DE DADES", c.getString(1));

            txtNomProducte1.setText(c.getString(1));
            txtDescripcioProducte1.setText(c.getString(3));
        }
        if(v == btnCat2) {
            argsCategoria[0] = "2";
            Cursor c = db.query("productes", null, "categoria=?", argsCategoria, null, null, null);
            c.moveToNext();
            Log.e("BASE DE DADES", c.getString(1));

            txtNomProducte1.setText(c.getString(1));
            txtDescripcioProducte1.setText(c.getString(3));
        }
        if(v == btnCat3) {
            argsCategoria[0] = "3";
            Cursor c = db.query("productes", null, "categoria=?", argsCategoria, null, null, null);
            c.moveToNext();
            Log.e("BASE DE DADES", c.getString(1));

            txtNomProducte1.setText(c.getString(1));
            txtDescripcioProducte1.setText(c.getString(3));
        }
        if(v == btnCat4) {
            argsCategoria[0] = "4";
            Cursor c = db.query("productes", null, "categoria=?", argsCategoria, null, null, null);
            c.moveToNext();
            Log.e("BASE DE DADES", c.getString(1));

            txtNomProducte1.setText(c.getString(1));
            txtDescripcioProducte1.setText(c.getString(3));
        }
        if(v == btnCat5) {
            argsCategoria[0] = "5";
            Cursor c = db.query("productes", null, "categoria=?", argsCategoria, null, null, null);
            c.moveToNext();
            Log.e("BASE DE DADES", c.getString(1));

            txtNomProducte1.setText(c.getString(1));
            txtDescripcioProducte1.setText(c.getString(3));
        }
        if(v == btnCat6) {
            argsCategoria[0] = "6";
            Cursor c = db.query("productes", null, "categoria=?", argsCategoria, null, null, null);
            c.moveToNext();
            Log.e("BASE DE DADES", c.getString(1));

            txtNomProducte1.setText(c.getString(1));
            txtDescripcioProducte1.setText(c.getString(3));
        }

    }

}
