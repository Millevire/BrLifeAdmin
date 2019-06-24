package com.example.esteban.brlifeadmin;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import com.android.volley.RequestQueue;
import com.example.esteban.brlifeadmin.Adapter.AdaptaderTipoProducto;
import com.example.esteban.brlifeadmin.Adapter.AdapterComuna;
import com.example.esteban.brlifeadmin.Adapter.AdapterMantenedorDosAtributos;
import com.example.esteban.brlifeadmin.Adapter.AdapterMantenedorTresAtributos;
import com.example.esteban.brlifeadmin.Adapter.AdapterProducto;
import com.example.esteban.brlifeadmin.AlertDialog.AlertNuevoMantendorComuna;
import com.example.esteban.brlifeadmin.AlertDialog.AlertNuevoMantenedorDosAtributos;
import com.example.esteban.brlifeadmin.AlertDialog.AlertNuevoMantenedorTipoProducto;
import com.example.esteban.brlifeadmin.AlertDialog.AlertNuevoMantenedorTresAtributos;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.Producto;
import com.example.esteban.brlifeadmin.ConexionesWebServiceNuevo.CargarMantendorComunaHttpConecction;
import com.example.esteban.brlifeadmin.ConexionesWebServiceNuevo.CargarMantenedorDosAtributosHttpConecction;
import com.example.esteban.brlifeadmin.ConexionesWebServiceNuevo.CargarMantenedorProductoHttpConecction;
import com.example.esteban.brlifeadmin.ConexionesWebServiceNuevo.CargarMantenedorProductoNutrienteHttpConecction;
import com.example.esteban.brlifeadmin.ConexionesWebServiceNuevo.CargarMantenedorTipoProductoHttpConecction;
import com.example.esteban.brlifeadmin.ConexionesWebServiceNuevo.CargarMantenedorTresAtributosHttpConecction;
import com.example.esteban.brlifeadmin.ConexionesWebServiceNuevo.CargarNuevoIdHttpConecction;
import com.example.esteban.brlifeadmin.Enum.SelccionMantenedor;

