package com.example.esteban.brlifeadmin.AlertDialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.esteban.brlifeadmin.Adapter.SpinAdapter;
import com.example.esteban.brlifeadmin.Adapter.SpinAdapterTresAtributos;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.Comuna;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.Mantenedor;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.MantenedorTresAtributos;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.TipoProducto;
import com.example.esteban.brlifeadmin.ConexionWebService.CargarBaseDeDatosDosAtributos;
import com.example.esteban.brlifeadmin.ConexionWebService.CargarBaseDeDatosMantenedorTresAtributos;
import com.example.esteban.brlifeadmin.ConexionWebService.CrudMantenedorTresAtributos;
import com.example.esteban.brlifeadmin.R;

public class AlertNuevoMantendorComuna {
    ArrayAdapter<Mantenedor> adapterRegion;
    ArrayAdapter<MantenedorTresAtributos> adapterProvincia;
    int posicionregion;
    int posicionprovincia;

    //metodo interfaz que comunicara Alert dialogo con actividad donde se implemente
    public interface FinalizoCuadroDialogoAgregar{
        void ResultadoCuadroDialogoAgregarComuna(boolean val);
    }

    private FinalizoCuadroDialogoAgregar interfaz;

    public AlertNuevoMantendorComuna(final Context contexto, final FinalizoCuadroDialogoAgregar actividad, final boolean tipo, final Comuna comuna, final String mantenedor) {
        interfaz = actividad;
        //inflar vista para alert dialogo eliminar
        final Dialog dialogo=new Dialog(contexto);
        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogo.setCancelable(false);
        //dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogo.setContentView(R.layout.alert_nuevo_mantenedor_comuna);
        dialogo.show();

        //Declaracion de widget
        final EditText etAlertNuevoMantenedorTresAtributos=dialogo.findViewById(R.id.etAlertNuevoMantenedorComuna);
        Spinner spAlertMantenedorProvincia = dialogo.findViewById(R.id.spAlertMantenedorProvincia);
        Spinner spAlertMantenedorRegion = dialogo.findViewById(R.id.spAlertMantenedorRegion);
        Button btnCancelarAlertNuevoMantenedorComuna = dialogo.findViewById(R.id.btnCancelarAlertNuevoMantenedorComuna);
        Button btnAceptarlarAlertNuevaMantenedorComuna = dialogo.findViewById(R.id.btnAceptarlarAlertNuevoMantenedorComuna);
        final TextView tvTituloAlertNuevoMantenedorComuna = dialogo.findViewById(R.id.tvTituloAlertNuevoMantenedorComuna);
        TextView tvTipoMantenedorSpinnerRegion = dialogo.findViewById(R.id.tvTipoRegion);
        TextView tvTipoMantenedorSpinnerProvincia = dialogo.findViewById(R.id.tvTipoMantenedorProvincia);

        tvTipoMantenedorSpinnerRegion.setText("Region:");
        tvTipoMantenedorSpinnerProvincia.setText("Provincia:");
        adapterRegion=new SpinAdapter(contexto,android.R.layout.simple_list_item_1, CargarBaseDeDatosDosAtributos.getListaMantenedors());
        //adapterProvincia =new ArrayAdapter(contexto,android.R.layout.simple_list_item_1, CargarBaseDeDatosMantenedorTresAtributos.getListaMantenedorTresAtributos());
        adapterProvincia =new SpinAdapterTresAtributos(contexto,android.R.layout.simple_list_item_1, CargarBaseDeDatosMantenedorTresAtributos.getListaMantenedorTresAtributos());
        spAlertMantenedorProvincia.setAdapter(adapterProvincia);
        spAlertMantenedorRegion.setAdapter(adapterRegion);
        tvTituloAlertNuevoMantenedorComuna.setText("Nueva Comuna:");


        //Si es verdadero se edita
        if (tipo){
            etAlertNuevoMantenedorTresAtributos.setText(comuna.getNombreComuna());
            int posi = (int) adapterRegion.getItemId(comuna.getIdRegion());
            int posiprovi = (int) adapterProvincia.getItemId(comuna.getIdProvincia());
            spAlertMantenedorRegion.setSelection(posi);
            spAlertMantenedorProvincia.setSelection(posiprovi);
        }

        spAlertMantenedorRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //enviar posicion
                posicionregion(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spAlertMantenedorProvincia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //enviar posicion
                posicionprovincia(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAceptarlarAlertNuevaMantenedorComuna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogo.dismiss();
            }

        });






        //Cerrrar Alert dialogo
        btnCancelarAlertNuevoMantenedorComuna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogo.dismiss();
            }
        });


    }


    public void posicionregion(int posicion){

        this.posicionregion=posicion;
    }
    public void posicionprovincia(int posicion){

        this.posicionprovincia =posicion;
    }

}
