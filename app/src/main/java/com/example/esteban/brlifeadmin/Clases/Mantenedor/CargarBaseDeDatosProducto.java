package com.example.esteban.brlifeadmin.Clases.Mantenedor;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.Mantenedor;
import com.example.esteban.brlifeadmin.Producto;
import com.example.esteban.brlifeadmin.R;
import com.example.esteban.brlifeadmin.SeleccionTipoMedicion;

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

    public static void editar(int id,String nombre,int marca,int fkTipoProducto, int calorias, float proteinas, float grasaTotal, float colesterol, float carbohidratos, float azucarTotal, float sodio){
        for(int x = 0; x< listaProducto.size(); ++x){
            if (listaProducto.get(x).getIdProducto()==id){
                listaProducto.get(x).setNombreProducto(nombre);
                listaProducto.get(x).setIdMarca(marca);
                listaProducto.get(x).setFkTipoProducto(fkTipoProducto);
                listaProducto.get(x).setCalorias(calorias);
                listaProducto.get(x).setProteinas(proteinas);
                listaProducto.get(x).setGrasaTotal(grasaTotal);
                listaProducto.get(x).setColesterol(colesterol);
                listaProducto.get(x).setCarbohidratos(carbohidratos);
                listaProducto.get(x).setAzucarTotal(azucarTotal);
                listaProducto.get(x).setSodio(sodio);
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
                producto.setCalorias(jsonObject.getInt("Calorias"));
                producto.setProteinas((float) jsonObject.getDouble("Proteina"));
                producto.setGrasaTotal((float) jsonObject.getDouble("GrasasTotales"));
                producto.setColesterol((float) jsonObject.getDouble("Colesterol"));
                producto.setCarbohidratos((float) jsonObject.getDouble("CarboHidratos"));
                producto.setAzucarTotal((float) jsonObject.getDouble("AzucaresTotales"));
                producto.setSodio((float) jsonObject.getDouble("Sodio"));
                producto.setNombreTipoProducto(jsonObject.getString("Nombre_TipoProducto"));
                listaProducto.add(producto);

            }

        }catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
