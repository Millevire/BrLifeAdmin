package com.example.esteban.brlifeadmin.AlertDialog;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.esteban.brlifeadmin.ConexionWebService.CargarBaseDeDatosComuna;
import com.example.esteban.brlifeadmin.ConexionWebService.CargarBaseDeDatosDosAtributos;
import com.example.esteban.brlifeadmin.ConexionWebService.CargarBaseDeDatosTIpoProducto;
import com.example.esteban.brlifeadmin.ConexionWebService.CrudMantenedorComuna;
import com.example.esteban.brlifeadmin.ConexionWebService.CrudMantenedorDosAtibutos;
import com.example.esteban.brlifeadmin.ConexionWebService.CrudMantenedorTipoProducto;
import com.example.esteban.brlifeadmin.ConexionWebService.CrudMantenedorTresAtributos;
import com.example.esteban.brlifeadmin.CrudActivity;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.DialogKeyListener;
import com.example.esteban.brlifeadmin.Enum.SelccionMantenedor;
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

        //Detectar boton Back de celular
        DialogKeyListener dkl=new DialogKeyListener();
        dialogo.setOnKeyListener(dkl);


        //Widget
        Button btnAlertEliminar=dialogo.findViewById(R.id.btnAlertEliminar);
        Button btnAlertCancelar=dialogo.findViewById(R.id.btnAlertCancelar);

        //Accion boton Eliminar
        btnAlertEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    interfaz.ResultadoCuadroDialogo(true);
                if (mantenedor.equals(SelccionMantenedor.Producto.getSeleccion())) {
                    //adapterProducto.notifyDataSetChanged();}
                }else if (mantenedor.equals(SelccionMantenedor.TipoProducto.getSeleccion())){
                    //    public CrudMantenedorTipoProducto(String nombre, boolean variedadsabor, boolean variedadmarca, Context context, String tipoConsulta, int id , String mantenedor){
                    new CrudMantenedorTipoProducto("",false,false,contexto,"eliminar", id,mantenedor);
                    CargarBaseDeDatosTIpoProducto.eliminar(id);
                }
                else if (mantenedor.equals(SelccionMantenedor.Comuna.getSeleccion())){
                    //    public CrudMantenedorTipoProducto(String nombre, boolean variedadsabor, boolean variedadmarca, Context context, String tipoConsulta, int id , String mantenedor){
                    new CrudMantenedorComuna(id,0,0,"",contexto,"eliminar",mantenedor);
                    CargarBaseDeDatosComuna.eliminar(id);
                }else if (mantenedor.equals(SelccionMantenedor.Sabor.getSeleccion()) || mantenedor.equals(SelccionMantenedor.Marca.getSeleccion()) || mantenedor.equals(SelccionMantenedor.Provincia.getSeleccion())){
                    //new CrudMantenedorTresAtributos(contexto,"",);
                   
                    new CrudMantenedorTresAtributos(contexto,"",0,"eliminar",id,mantenedor);
                }else{
                    new CrudMantenedorDosAtibutos("",contexto,"eliminar",id,mantenedor);
                    CargarBaseDeDatosDosAtributos.eliminar(id);
                }




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


    public AlertDelete(final Context contexto, final int id,final int fk, FinalizoCuadroDialogo actividad, final String mantenedor){
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



                 if (mantenedor.equals(SelccionMantenedor.Sabor.getSeleccion()) || mantenedor.equals(SelccionMantenedor.Marca.getSeleccion()) || mantenedor.equals(SelccionMantenedor.Provincia.getSeleccion())){
                    //new CrudMantenedorTresAtributos(contexto,"",);

                    new CrudMantenedorTresAtributos(contexto,"",fk,"eliminar",id,mantenedor);
                    CargarBaseDeDatosTIpoProducto.eliminar(id);
                     interfaz.ResultadoCuadroDialogo(true);
                }




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
