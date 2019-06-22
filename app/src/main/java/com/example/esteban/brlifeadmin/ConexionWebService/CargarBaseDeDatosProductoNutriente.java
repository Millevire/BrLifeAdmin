package com.example.esteban.brlifeadmin.ConexionWebService;

import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.ProductoNutriente;
import com.example.esteban.brlifeadmin.Enum.SelccionMantenedor;
import com.example.esteban.brlifeadmin.R;


import org.json.JSONObject;

import java.util.ArrayList;

public class CargarBaseDeDatosProductoNutriente implements Response.Listener<JSONObject>,Response.ErrorListener{
    public static ArrayList<ProductoNutriente>listaProductoNutriente=new ArrayList<>();

    static RequestQueue request;
    static JsonObjectRequest jsonObjectRequest;
    static ProgressDialog progreso;
    public Context contexto;
    public int idProducto;
    public int idNutriente;




    /**
     * Constructor para agregar un nuevo registro producto nutriente
     * @param contexto
     */
    public CargarBaseDeDatosProductoNutriente(Context contexto,ProductoNutriente productoNutriente){
    this.contexto=contexto;

    }


    /**
     * Constructor para traer producto nutriente especifico
     * @param contexto contexto de actividad
     * @param idProducto id de producto para busqueda
     * @param idNutriente id de nutriente para busqueda
     */
    public CargarBaseDeDatosProductoNutriente(Context contexto,int idProducto, int idNutriente){
        this.contexto=contexto;
        this.idProducto=idProducto;
        this.idNutriente=idNutriente;

    }




    public void nuevoProductoNutriente(ProductoNutriente productoNutriente){
        progreso=new ProgressDialog(contexto);
        progreso.setMessage(contexto.getString(R.string.mensajeBarraProgresoCargando));
        progreso.show();

        String url="http://www.brotherwareoficial.com/WebServices/Mantenedor"+ SelccionMantenedor.ProductoNutriente.getSeleccion() +".php?tipoconsulta=i&idProducto="+productoNutriente.getIdProducto()
                +"&idNutriente="+productoNutriente.getIdNutriente()+"&valor="+productoNutriente.getValor();


        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        request.add(jsonObjectRequest);


    }



    public static ArrayList<ProductoNutriente> getListaProductoNutriente() {
        return listaProductoNutriente;
    }


    /**
     *
     * agregar un nuevo Producto nutriente
     * @param productoNutriente objeto a ingresar a lsta
     */
    public static void agregar(ProductoNutriente productoNutriente){
        listaProductoNutriente.add(productoNutriente);
    }


    /**
     *
     * metodo para eliminar un objeto de lista mediante id de objeto
     * @param idProductoNutriente
     */
    public static void eliminar(int idProductoNutriente){
        for(ProductoNutriente productoNutriente: listaProductoNutriente){
            if (productoNutriente.getIdProductoNutriente()==idProductoNutriente){
                listaProductoNutriente.remove(productoNutriente);
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {

    }
}
