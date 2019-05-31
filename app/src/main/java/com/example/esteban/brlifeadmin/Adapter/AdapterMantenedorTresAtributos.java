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

import com.example.esteban.brlifeadmin.Clases.Mantenedor.MantenedorTresAtributos;
import com.example.esteban.brlifeadmin.R;

import java.util.ArrayList;

public class AdapterMantenedorTresAtributos extends BaseAdapter {
    private Context context;
    private ArrayList<MantenedorTresAtributos> listaMantenedorTresAtributos =new ArrayList<>();
    private String tipoMantenedor;




    public AdapterMantenedorTresAtributos(Context context, ArrayList<MantenedorTresAtributos> listaMantenedorTresAtributos, String tipoMantenedor){
        this.context = context;
        this.listaMantenedorTresAtributos=listaMantenedorTresAtributos;
        this.tipoMantenedor=tipoMantenedor;
    }

    @Override
    public int getCount() {
        return listaMantenedorTresAtributos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaMantenedorTresAtributos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = View.inflate(context, R.layout.adapter_mantenedor_dos_atributos,null);

        }

        TextView tvNombreTipoProducto=(TextView)convertView.findViewById(R.id.tvNombreTipoProducto);
        Button btnMoresTipoProducto=(Button)convertView.findViewById(R.id.btnMoreTipoProducto);


        tvNombreTipoProducto.setText(listaMantenedorTresAtributos.get(position).getNombreMantenedorTresAtributos());


        btnMoresTipoProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup=new PopupMenu(context,v);
                popup.setOnMenuItemClickListener((PopupMenu.OnMenuItemClickListener) AdapterMantenedorTresAtributos.this);
                popup.inflate(R.menu.popup_menu);

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){
                            case R.id.itemEdit:


                                return true;

                            case R.id.itemDelete:


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
}
