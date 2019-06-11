package com.example.esteban.brlifeadmin;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.esteban.brlifeadmin.Adapter.SpinAdapter;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.MantenedorTresAtributos;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.TipoProducto;
import com.example.esteban.brlifeadmin.ConexionWebService.CargarBaseDeDatosDosAtributos;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.Mantenedor;
import com.example.esteban.brlifeadmin.ConexionWebService.CargarBaseDeDatosMantenedorTresAtributos;
import com.example.esteban.brlifeadmin.ConexionWebService.CargarBaseDeDatosNuevoId;
import com.example.esteban.brlifeadmin.ConexionWebService.CargarBaseDeDatosTIpoProducto;
import com.example.esteban.brlifeadmin.ConexionWebService.CrudMantenedorTresAtributos;
import com.example.esteban.brlifeadmin.Enum.SelccionMantenedor;
import com.example.esteban.brlifeadmin.Enum.SeleccionTipoMedicion;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

public class NuevoProductoActivity extends AppCompatActivity {
 private Spinner spTipoProducto,spTipoMedicion,spMarca,spSabor;
 private RadioButton rbRegistroCodigoBarra,rbRegistroNormal;
 private ArrayAdapter<TipoProducto> adapterTipoProducto;
 private SpinAdapter adapterTipoMedicion;
 private ArrayAdapter<MantenedorTresAtributos> adapterMarca;
 private ArrayAdapter<MantenedorTresAtributos> adapterSabor;
 private ArrayList<MantenedorTresAtributos>listaFiltroSabor=new ArrayList<>();
    private ArrayList<MantenedorTresAtributos>listaFiltroMarca=new ArrayList<>();
    //private ArrayList<String>listaTipoMedicion=new ArrayList<>();
 private Button btnOpenBarCode,btnBack;
 private EditText etBarCode;
 private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_producto);

        //Declaracion WIDGET

        spTipoProducto=(Spinner)findViewById(R.id.spTipoProducto);
        spTipoMedicion=(Spinner)findViewById(R.id.spTipoMedicion);
        spMarca=(Spinner)findViewById(R.id.spMarca);
        spSabor=(Spinner)findViewById(R.id.spSabor);
        btnOpenBarCode=(Button)findViewById(R.id.btnOpenBarCode);
        btnBack=(Button)findViewById(R.id.btnBack);
        etBarCode=(EditText)findViewById(R.id.etBarCode);
        rbRegistroCodigoBarra=(RadioButton)findViewById(R.id.rbRegistroCodigoBarra);
        rbRegistroNormal=(RadioButton)findViewById(R.id.rbRegistroNormal);

        //Limpiar listas
        listaFiltroMarca.clear();
        listaFiltroSabor.clear();
        //Cargar listas Sabor y tipo medicion para spinner de ActivityNuevoProducto con listas dedicadas



//Cargar Spinner Tipo Medicion


spTipoProducto.getSelectedItem();



        CargarSpinner();


        /**
         * Spinner spTiporoducto:
         * al seleccionar un tipo de producto, se obtendra id de tipoProducto para cargar a spMarca marcas asociadas a tipo producto
         * y cargara en spSabor sabores asociados a tipo de producto.
         * los resultados estaran el listas filtro(listaFiltroSabor y listaFiltroMarca). Estas listas se llenaran con metodos de filtro alojados en clase
         * CargarBaseDeDatosMantenedorTresAtributos donde encuentran dos metodos. filtroMarca y filtroSabor. estos metodos necesitan un idTipoProducto para
         * hacer filtros y llenar las listas con los resultados.
         */
        spTipoProducto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

               listaFiltroMarca.clear();
               listaFiltroSabor.clear();
                id = CargarBaseDeDatosNuevoId.getNuevaid();
                if (id > 0){
                    Toast.makeText(NuevoProductoActivity.this, "" + id, Toast.LENGTH_SHORT).show();
                }

               //obtener id de tipo producto seleccionado
               int idTipoproducto=CargarBaseDeDatosTIpoProducto.getListaTipoProducto().get(position).getIdTipoProducto();

               //Filtrar
               listaFiltroSabor = CargarBaseDeDatosMantenedorTresAtributos.filtroSabor(idTipoproducto);
               adapterSabor = new ArrayAdapter(NuevoProductoActivity.this,android.R.layout.simple_list_item_1,listaFiltroSabor);
               spSabor.setAdapter(adapterSabor);


               //llenar spinner marca
               listaFiltroMarca=CargarBaseDeDatosMantenedorTresAtributos.filtroMarca(idTipoproducto);
               adapterMarca=new ArrayAdapter(NuevoProductoActivity.this,android.R.layout.simple_list_item_1,listaFiltroMarca);
               spMarca.setAdapter(adapterMarca);





           }



           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });





        rbRegistroCodigoBarra.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    etBarCode.setText("");
                    etBarCode.setEnabled(true);
                    btnOpenBarCode.setEnabled(true);

                }

            }
        });

       rbRegistroNormal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if (isChecked){
                   etBarCode.setText("0");
                   etBarCode.setEnabled(false);
                   btnOpenBarCode.setEnabled(false);
               }

           }
       });
        btnOpenBarCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanner();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    public void CargarSpinner(){
        //llenar spinner tipoProducto
        adapterTipoProducto= new ArrayAdapter(this,android.R.layout.simple_list_item_1, CargarBaseDeDatosTIpoProducto.getListaTipoProducto());
        spTipoProducto.setAdapter(adapterTipoProducto);


       new SeleccionTipoMedicion();
       SeleccionTipoMedicion.getTipoMedicion();
        adapterTipoMedicion=new SpinAdapter(this,android.R.layout.simple_list_item_1,SeleccionTipoMedicion.getTipoMedicion());
        spTipoMedicion.setAdapter(adapterTipoMedicion);

    }



    /**
     *
     */
    public void scanner(){
        IntentIntegrator intent= new IntentIntegrator(this);
        intent.setDesiredBarcodeFormats(IntentIntegrator.PRODUCT_CODE_TYPES);

        intent.setPrompt(getString(R.string.escanearCodigoBarra));
        intent.setCameraId(0);
        intent.setBeepEnabled(false);
        intent.setBarcodeImageEnabled(false);
        intent.initiateScan();
    }

    /**
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if (result !=null){
            if (result.getContents()==null){
                Toast.makeText(this, R.string.mensajeCancelasteEscaneoCodigoBarra, Toast.LENGTH_LONG).show();

            }else{

                etBarCode.setText(result.getContents().toString());

            }

        }else {
            super.onActivityResult(requestCode,resultCode,data);
        }
    }
}
