package com.example.esteban.brlifeadmin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.esteban.brlifeadmin.AlertDialog.AlertDelete;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.Producto;
import com.example.esteban.brlifeadmin.ConexionesWebServiceNuevo.CargarMantenedorProductoHttpConecction;
import com.example.esteban.brlifeadmin.ConexionesWebServiceNuevo.CargarMantenedorProductoNutrienteHttpConecction;
import com.example.esteban.brlifeadmin.ConexionesWebServiceNuevo.CargarMantenedorTresAtributosHttpConecction;
import com.example.esteban.brlifeadmin.Enum.SelccionMantenedor;
import com.example.esteban.brlifeadmin.NuevoProductoActivity;
import com.example.esteban.brlifeadmin.R;
import com.example.esteban.brlifeadmin.Enum.SeleccionTipoProducto;

import org.json.JSONException;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;

public class AdapterProducto extends BaseAdapter implements AlertDelete.FinalizoCuadroDialogo {
    private Context context;
    private ArrayList<Producto> listaProducto =new ArrayList<>();
    private ArrayList<Producto>listaAux=new ArrayList<>();

    //Variables id
    int idSabor;
    int idMarca;
    String nombreMarca;
    String nombreSabor;


    private String tipoMantenedor;

    public AdapterProducto(Context context, ArrayList<Producto> listaProducto, String tipoMantenedor){
        this.context=context;
        this.listaProducto=listaProducto;
        this.listaAux.addAll(listaProducto);
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = View.inflate(context, R.layout.adapter_producto,null);

        }

        final Intent intent =new Intent(context, NuevoProductoActivity.class);

        TextView tvNombreProducto=(TextView)convertView.findViewById(R.id.tvNombreProducto);
        TextView tvSaborProducto=(TextView)convertView.findViewById(R.id.tvSaborProducto);
        TextView tvMarcaProducto=(TextView)convertView.findViewById(R.id.tvMarcaProducto);
        ImageView ivImagen=(ImageView)convertView.findViewById(R.id.ivImagen);
        Button btnEliminarProducto=convertView.findViewById(R.id.btnEliminarProducto);
        Button btnEditarProducto=convertView.findViewById(R.id.btnEditarProducto);


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
                break;
            case Cafe:
                ivImagen.setImageResource(R.drawable.cafe);
                break;
            case Caramelo:
                ivImagen.setImageResource(R.drawable.caramelo);
                break;
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


        btnEliminarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDelete(context,listaProducto.get(position).getIdProducto(),AdapterProducto.this, SelccionMantenedor.Producto.getSeleccion());
            }
        });


        btnEditarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                intent.putExtra("accion","editar");
                Producto producto = CargarMantenedorProductoHttpConecction.listaProducto.get(position);
                //new CargarBaseDeDatosProductoNutriente(CrudActivity.this, producto.getIdProducto(),                         SelccionMantenedor.ProductoNutriente.getSeleccion());
                try {
                    CargarMantenedorProductoNutrienteHttpConecction.buscarMantenedorProductoNutriente(context,SelccionMantenedor.ProductoNutriente.getSeleccion(),producto.getIdProducto());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                intent.putExtra("Producto", (Serializable) producto);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    public void Filtro(String filtro){
        filtro=filtro.toLowerCase(Locale.getDefault());
        listaProducto.clear();
        if(filtro.length()==0){
            listaProducto.addAll(listaAux);

        }else{
            for (Producto producto:listaAux){
                if (producto.getNombreProducto().toLowerCase(Locale.getDefault()).contains(filtro))
                {
                    listaProducto.add(producto);

                }else if(nombreMarca.toLowerCase(Locale.getDefault()).contains(filtro)){
                    listaProducto.add(producto);

                }else if(nombreSabor.toLowerCase(Locale.getDefault()).contains(filtro)){
                    listaProducto.add(producto);

                }


            }

        }
        notifyDataSetChanged();

    }

    @Override
    public void ResultadoCuadroDialogo(Boolean val) {
        notifyDataSetChanged();
    }
}
