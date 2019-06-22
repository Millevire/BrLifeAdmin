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
import com.example.esteban.brlifeadmin.AlertDialog.AlertNuevoMantendorComuna;
import com.example.esteban.brlifeadmin.AlertDialog.AlertNuevoMantenedorDosAtributos;
import com.example.esteban.brlifeadmin.AlertDialog.AlertNuevoMantenedorTipoProducto;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.Comuna;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.Mantenedor;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.MantenedorTresAtributos;
import com.example.esteban.brlifeadmin.ConexionesWebServiceNuevo.CargarMantenedorDosAtributosHttpConecction;
import com.example.esteban.brlifeadmin.ConexionesWebServiceNuevo.CargarMantenedorTresAtributosHttpConecction;
import com.example.esteban.brlifeadmin.R;

import java.util.ArrayList;

public class AdapterComuna extends BaseAdapter implements PopupMenu.OnMenuItemClickListener, AlertDelete.FinalizoCuadroDialogo, AlertNuevoMantenedorDosAtributos.FinalizoCuadroDialogoAgregar, AlertNuevoMantenedorTipoProducto.FinalizoCuadroDialogoAgregar, AlertNuevoMantendorComuna.FinalizoCuadroDialogoAgregar {
    private Context context;
    private static ArrayList<Comuna> listaComuna =new ArrayList<>();
    private String tipoMantenedor;

    public AdapterComuna(Context context, ArrayList<Comuna> listaComuna, String tipoMantenedor) {
        this.context = context;
        this.listaComuna = listaComuna;
        this.tipoMantenedor = tipoMantenedor;
    }

    @Override
    public int getCount() {
        return listaComuna.size();
    }

    @Override
    public Object getItem(int position) {
        return listaComuna.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //inflar vista de adaptador
        if (convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = View.inflate(context, R.layout.adapter_mantenedor_comuna,null);
        }

        //Referencia de widget
        TextView tvNombreMantenedorComuna=(TextView)convertView.findViewById(R.id.tvNombreMantenedorComuna);
        Button btnMoreMantenedorComuna=(Button)convertView.findViewById(R.id.btnMoreMantenedorComuna);
        TextView tvTipoProducto_Provincia=convertView.findViewById(R.id.tvTipoProducto_Provincia);
        TextView tvTipoProducto_Region=convertView.findViewById(R.id.tvTipoProducto_Region);

        //Para el nombre de la comuna
        tvNombreMantenedorComuna.setText(listaComuna.get(position).getNombreComuna());

        //Para Obtener el nombre de la region
        Mantenedor mantenedorRegion = CargarMantenedorDosAtributosHttpConecction.buscar(listaComuna.get(position).getIdRegion());
        MantenedorTresAtributos mantenedorProvincia = CargarMantenedorTresAtributosHttpConecction.buscar(listaComuna.get(position).getIdProvincia());

        if (mantenedorRegion != null && mantenedorProvincia != null){
            tvTipoProducto_Provincia.setText("Provincia: " + mantenedorProvincia.getNombreMantenedorTresAtributos());
            tvTipoProducto_Region.setText(mantenedorRegion.getNombreTipoProducto());
        }

        btnMoreMantenedorComuna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup=new PopupMenu(context,v);
                popup.setOnMenuItemClickListener(AdapterComuna.this);
                popup.inflate(R.menu.popup_menu);

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){
                            case R.id.itemEdit:
                                new AlertNuevoMantendorComuna(context,  AdapterComuna.this,true, listaComuna.get(position),tipoMantenedor);
                                //new CargarBaseDeDatosDosAtributos(context);

                                return true;

                            case R.id.itemDelete:
                                int id= listaComuna.get(position).getIdComuna();

                                new AlertDelete(context,id,AdapterComuna.this,tipoMantenedor);
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

    @Override
    public void ResultadoCuadroDialogoAgregarComuna(boolean val) {
        this.notifyDataSetChanged();
    }
}
