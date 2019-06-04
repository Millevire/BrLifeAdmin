package com.example.esteban.brlifeadmin.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.esteban.brlifeadmin.Clases.Mantenedor.Comuna;

import java.util.ArrayList;

public class AdapterComuna extends BaseAdapter {
    private Context context;
    private ArrayList<Comuna> listaComuna =new ArrayList<>();
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
