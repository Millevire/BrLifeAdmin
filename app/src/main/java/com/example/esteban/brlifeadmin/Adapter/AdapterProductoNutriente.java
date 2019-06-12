package com.example.esteban.brlifeadmin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.esteban.brlifeadmin.Clases.Mantenedor.Mantenedor;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.ProductoNutriente;
import com.example.esteban.brlifeadmin.ConexionWebService.CargarBaseDeDatosDosAtributos;
import com.example.esteban.brlifeadmin.ConexionWebService.CrudMantenedorDosAtibutos;
import com.example.esteban.brlifeadmin.R;

import java.util.ArrayList;

public class AdapterProductoNutriente extends BaseAdapter {
    private Context context;
    private ArrayList<ProductoNutriente> listaProductoNutriente=new ArrayList<>();

    public AdapterProductoNutriente(Context context,ArrayList<ProductoNutriente> listaProductoNutriente){
        this.context=context;
        this.listaProductoNutriente=listaProductoNutriente;
    }

    @Override
    public int getCount() {
        return listaProductoNutriente.size();
    }

    @Override
    public Object getItem(int position) {
        return listaProductoNutriente.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = View.inflate(context, R.layout.adapter_producto_nutriente,null);

        }

        TextView tvNombreNutriente=convertView.findViewById(R.id.tvNombreNutriente);
        TextView tvValorNutriente=convertView.findViewById(R.id.tvValorNutriente);
        Button btnEliminarProductoNutriente=convertView.findViewById(R.id.btnEliminarProductoNutriente);

        //Buscar Nutriente con id ocupando metodo de busqueda
        Mantenedor mantenedor= CargarBaseDeDatosDosAtributos.buscar(listaProductoNutriente.get(position).getIdNutriente());

        //Setear valor de nutriente
        tvValorNutriente.setText(String.valueOf(listaProductoNutriente.get(position).getValor()));



        tvNombreNutriente.setText(mantenedor.getNombreTipoProducto());
        return convertView;
    }
}
