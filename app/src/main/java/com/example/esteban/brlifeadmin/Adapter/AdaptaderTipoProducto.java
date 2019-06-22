package com.example.esteban.brlifeadmin.Adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import com.example.esteban.brlifeadmin.AlertDialog.AlertDelete;
import com.example.esteban.brlifeadmin.AlertDialog.AlertNuevoMantenedorDosAtributos;
import com.example.esteban.brlifeadmin.AlertDialog.AlertNuevoMantenedorTipoProducto;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.TipoProducto;
import com.example.esteban.brlifeadmin.R;

import java.util.ArrayList;
import java.util.Locale;

public class AdaptaderTipoProducto extends BaseAdapter implements PopupMenu.OnMenuItemClickListener,AlertDelete.FinalizoCuadroDialogo,AlertNuevoMantenedorDosAtributos.FinalizoCuadroDialogoAgregar, AlertNuevoMantenedorTipoProducto.FinalizoCuadroDialogoAgregar {

    private Context context;
    private ArrayList<TipoProducto> listaTipoProductos =new ArrayList<>();
    private ArrayList<TipoProducto>listaAux=new ArrayList<>();
    private String tipoMantenedor;


    public AdaptaderTipoProducto(Context context, ArrayList<TipoProducto> listaTipoProductos, String tipoMantenedor) {
        this.context = context;
        this.listaTipoProductos = listaTipoProductos;
        this.listaAux.addAll(listaTipoProductos);
        this.tipoMantenedor = tipoMantenedor;
    }

    @Override
    public int getCount() {
        return listaTipoProductos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaTipoProductos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = View.inflate(context, R.layout.adapter_mantenedor_tres_atributos,null);

        }

        //Variables auxiliares informativas
        String sabor, marca;

        TextView tvNombreTipoProducto=(TextView)convertView.findViewById(R.id.tvNombreMantenedorTresAtributo);
        Button btnMoresTipoProducto=(Button)convertView.findViewById(R.id.btnMoreMantenedorTresAtributos);
        tvNombreTipoProducto.setText(listaTipoProductos.get(position).getNombreTipoProducto().toString());

        //Operador ternario
        sabor = (listaTipoProductos.get(position).isVaridadSabor())?"Si":"No";
        marca = (listaTipoProductos.get(position).isVariedadMarca())?"Si":"No";


        //Para mostrar si tienen variedad marca y sabor
        TextView tvTipoProducto_Region=convertView.findViewById(R.id.tvTipoProducto_Region);

        tvTipoProducto_Region.setText("Variedad Marca: " + marca + " Variedad Sabor: " + sabor);

        btnMoresTipoProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup=new PopupMenu(context,v);
                popup.setOnMenuItemClickListener(AdaptaderTipoProducto.this);
                popup.inflate(R.menu.popup_menu);

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){
                            case R.id.itemEdit:
                                new AlertNuevoMantenedorTipoProducto(context,  AdaptaderTipoProducto.this,true, listaTipoProductos.get(position),tipoMantenedor);
                                //new CargarBaseDeDatosDosAtributos(context);

                                return true;

                            case R.id.itemDelete:
                                int id= listaTipoProductos.get(position).getIdTipoProducto();

                                new AlertDelete(context,id,AdaptaderTipoProducto.this,tipoMantenedor);
                                //new CargarBaseDeDatosDosAtributos(context);

                                return true;

                            default: return false;

                        }

                    }
                });
                popup.show();
            }
        });

        return convertView;
    }

    public void Filtro(String filtro){
        filtro=filtro.toLowerCase(Locale.getDefault());
        listaTipoProductos.clear();
        if(filtro.length()==0){
            listaTipoProductos.addAll(listaAux);

        }else{
            for (TipoProducto tipoProducto:listaAux){
                if (tipoProducto.getNombreTipoProducto().toLowerCase(Locale.getDefault()).contains(filtro))
                {
                    listaTipoProductos.add(tipoProducto);

                }


            }

        }
        notifyDataSetChanged();

    }


    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        return false;
    }

    @Override
    public void ResultadoCuadroDialogo(Boolean val) {
        this.notifyDataSetChanged();

    }

    @Override
    public void ResultadoCuadroDialogoAgregar(boolean val) {
        this.notifyDataSetChanged();


    }

    @Override
    public void ResultadoCuadroDialogoAgregarTipoProducto(boolean val) {
        this.notifyDataSetChanged();
    }
}
