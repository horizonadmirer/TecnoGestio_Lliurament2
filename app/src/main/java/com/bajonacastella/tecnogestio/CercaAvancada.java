package com.bajonacastella.tecnogestio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class CercaAvancada extends AppCompatActivity {

    Button btnCercar;
    SeekBar barraPreu;
    NumberPicker picker;
    Spinner spinnerMarca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerca_avancada);
        barraPreu = findViewById(R.id.barra_preu);

        final TextView barraPreuValor = findViewById(R.id.txValorBarra);

        barraPreu.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                barraPreuValor.setText(String.valueOf(progress) + " €");
                if(progress == 0) {
                    barraPreuValor.setText("TOTS");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });

        spinnerMarca =findViewById(R.id.spinnerMarca);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.marques_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerMarca.setAdapter(adapter);

        picker = findViewById(R.id.categoriaPicker);
        picker.setMinValue(0);
        picker.setMaxValue(6);
        picker.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        picker.setDisplayedValues( new String[] { "Totes les categories","PCs", "Components", "Telefonia", "Cables", "Consoles", "Impresores" } );

        btnCercar = findViewById(R.id.btn_cercar);

    }

    public void cercaAvancada (View v) {

        Intent i = new Intent(getApplicationContext(),Resultats.class);

        i.putExtra("barraPreu",barraPreu.getProgress());
        i.putExtra("categoria",picker.getValue());
        i.putExtra("marca",spinnerMarca.getSelectedItem().toString());
        startActivity(i);

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
