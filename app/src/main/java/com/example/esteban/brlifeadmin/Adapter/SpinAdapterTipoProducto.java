package com.example.esteban.brlifeadmin.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.esteban.brlifeadmin.Clases.Mantenedor.MantenedorTresAtributos;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.TipoProducto;

import java.util.ArrayList;

public class SpinAdapterTipoProducto extends ArrayAdapter<TipoProducto> {
    // Your sent context
    private Context context;
    // Your custom values for the spinner (User)
    private ArrayList<TipoProducto> mantenedor = new ArrayList<>();

    public SpinAdapterTipoProducto(Context context, int textViewResourceId,
                                   ArrayList<TipoProducto> mantenedor) {
        super(context, textViewResourceId, mantenedor);
        this.context = context;
        this.mantenedor = mantenedor;
    }

    @Override
    public int getCount(){
        return mantenedor.size();
    }

    @Override
    public TipoProducto getItem(int position){
        return mantenedor.get(position);
    }

    //Se sobre escribe este metodo para tener el indice en el arraylist
    @Override
    public long getItemId(int id){
        for (TipoProducto m: mantenedor) {
            if (m.getIdTipoProducto() == id){
                return mantenedor.indexOf(m);
            }
        }

        return id;
    }


    // And the "magic" goes here
    // This is for the "passive" state of the spinner
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        // Then you can get the current item using the values array (Users array) and the current position
        // You can NOW reference each method you has created in your bean object (User class)
        label.setText(mantenedor.get(position).getNombreTipoProducto());
        label.setTextSize(14);
        label.setGravity(Gravity.CENTER);
        // And finally return your dynamic (or custom) view for each spinner item
        return label;
    }

    // And here is when the "chooser" is popped up
    // Normally is the same view, but you can customize it if you want
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setTextSize(14);
        label.setGravity(Gravity.CENTER);
        label.setText(mantenedor.get(position).getNombreTipoProducto());

        return label;
    }

}
