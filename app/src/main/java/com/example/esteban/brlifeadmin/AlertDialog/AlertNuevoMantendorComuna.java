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
import com.example.esteban.brlifeadmin.ConexionWebService.CargarBaseDeDatosComuna;
import com.example.esteban.brlifeadmin.ConexionWebService.CargarBaseDeDatosDosAtributos;
import com.example.esteban.brlifeadmin.ConexionWebService.CargarBaseDeDatosMantenedorTresAtributos;
import com.example.esteban.brlifeadmin.ConexionWebService.CrudMantenedorComuna;
import com.example.esteban.brlifeadmin.ConexionWebService.CrudMantenedorTresAtributos;
import com.example.esteban.brlifeadmin.DialogKeyListener;
import com.example.esteban.brlifeadmin.R;

import java.util.ArrayList;

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
    private ArrayList<MantenedorTresAtributos> listaFiltroProvincia = new ArrayList<>();


    public AlertNuevoMantendorComuna(final Context contexto, final FinalizoCuadroDialogoAgregar actividad, final boolean tipo, final Comuna comuna, final String mantenedor) {
        interfaz = actividad;

        //inflar vista para alert dialogo eliminar
        final Dialog dialogo=new Dialog(contexto);
        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogo.setCancelable(false);
        //dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogo.setContentView(R.layout.alert_nuevo_mantenedor_comuna);
        dialogo.show();

        //Detectar boton Back de celular
        DialogKeyListener dkl=new DialogKeyListener();
        dialogo.setOnKeyListener(dkl);

        //Declaracion de widget
        final EditText etAlertNuevoMantenedorTresAtributos=dialogo.findViewById(R.id.etAlertNuevoMantenedorComuna);
        final Spinner spAlertMantenedorProvincia = dialogo.findViewById(R.id.spAlertMantenedorProvincia);
        final Spinner spAlertMantenedorRegion = dialogo.findViewById(R.id.spAlertMantenedorRegion);
        Button btnCancelarAlertNuevoMantenedorComuna = dialogo.findViewById(R.id.btnCancelarAlertNuevoMantenedorComuna);
        Button btnAceptarlarAlertNuevaMantenedorComuna = dialogo.findViewById(R.id.btnAceptarlarAlertNuevoMantenedorComuna);
        final TextView tvTituloAlertNuevoMantenedorComuna = dialogo.findViewById(R.id.tvTituloAlertNuevoMantenedorComuna);
        TextView tvTipoMantenedorSpinnerRegion = dialogo.findViewById(R.id.tvTipoRegion);
        TextView tvTipoMantenedorSpinnerProvincia = dialogo.findViewById(R.id.tvTipoMantenedorProvincia);

        tvTipoMantenedorSpinnerRegion.setText("Region:");
        tvTipoMantenedorSpinnerProvincia.setText("Provincia:");
        adapterRegion=new SpinAdapter(contexto,android.R.layout.simple_list_item_1, CargarBaseDeDatosDosAtributos.getListaMantenedors());
        adapterProvincia =new SpinAdapterTresAtributos(contexto,android.R.layout.simple_list_item_1, CargarBaseDeDatosMantenedorTresAtributos.getListaMantenedorTresAtributos());
        spAlertMantenedorProvincia.setAdapter(adapterProvincia);
        spAlertMantenedorRegion.setAdapter(adapterRegion);
        tvTituloAlertNuevoMantenedorComuna.setText("Nueva Comuna:");



        spAlertMantenedorRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //enviar posicion
                listaFiltroProvincia.clear();
                posicionregion(position);
                //Filtrar
                int idregion = CargarBaseDeDatosDosAtributos.getListaMantenedors().get(posicionregion).getIdTipoProducto();
                listaFiltroProvincia = CargarBaseDeDatosMantenedorTresAtributos.filtroSabor(idregion);
                adapterProvincia = new SpinAdapterTresAtributos(contexto,android.R.layout.simple_list_item_1,listaFiltroProvincia);
                spAlertMantenedorProvincia.setAdapter(adapterProvincia);

                //Si es verdadero se edita
                if (tipo){
                    etAlertNuevoMantenedorTresAtributos.setText(comuna.getNombreComuna());
                    int posi = (int) adapterRegion.getItemId(comuna.getIdRegion());
                    int posiprovi = (int) adapterProvincia.getItemId(comuna.getIdProvincia());
                    spAlertMantenedorRegion.setSelection(posi);
                    spAlertMantenedorProvincia.setSelection(posiprovi);
                }

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

        //Si es verdadero se edita
        if (tipo){
            etAlertNuevoMantenedorTresAtributos.setText(comuna.getNombreComuna());
            int posi = (int) adapterRegion.getItemId(comuna.getIdRegion());
            int posiprovi = (int) adapterProvincia.getItemId(comuna.getIdProvincia());
            spAlertMantenedorRegion.setSelection(posi);
            spAlertMantenedorProvincia.setSelection(posiprovi);
        }

        btnAceptarlarAlertNuevaMantenedorComuna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etAlertNuevoMantenedorTresAtributos.getText().toString().isEmpty()){
                    Toast.makeText(contexto, "El campo no puede estar vacio", Toast.LENGTH_SHORT).show();
                }else


                    //En el caso de editar
                    if (tipo==true){
                        new CrudMantenedorComuna(
                                comuna.getIdComuna(),
                                CargarBaseDeDatosMantenedorTresAtributos.getListaMantenedorTresAtributos().get(posicionprovincia).getIdMantenedorTresAtributos(),
                                CargarBaseDeDatosDosAtributos.getListaMantenedors().get(posicionregion).getIdTipoProducto(),
                                etAlertNuevoMantenedorTresAtributos.getText().toString(),
                                contexto,"editar",
                                mantenedor);
                        //new CargarBaseDeDatosDosAtributos(contexto);
                        CargarBaseDeDatosComuna.editar(comuna.getIdComuna(),
                                etAlertNuevoMantenedorTresAtributos.getText().toString(),
                                CargarBaseDeDatosDosAtributos.getListaMantenedors().get(posicionregion).getIdTipoProducto(),
                                CargarBaseDeDatosMantenedorTresAtributos.getListaMantenedorTresAtributos().get(posicionprovincia).getIdMantenedorTresAtributos()
                                );
                        actividad.ResultadoCuadroDialogoAgregarComuna(true);
                        dialogo.dismiss();
                        // contexto.startActivity(intent);
                    }else {

                        // interfaz.ResultadoCuadroDialogoAgregar(etAlertNuevoTipoProoducto.getText().toString());
                        new CrudMantenedorComuna(0,
                                CargarBaseDeDatosMantenedorTresAtributos.getListaMantenedorTresAtributos().get(posicionprovincia).getIdMantenedorTresAtributos(),
                                CargarBaseDeDatosDosAtributos.getListaMantenedors().get(posicionregion).getIdTipoProducto(),
                                etAlertNuevoMantenedorTresAtributos.getText().toString(),
                                contexto,"nuevo",
                                mantenedor);

                        // new CargarBaseDeDatosDosAtributos(contexto);

                        CargarBaseDeDatosComuna.agregar(new Comuna(0,
                                etAlertNuevoMantenedorTresAtributos.getText().toString(),
                                CargarBaseDeDatosMantenedorTresAtributos.getListaMantenedorTresAtributos().get(posicionprovincia).getIdMantenedorTresAtributos(),
                                CargarBaseDeDatosDosAtributos.getListaMantenedors().get(posicionregion).getIdTipoProducto()));
                        actividad.ResultadoCuadroDialogoAgregarComuna(true);
                        dialogo.dismiss();
                        //contexto.startActivity(intent);
                    }
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