import org.json.JSONException;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class CrudActivity extends AppCompatActivity implements  AlertNuevoMantenedorDosAtributos.FinalizoCuadroDialogoAgregar,AlertNuevoMantenedorTresAtributos.FinalizoCuadroDialogoAgregarTrestAtributos, AlertNuevoMantenedorTipoProducto.FinalizoCuadroDialogoAgregar, AlertNuevoMantendorComuna.FinalizoCuadroDialogoAgregar {
 private EditText etBuscar;
 private Button btnBuscar,btnBack;
 private TextView tvTitulo;
 private ListView lvLista;

 private AdapterMantenedorDosAtributos adapterMantenedorDosAtributos;
 private AdapterMantenedorTresAtributos adapterMantenedorTresAtributos;
 private AdaptaderTipoProducto adaptaderTipoProducto;
 private AdapterComuna adaptaderComuna;
 private AdapterProducto adapterProducto;
 private  String mantenedor;


    /**
     * AL momento de agregar un nuevo mantenedor especifico hay que agregarlo en el metodo oncreate
     *En el Oncreate se debe colocar el especifico por el switch y el metodo CargaBasedeDatos correcto y necesarios
     * Luego en el metodo llenr se le pasa el adapter especifico con los datos a lvLista
     * Luego se necesita implementar en la clase el AlertNuevoMantenedorXX.FinalizaCuadroDialogoAgregar para pasar al
     *adapter especifico hacer un refresh con notifyDataSetChanged
     * Por ultimo en el metodo onResume se necesita con el adaptador especifico refrescar con notifyDataSetChanged
     * Para su carga completa
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);


        //Para que funcione el HttpConexion
        StrictMode.ThreadPolicy policy = new
        StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        //Widget
        lvLista=(ListView)findViewById(R.id.lvLista);
        tvTitulo=(TextView)findViewById(R.id.tvTitulo);
        btnBack=(Button)findViewById(R.id.btnBack);


       //intente para actividad nuevo producto
        final Intent intent =new Intent(this,NuevoProductoActivity.class);
        //Bundle para saber de donde estan llamando actividad
        final Bundle bundle=getIntent().getExtras();



        //Limpiar listas

        lvLista.setAdapter(null);

        //Verificar estado de bundle
        if (bundle !=null){

            //obtener informacion de bundle
        mantenedor=bundle.getString("mantenedor");
        SelccionMantenedor selccionMantenedor  = SelccionMantenedor.valueOf(mantenedor);
            switch (selccionMantenedor){
                case TipoProducto:

                   tvTitulo.setText(SelccionMantenedor.TipoProducto.getSeleccion().toString());
                    break;

                case Region:
                    tvTitulo.setText(SelccionMantenedor.Region.getSeleccion().toString());

                    break;
                case Rol:

                    tvTitulo.setText(SelccionMantenedor.Rol.getSeleccion().toString());

                    break;
                case Interes:

                    tvTitulo.setText(SelccionMantenedor.Interes.getSeleccion().toString());

                    break;
                case HorarioComida:
                    tvTitulo.setText(SelccionMantenedor.HorarioComida.getSeleccion().toString());

                    break;
                case Producto:
                    tvTitulo.setText(SelccionMantenedor.Producto.getSeleccion().toString());

                    break;
                case Sabor:

                    //Cargar titulo dependiento del nombre del mantenedor
                    tvTitulo.setText(SelccionMantenedor.Sabor.getSeleccion().toString());

                    //Traer datos de web service para llenar lista;

                    //Traer datos de tipo producto para llenar Spinner

                    try {
                        CargarMantenedorTipoProductoHttpConecction.buscarMantenedorTipoProducto(this,SelccionMantenedor.TipoProducto.getSeleccion());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    break;
                case Marca:

                    //Cargar titulo dependiento del nombre del mantenedor
                    tvTitulo.setText(SelccionMantenedor.Marca.getSeleccion().toString());

                    //Traer datos de web service para llenar lista;

                   //Traer datos de tipo producto para llenar Spinner
                    try {
                        CargarMantenedorTipoProductoHttpConecction.buscarMantenedorTipoProducto(this,SelccionMantenedor.TipoProducto.getSeleccion());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case Nutriente:
                    tvTitulo.setText(SelccionMantenedor.Nutriente.getSeleccion().toString());

                    break;
                case TipoPersona:
                    //CargarBaseDeDatosDosAtributosNuevo.limpiarlista();
                    tvTitulo.setText(SelccionMantenedor.TipoPersona.getSeleccion().toString());

                    break;
                case Objetivo:
                    //CargarBaseDeDatosDosAtributosNuevo.limpiarlista();
                    tvTitulo.setText(SelccionMantenedor.Objetivo.getSeleccion().toString());

                    break;
                case Provincia:

                    tvTitulo.setText(SelccionMantenedor.Provincia.getSeleccion().toString());

                    //Cargar base de datos para Spinner de AlertNuevoMantenedorTresAtributos con Region
                    try {
                        CargarMantenedorDosAtributosHttpConecction.buscarMantenedorDosAtributos(this,SelccionMantenedor.Region.getSeleccion());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case Comuna:

                    tvTitulo.setText(SelccionMantenedor.Comuna.getSeleccion().toString());

                    //Para Cargar la provincia
                    try {
                        CargarMantenedorTresAtributosHttpConecction.buscarMantenedorTresAtributos(this,SelccionMantenedor.Provincia.getSeleccion());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    //Cargar base de datos para Spinner de AlertNuevoMantenedorTresAtributos con Region
                    try {
                        CargarMantenedorDosAtributosHttpConecction.buscarMantenedorDosAtributos(this,SelccionMantenedor.Region.getSeleccion());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    break;
                default:
                    break;
            }




        }






        lvLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                return false;
            }
        });

    btnBack.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    });


        try {
            llenr();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.nuevoNutriente);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SelccionMantenedor selccionMantenedor  = SelccionMantenedor.valueOf(mantenedor);
                switch (selccionMantenedor){

                    //Enviar a actividad para agregar Producto
                    case Producto:
                        try {
                            CargarNuevoIdHttpConecction.buscarMantenedorNuevoId(CrudActivity.this,SelccionMantenedor.Producto.getSeleccion());
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        intent.putExtra("accion","agregar");
                        startActivity(intent);
                        break;

                    //Abrir Alert dialogo para agregar Provincia
                    case Provincia:
                        new AlertNuevoMantenedorTresAtributos(CrudActivity.this,CrudActivity.this,false,null,SelccionMantenedor.Provincia.getSeleccion());
                        break;

                    //Abrir Alert dialogo para agregar Sabor
                    case Sabor:
                        new AlertNuevoMantenedorTresAtributos(CrudActivity.this,CrudActivity.this,false,null,SelccionMantenedor.Sabor.getSeleccion());
                        break;

                        //Abrir Alert dialogo para agregar Comuna
                    case Comuna:
                        new AlertNuevoMantendorComuna(CrudActivity.this,CrudActivity.this,false,null,SelccionMantenedor.Comuna.getSeleccion());
                        break;

                    //Abrir Alert dialogo para agregar Marca
                    case Marca:
                        new AlertNuevoMantenedorTresAtributos(CrudActivity.this,CrudActivity.this,false,null,SelccionMantenedor.Marca.getSeleccion());
                        break;
                    case TipoProducto:
                        new AlertNuevoMantenedorTipoProducto(CrudActivity.this,  CrudActivity.this,false,null,mantenedor);
                        break;

                    //Abrir Alert dialogo para agregar mantenedor de dos atributos
                        default:
                            new AlertNuevoMantenedorDosAtributos(CrudActivity.this,CrudActivity.this,false,null,mantenedor);
                            break;
                }

            }
        });
    }

    public void llenr() throws IOException, JSONException {


       if (mantenedor.equals(SelccionMantenedor.Producto.getSeleccion())) {
           adapterProducto = new AdapterProducto(this, CargarMantenedorProductoHttpConecction.getListaProducto(), mantenedor);
           lvLista.setAdapter(adapterProducto);
       }else if (mantenedor.equals(SelccionMantenedor.TipoProducto.getSeleccion())){
           adaptaderTipoProducto =new AdaptaderTipoProducto(this, CargarMantenedorTipoProductoHttpConecction.buscarMantenedorTipoProducto(this,mantenedor),mantenedor);
           lvLista.setAdapter(adaptaderTipoProducto);
       }
       else if (mantenedor.equals(SelccionMantenedor.Comuna.getSeleccion())){
           adaptaderComuna =new AdapterComuna(this, CargarMantendorComunaHttpConecction.buscarMantenedorComuna(this,mantenedor),mantenedor);
               lvLista.setAdapter(adaptaderComuna);
        }else if (mantenedor.equals(SelccionMantenedor.Sabor.getSeleccion()) || mantenedor.equals(SelccionMantenedor.Marca.getSeleccion()) || mantenedor.equals(SelccionMantenedor.Provincia.getSeleccion())){
//           adapterMantenedorTresAtributos =new AdapterMantenedorTresAtributos(this,CargarBaseDeDatosMantenedorTresAtributos.getListaMantenedorTresAtributos(),mantenedor);
           adapterMantenedorTresAtributos =new AdapterMantenedorTresAtributos(this, CargarMantenedorTresAtributosHttpConecction.buscarMantenedorTresAtributos(this,mantenedor),mantenedor);
           lvLista.setAdapter(adapterMantenedorTresAtributos);
       }else{

           adapterMantenedorDosAtributos =new AdapterMantenedorDosAtributos(this, CargarMantenedorDosAtributosHttpConecction.buscarMantenedorDosAtributos(this,mantenedor),mantenedor);
           lvLista.setAdapter(adapterMantenedorDosAtributos);
       }


    }




    @Override
    protected void onResume() {
        super.onResume();
        if (mantenedor.equals(SelccionMantenedor.Producto.getSeleccion())) {
            adapterProducto.notifyDataSetChanged();
        }else if (mantenedor.equals(SelccionMantenedor.TipoProducto.getSeleccion())){
            adaptaderTipoProducto.notifyDataSetChanged();
        }else if (mantenedor.equals(SelccionMantenedor.Comuna.getSeleccion())){
            adaptaderComuna.notifyDataSetChanged();
        }else if (mantenedor.equals(SelccionMantenedor.Sabor.getSeleccion()) || mantenedor.equals(SelccionMantenedor.Marca.getSeleccion()) || mantenedor.equals(SelccionMantenedor.Provincia.getSeleccion())){
            adapterMantenedorTresAtributos.notifyDataSetChanged();
        }else adapterMantenedorDosAtributos.notifyDataSetChanged();


    }

    @Override
    public void ResultadoCuadroDialogoAgregar(boolean val) {
        adapterMantenedorDosAtributos.notifyDataSetChanged();
    }


    @Override
    public void ResultadoCuadroDialogoAgregarTresAtributos(boolean val) {
      adapterMantenedorTresAtributos.notifyDataSetChanged();
    }

    @Override
    public void ResultadoCuadroDialogoAgregarTipoProducto(boolean hasCapture) {
        adaptaderTipoProducto.notifyDataSetChanged();
    }

    @Override
    public void ResultadoCuadroDialogoAgregarComuna(boolean val) {
        adaptaderComuna.notifyDataSetChanged();
    }

}
