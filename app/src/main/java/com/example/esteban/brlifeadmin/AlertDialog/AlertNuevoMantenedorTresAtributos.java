package com.example.esteban.brlifeadmin.AlertDialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.esteban.brlifeadmin.Clases.Mantenedor.Mantenedor;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.TipoProducto;
import com.example.esteban.brlifeadmin.ConexionWebService.CargarBaseDeDatosDosAtributos;
import com.example.esteban.brlifeadmin.ConexionWebService.CargarBaseDeDatosTIpoProducto;
import com.example.esteban.brlifeadmin.Enum.SelccionMantenedor;
import com.example.esteban.brlifeadmin.R;

public class AlertNuevoMantenedorTresAtributos {
    ArrayAdapter <Mantenedor>adapterRegion;
    ArrayAdapter <TipoProducto>adapterTipoProducto;




    //metodo interfaz que comunicara Alert dialogo con actividad donde se implemente
    public interface FinalizoCuadroDialogo{
        void ResultadoCuadroDialogo(Boolean val);
    }

    //interfaz de comunicacion
    private FinalizoCuadroDialogo interfaz;

    public AlertNuevoMantenedorTresAtributos(final Context contexto, FinalizoCuadroDialogo actividad, String mantenedor){



        //Recibir una interfaz desde donde es llamado para aplicar metodos de comunicacion
        interfaz=actividad;

        //inflar vista para alert dialogo eliminar
        final Dialog dialogo=new Dialog(contexto);
        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogo.setCancelable(false);
        //dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogo.setContentView(R.layout.alert_nuevo_mantenedor_tres_atributos);
        dialogo.show();


        //Declaracion de widget
        EditText etAlertNuevoMantenedorTresAtributos=dialogo.findViewById(R.id.etAlertNuevoMantenedorTresAtributos);
        Spinner spAlertMantenedorTresAtributos=dialogo.findViewById(R.id.spAlertMantenedorTresAtributos);
        Button btnCancelarAlertNuevoMantenedorTresAtributos=dialogo.findViewById(R.id.btnCancelarAlertNuevoMantenedorTresAtributos);
        Button btnAceptarlarAlertNuevaMantenedorTresAtributos=dialogo.findViewById(R.id.btnAceptarlarAlertNuevaMantenedorTresAtributos);
        TextView tvTituloAlertNuevoMantenedorTresAtributo=dialogo.findViewById(R.id.tvTituloAlertNuevoMantenedorTresAtributo);
        TextView tvTipoMantenedorSpinner=dialogo.findViewById(R.id.tvTipoMantenedorSpinner);

        //cambiar titulo dpendiendo de mantenedor
        tvTituloAlertNuevoMantenedorTresAtributo.setText("Nuevo "+mantenedor);

        //Cambiar hint dependiendo de nombre de mantenedor

        etAlertNuevoMantenedorTresAtributos.setHint("Ingrese nuevo "+mantenedor);



        //Region llenar Spinner con informacion dependiendo de mantenedor
        if (mantenedor.equals(SelccionMantenedor.Provincia.getSeleccion())){

            tvTipoMantenedorSpinner.setText("Region:");
            adapterRegion=new ArrayAdapter(contexto,android.R.layout.simple_list_item_1, CargarBaseDeDatosDosAtributos.getListaMantenedors());
            spAlertMantenedorTresAtributos.setAdapter(adapterRegion);

        }else {

            //Cambiar titulo de Spinner
            tvTipoMantenedorSpinner.setText("Tipo de Producto:");

            adapterTipoProducto=new ArrayAdapter(contexto,android.R.layout.simple_list_item_1, CargarBaseDeDatosTIpoProducto.getListaTipoProducto());
            spAlertMantenedorTresAtributos.setAdapter(adapterTipoProducto);
        }






      //Cerrrar Alert dialogo
        btnCancelarAlertNuevoMantenedorTresAtributos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogo.dismiss();
            }
        });



    }


}
