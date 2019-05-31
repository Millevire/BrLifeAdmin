package com.example.esteban.brlifeadmin.AlertDialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.esteban.brlifeadmin.ConexionWebService.CargarBaseDeDatosDosAtributos;
import com.example.esteban.brlifeadmin.ConexionWebService.CrudMantenedorDosAtibutos;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.Mantenedor;
import com.example.esteban.brlifeadmin.CrudActivity;
import com.example.esteban.brlifeadmin.R;

public class AlertNuevoMantenedor {

    //metodo interfaz que comunicara Alert dialogo con actividad donde se implemente
    public interface FinalizoCuadroDialogoAgregar{
        void ResultadoCuadroDialogoAgregar(boolean val);}

   private FinalizoCuadroDialogoAgregar interfaz;

    public AlertNuevoMantenedor(final Context contexto, final FinalizoCuadroDialogoAgregar actividad, final boolean tipo, final Mantenedor tipoProducto, final String mantenedor){
        interfaz= actividad;

        //Intent para cargar actividad CrudActivity
        final Intent intent =new Intent(contexto,CrudActivity.class);


        //inflar vista para alert dialogo eliminar
        final Dialog dialogo=new Dialog(contexto);
        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogo.setCancelable(false);
        // dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogo.setContentView(R.layout.alert_nuevo_mantenedor);
        dialogo.show();


        //widget
        Button btnAceptarlarAlertNuevoTipoProduto=dialogo.findViewById(R.id.btnAceptarlarAlertNuevoTipoProduto);
        Button btnCancelarlarAlertNuevoTipoProduto=dialogo.findViewById(R.id.btnCancelarAlertNuevoTipoProduto);
        TextView tvTitutloAlertNuevoTipoProducto=dialogo.findViewById(R.id.tvTitutloAlertNuevoTipoProducto);
        final EditText etAlertNuevoTipoProoducto=dialogo.findViewById(R.id.etAlertNuevoTipoProoducto);

//validar tpo de de accion para dialogo, true=editar false=agregar nuevo registro
        if (tipo==true){
            etAlertNuevoTipoProoducto.setText(tipoProducto.getNombreTipoProducto());
            tvTitutloAlertNuevoTipoProducto.setText("Editar tipo producto");
        }


        //Accion boton Aceptar
        btnAceptarlarAlertNuevoTipoProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etAlertNuevoTipoProoducto.getText().toString().isEmpty()){
                    Toast.makeText(contexto, "El campo no puede estar vacio", Toast.LENGTH_SHORT).show();
                }else
                if (tipo==true){
                    new CrudMantenedorDosAtibutos(etAlertNuevoTipoProoducto.getText().toString(),contexto,"editar", tipoProducto.getIdTipoProducto(),mantenedor);
                    //new CargarBaseDeDatosDosAtributos(contexto);
                    interfaz.ResultadoCuadroDialogoAgregar(true);
                    CargarBaseDeDatosDosAtributos.editar(tipoProducto.getIdTipoProducto(),etAlertNuevoTipoProoducto.getText().toString());
                    dialogo.dismiss();
                   // contexto.startActivity(intent);
                }else {

                    // interfaz.ResultadoCuadroDialogoAgregar(etAlertNuevoTipoProoducto.getText().toString());
                    new CrudMantenedorDosAtibutos(etAlertNuevoTipoProoducto.getText().toString(),contexto,"nuevo", 0,mantenedor);
                   // new CargarBaseDeDatosDosAtributos(contexto);
                    actividad.ResultadoCuadroDialogoAgregar(true);

                    CargarBaseDeDatosDosAtributos.agregar(new Mantenedor(etAlertNuevoTipoProoducto.getText().toString()));

                    dialogo.dismiss();
                    //contexto.startActivity(intent);
                }



            }
        });

        //Accion boton Cancelar
        btnCancelarlarAlertNuevoTipoProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogo.dismiss();
            }
        });

    }
}
