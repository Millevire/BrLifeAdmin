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

import com.example.esteban.brlifeadmin.Clases.Mantenedor.Mantenedor;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.MantenedorTresAtributos;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.TipoProducto;
import com.example.esteban.brlifeadmin.ConexionWebService.CargarBaseDeDatosDosAtributos;
import com.example.esteban.brlifeadmin.ConexionWebService.CargarBaseDeDatosMantenedorTresAtributos;
import com.example.esteban.brlifeadmin.ConexionWebService.CargarBaseDeDatosTIpoProducto;
import com.example.esteban.brlifeadmin.ConexionWebService.CrudMantenedorTresAtributos;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.DialogKeyListener;
import com.example.esteban.brlifeadmin.Enum.SelccionMantenedor;
import com.example.esteban.brlifeadmin.R;

public class AlertNuevoMantenedorTresAtributos {
    ArrayAdapter <Mantenedor>adapterRegion;
    ArrayAdapter <TipoProducto>adapterTipoProducto;
    int posicion;



    //metodo interfaz que comunicara Alert dialogo con actividad donde se implemente
    public interface FinalizoCuadroDialogoAgregarTrestAtributos{
        void ResultadoCuadroDialogoAgregarTresAtributos(boolean val);
    }

    //interfaz de comunicacion
    private FinalizoCuadroDialogoAgregarTrestAtributos interfaz;

