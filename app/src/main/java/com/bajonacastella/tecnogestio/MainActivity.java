package com.bajonacastella.tecnogestio;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

/**
 * Classe Main en la qual mostrarem 6 botons per a les 6 categories amb un resultat bàsic.
 * M07-UF1 Lliurament 5
 * Marc Bajona i Ester Castellà
 */

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    SharedPreferences prefs;
    boolean benvinguda;
    String[] argsCategoria;
    ImageButton btnCat1,btnCat2,btnCat3,btnCat4,btnCat5,btnCat6;
    ArrayAdapter adaptador;
    ListView llista;
    String[] valorNul = {"No hi ha productes"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        argsCategoria = new String[1];
        llista = findViewById(R.id.llistaBasica);
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

        // Mostrem una alerta per donar la benvinguda al usuari a l'app.
        prefs = getSharedPreferences("FitxerPreferencies",MODE_PRIVATE);
        benvinguda = prefs.getBoolean("benvinguda",false);

        if(!benvinguda)
        {
            new AlertDialog.Builder(this, R.style.AppTheme)
                    .setTitle("Benvingut/da a TecnoGestió!")
                    .setCancelable(false)
                    .setMessage("Ara podràs consultar els productes per categoria bàsica o, si ho desitges, també " +
                            "podràs fer cerques avançades. \n Gràcies per escollir la nostra aplicació!")
                    .setIcon(R.mipmap.ic_launcher_round)
                    .setPositiveButton("Molt bé, gràcies!", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putBoolean("benvinguda", true);
                            editor.apply();
                        }
                    })
                    .show();
        }

    }

    // Mètodes per mostrar el menú corresponent.
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
     * tots els resultats en una ListView personalitzada
     * en la part inferior de la pantalla del mòbil.
     *
     * Per cada botó, crearà un a query en un cursor que serà passat
     * com a paràmetre al mètode crearList() i retornarà null o un adapter
     * per la nostra listView.
     * @param v
     */
    public void botonsCategories (View v) {
        if(v == btnCat1) {
            argsCategoria[0] = "1";
            Cursor c = db.query("productes", null, "categoria=?", argsCategoria, null, null, null);

            adaptador = crearList(c);
            if(adaptador != null) {
                llista.setAdapter(adaptador);
            } else {
                llista.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, valorNul));
            }
        }
        if(v == btnCat2) {
            argsCategoria[0] = "2";
            Cursor c = db.query("productes", null, "categoria=?", argsCategoria, null, null, null);

            adaptador = crearList(c);
            if(adaptador != null) {
                llista.setAdapter(adaptador);
            } else {
                llista.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, valorNul));
            }
        }
        if(v == btnCat3) {
            argsCategoria[0] = "3";
            Cursor c = db.query("productes", null, "categoria=?", argsCategoria, null, null, null);

            adaptador = crearList(c);
            if(adaptador != null) {
                llista.setAdapter(adaptador);
            } else {
                llista.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, valorNul));
            }
        }
        if(v == btnCat4) {
            argsCategoria[0] = "4";
            Cursor c = db.query("productes", null, "categoria=?", argsCategoria, null, null, null);

            adaptador = crearList(c);
            if(adaptador != null) {
                llista.setAdapter(adaptador);
            } else {
                llista.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, valorNul));
            }
        }
        if(v == btnCat5) {
            argsCategoria[0] = "5";
            Cursor c = db.query("productes", null, "categoria=?", argsCategoria, null, null, null);

            adaptador = crearList(c);
            if(adaptador != null) {
                llista.setAdapter(adaptador);
            } else {
                llista.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, valorNul));
            }
        }
        if(v == btnCat6) {
            argsCategoria[0] = "6";
            Cursor c = db.query("productes", null, "categoria=?", argsCategoria, null, null, null);

            adaptador = crearList(c);
            if(adaptador != null) {
                llista.setAdapter(adaptador);
            } else {
                llista.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, valorNul));
            }
        }
    }


    /**
     * Mètode per inicialitzar un adaptador personalitzat amb el nostre constructor de
     * listView prèviament creat.
     * @param c     Cursor que té la query referent als productes per cada categoria.
     * @return      Retorna NULL si no té resultats o retorna un ListViewPersonalitzada
     *              construït per mostrar les dades corresponents als productes sel·leccionats.
     */
    public ListViewPersonalitzada crearList (Cursor c) {
        int numResultats = c.getCount();
        ListViewPersonalitzada adaptador = null;
        if(numResultats != 0) {
            String[] nomProducte = new String[numResultats];
            String[] descripcioProducte = new String[numResultats];
            String[] marcaProducte = new String[numResultats];
            String[] stockProducte = new String[numResultats];
            String[] preuProducte = new String[numResultats];
            Integer[] idImatge = new Integer[numResultats];
            int i = 0;

            //Movem el cursor a la primera posició.
            if (c.moveToFirst()) {
                do {
                    nomProducte[i] = c.getString(1);
                    descripcioProducte[i] = c.getString(3);
                    marcaProducte[i] = c.getString(2);
                    stockProducte[i] = c.getString(4);
                    preuProducte[i] = c.getString(5) + " €";
                    idImatge[i] = getResources().getIdentifier(c.getString(7), "drawable", getPackageName());
                    i++;
                } while (c.moveToNext());

                adaptador = new ListViewPersonalitzada(this, nomProducte, descripcioProducte, marcaProducte, stockProducte, preuProducte, idImatge);
            }
        }
        c.close();
        return adaptador;
    }

}
