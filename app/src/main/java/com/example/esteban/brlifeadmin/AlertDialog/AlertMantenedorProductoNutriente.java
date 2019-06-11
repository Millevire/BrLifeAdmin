package com.example.esteban.brlifeadmin.AlertDialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.QuickContactBadge;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.esteban.brlifeadmin.Adapter.SpinAdapter;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.Mantenedor;
import com.example.esteban.brlifeadmin.ConexionWebService.CargarBaseDeDatosDosAtributos;
import com.example.esteban.brlifeadmin.DialogKeyListener;
import com.example.esteban.brlifeadmin.R;


public class AlertMantenedorProductoNutriente  {
    SpinAdapter adapterNutriente;

    public  interface  FinalizoCuadroDialogoProductoNutriente{
        void ResultadoCuadroDialogoProductoNutriente(Boolean val);
    }

    private FinalizoCuadroDialogoProductoNutriente interfaz;
    public AlertMantenedorProductoNutriente(final Context contexto, FinalizoCuadroDialogoProductoNutriente actividad){
        interfaz=actividad;

        //inflar vista para alert dialogo eliminar
        final Dialog dialogo=new Dialog(contexto);
        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogo.setCancelable(false);
        dialogo.setContentView(R.layout.alert_nuevo_mantenedor_productonutriente);
        dialogo.show();

        //Detectar boton Back de celular
        DialogKeyListener dkl=new DialogKeyListener();
        dialogo.setOnKeyListener(dkl);

        Spinner spAlertProductoNutriente=dialogo.findViewById(R.id.spAlertProductoNutriente);
        TextView tvAlertProductoNutriente=dialogo.findViewById(R.id.tvAlertProductoNutriente);
        TextView tvTitutloAlertProductoNutriente=dialogo.findViewById(R.id.tvTitutloAlertProductoNutriente);
        EditText etAlertProductoNutriente=dialogo.findViewById(R.id.etAlertProductoNutriente);
        Button btnCancelarAlertProductoNutriente=dialogo.findViewById(R.id.btnCancelarAlertProductoNutriente);
        Button btnAceptarAlertProductoNutriente=dialogo.findViewById(R.id.btnAceptarAlertProductoNutriente);

        tvTitutloAlertProductoNutriente.setText("Nuevo Nutriente ");
        etAlertProductoNutriente.setHint("Valor");

        //Cargar Lista Nutrientes a Spinner
        adapterNutriente=new SpinAdapter(contexto,android.R.layout.simple_list_item_1, CargarBaseDeDatosDosAtributos.getListaMantenedors());
        spAlertProductoNutriente.setAdapter(adapterNutriente);


        //Cerrar AlertDialog
        btnCancelarAlertProductoNutriente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogo.dismiss();
            }
        });

    }

}
