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
import java.util.Locale;

public class AdapterMantenedorDosAtributos extends BaseAdapter implements PopupMenu.OnMenuItemClickListener,AlertDelete.FinalizoCuadroDialogo,AlertNuevoMantenedorDosAtributos.FinalizoCuadroDialogoAgregar {

    private Context context;
    private ArrayList<Mantenedor> listaMantenedor =new ArrayList<>();
    private ArrayList<Mantenedor>listaAux=new ArrayList<>();

    private String tipoMantenedor;


    public AdapterMantenedorDosAtributos(Context context, ArrayList<Mantenedor> listaMantenedor, String tipoMantenedor){
        this.context = context;
        this.listaMantenedor = listaMantenedor;
        this.listaAux.addAll(listaMantenedor);
        this.tipoMantenedor=tipoMantenedor;
    }



    @Override
    public int getCount() {
        return listaMantenedor.size();
    }

    @Override
    public Object getItem(int position) {
        return listaMantenedor.get(position);
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

        tvNombreTipoProducto.setText(listaMantenedor.get(position).getNombreTipoProducto().toString());


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
                        new AlertNuevoMantenedorDosAtributos(context,AdapterMantenedorDosAtributos.this,true, listaMantenedor.get(position),tipoMantenedor);
                            //new CargarBaseDeDatosDosAtributos(context);

                            return true;

                        case R.id.itemDelete:
                            int id= listaMantenedor.get(position).getIdTipoProducto();

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

    public void Filtro(String filtro){
        filtro=filtro.toLowerCase(Locale.getDefault());
        listaMantenedor.clear();
        if(filtro.length()==0){
            listaMantenedor.addAll(listaAux);

        }else{
            for (Mantenedor mantenedor:listaAux){
                if (mantenedor.getNombreTipoProducto().toLowerCase(Locale.getDefault()).contains(filtro))
                {
                    listaMantenedor.add(mantenedor);

                }


            }

        }
        notifyDataSetChanged();

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
