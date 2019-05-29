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


import com.example.esteban.brlifeadmin.Adapter.AdapterMantenedorDosAtributos;
import com.example.esteban.brlifeadmin.Adapter.AdapterProducto;
import com.example.esteban.brlifeadmin.AlertDialog.AlertNuevoMantenedor;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.CargarBaseDeDatos;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.CargarBaseDeDatosProducto;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.Mantenedor;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.SelccionMantenedor;

import java.util.ArrayList;

public class CrudActivity extends AppCompatActivity implements  AlertNuevoMantenedor.FinalizoCuadroDialogoAgregar{
 private EditText etBuscar;
 private Button btnBuscar,btnBack;
 private TextView tvTitulo;
 private ListView lvLista;
 private ArrayList<Mantenedor> listaMantenedor;

 private AdapterMantenedorDosAtributos adapterMantenedorDosAtributos;
 private AdapterMantenedorTresAtributos adapterMantenedorTresAtributos;
 private AdapterProducto adapterProducto;
 private  String mantenedor;



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

        listaMantenedor =new ArrayList<>();
        listaMantenedor.clear();

        //Verificar estado de bundle
        if (bundle !=null){

            //obtener informacion de bundle
        mantenedor=bundle.getString("mantenedor");
        SelccionMantenedor selccionMantenedor  = SelccionMantenedor.valueOf(mantenedor);
            switch (selccionMantenedor){
                case TipoProducto:
                    lvLista.setAdapter(null);
                   tvTitulo.setText(SelccionMantenedor.TipoProducto.getSeleccion().toString());
                    //cargarWebService();
                    new CargarBaseDeDatos(this,SelccionMantenedor.TipoProducto.getSeleccion());
                    break;

                case Region:
                    lvLista.setAdapter(null);
                    tvTitulo.setText(SelccionMantenedor.Region.getSeleccion().toString());
                    //cargarWebService();
                    new CargarBaseDeDatos(this,SelccionMantenedor.Region.getSeleccion());
                    break;
                case Rol:
                    lvLista.setAdapter(null);
                    tvTitulo.setText(SelccionMantenedor.Rol.getSeleccion().toString());
                    //cargarWebService();
                    new CargarBaseDeDatos(this,SelccionMantenedor.Rol.getSeleccion());
                    break;
                case Interes:
                    lvLista.setAdapter(null);
                    tvTitulo.setText(SelccionMantenedor.Interes.getSeleccion().toString());
                    //cargarWebService();
                    new CargarBaseDeDatos(this,SelccionMantenedor.Interes.getSeleccion());
                    break;
                case HorarioComida:
                    lvLista.setAdapter(null);
                    tvTitulo.setText(SelccionMantenedor.HorarioComida.getSeleccion().toString());
                    //cargarWebService();
                    new CargarBaseDeDatos(this,SelccionMantenedor.HorarioComida.getSeleccion());
                    break;
                case Producto:
                    lvLista.setAdapter(null);
                    tvTitulo.setText(SelccionMantenedor.Producto.getSeleccion().toString());
                    //cargarWebService();
                    new CargarBaseDeDatosProducto(this,SelccionMantenedor.Producto.getSeleccion());
                    new CargarBaseDeDatos(this,SelccionMantenedor.TipoProducto.getSeleccion());
                    break;
                case Sabor:
                    lvLista.setAdapter(null);
                    tvTitulo.setText(SelccionMantenedor.Sabor.getSeleccion().toString());
                    //cargarWebService();
                    new CargarBaseDeDatosMantenedorTresAtributos(this,SelccionMantenedor.Sabor.getSeleccion());
                    break;
                case Marca:
                    lvLista.setAdapter(null);
                    tvTitulo.setText(SelccionMantenedor.Marca.getSeleccion().toString());
                    //cargarWebService();
                    new CargarBaseDeDatosMantenedorTresAtributos(this,SelccionMantenedor.Marca.getSeleccion());
                    break;
                case Nutriente:
                    lvLista.setAdapter(null);
                    tvTitulo.setText(SelccionMantenedor.Nutriente.getSeleccion().toString());
                    //cargarWebService();
                    new CargarBaseDeDatos(this,SelccionMantenedor.Nutriente.getSeleccion());
                    break;
                case Provincia:
                    lvLista.setAdapter(null);
                    tvTitulo.setText(SelccionMantenedor.Provincia.getSeleccion().toString());
                    //cargarWebService();
                    new CargarBaseDeDatosMantenedorTresAtributos(this,SelccionMantenedor.Provincia.getSeleccion());
                    break;
                case Comuna:
                    lvLista.setAdapter(null);
                    tvTitulo.setText(SelccionMantenedor.Comuna.getSeleccion().toString());
                    //cargarWebService();
                    new CargarBaseDeDatos(this,SelccionMantenedor.Comuna.getSeleccion());
                    break;
                default:
                    break;
            }




        }
        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CrudActivity.this, ""+position, Toast.LENGTH_SHORT).show();
            }
        });

    btnBack.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    });



        llenr();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.nuevo);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          if (mantenedor.equals(SelccionMantenedor.Producto.getSeleccion())){
              startActivity(intent);
          }else
           new AlertNuevoMantenedor(CrudActivity.this,CrudActivity.this,false,null,mantenedor);

            }
        });
    }

    public void llenr(){


       if (mantenedor.equals(SelccionMantenedor.Producto.getSeleccion())){

    adapterProducto=new AdapterProducto(this, CargarBaseDeDatosProducto.getListaProducto(),mantenedor);
    lvLista.setAdapter(adapterProducto);
        }else if (mantenedor.equals(SelccionMantenedor.Sabor.getSeleccion()) || mantenedor.equals(SelccionMantenedor.Marca.getSeleccion()) || mantenedor.equals(SelccionMantenedor.Provincia.getSeleccion())){
           adapterMantenedorTresAtributos =new AdapterMantenedorTresAtributos(this,CargarBaseDeDatosMantenedorTresAtributos.getListaMantenedorTresAtributos(),mantenedor);
           lvLista.setAdapter(adapterMantenedorTresAtributos);
       }else{
    adapterMantenedorDosAtributos =new AdapterMantenedorDosAtributos(this,CargarBaseDeDatos.getListaMantenedors(),mantenedor);
    lvLista.setAdapter(adapterMantenedorDosAtributos);
     }




    }





    @Override
    protected void onResume() {
        super.onResume();
        if (mantenedor.equals(SelccionMantenedor.Producto.getSeleccion())){
            adapterProducto.notifyDataSetChanged();

        }else if (mantenedor.equals(SelccionMantenedor.Sabor.getSeleccion()) || mantenedor.equals(SelccionMantenedor.Marca.getSeleccion()) || mantenedor.equals(SelccionMantenedor.Provincia.getSeleccion())){
            adapterMantenedorTresAtributos.notifyDataSetChanged();
        }else adapterMantenedorDosAtributos.notifyDataSetChanged();


       // Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
      //adaptador.actualizarLista(CargarBaseDeDatos.getListaMantenedors());
      //llenr();


    }



    @Override
    public void ResultadoCuadroDialogoAgregar(boolean val) {
        adapterMantenedorDosAtributos.notifyDataSetChanged();
    }


}
