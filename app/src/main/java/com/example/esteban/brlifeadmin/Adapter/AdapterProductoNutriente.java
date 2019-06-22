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
import com.example.esteban.brlifeadmin.ConexionesWebServiceNuevo.CargarMantenedorDosAtributosHttpConecction;
import com.example.esteban.brlifeadmin.ConexionesWebServiceNuevo.CargarMantenedorProductoNutrienteHttpConecction;
import com.example.esteban.brlifeadmin.R;

import java.util.ArrayList;
import java.util.Locale;

public class AdapterProductoNutriente extends BaseAdapter {
    private Context context;
    private ArrayList<ProductoNutriente> listaProductoNutriente=new ArrayList<>();
    private ArrayList<ProductoNutriente> listaAux=new ArrayList<>();

    public String nombreNutriente;

    public AdapterProductoNutriente(Context context,ArrayList<ProductoNutriente> listaProductoNutriente){
        this.context=context;
        this.listaProductoNutriente=listaProductoNutriente;
        this.listaAux.addAll(listaProductoNutriente);
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = View.inflate(context, R.layout.adapter_producto_nutriente,null);

        }

        TextView tvNombreNutriente=convertView.findViewById(R.id.tvNombreNutriente);
        TextView tvValorNutriente=convertView.findViewById(R.id.tvValorNutriente);
        Button btnEliminarProductoNutriente=convertView.findViewById(R.id.btnEliminarProductoNutriente);

        Mantenedor mantenedor= CargarMantenedorDosAtributosHttpConecction.buscar(listaProductoNutriente.get(position).getIdNutriente());

        //Buscar Nutriente con id ocupando metodo de busqueda
       nombreNutriente=mantenedor.getNombreTipoProducto();

        //Setear valor de nutriente
        tvValorNutriente.setText(String.valueOf(listaProductoNutriente.get(position).getValor()));


        //Boton eliminar registro de lista producto nutriente mediante id
        btnEliminarProductoNutriente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CargarMantenedorProductoNutrienteHttpConecction.eliminar(listaProductoNutriente.get(position).getIdNutriente());
                notifyDataSetChanged();
            }
        });


        tvNombreNutriente.setText(mantenedor.getNombreTipoProducto());
        return convertView;
    }
    //filtro
    public void Filtro(String filtro){
        filtro=filtro.toLowerCase(Locale.getDefault());
        listaProductoNutriente.clear();
        if(filtro.length()==0){
            listaProductoNutriente.addAll(listaAux);

        }else{
            for (ProductoNutriente productoNutriente:listaAux){
                if (nombreNutriente.toLowerCase(Locale.getDefault()).contains(filtro))
                {
                    listaProductoNutriente.add(productoNutriente);

                }


            }

        }
        notifyDataSetChanged();

    }
}
