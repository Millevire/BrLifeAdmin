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

import com.example.esteban.brlifeadmin.ConexionWebService.CrudMantenedorDosAtibutos;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.Mantenedor;
import com.example.esteban.brlifeadmin.ConexionesWebServiceNuevo.CargarMantenedorDosAtributosHttpConecction;
import com.example.esteban.brlifeadmin.CrudActivity;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.DialogKeyListener;
import com.example.esteban.brlifeadmin.R;

public class AlertNuevoMantenedorDosAtributos {


    //metodo interfaz que comunicara Alert dialogo con actividad donde se implemente
    public interface FinalizoCuadroDialogoAgregar{
        void ResultadoCuadroDialogoAgregar(boolean val);}

    private FinalizoCuadroDialogoAgregar prueba;


    public AlertNuevoMantenedorDosAtributos(final Context contexto, final FinalizoCuadroDialogoAgregar actividad, final boolean tipo, final Mantenedor tipoProducto, final String mantenedor){
        prueba= actividad;

        //Intent para cargar actividad CrudActivity
        final Intent intent =new Intent(contexto,CrudActivity.class);


        //inflar vista para alert dialogo eliminar
        final Dialog dialogo=new Dialog(contexto);
        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogo.setCancelable(false);
        // dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogo.setContentView(R.layout.alert_nuevo_mantenedor_dos_atributos);
        dialogo.show();


        //Detectar boton Back de celular
        DialogKeyListener dkl=new DialogKeyListener();
        dialogo.setOnKeyListener(dkl);

        //Declaracion widget
        Button btnAceptarlarAlertNuevoMantenedorDosAtributos=dialogo.findViewById(R.id.btnAceptarAlertNuevoMantenedorDosAtributos);
        Button btnCancelarAlertNuevoMantenedorDosAtributos=dialogo.findViewById(R.id.btnCancelarAlertNuevoMantenedorDosAtributos);
        TextView tvTitutloAlertNuevoMantenedorDosAtributos=dialogo.findViewById(R.id.tvTitutloAlertNuevoMantenedorDosAtributos);
        final EditText etAlertNuevoMantenedorDosAtributos=dialogo.findViewById(R.id.etAlertNuevoMantenedorDosAtributos);


        //Titulo de Alert dialogo
        tvTitutloAlertNuevoMantenedorDosAtributos.setText("Nuevo "+mantenedor);

        //Hint dependiendo de nombre de mantenedor
        etAlertNuevoMantenedorDosAtributos.setHint("Ingrese nuevo "+mantenedor);

        //validar tpo de de accion para dialogo, true=editar false=agregar nuevo registro
        if (tipo==true){
            etAlertNuevoMantenedorDosAtributos.setText(tipoProducto.getNombreTipoProducto());
            tvTitutloAlertNuevoMantenedorDosAtributos.setText("Editar tipo producto");
        }


        //Accion boton Aceptar
        btnAceptarlarAlertNuevoMantenedorDosAtributos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etAlertNuevoMantenedorDosAtributos.getText().toString().isEmpty()){
                    Toast.makeText(contexto, "El campo no puede estar vacio", Toast.LENGTH_SHORT).show();
                }else
                    //En el caso de editar
                    if (tipo==true){
                        new CrudMantenedorDosAtibutos(etAlertNuevoMantenedorDosAtributos.getText().toString(),contexto,"editar", tipoProducto.getIdTipoProducto(),mantenedor);
                        //new CargarBaseDeDatosDosAtributos(contexto);
                        actividad.ResultadoCuadroDialogoAgregar(true);
                        CargarMantenedorDosAtributosHttpConecction.editar(tipoProducto.getIdTipoProducto(),etAlertNuevoMantenedorDosAtributos.getText().toString());
                        dialogo.dismiss();
                        // contexto.startActivity(intent);
                    }else {

                        // interfaz.ResultadoCuadroDialogoAgregar(etAlertNuevoTipoProoducto.getText().toString());
                        new CrudMantenedorDosAtibutos(etAlertNuevoMantenedorDosAtributos.getText().toString(),contexto,"nuevo", 0,mantenedor);
                        // new CargarBaseDeDatosDosAtributos(contexto);
                        actividad.ResultadoCuadroDialogoAgregar(true);

                        CargarMantenedorDosAtributosHttpConecction.agregar(new Mantenedor(etAlertNuevoMantenedorDosAtributos.getText().toString()));

                        dialogo.dismiss();
                        //contexto.startActivity(intent);
                    }



            }
        });

        //Accion boton Cancelar
        btnCancelarAlertNuevoMantenedorDosAtributos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogo.dismiss();
            }
        });

    }


    public void alertNuevoMantennedor(){




    }
}
