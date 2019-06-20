package com.example.esteban.brlifeadmin.ConexionWebService;

import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.Producto;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.ProductoNutriente;
import com.example.esteban.brlifeadmin.Enum.SelccionMantenedor;
import com.example.esteban.brlifeadmin.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CargarBaseDeDatosProductoNutriente implements Response.Listener<JSONObject>,Response.ErrorListener{
    public static ArrayList<ProductoNutriente>listaProductoNutriente=new ArrayList<>();
    public static ArrayList<ProductoNutriente>listaProductoNutrienteacion = new ArrayList<>();

    static RequestQueue request;
    static JsonObjectRequest jsonObjectRequest;
    static ProgressDialog progreso;
    public Context contexto;
    public int idProducto;
    public String mantenedor;

    public static ArrayList<ProductoNutriente> getListaProductoNutrienteacion() {
        return listaProductoNutrienteacion;
    }


    /**
     * Constructor para agregar un nuevo registro producto nutriente
     * @param contexto
     */


    /**
     * Constructor para traer producto nutriente especifico
     * @param contexto contexto de actividad
     * @param idProducto id de producto para busqueda
     */
    public CargarBaseDeDatosProductoNutriente(Context contexto,int idProducto, String tipoMantenedor){
        this.contexto=contexto;
        this.idProducto=idProducto;
        mantenedor=tipoMantenedor;
        request= Volley.newRequestQueue(contexto);
        llenarBaseDeDatosProductoNutriente(contexto, idProducto, mantenedor);
    }


    private void llenarBaseDeDatosProductoNutriente(Context context, int idProducto, String mantenedor) {
        progreso=new ProgressDialog(context);
        progreso.setMessage(context.getString(R.string.mensajeBarraProgresoCargando));
        progreso.show();

        String url="http://www.brotherwareoficial.com/WebServices/Mantenedor"+ mantenedor +".php?tipoconsulta=s&idProducto="+idProducto;

        url=url.replace(" ","%20");

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
        productoNutriente.setAccion("i");
        listaProductoNutrienteacion.add(productoNutriente);
    }


    /**
     *
     * metodo para eliminar un objeto de lista mediante id de objeto
     * @param idNutriente
     */
    public static void eliminar(int idNutriente){
        ProductoNutriente productoNutrientere = null;
        for(ProductoNutriente productoNutriente: listaProductoNutriente){
            if (productoNutriente.getIdNutriente()== idNutriente){
                productoNutriente.setAccion("e");
                listaProductoNutrienteacion.add(productoNutriente);
                //listaProductoNutriente.remove(productoNutriente);
                productoNutrientere = productoNutriente;
            }
        }
        listaProductoNutriente.remove(productoNutrientere);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {
        ProductoNutriente productoNutriente = null;
        progreso.hide();
        listaProductoNutriente.clear();

        JSONArray json=response.optJSONArray(this.mantenedor);
        try {
            for (int i=0; i<json.length(); i++){
                JSONObject jsonObject=null;

                productoNutriente = new ProductoNutriente();
                jsonObject=json.getJSONObject(i);

                productoNutriente.setIdProductoNutriente(jsonObject.getInt("Id_"+ this.mantenedor));
                productoNutriente.setIdProducto(jsonObject.getInt("Id_Producto"));
                productoNutriente.setIdNutriente(jsonObject.getInt("Id_Nutriente"));
                productoNutriente.setValor((float) jsonObject.getDouble("Valor"));
                productoNutriente.setNombreNutriente(jsonObject.getString("Nombre_Nutriente"));
                productoNutriente.setAccion("a");
                listaProductoNutriente.add(productoNutriente);
                //listaProductoNutrienteacion.add(productoNutriente);
            }

        }catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
