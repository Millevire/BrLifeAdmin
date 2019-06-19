package com.example.esteban.brlifeadmin.AlertDialog;

import android.app.Dialog;
import android.content.Context;
import android.renderscript.ScriptGroup;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.esteban.brlifeadmin.Adapter.SpinAdapter;
import com.example.esteban.brlifeadmin.ConexionWebService.CargarBaseDeDatosProductoNutriente;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.ProductoNutriente;
import com.example.esteban.brlifeadmin.ConexionWebService.CargarBaseDeDatosDosAtributos;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.DialogKeyListener;
import com.example.esteban.brlifeadmin.R;


public class AlertMantenedorProductoNutriente  {
    SpinAdapter adapterNutriente;
    public int idNutriente;

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
        TextView tvTitutloAlertProductoNutriente=dialogo.findViewById(R.id.tvTitutloAlertProductoNutriente);
        final EditText etAlertProductoNutriente=dialogo.findViewById(R.id.etAlertProductoNutrienteValor);
        Button btnCancelarAlertProductoNutriente=dialogo.findViewById(R.id.btnCancelarAlertProductoNutriente);
        Button btnAceptarAlertProductoNutriente=dialogo.findViewById(R.id.btnAceptarAlertProductoNutriente);
        tvTitutloAlertProductoNutriente.setText("Nuevo Nutriente ");
        etAlertProductoNutriente.setHint("Valor");

        //Cargar Lista Nutrientes a Spinner
        adapterNutriente=new SpinAdapter(contexto,android.R.layout.simple_list_item_1, CargarBaseDeDatosDosAtributos.getListaMantenedors());
        spAlertProductoNutriente.setAdapter(adapterNutriente);


        spAlertProductoNutriente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                IdNutriente(CargarBaseDeDatosDosAtributos.getListaMantenedors().get(position).getIdTipoProducto());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAceptarAlertProductoNutriente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Agregar nuevo registro a lista producto nutriente
                //Se agregar con accion  i en el caso de insertar un nutriente al producto
                CargarBaseDeDatosProductoNutriente.agregar(new ProductoNutriente(0,0,idNutriente,Float.parseFloat(etAlertProductoNutriente.getText().toString()),"i"));
                //Comunicar cambio en lista
                interfaz.ResultadoCuadroDialogoProductoNutriente(true);
                dialogo.dismiss();
            }

        });


        //Cerrar AlertDialog
        btnCancelarAlertProductoNutriente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogo.dismiss();
            }
        });

    }

    public void IdNutriente(int posicion){
        this.idNutriente=posicion;
    }

}
