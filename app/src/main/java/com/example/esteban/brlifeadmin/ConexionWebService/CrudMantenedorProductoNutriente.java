package com.example.esteban.brlifeadmin.ConexionWebService;

import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.Producto;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.ProductoNutriente;

import org.json.JSONObject;

import java.util.ArrayList;

public class CrudMantenedorProductoNutriente implements Response.Listener<JSONObject>,Response.ErrorListener {

    static RequestQueue request;
    static JsonObjectRequest jsonObjectRequest;
    static ProgressDialog progreso;

    public CrudMantenedorProductoNutriente(Context context, int idproducto, String mantenedor) {
        request= Volley.newRequestQueue(context);
        progreso=new ProgressDialog(context);
        progreso.setMessage("Cargando...");
        progreso.show();
        ArrayList<ProductoNutriente> listaProductoNutriente= CargarBaseDeDatosProductoNutriente.getListaProductoNutrienteacion();

        for (ProductoNutriente e: listaProductoNutriente) {
            e.setIdProducto(idproducto);
            switch (e.getAccion()){

                //Enviar a actividad para agregar Producto
                case "i":
                    agregarNuevoProductoNutriente(e,context,mantenedor);
                    break;
                //Abrir Alert dialogo para agregar Provincia
                case "a":
                    editarComuna(e,context,mantenedor);
                    break;

                //Abrir Alert dialogo para agregar Sabor
                case "e":
                    eliminarProductoNutriente(e,context,mantenedor);
                    break;
            }

        }
    }

    private void agregarNuevoProductoNutriente(ProductoNutriente productoNutriente, Context contexto, String mantenedor) {




        String url="http://www.brotherwareoficial.com/WebServices/MantenedorProductoNutriente.php?tipoconsulta=i" +
                "&idProducto="+ productoNutriente.getIdProducto() +
                "&idNutriente="+ productoNutriente.getIdNutriente() +
                "&valor="+productoNutriente.getValor();


        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);


    }

    private void editarComuna(ProductoNutriente productoNutriente, Context contexto, String mantenedor){
        //progreso=new ProgressDialog(contexto);
        //progreso.setMessage("Cargando...");
        //progreso.show();


        String url="http://www.brotherwareoficial.com/WebServices/MantenedorProductoNutriente.php?tipoconsulta=a" +
                "&idProductoNutriente= "+productoNutriente.getIdProductoNutriente() +
                "&idProducto="+productoNutriente.getIdProducto() +
                "&idNutriente="+productoNutriente.getIdNutriente() +
                "&valor=4"+ productoNutriente.getValor();


        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);

    }

    private void eliminarProductoNutriente(ProductoNutriente productoNutriente, Context contexto, String mantenedor) {
        //progreso=new ProgressDialog(contexto);
        //progreso.setMessage("Cargando...");
        //progreso.show();


        String url="http://www.brotherwareoficial.com/WebServices/MantenedorProductoNutriente.php?tipoconsulta=e" +
                "&idProducto="+productoNutriente.getIdProducto() +
                "&idNutriente="+productoNutriente.getIdNutriente();
        //url=url.replace(" ","%20");

        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }



    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {
        progreso.hide();
    }
}
