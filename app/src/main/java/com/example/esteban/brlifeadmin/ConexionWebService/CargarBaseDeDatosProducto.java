package com.example.esteban.brlifeadmin.ConexionWebService;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.Producto;
import com.example.esteban.brlifeadmin.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CargarBaseDeDatosProducto implements Response.Listener<JSONObject>,Response.ErrorListener {
    public static ArrayList<Producto> listaProducto =new ArrayList<>();


    static RequestQueue request;
    static JsonObjectRequest jsonObjectRequest;
    static ProgressDialog progreso;
    public Context contexto;
    public String mantenedor;


    public CargarBaseDeDatosProducto(Context context, String tipoMantenedor){
        contexto=context;
        mantenedor=tipoMantenedor;
        request= Volley.newRequestQueue(context);
        llenarBaseDeDatosProducto(context,mantenedor);


    }

    public static void eliminar(int id){
        for(int x = 0; x< listaProducto.size(); ++x){
            if (listaProducto.get(x).getIdProducto()==id){
                listaProducto.remove(x);

            }


        }
    }

    public static void editar(int id, int fkTipoProducto, int marca, int sabor, String nombre, float cantidadracion, int tipomedicion, boolean validacion){
        for(int x = 0; x< listaProducto.size(); ++x){
            if (listaProducto.get(x).getIdProducto()==id){
                listaProducto.get(x).setFkTipoProducto(fkTipoProducto);
                listaProducto.get(x).setIdMarca(marca);
                listaProducto.get(x).setIdSabor(sabor);
                listaProducto.get(x).setNombreProducto(nombre);
                listaProducto.get(x).setCantidadRacion(cantidadracion);
                listaProducto.get(x).setCantidadRacion(cantidadracion);
                listaProducto.get(x).setTipoMedicion(tipomedicion);
                listaProducto.get(x).setValidacion(validacion);
            }


        }

    }

    public static void agregar(Producto producto){

        listaProducto.add(producto);
    }

    public static ArrayList<Producto> getListaProducto() {
        return listaProducto;
    }



    private void llenarBaseDeDatosProducto(Context context, String mantenedor) {
        progreso=new ProgressDialog(context);
        progreso.setMessage(context.getString(R.string.mensajeBarraProgresoCargando));
        progreso.show();

        String url=context.getString(R.string.URLwebServicePart1)+mantenedor+context.getString(R.string.URLwebServicePart2);

        //String url="http://www.brotherwareoficial.com/WebServices/MantenedorTipoProducto.php?tipoconsulta=s";
        url=url.replace(" ","%20");

        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        request.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d(contexto.getString(R.string.ERROR),error.toString());

        progreso.hide();
    }

    @Override
    public void onResponse(JSONObject response) {
        Producto producto =null;

        progreso.hide();
        listaProducto.clear();

        JSONArray json=response.optJSONArray(this.mantenedor);
        try {
            for (int i=0; i<json.length(); i++){
                JSONObject jsonObject=null;

                int validacion = 0;

                 producto=new Producto();
                jsonObject=json.getJSONObject(i);
                producto.setIdProducto(jsonObject.getInt("Id_"+ this.mantenedor));
                producto.setCodigoBarra(jsonObject.getString("CodigoBarra"));
                producto.setFkTipoProducto(jsonObject.getInt("Id_Tipo"+ this.mantenedor));
                producto.setIdMarca(jsonObject.getInt("Id_Marca"));
                producto.setIdSabor(jsonObject.getInt("Id_Sabor"));
                producto.setNombreProducto(jsonObject.getString("Nombre"+ this.mantenedor));
                producto.setCantidadRacion((float) jsonObject.getDouble("CantidadRacion"));
                producto.setTipoMedicion(jsonObject.getInt("TipoMedicion"));
                validacion = jsonObject.getInt("Validacion");

                if (validacion == 0){
                    producto.setValidacion(false);
                }else{
                    producto.setValidacion(true);
                }

                producto.setNombreTipoProducto(jsonObject.getString("Nombre_Tipo"+this.mantenedor));
                listaProducto.add(producto);

            }

        }catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
