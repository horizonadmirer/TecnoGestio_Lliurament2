package com.bajonacastella.tecnogestio;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Classe ListViewPersonalitzada en la qual es fa un extends d'ArrayAdapter
 * per poder personalitzar un ListView tal i com volem.
 * M07-UF1 Lliurament 5
 * Marc Bajona i Ester Castellà
 */

public class ListViewPersonalitzada extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] maintitle;
    private final String[] description;
    private final String[] brand;
    private final String[] stock;
    private final String[] price;
    private final Integer[] imgid;

    /**
     * Constructor de la ListViewPersonalitzada.
     * @param context
     * @param nomProducte
     * @param descripcioProducte
     * @param marcaProducte
     * @param stockProducte
     * @param preuProducte
     * @param idImatge
     */
    public ListViewPersonalitzada(Activity context, String[] nomProducte, String[] descripcioProducte, String[] marcaProducte,  String[] stockProducte,  String[] preuProducte, Integer[] idImatge) {
        super(context, R.layout.custom_listview, nomProducte);

        this.context=context;
        this.maintitle=nomProducte;
        this.description =descripcioProducte;
        this.brand=marcaProducte;
        this.stock=stockProducte;
        this.price=preuProducte;
        this.imgid=idImatge;

    }

    /**
     * Mètode per crear cada fila (producte) de la manera que volem.
     * @param position
     * @param view
     * @param parent
     * @return La fila corresponent.
     */
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.custom_listview, null,true);

        ImageView imageView = rowView.findViewById(R.id.imgProducte);
        TextView nomP = rowView.findViewById(R.id.txtNomProducte);
        TextView descP = rowView.findViewById(R.id.txtDescripcioProducte);
        TextView marcaUnitatsP = rowView.findViewById(R.id.txtMarcaUnitats);
        TextView preuP = rowView.findViewById(R.id.txtPreu);

        imageView.setImageResource(imgid[position]);
        nomP.setText(maintitle[position]);
        descP.setText(description[position]);
        marcaUnitatsP.setText(brand[position] + " | " + stock[position] + " ud. restants");
        preuP.setText(price[position]);


        return rowView;

    }
}
