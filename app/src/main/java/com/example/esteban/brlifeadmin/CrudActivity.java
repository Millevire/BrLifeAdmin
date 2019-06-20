package com.example.esteban.brlifeadmin;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


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
import com.example.esteban.brlifeadmin.ConexionWebService.CargarBaseDeDatosComuna;
import com.example.esteban.brlifeadmin.ConexionWebService.CargarBaseDeDatosDosAtributos;
import com.example.esteban.brlifeadmin.ConexionWebService.CargarBaseDeDatosMantenedorTresAtributos;
import com.example.esteban.brlifeadmin.ConexionWebService.CargarBaseDeDatosNuevoId;
import com.example.esteban.brlifeadmin.ConexionWebService.CargarBaseDeDatosProducto;
import com.example.esteban.brlifeadmin.ConexionWebService.CargarBaseDeDatosProductoNutriente;
import com.example.esteban.brlifeadmin.ConexionWebService.CargarBaseDeDatosTIpoProducto;
import com.example.esteban.brlifeadmin.Enum.SelccionMantenedor;

import java.io.Serializable;

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
        CargarBaseDeDatosTIpoProducto.getListaTipoProducto().clear();
        CargarBaseDeDatosDosAtributos.getListaMantenedors().clear();
        CargarBaseDeDatosTIpoProducto.getListaTipoProducto().clear();
        CargarBaseDeDatosProducto.getListaProducto().clear();
        CargarBaseDeDatosComuna.getListaComuna().clear();


        //Verificar estado de bundle
        if (bundle !=null){

            //obtener informacion de bundle
        mantenedor=bundle.getString("mantenedor");
        SelccionMantenedor selccionMantenedor  = SelccionMantenedor.valueOf(mantenedor);
            switch (selccionMantenedor){
                case TipoProducto:

                   tvTitulo.setText(SelccionMantenedor.TipoProducto.getSeleccion().toString());
                    //cargarWebService();
                    new CargarBaseDeDatosTIpoProducto(this,SelccionMantenedor.TipoProducto.getSeleccion());
                    break;

                case Region:

                    tvTitulo.setText(SelccionMantenedor.Region.getSeleccion().toString());
                    //cargarWebService();
                    new CargarBaseDeDatosDosAtributos(this,SelccionMantenedor.Region.getSeleccion());
                    break;
                case Rol:

                    tvTitulo.setText(SelccionMantenedor.Rol.getSeleccion().toString());
                    //cargarWebService();
                    new CargarBaseDeDatosDosAtributos(this,SelccionMantenedor.Rol.getSeleccion());
                    break;
                case Interes:

                    tvTitulo.setText(SelccionMantenedor.Interes.getSeleccion().toString());
                    //cargarWebService();
                    new CargarBaseDeDatosDosAtributos(this,SelccionMantenedor.Interes.getSeleccion());
                    break;
                case HorarioComida:

                    tvTitulo.setText(SelccionMantenedor.HorarioComida.getSeleccion().toString());
                    //cargarWebService();
                    new CargarBaseDeDatosDosAtributos(this,SelccionMantenedor.HorarioComida.getSeleccion());
                    break;
                case Producto:

                    tvTitulo.setText(SelccionMantenedor.Producto.getSeleccion().toString());
                    //cargarWebService();
                      new CargarBaseDeDatosDosAtributos(this,SelccionMantenedor.Nutriente.getSeleccion());
                    //llenar lsta de productos en actividad
                    new CargarBaseDeDatosProducto(this,SelccionMantenedor.Producto.getSeleccion());

                    //llenar lista de Marca
                    new CargarBaseDeDatosTIpoProducto(this,SelccionMantenedor.TipoProducto.getSeleccion());


                    //Limpiar listas sabor y marca
                    CargarBaseDeDatosMantenedorTresAtributos.limpiarListaMarcaSabor();

                    //Cargar listas marca y tipo medicion para spinner de ActivityNuevoProducto con listas dedicadas
                    new CargarBaseDeDatosMantenedorTresAtributos(this,SelccionMantenedor.Marca.getSeleccion());



                    //Cargar listas Sabor y tipo medicion para spinner de ActivityNuevoProducto con listas dedicadas
                    new CargarBaseDeDatosMantenedorTresAtributos(this,SelccionMantenedor.Sabor.getSeleccion());
                    //CargarBaseDeDatosMantenedorTresAtributos.llenarListaSabor();



                    break;
                case Sabor:


                    //Cargar titulo dependiento del nombre del mantenedor
                    tvTitulo.setText(SelccionMantenedor.Sabor.getSeleccion().toString());

                    //Traer datos de web service para llenar lista;
                    new CargarBaseDeDatosMantenedorTresAtributos(this,SelccionMantenedor.Sabor.getSeleccion());

                    //Traer datos de tipo producto para llenar Spinner
                    new CargarBaseDeDatosTIpoProducto(this,SelccionMantenedor.TipoProducto.getSeleccion());
                    break;
                case Marca:


                    //Cargar titulo dependiento del nombre del mantenedor
                    tvTitulo.setText(SelccionMantenedor.Marca.getSeleccion().toString());

                    //Traer datos de web service para llenar lista;
                    new CargarBaseDeDatosMantenedorTresAtributos(this,SelccionMantenedor.Marca.getSeleccion());

                   //Traer datos de tipo producto para llenar Spinner
                    new CargarBaseDeDatosTIpoProducto(this,SelccionMantenedor.TipoProducto.getSeleccion());
                    break;
                case Nutriente:

                    tvTitulo.setText(SelccionMantenedor.Nutriente.getSeleccion().toString());

                    new CargarBaseDeDatosDosAtributos(this,SelccionMantenedor.Nutriente.getSeleccion());
                    break;
                case TipoPersona:

                    tvTitulo.setText(SelccionMantenedor.TipoPersona.getSeleccion().toString());

                    new CargarBaseDeDatosDosAtributos(this,SelccionMantenedor.TipoPersona.getSeleccion());
                    break;
                case Objetivo:

                    tvTitulo.setText(SelccionMantenedor.Objetivo.getSeleccion().toString());

                    new CargarBaseDeDatosDosAtributos(this,SelccionMantenedor.Objetivo.getSeleccion());
                    break;
                case Provincia:

                    tvTitulo.setText(SelccionMantenedor.Provincia.getSeleccion().toString());

                    new CargarBaseDeDatosMantenedorTresAtributos(this,SelccionMantenedor.Provincia.getSeleccion());
                    //Cargar base de datos para Spinner de AlertNuevoMantenedorTresAtributos con Region
                    new CargarBaseDeDatosDosAtributos(this,SelccionMantenedor.Region.getSeleccion());
                    break;
                case Comuna:

                    tvTitulo.setText(SelccionMantenedor.Comuna.getSeleccion().toString());

                    new CargarBaseDeDatosComuna(this,SelccionMantenedor.Comuna.getSeleccion());
                    //Para Cargar la provincia
                    new CargarBaseDeDatosMantenedorTresAtributos(this,SelccionMantenedor.Provincia.getSeleccion());
                    //Cargar base de datos para Spinner de AlertNuevoMantenedorTresAtributos con Region
                    new CargarBaseDeDatosDosAtributos(this,SelccionMantenedor.Region.getSeleccion());
                    break;
                default:
                    break;
            }




        }
        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(CrudActivity.this, ""+position, Toast.LENGTH_SHORT).show();
            }
        });
        lvLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                intent.putExtra("accion","editar");
                Producto producto = CargarBaseDeDatosProducto.listaProducto.get(position);
                new CargarBaseDeDatosProductoNutriente(CrudActivity.this,producto.getIdProducto(), SelccionMantenedor.ProductoNutriente.getSeleccion());
                intent.putExtra("Producto", (Serializable) producto);
                startActivity(intent);
                return false;
            }
        });

    btnBack.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    });



        llenr();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.nuevoNutriente);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SelccionMantenedor selccionMantenedor  = SelccionMantenedor.valueOf(mantenedor);
                switch (selccionMantenedor){

                    //Enviar a actividad para agregar Producto
                    case Producto:
                        //Cargar listas Sabor y tipo medicion para spinner de ActivityNuevoProducto con listas dedicadas
                        //CargarBaseDeDatosNuevoId.getNuevaid();
                        intent.putExtra("accion","agregar");
                        startActivity(intent);
                        //CargarBaseDeDatosMantenedorTresAtributos.llenarListaSabor();
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

    public void llenr(){


       if (mantenedor.equals(SelccionMantenedor.Producto.getSeleccion())) {
           adapterProducto = new AdapterProducto(this, CargarBaseDeDatosProducto.getListaProducto(), mantenedor);
           lvLista.setAdapter(adapterProducto);

       }else if (mantenedor.equals(SelccionMantenedor.TipoProducto.getSeleccion())){
           adaptaderTipoProducto =new AdaptaderTipoProducto(this, CargarBaseDeDatosTIpoProducto.getListaTipoProducto(),mantenedor);
           lvLista.setAdapter(adaptaderTipoProducto);
       }
       else if (mantenedor.equals(SelccionMantenedor.Comuna.getSeleccion())){
           adaptaderComuna =new AdapterComuna(this, CargarBaseDeDatosComuna.getListaComuna(),mantenedor);
               lvLista.setAdapter(adaptaderComuna);
        }else if (mantenedor.equals(SelccionMantenedor.Sabor.getSeleccion()) || mantenedor.equals(SelccionMantenedor.Marca.getSeleccion()) || mantenedor.equals(SelccionMantenedor.Provincia.getSeleccion())){
           adapterMantenedorTresAtributos =new AdapterMantenedorTresAtributos(this,CargarBaseDeDatosMantenedorTresAtributos.getListaMantenedorTresAtributos(),mantenedor);
           lvLista.setAdapter(adapterMantenedorTresAtributos);
       }else{
            adapterMantenedorDosAtributos =new AdapterMantenedorDosAtributos(this, CargarBaseDeDatosDosAtributos.getListaMantenedors(),mantenedor);
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


       // Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
      //adaptador.actualizarLista(CargarBaseDeDatosDosAtributos.getListaMantenedors());
      //llenr();


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