    public AlertNuevoMantenedorTresAtributos(final Context contexto, final FinalizoCuadroDialogoAgregarTrestAtributos actividad, final boolean tipo, final MantenedorTresAtributos mantenedorTresAtributos, final String tipoMantenedor){



        //Recibir una interfaz desde donde es llamado para aplicar metodos de comunicacion
        interfaz=actividad;

        //inflar vista para alert dialogo eliminar
        final Dialog dialogo=new Dialog(contexto);
        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogo.setCancelable(false);
        //dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogo.setContentView(R.layout.alert_nuevo_mantenedor_tres_atributos);
        dialogo.show();

        //Detectar boton Back de celular
        DialogKeyListener dkl=new DialogKeyListener();
        dialogo.setOnKeyListener(dkl);

        //Declaracion de widget
        final EditText etAlertNuevoMantenedorTresAtributos=dialogo.findViewById(R.id.etAlertNuevoMantenedorTresAtributos);
        final Spinner spAlertMantenedorTresAtributos=dialogo.findViewById(R.id.spAlertProductoNutriente);
        Button btnCancelarAlertNuevoMantenedorTresAtributos=dialogo.findViewById(R.id.btnCancelarAlertNuevoMantenedorTresAtributos);
        Button btnAceptarlarAlertNuevaMantenedorTresAtributos=dialogo.findViewById(R.id.btnAceptarlarAlertNuevaMantenedorTresAtributos);
        final TextView tvTituloAlertNuevoMantenedorTresAtributo=dialogo.findViewById(R.id.tvTituloAlertNuevoMantenedorTresAtributo);
        TextView tvTipoMantenedorSpinner=dialogo.findViewById(R.id.tvTipoMantenedorSpinner);





        //Region llenar Spinner con informacion dependiendo de mantenedor
        if (tipoMantenedor.equals(SelccionMantenedor.Provincia.getSeleccion())){
            tvTituloAlertNuevoMantenedorTresAtributo.setText(tipoMantenedor);
            tvTipoMantenedorSpinner.setText("Region:");
            adapterRegion=new ArrayAdapter(contexto,android.R.layout.simple_list_item_1, CargarBaseDeDatosDosAtributos.getListaMantenedors());
            spAlertMantenedorTresAtributos.setAdapter(adapterRegion);

        }else {

            //Cambiar titulo de Spinner
            tvTituloAlertNuevoMantenedorTresAtributo.setText(tipoMantenedor);
            tvTipoMantenedorSpinner.setText("Tipo de Producto:");
                adapterTipoProducto=new ArrayAdapter(contexto,android.R.layout.simple_list_item_1, CargarBaseDeDatosTIpoProducto.getListaTipoProducto());
                spAlertMantenedorTresAtributos.setAdapter(adapterTipoProducto);


        }

        spAlertMantenedorTresAtributos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              //enviar posicion
               posicion(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Accion de editar
        if (tipo){
            etAlertNuevoMantenedorTresAtributos.setText(mantenedorTresAtributos.getNombreMantenedorTresAtributos());

            if (tipoMantenedor.equals(SelccionMantenedor.Marca.getSeleccion())|| tipoMantenedor.equals(SelccionMantenedor.Sabor.getSeleccion())){
            spAlertMantenedorTresAtributos.setVisibility(View.INVISIBLE);
            tvTipoMantenedorSpinner.setVisibility(View.INVISIBLE);
            }else

            btnAceptarlarAlertNuevaMantenedorTresAtributos.setText("actualizar");
        }


        btnAceptarlarAlertNuevaMantenedorTresAtributos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tipo){
                         //En el caso editar
                    if (tipoMantenedor.equals(SelccionMantenedor.Provincia.getSeleccion())){
                        new CrudMantenedorTresAtributos(contexto,etAlertNuevoMantenedorTresAtributos.getText().toString(), CargarBaseDeDatosDosAtributos.getListaMantenedors().get(posicion).getIdTipoProducto(),"editar",mantenedorTresAtributos.getIdMantenedorTresAtributos(),tipoMantenedor);
                        CargarBaseDeDatosMantenedorTresAtributos.editar(mantenedorTresAtributos.getIdMantenedorTresAtributos(),CargarBaseDeDatosDosAtributos.getListaMantenedors().get(posicion).getIdTipoProducto(),etAlertNuevoMantenedorTresAtributos.getText().toString());
                        actividad.ResultadoCuadroDialogoAgregarTresAtributos(true);
                    }else{
                        new CrudMantenedorTresAtributos(contexto,etAlertNuevoMantenedorTresAtributos.getText().toString(),mantenedorTresAtributos.getFkMantenedorTresAtributos(),"editar",mantenedorTresAtributos.getIdMantenedorTresAtributos(),tipoMantenedor);
                        CargarBaseDeDatosMantenedorTresAtributos.editar(mantenedorTresAtributos.getIdMantenedorTresAtributos(),mantenedorTresAtributos.getFkMantenedorTresAtributos(),etAlertNuevoMantenedorTresAtributos.getText().toString());
                        actividad.ResultadoCuadroDialogoAgregarTresAtributos(true);


                    }

                       dialogo.dismiss();
                }else{


                    //En e caso de agregar
                    if (tipoMantenedor.equals(SelccionMantenedor.Provincia.getSeleccion())){

                        new CrudMantenedorTresAtributos(contexto,etAlertNuevoMantenedorTresAtributos.getText().toString(),CargarBaseDeDatosDosAtributos.getListaMantenedors().get(posicion).getIdTipoProducto(),"nuevo",0,tipoMantenedor);
                        actividad.ResultadoCuadroDialogoAgregarTresAtributos(true);
                        CargarBaseDeDatosMantenedorTresAtributos.agregar(new MantenedorTresAtributos(0,CargarBaseDeDatosDosAtributos.getListaMantenedors().get(posicion).getIdTipoProducto(),etAlertNuevoMantenedorTresAtributos.getText().toString()));

                        dialogo.dismiss();

                    }else{

                        new CrudMantenedorTresAtributos(contexto,etAlertNuevoMantenedorTresAtributos.getText().toString(),CargarBaseDeDatosTIpoProducto.getListaTipoProducto().get(posicion).getIdTipoProducto(),"nuevo",0,tipoMantenedor);
                        actividad.ResultadoCuadroDialogoAgregarTresAtributos(true);
                        CargarBaseDeDatosMantenedorTresAtributos.agregar(new MantenedorTresAtributos(CrudMantenedorTresAtributos.idNuevoMantenedorTresAtributos,CargarBaseDeDatosTIpoProducto.getListaTipoProducto().get(posicion).getIdTipoProducto(),etAlertNuevoMantenedorTresAtributos.getText().toString()));


                        dialogo.dismiss();
                    }
                }





                }

        });






      //Cerrrar Alert dialogo
        btnCancelarAlertNuevoMantenedorTresAtributos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogo.dismiss();
            }
        });



    }

    /** /
     * metodo para obtener osicion de un objeto en un spinner
     * @param posicion parametro de posicion
     */

    public void posicion(int posicion){

        this.posicion=posicion;
    }



}
