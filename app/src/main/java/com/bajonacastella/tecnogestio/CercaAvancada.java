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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerca_avancada);
        SeekBar barraPreu = findViewById(R.id.barra_preu);
        final TextView barraPreuValor = findViewById(R.id.txValorBarra);

        barraPreu.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                barraPreuValor.setText(String.valueOf(progress) + " €");
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

        Spinner spinnerMarca =findViewById(R.id.spinnerMarca);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.marques_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerMarca.setAdapter(adapter);

        NumberPicker picker = findViewById(R.id.categoriaPicker);
        picker.setMinValue(0);
        picker.setMaxValue(5);
        picker.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        picker.setDisplayedValues( new String[] { "Categoria 1", "Categoria 2", "Categoria 3", "Categoria 4", "Categoria 5", "Categoria 6" } );

        btnCercar = findViewById(R.id.btn_cercar);
        btnCercar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Resultats.class));
            }
        });
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
