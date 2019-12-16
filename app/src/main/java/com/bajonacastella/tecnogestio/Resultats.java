package com.bajonacastella.tecnogestio;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Classe Resultats en la qual mostrarem els resultats en un ListView segons la cerca avançada.
 * M07-UF1 Lliurament 4
 * Marc Bajona i Ester Castellà
 */

public class Resultats extends AppCompatActivity {

    //WebView wv;
    SQLiteDatabase db;
    TextView tx;
    ArrayAdapter adaptador;
    ListView llista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultats);
        //wv = findViewById(R.id.webViewDades);
        //wv.loadUrl("file:///android_asset/resultats.html");

        llista = findViewById(R.id.llistaResultats);

        // Agafem els valors corresponents als ítems que hi han en la classe Cerca Avançada.
        int valorSeekbar = getIntent().getIntExtra("barraPreu",100000); // Seekbar funcionant (rang de preu)
        int valorNumberPicker = getIntent().getIntExtra("categoria",0); // NumberPicker funcionant (categories)
        String valorSpinner = getIntent().getStringExtra("marca"); // Spinner funcionant (marques)


        // Obrim la nostra base de dades creada amb DB Browser i penjada al nostre mòbil en format només lectura.
        db = SQLiteDatabase.openDatabase(
                "/data/data/com.bajonacastella.tecnogestio/databases/db_tecnogestio.db",
                null,
                SQLiteDatabase.OPEN_READONLY
        );


        //Construïm mica a mica la query avançada, part per part.
        //Tenim en compte si l'usuari no sel·leciona una marca, categoria o rang de preu específic,
        //per tal així de mostrar tots els productes de la base de dades.

        String marcaQuery = "";
        if(valorSpinner.equals("Totes les marques")) {
            marcaQuery = "marca LIKE '%'";
        } else {
            marcaQuery = "marca = '"+valorSpinner+"'";
        }

        String categoriaQuery = "";
        if(valorNumberPicker == 0) {
            categoriaQuery = "categoria IN(1,2,3,4,5,6)";
        } else {
            categoriaQuery = "categoria = " + valorNumberPicker;
        }

        String preuQuery = "";
        if(valorSeekbar == 0) {
            preuQuery = "preuUnitari BETWEEN 0 AND 1000000";
        } else {
            preuQuery = "preuUnitari BETWEEN 0 AND "+valorSeekbar+"";
        }

        String whereQuery = categoriaQuery + " AND " +  marcaQuery + " AND " + preuQuery;
        String orderByQuery = "preuUnitari ASC";


        // Finalment fem la query i si un resultat o més, construïm la llista que mostrarà el ArrayAdapter.
        Cursor c = db.query("productes", null, whereQuery, null, null, null, orderByQuery);
        int numResultats = c.getCount();
        if(numResultats != 0) {
            Log.e("REGISTRES: ", String.valueOf(c.getCount()));
            //String[] values = new String[numResultats];
            String[] nomProducte = new String[numResultats];
            String[] descripcioProducte =  new String[numResultats];
            String[] marcaProducte =  new String[numResultats];
            String[] stockProducte =  new String[numResultats];
            String[] preuProducte =  new String[numResultats];
            Integer[] idImatge = new Integer[numResultats];
            int i = 0;

            //Movem el cursor a la primera posició.
            if (c.moveToFirst()) {
                do {
                    /*// Construïm l'String amb tota l'informació corresponent al producte i fem un format més comprensible per l'usuari.
                    values[i] = "\n" + c.getString(1) + "\n" + c.getString(3) + "\nPreu unitat: " + c.getString(5) + " €\n"
                                + "Marca: "+  c.getString(2)  +"\t\tStock restant: " + c.getString(4) + "\n"; */

                    nomProducte[i] = c.getString(1);
                    descripcioProducte[i] = c.getString(3);
                    marcaProducte[i] = c.getString(2);
                    stockProducte[i] = c.getString(4);
                    preuProducte[i] = c.getString(5) + " €";
                    idImatge[i] = getResources().getIdentifier(c.getString(7),"drawable",getPackageName());
                    //Log.e("IMATGE NOM", "" + idImatge[i]);

                    i++;
                } while (c.moveToNext());

                ListViewPersonalitzada adaptador = new ListViewPersonalitzada(this,nomProducte,descripcioProducte,marcaProducte,stockProducte,preuProducte,idImatge);
                //adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);

                llista.setAdapter(adaptador);
            }
        } else {
            Log.e("BASE DE DADES", "No hi ha resultats");
            String[] valorNul = {"No hi ha resultats"};
            adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, valorNul);

            llista.setAdapter(adaptador);
        }
        c.close();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cerca_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_tornar:
                Log.i("ActionBar", "Buscar!");
                finish();

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
