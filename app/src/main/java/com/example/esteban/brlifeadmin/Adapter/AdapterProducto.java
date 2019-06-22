package com.example.esteban.brlifeadmin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.esteban.brlifeadmin.Clases.Mantenedor.Producto;
import com.example.esteban.brlifeadmin.ConexionesWebServiceNuevo.CargarMantenedorTresAtributosHttpConecction;
import com.example.esteban.brlifeadmin.R;
import com.example.esteban.brlifeadmin.Enum.SeleccionTipoProducto;

import java.util.ArrayList;

public class AdapterProducto extends BaseAdapter {
    private Context context;
    private ArrayList<Producto> listaProducto =new ArrayList<>();
    private String tipoMantenedor;

    public AdapterProducto(Context context, ArrayList<Producto> listaProducto, String tipoMantenedor){
        this.context=context;
        this.listaProducto=listaProducto;
        this.tipoMantenedor=tipoMantenedor;
    }
    @Override
    public int getCount() {
        return listaProducto.size();
    }

    @Override
    public Object getItem(int position) {
        return listaProducto.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = View.inflate(context, R.layout.adapter_producto,null);

        }
        TextView tvNombreProducto=(TextView)convertView.findViewById(R.id.tvNombreProducto);
        TextView tvSaborProducto=(TextView)convertView.findViewById(R.id.tvSaborProducto);
        TextView tvMarcaProducto=(TextView)convertView.findViewById(R.id.tvMarcaProducto);
        TextView tvCaloriasProducto=(TextView)convertView.findViewById(R.id.tvCaloriasProducto);
        ImageView ivImagen=(ImageView)convertView.findViewById(R.id.ivImagen);


        //Variables id
        int idSabor;
        int idMarca;
        String nombreMarca;
        String nombreSabor;


        SeleccionTipoProducto nombreTipoProducto= SeleccionTipoProducto.valueOf(listaProducto.get(position).getNombreTipoProducto());

        switch (nombreTipoProducto){
            case Crustaceo:
                ivImagen.setImageResource(R.drawable.crustaceo);
                break;
            case Agua:
                ivImagen.setImageResource(R.drawable.agua);
                break;
            case Huevo:
                ivImagen.setImageResource(R.drawable.huevos);
                break;
            case Pescado:
                ivImagen.setImageResource(R.drawable.pescado);
                break;
            case Carne:
                ivImagen.setImageResource(R.drawable.carne);
                break;
            case Cereal:
                ivImagen.setImageResource(R.drawable.cereal);
                break;
            case Legrumbre:
                ivImagen.setImageResource(R.drawable.legumbre);
                break;
            case Lacteo:
                ivImagen.setImageResource(R.drawable.lacteo);
                break;
            case Bebida:
                ivImagen.setImageResource(R.drawable.bebida);
                break;
            case Fruta:
                ivImagen.setImageResource(R.drawable.fruta);
                break;
            case Galleta:
                ivImagen.setImageResource(R.drawable.galleta);
                break;
            case Fritura:
                ivImagen.setImageResource(R.drawable.frituras);
        }


        //Obtener nombre segun id de marca y sabor

        //id de marca y sabor de registro
        idMarca=listaProducto.get(position).getIdMarca();
        idSabor=listaProducto.get(position).getIdSabor();

        //obtener nombre
        nombreMarca= CargarMantenedorTresAtributosHttpConecction.buscarMarca(idMarca);
        nombreSabor=CargarMantenedorTresAtributosHttpConecction.buscaSabor(idSabor);

        //Setear nombres en textview
        tvMarcaProducto.setText(nombreMarca);
        tvSaborProducto.setText(nombreSabor);


        //nombre producto
        tvNombreProducto.setText(listaProducto.get(position).getNombreProducto());




        return convertView;
    }
}
