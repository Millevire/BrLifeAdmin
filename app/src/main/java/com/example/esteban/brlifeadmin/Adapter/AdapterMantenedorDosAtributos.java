package com.example.esteban.brlifeadmin.Adapter;

import android.content.Context;
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
import com.example.esteban.brlifeadmin.Clases.Mantenedor.Mantenedor;
import com.example.esteban.brlifeadmin.R;

import java.util.ArrayList;

public class AdapterMantenedorDosAtributos extends BaseAdapter implements PopupMenu.OnMenuItemClickListener,AlertDelete.FinalizoCuadroDialogo,AlertNuevoMantenedorDosAtributos.FinalizoCuadroDialogoAgregar {

    private Context context;
    private ArrayList<Mantenedor> mantenedor =new ArrayList<>();
    private String tipoMantenedor;


    public AdapterMantenedorDosAtributos(Context context, ArrayList<Mantenedor> mantenedor, String tipoMantenedor){
        this.context = context;
        this.mantenedor = mantenedor;
        this.tipoMantenedor=tipoMantenedor;
    }

    public void actualizarLista(ArrayList <Mantenedor> lista){
        mantenedor.clear();
        mantenedor.addAll(lista);
        this.notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        return mantenedor.size();
    }

    @Override
    public Object getItem(int position) {
        return mantenedor.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
           convertView = View.inflate(context, R.layout.adapter_mantenedor_dos_atributos,null);

        }



        TextView tvNombreTipoProducto=(TextView)convertView.findViewById(R.id.tvNombreTipoProducto);
        Button btnMoresTipoProducto=(Button)convertView.findViewById(R.id.btnMoreTipoProducto);

        tvNombreTipoProducto.setText(mantenedor.get(position).getNombreTipoProducto().toString());


        btnMoresTipoProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup=new PopupMenu(context,v);
                popup.setOnMenuItemClickListener(AdapterMantenedorDosAtributos.this);
                popup.inflate(R.menu.popup_menu);

            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {

                    switch (item.getItemId()){
                        case R.id.itemEdit:
                        new AlertNuevoMantenedorDosAtributos(context,AdapterMantenedorDosAtributos.this,true, mantenedor.get(position),tipoMantenedor);
                            //new CargarBaseDeDatosDosAtributos(context);

                            return true;

                        case R.id.itemDelete:
                            int id= mantenedor.get(position).getIdTipoProducto();

                         new AlertDelete(context,id,AdapterMantenedorDosAtributos.this,tipoMantenedor);
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




    @Override
    public boolean onMenuItemClick(MenuItem item) {


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




}
