package com.example.esteban.brlifeadmin.AlertDialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.esteban.brlifeadmin.Clases.Mantenedor.TipoProducto;
import com.example.esteban.brlifeadmin.ConexionWebService.CargarBaseDeDatosTIpoProducto;
import com.example.esteban.brlifeadmin.ConexionWebService.CrudMantenedorTipoProducto;
import com.example.esteban.brlifeadmin.CrudActivity;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.DialogKeyListener;
import com.example.esteban.brlifeadmin.R;

public class AlertNuevoMantenedorTipoProducto {


    //metodo interfaz que comunicara Alert dialogo con actividad donde se implemente
    public interface FinalizoCuadroDialogoAgregar{
        void ResultadoCuadroDialogoAgregarTipoProducto(boolean val);
    }

    private FinalizoCuadroDialogoAgregar interfaz;

    //Metodo para Tipo Producto
    public AlertNuevoMantenedorTipoProducto(final Context contexto, final FinalizoCuadroDialogoAgregar actividad, final boolean tipo, final TipoProducto tipoProducto, final String mantenedor) {
        interfaz= actividad;

        //Intent para cargar actividad CrudActivity
        final Intent intent =new Intent(contexto, CrudActivity.class);

        //inflar vista para alert dialogo eliminar
        final Dialog dialogo=new Dialog(contexto);
        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogo.setCancelable(false);
        // dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogo.setContentView(R.layout.alert_nuevo_mantenedor_tipoproducto);
        dialogo.show();

        //Detectar boton Back de celular
        DialogKeyListener dkl=new DialogKeyListener();
        dialogo.setOnKeyListener(dkl);

        //widget
        //Se declaran y referencian los botones y elementos del layout
        Button btnAceptarAlertTipoProducto=dialogo.findViewById(R.id.btnAceptarAlertProductoNutriente);
        Button btnCancelarAlertTipoProducto=dialogo.findViewById(R.id.btnCancelarAlertProductoNutriente);
        TextView tvTitutloAlertTipoProducto=dialogo.findViewById(R.id.tvTitutloAlertProductoNutriente);
        final Switch switchsabor = dialogo.findViewById(R.id.switchSabor);
        final Switch switchmarca = dialogo.findViewById(R.id.switchMarca);
        final EditText etAlertTipoProducto=dialogo.findViewById(R.id.etAlertMantenedorTipoProducto);

        tvTitutloAlertTipoProducto.setText("Nuevo "+mantenedor);

        //validar tpo de de accion para dialogo, true=editar false=agregar nuevo registro
        if (tipo==true){
            etAlertTipoProducto.setText(tipoProducto.getNombreTipoProducto());
            switchmarca.setChecked(tipoProducto.isVariedadMarca());
            switchsabor.setChecked(tipoProducto.isVaridadSabor());
            tvTitutloAlertTipoProducto.setText("Editar");
        }else{
            switchmarca.setChecked(false);
            switchsabor.setChecked(false);
        }


        //Accion boton Aceptar
        btnAceptarAlertTipoProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (etAlertTipoProducto.getText().toString().isEmpty()){
                    Toast.makeText(contexto, "El campo no puede estar vacio", Toast.LENGTH_SHORT).show();
                }else
                    //En el caso de editar
                    if (tipo==true){
                        //Metodo necesita el nombre, booleanos de variedad sabor, booleanos de variedad marca, contecto, actividad y mantenedor
                        new CrudMantenedorTipoProducto(etAlertTipoProducto.getText().toString(), switchsabor.isChecked(), switchmarca.isChecked(), contexto,"editar", tipoProducto.getIdTipoProducto(),mantenedor);
                        //new CargarBaseDeDatosDosAtributos(contexto);
                        interfaz.ResultadoCuadroDialogoAgregarTipoProducto(true);
                        //Se pasan todos los parametros al momento de editar para actualizarlo en la lista estatica y verse reflejada en la vista
                        CargarBaseDeDatosTIpoProducto.editar(tipoProducto.getIdTipoProducto(),etAlertTipoProducto.getText().toString(), switchsabor.isChecked(), switchmarca.isChecked());
                        dialogo.dismiss();
                        // contexto.startActivity(intent);
                    }else {
                        //En el caso de Agregar
                        new CrudMantenedorTipoProducto(etAlertTipoProducto.getText().toString(),(boolean) switchsabor.isChecked(),(boolean) switchmarca.isChecked(), contexto,"nuevo", 0,mantenedor);
                        // new CargarBaseDeDatosDosAtributos(contexto);
                        actividad.ResultadoCuadroDialogoAgregarTipoProducto(true);

                        CargarBaseDeDatosTIpoProducto.agregar(new TipoProducto(etAlertTipoProducto.getText().toString(), switchsabor.isChecked(), switchmarca.isChecked()));

                        dialogo.dismiss();
                        //contexto.startActivity(intent);
                    }



            }
        });

        //Accion boton Cancelar
        btnCancelarAlertTipoProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogo.dismiss();
            }
        });
    }
}
