package com.example.esteban.brlifeadmin.Adapter;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.esteban.brlifeadmin.AlertDialog.AlertDelete;
import com.example.esteban.brlifeadmin.AlertDialog.AlertNuevoMantenedorTresAtributos;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.Mantenedor;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.MantenedorTresAtributos;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.TipoProducto;
import com.example.esteban.brlifeadmin.ConexionWebService.CargarBaseDeDatosDosAtributos;
import com.example.esteban.brlifeadmin.ConexionWebService.CargarBaseDeDatosMantenedorTresAtributos;
import com.example.esteban.brlifeadmin.ConexionWebService.CargarBaseDeDatosTIpoProducto;
import com.example.esteban.brlifeadmin.Enum.SelccionMantenedor;
import com.example.esteban.brlifeadmin.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdapterMantenedorTresAtributos extends BaseAdapter implements AlertDelete.FinalizoCuadroDialogo, PopupMenu.OnMenuItemClickListener,AlertNuevoMantenedorTresAtributos.FinalizoCuadroDialogoAgregarTrestAtributos {
    private Context context;
    private ArrayList<MantenedorTresAtributos> listaMantenedorTresAtributos =new ArrayList<>();
    private String tipoMantenedor;



//Constructor para adapter
    public AdapterMantenedorTresAtributos(Context context, ArrayList<MantenedorTresAtributos> listaMantenedorTresAtributos, String tipoMantenedor){
        this.context = context;
        this.listaMantenedorTresAtributos=listaMantenedorTresAtributos;
        this.tipoMantenedor=tipoMantenedor;
    }


    //Al momento de extender la clase como base adapter estos metodos lo creara automaticamente y hay que editarlos como este ejemplo con el arraylist con el objeto que se usara en el adaptador
    //estos metodos nos sirven para obtener el objeto dependiendo de la posicion en un arraylst.
    @Override
    public int getCount() {
        return listaMantenedorTresAtributos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaMantenedorTresAtributos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override

    //este metodo tambien lo creara por defecto, solo hay que inflar la vista como se ve acontinuacion.
    //en este metodo inflaremos la vista y referenciaremos los widget para trabajar con ellos.
    public View getView(final int position, View convertView, ViewGroup parent) {

        //inflar vista de adaptador
        if (convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = View.inflate(context, R.layout.adapter_mantenedor_tres_atributos,null);

        }

        //Referencia de widget
        TextView tvNombreMantenedorTresAtributo=(TextView)convertView.findViewById(R.id.tvNombreMantenedorTresAtributo);
        Button btnMoreMantenedorTresAtributos=(Button)convertView.findViewById(R.id.btnMoreMantenedorTresAtributos);
        TextView tvTipoProducto_Region=convertView.findViewById(R.id.tvTipoProducto_Region);

        //Setear nombre de mantenedor
        tvNombreMantenedorTresAtributo.setText(listaMantenedorTresAtributos.get(position).getNombreMantenedorTresAtributos());



        //Seteamos nombre del mantenedor de dos atributos concontrados por el id

        if (tipoMantenedor.equals(SelccionMantenedor.Provincia.getSeleccion())){
            //buscar objeto de mantenedor de dos atributos para traer nombre de fk y setear en vista de adapter
            //Creamos un objeto de mantenedor de dos atributos
            Mantenedor mantenedorDosAtributos= CargarBaseDeDatosDosAtributos.buscar(listaMantenedorTresAtributos.get(position).getFkMantenedorTresAtributos());

            if (mantenedorDosAtributos !=null){
                tvTipoProducto_Region.setText(mantenedorDosAtributos.getNombreTipoProducto());
            }
        }else{

            TipoProducto tipoProducto= CargarBaseDeDatosTIpoProducto.buscar(listaMantenedorTresAtributos.get(position).getFkMantenedorTresAtributos());
           if (tipoProducto!=null) {
               tvTipoProducto_Region.setText(tipoProducto.getNombreTipoProducto());
           }


        }





        //boton more que es que de tres puntitos.
        //en este haremos el negocio para eliminar o editar el mantenedor
        btnMoreMantenedorTresAtributos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup=new PopupMenu(context,v);
                popup.setOnMenuItemClickListener(AdapterMantenedorTresAtributos.this);
                popup.inflate(R.menu.popup_menu);

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){
                            case R.id.itemEdit:

                         new AlertNuevoMantenedorTresAtributos(context,AdapterMantenedorTresAtributos.this,true,listaMantenedorTresAtributos.get(position),tipoMantenedor);

                                return true;

                            case R.id.itemDelete:
                              new AlertDelete(context,listaMantenedorTresAtributos.get(position).getIdMantenedorTresAtributos(),AdapterMantenedorTresAtributos.this,tipoMantenedor);

                                return true;

                            default: return false;




                        }

                    }
                });
                popup.show();
            }
        });

        return convertView;
    }
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }


    @Override
    public void ResultadoCuadroDialogo(Boolean val) {
        this.notifyDataSetChanged();
    }


    @Override
    public void ResultadoCuadroDialogoAgregarTresAtributos(boolean val) {

        Toast.makeText(context, "hola", Toast.LENGTH_SHORT).show();
        this.notifyDataSetChanged();
    }
}
