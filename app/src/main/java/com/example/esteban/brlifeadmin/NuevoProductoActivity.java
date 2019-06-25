package com.example.esteban.brlifeadmin;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.esteban.brlifeadmin.Adapter.AdapterProductoNutriente;
import com.example.esteban.brlifeadmin.Adapter.SpinAdapter;
import com.example.esteban.brlifeadmin.Adapter.SpinAdapterTipoProducto;
import com.example.esteban.brlifeadmin.Adapter.SpinAdapterTresAtributos;
import com.example.esteban.brlifeadmin.AlertDialog.AlertMantenedorProductoNutriente;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.MantenedorTresAtributos;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.Producto;
import com.example.esteban.brlifeadmin.ConexionWebService.CrudMantenedorProductoNutriente;
import com.example.esteban.brlifeadmin.ConexionesWebServiceNuevo.CargarMantenedorProductoHttpConecction;
import com.example.esteban.brlifeadmin.ConexionesWebServiceNuevo.CargarMantenedorProductoNutrienteHttpConecction;
import com.example.esteban.brlifeadmin.ConexionesWebServiceNuevo.CargarMantenedorTipoProductoHttpConecction;
import com.example.esteban.brlifeadmin.ConexionesWebServiceNuevo.CargarMantenedorTresAtributosHttpConecction;
import com.example.esteban.brlifeadmin.ConexionesWebServiceNuevo.CargarNuevoIdHttpConecction;
import com.example.esteban.brlifeadmin.Enum.SelccionMantenedor;
import com.example.esteban.brlifeadmin.Enum.SeleccionTipoMedicion;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class NuevoProductoActivity extends AppCompatActivity implements AlertMantenedorProductoNutriente.FinalizoCuadroDialogoProductoNutriente {
 private Spinner spTipoProducto,spTipoMedicion,spMarca,spSabor;
 private RadioButton rbRegistroCodigoBarra,rbRegistroNormal;
 private SpinAdapterTipoProducto adapterTipoProducto;
 private SpinAdapter adapterTipoMedicion;
 private SpinAdapterTresAtributos adapterMarca;
 private SpinAdapterTresAtributos adapterSabor;
 private ArrayList<MantenedorTresAtributos>listaFiltroSabor=new ArrayList<>();
 private ArrayList<MantenedorTresAtributos>listaFiltroMarca=new ArrayList<>();
 private AdapterProductoNutriente adapterProductoNutriente;
 private ListView lvProductoNutriente;
 private Button btnOpenBarCode,btnBack, btnAgregarProducto;
 private EditText etBarCode, etCantidadracion, etnombreproducto;
 private Switch validado;
 private int id;
 private Producto producto;
 private String accion, nombretipoproducto;


 private int idTipoproducto, idmarca, idsabor, idmedicion;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //Se obtiene el id del crud producto para eliminar en caso de cancelar el ingreso del producto
        if (accion.equals("agregar")) {
            id = CargarNuevoIdHttpConecction.getNuevaid();
            new CrudProducto(id,NuevoProductoActivity.this,"eliminar",SelccionMantenedor.Producto.getSeleccion());
        }
        CargarMantenedorProductoNutrienteHttpConecction.limpiarlista();
        adapterProductoNutriente.notifyDataSetChanged();
        finish();

    }

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
        btnAgregarProducto=(Button)findViewById(R.id.btnAgregarProducto);
        etBarCode=(EditText)findViewById(R.id.etBarCode);
        etCantidadracion=(EditText)findViewById(R.id.etCantidadracion);
        etnombreproducto=(EditText)findViewById(R.id.etnombreproducto);
        rbRegistroCodigoBarra=(RadioButton)findViewById(R.id.rbRegistroCodigoBarra);
        rbRegistroNormal=(RadioButton)findViewById(R.id.rbRegistroNormal);
        lvProductoNutriente=(ListView)findViewById(R.id.lvProductoNutriente);
        validado = (Switch)findViewById(R.id.validado);

        //Limpiar listas
        listaFiltroMarca.clear();
        listaFiltroSabor.clear();

        accion = getIntent().getExtras().getString("accion");

        //Para insertar el id y tenerlo al momento de agregar o cancelar
        if (accion.equals("agregar")) {
            //new CrudProducto(0, NuevoProductoActivity.this, "nuevo", SelccionMantenedor.Producto.getSeleccion());
            id = CargarNuevoIdHttpConecction.getNuevaid();
            Toast.makeText(this, "Nuevo Id: "+id , Toast.LENGTH_SHORT).show();
            btnAgregarProducto.setText("Agregar");

        }


        //Cargar lista con productonutriente
        //adapterProductoNutriente=new AdapterProductoNutriente(this, CargarMantenedorProductoNutrienteHttpConecction.getListaProductoNutriente());
        //lvProductoNutriente.setAdapter(adapterProductoNutriente);



        CargarSpinner();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.nuevoNutriente);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertMantenedorProductoNutriente(NuevoProductoActivity.this,NuevoProductoActivity.this);
            }
        });

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
               //obtener id de tipo producto seleccionado
               idTipoproducto=CargarMantenedorTipoProductoHttpConecction.getListaTipoProducto().get(position).getIdTipoProducto();
               nombretipoproducto = CargarMantenedorTipoProductoHttpConecction.getListaTipoProducto().get(position).getNombreTipoProducto();
               //Filtrar
               listaFiltroSabor = CargarMantenedorTresAtributosHttpConecction.filtroSabor(idTipoproducto);
               adapterSabor = new SpinAdapterTresAtributos(NuevoProductoActivity.this,android.R.layout.simple_list_item_1,listaFiltroSabor);
               spSabor.setAdapter(adapterSabor);


               if (CargarMantenedorTipoProductoHttpConecction.getListaTipoProducto().get(position).isVaridadSabor() == false){
                   spSabor.setSelection(0);
                   spSabor.setEnabled(false);
               }else {
                   spSabor.setEnabled(true);
                   if (adapterSabor != null && producto != null) {
                       if (producto.getFkTipoProducto() == idTipoproducto) {
                           if (accion.equals("editar")) {
                               int posi = (int) adapterSabor.getItemId(producto.getIdSabor());
                               spSabor.setSelection(posi);
                           }
                       }
                   }
               }

               //llenar spinner marca
               listaFiltroMarca=CargarMantenedorTresAtributosHttpConecction.filtroMarca(idTipoproducto);
               adapterMarca=new SpinAdapterTresAtributos(NuevoProductoActivity.this,android.R.layout.simple_list_item_1,listaFiltroMarca);
               spMarca.setAdapter(adapterMarca);

               if (CargarMantenedorTipoProductoHttpConecction.getListaTipoProducto().get(position).isVariedadMarca() == false) {
                   spMarca.setSelection(0);
                   spMarca.setEnabled(false);
               }else{
                   spMarca.setEnabled(true);
                   if (adapterMarca != null && producto != null) {
                       if (producto.getFkTipoProducto() == idTipoproducto) {
                           if (accion.equals("editar")) {
                               int posi = (int) adapterMarca.getItemId(producto.getIdMarca());
                               spMarca.setSelection(posi);
                           }
                       }
                   }
               }

           }
           @Override
           public void onNothingSelected(AdapterView<?> parent) {
           }
       });

        spSabor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idsabor = listaFiltroSabor.get(position).getIdMantenedorTresAtributos();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spMarca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //int pos = (int) adapterMarca.getItemId(position);
                idmarca = listaFiltroMarca.get(position).getIdMantenedorTresAtributos();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spTipoMedicion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idmedicion = position + 1;

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
                //Se obtiene el id del crud producto para eliminar en caso de cancelar el ingreso del producto
                if (accion.equals("agregar")) {
                    id = CargarNuevoIdHttpConecction.getNuevaid();
                    new CrudProducto(id,NuevoProductoActivity.this,"eliminar",SelccionMantenedor.Producto.getSeleccion());
                }
                CargarMantenedorProductoNutrienteHttpConecction.limpiarlista();
                adapterProductoNutriente.notifyDataSetChanged();
                finish();
            }
        });



        btnAgregarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Se obtiene el id para actualizar y agregarlo con todos los datos a tabla
                boolean validacion = false;
                if (id > 0){
                    Toast.makeText(NuevoProductoActivity.this, "" + id, Toast.LENGTH_SHORT).show();
                }

                Producto producto = ObtenerDatosFormulario();
                validacion = ValidarDatosFormulario(producto);
                if (validacion){
                    if (accion.equals("agregar")) {
                        id = CargarNuevoIdHttpConecction.getNuevaid();
                        producto.setIdProducto(id);
                        new CrudMantenedorProductoNutriente(NuevoProductoActivity.this, id, "ProductoNutriente");
                        CargarMantenedorProductoHttpConecction.agregar(producto);
                        new CrudProducto(id, producto.getCodigoBarra(), producto.getFkTipoProducto(), producto.getIdMarca(), producto.getIdSabor(), producto.getNombreProducto(), producto.getCantidadRacion(), producto.getTipoMedicion(), producto.isValidacion(), NuevoProductoActivity.this, "editar", SelccionMantenedor.Producto.getSeleccion());
                        //CargarBaseDeDatosProductoNutriente.limpiarlista();
                        adapterProductoNutriente.notifyDataSetChanged();
                    }else if(accion.equals("editar")){
                        producto.setIdProducto(id);
                        CargarMantenedorProductoHttpConecction.editar(producto.getIdProducto(), producto.getFkTipoProducto(), producto.getIdMarca(), producto.getIdSabor(), producto.getNombreProducto(),producto.getCantidadRacion(), producto.getTipoMedicion(), producto.isValidacion());
                        new CrudMantenedorProductoNutriente(NuevoProductoActivity.this, id, "ProductoNutriente");
                        new CrudProducto(producto.getIdProducto(), producto.getCodigoBarra(), producto.getFkTipoProducto(), producto.getIdMarca(), producto.getIdSabor(), producto.getNombreProducto(), producto.getCantidadRacion(), producto.getTipoMedicion(), producto.isValidacion(), NuevoProductoActivity.this, "editar", SelccionMantenedor.Producto.getSeleccion());
                        //CargarBaseDeDatosProductoNutriente.limpiarlista();
                        adapterProductoNutriente.notifyDataSetChanged();
                    }
                    CargarMantenedorProductoNutrienteHttpConecction.limpiarlista();
                    adapterProductoNutriente.notifyDataSetChanged();
                    finish();
                }

            }
        });


        //Metodo para pasar datos a formulario
        if (accion.equals("editar")){
            producto = (Producto) getIntent().getExtras().getSerializable("Producto");
            id = producto.getIdProducto();
            if (producto != null){
                cargarDatosaFormulario(producto);
                Toast.makeText(this, "Se logro", Toast.LENGTH_SHORT).show();
                adapterProductoNutriente = new AdapterProductoNutriente(this, CargarMantenedorProductoNutrienteHttpConecction.getListaProductoNutriente());
                lvProductoNutriente.setAdapter(adapterProductoNutriente);
            }
            btnAgregarProducto.setText("Editar");
        }

        //Eventos para cuando pierde el foco el editext
        etnombreproducto.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String nombreproducto = etnombreproducto.getText().toString();
                nombreproducto.replace(" ","");
                if (hasFocus) {

                } else {
                    if (etnombreproducto.getText().equals("")){
                        etnombreproducto.setError("Ingrese el nombre del producto");
                        btnAgregarProducto.setEnabled(false);
                    }else{
                        btnAgregarProducto.setEnabled(true);
                    }
                    //Toast.makeText(getApplicationContext(), "Pierde el focus", Toast.LENGTH_LONG).show();
                }
            }
        });

        etBarCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String barcode = etBarCode.getText().toString();
                barcode.replace(" ","");
                if (hasFocus) {

                } else {
                    if (barcode.equals("")){
                        etBarCode.setError("Ingrese codigo de barra");
                        btnAgregarProducto.setEnabled(false);
                    }else{
                        btnAgregarProducto.setEnabled(true);
                    }
                    //Toast.makeText(getApplicationContext(), "Pierde el focus", Toast.LENGTH_LONG).show();
                }
            }
        });

        etCantidadracion.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String cantidad = etCantidadracion.getText().toString();
                cantidad.replace(" ","");
                if (hasFocus) {

                } else {
                    if (cantidad.equals("")){
                        etCantidadracion.setError("Ingrese cantidad de racion del producto");
                        btnAgregarProducto.setEnabled(false);
                    }else{
                        btnAgregarProducto.setEnabled(true);
                    }
                    //Toast.makeText(getApplicationContext(), "Pierde el focus", Toast.LENGTH_LONG).show();
                }
            }
        });
    }



    public void CargarSpinner(){
        //llenar spinner tipoProducto
        adapterTipoProducto= new SpinAdapterTipoProducto(this,android.R.layout.simple_list_item_1, CargarMantenedorTipoProductoHttpConecction.getListaTipoProducto());
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

    public Producto ObtenerDatosFormulario(){
        Producto producto = new Producto();
        try{
            producto.setCodigoBarra(etBarCode.getText().toString());

        }catch (Exception e){
            producto.setCodigoBarra("");
        }
        try {
            producto.setFkTipoProducto(idTipoproducto);
        }catch (Exception e){
            producto.setFkTipoProducto(0);
        }
        try{
            producto.setNombreTipoProducto(nombretipoproducto);
        }catch (Exception ex){
            producto.setNombreTipoProducto("" +
                    "");
        }
        try {
            producto.setIdMarca(idmarca);
        }catch (Exception e){
            producto.setIdMarca(0);
        }
        try {
            producto.setIdSabor(idsabor);
        }catch (Exception e){
            producto.setIdSabor(0);
        }
        try {
            producto.setNombreProducto(etnombreproducto.getText().toString());
        }catch (Exception e){
            producto.setNombreProducto("");
        }
        try {
            producto.setCantidadRacion(Float.parseFloat(etCantidadracion.getText().toString()));
        }catch (Exception e){
            producto.setCantidadRacion(0);
        }
        try {
            producto.setTipoMedicion(idmedicion);
        }catch (Exception e){
            producto.setTipoMedicion(0);
        }
        try {
            producto.setValidacion(validado.isChecked());
        }catch (Exception e){
            producto.setValidacion(false);
        }

        return producto;
    }

    public boolean ValidarDatosFormulario(Producto producto){
        if (producto.getCodigoBarra().equals("")){
            etBarCode.setFocusable(true);
            etBarCode.setError("Ingrese el campo de codigo de barra");
            return false;
        }
        if (producto.getFkTipoProducto() == 0){
            spTipoProducto.setFocusable(true);
            Toast.makeText(this, "Seleccione un tipo de producto", Toast.LENGTH_SHORT).show();
        }
        if (producto.getNombreProducto().equals("")){
            etnombreproducto.setError("Ingrese el nombre del producto");
            etnombreproducto.setFocusable(true);
            return  false;
        }
        if (producto.getCantidadRacion() == 0){
            etCantidadracion.setFocusable(true);
            etCantidadracion.setError("Ingrese Cantidad de racion");
            return false;
        }
        if (producto.getTipoMedicion() == 0){
            spTipoMedicion.setFocusable(true);
            Toast.makeText(this, "Seleccione tipo medicion", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void cargarDatosaFormulario(Producto producto){
        try{
            if (producto.getIdProducto() == Long.parseLong(producto.getCodigoBarra())){
                rbRegistroNormal.setChecked(true);
                rbRegistroCodigoBarra.setChecked(false);
                etBarCode.setEnabled(false);
                etBarCode.setText("0");
            }else{
                rbRegistroNormal.setChecked(false);
                rbRegistroCodigoBarra.setChecked(true);
                etBarCode.setText(producto.getCodigoBarra());
            }
            //producto.setCodigoBarra(etBarCode.getText().toString());
        }catch (Exception e){
            producto.setCodigoBarra("error");
        }
        try {
            ///producto.setFkTipoProducto(idTipoproducto);
            int posi = (int) adapterTipoProducto.getItemId(producto.getFkTipoProducto());
            spTipoProducto.setSelection(posi);
        }catch (Exception e){
            producto.setFkTipoProducto(0);
        }
        try {

            //producto.setIdMarca(idmarca);
        }catch (Exception e){
            producto.setIdMarca(0);
        }
        try {
            //producto.setIdSabor(idsabor);

        }catch (Exception e){
            producto.setIdSabor(0);
        }
        try {
            //producto.setNombreProducto(etnombreproducto.getText().toString());
            etnombreproducto.setText(producto.getNombreProducto());
        }catch (Exception e){
            producto.setNombreProducto("");
        }
        try {
            //producto.setCantidadRacion(Float.parseFloat(etCantidadracion.getText().toString()));
            etCantidadracion.setText(producto.getCantidadRacion() + "");
        }catch (Exception e){
            producto.setCantidadRacion(0);
        }
        try {
            //producto.setTipoMedicion(idmedicion);
            int posi = (int) adapterTipoMedicion.getItemId(producto.getTipoMedicion());
            spTipoMedicion.setSelection(posi);
        }catch (Exception e){
            spTipoMedicion.setSelection(0);
        }
        try {
            //producto.setValidacion(validado.isChecked());
            validado.setChecked(producto.isValidacion());
        }catch (Exception e){
            producto.setValidacion(false);
        }
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

    @Override
    public void ResultadoCuadroDialogoProductoNutriente(Boolean val) {
     adapterProductoNutriente.notifyDataSetChanged();
    }
}
