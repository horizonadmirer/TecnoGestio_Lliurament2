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

public class Resultats extends AppCompatActivity {

    WebView wv;
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
        //tx = findViewById(R.id.txProva);
        int valorSeekbar = getIntent().getIntExtra("barraPreu",100000); // Seekbar funcionant (rang de preu)
        int valorNumberPicker = getIntent().getIntExtra("categoria",0); // NumberPicker funcionant (categories)
        String valorSpinner = getIntent().getStringExtra("marca"); // Spinner funcionant (marques)

        //tx.setText("El valor és: " + valorSpinner);

        db = SQLiteDatabase.openDatabase(
                "/data/data/com.bajonacastella.tecnogestio/databases/db_tecnogestio.db",
                null,
                SQLiteDatabase.OPEN_READONLY
        );

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


        Cursor c = db.query("productes", null, whereQuery, null, null, null, orderByQuery);
        int numResultats = c.getCount();
        if(numResultats != 0) {
            Log.e("REGISTRES: ", String.valueOf(c.getCount()));
            String[] values = new String[numResultats];
            int i = 0;
            //Movem el cursor a la primera posició
            if (c.moveToFirst()) {
                do {
                    values[i] = "\n" + c.getString(1) + "\n" + c.getString(3) + "\nPreu unitat: " + c.getString(5) + " €\n"
                                + "Marca: "+  c.getString(2)  +"\t\tStock restant: " + c.getString(4) + "\n";
                    i++;
                } while (c.moveToNext());

                adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);

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
