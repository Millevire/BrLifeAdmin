package com.example.esteban.brlifeadmin.AlertDialog;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.esteban.brlifeadmin.ConexionWebService.CargarBaseDeDatosDosAtributos;
import com.example.esteban.brlifeadmin.ConexionWebService.CrudMantenedorDosAtibutos;
import com.example.esteban.brlifeadmin.CrudActivity;
import com.example.esteban.brlifeadmin.R;

public class AlertDelete {

    //metodo interfaz que comunicara Alert dialogo con actividad donde se implemente
    public interface FinalizoCuadroDialogo{
        void ResultadoCuadroDialogo(Boolean val);
    }

    private FinalizoCuadroDialogo interfaz;

    public AlertDelete(final Context contexto, final int id, FinalizoCuadroDialogo actividad, final String mantenedor){
        interfaz =actividad;
        final Intent intent =new Intent(contexto,CrudActivity.class);

        //inflar vista para alert dialogo eliminar
        final Dialog dialogo=new Dialog(contexto);
        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogo.setCancelable(false);
        dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogo.setContentView(R.layout.alert_delete);
        dialogo.show();

        //Widget
        Button btnAlertEliminar=dialogo.findViewById(R.id.btnAlertEliminar);
        Button btnAlertCancelar=dialogo.findViewById(R.id.btnAlertCancelar);

        //Accion boton Eliminar
        btnAlertEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    interfaz.ResultadoCuadroDialogo(true);
                    new CrudMantenedorDosAtibutos("",contexto,"eliminar",id,mantenedor);
                    //new CargarBaseDeDatosDosAtributos(contexto);
                    CargarBaseDeDatosDosAtributos.eliminar(id);
                    dialogo.dismiss();




            }
        });

        //Accion boton cancear
        btnAlertCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogo.dismiss();
            }
        });
    }

}
