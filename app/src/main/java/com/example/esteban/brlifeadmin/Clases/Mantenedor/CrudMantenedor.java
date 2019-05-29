package com.example.esteban.brlifeadmin.Clases.Mantenedor;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class CrudMantenedor implements Response.Listener<JSONObject>,Response.ErrorListener{



    static RequestQueue request;
    static JsonObjectRequest jsonObjectRequest;
    static ProgressDialog progreso;
    JsonArrayRequest jsonArrayRequest;



    public CrudMantenedor(String nombre, Context context, String tipoConsulta, int id , String mantenedor){
        request= Volley.newRequestQueue(context);
      if (tipoConsulta.equals("nuevo")){
          agregarNuevoTipoProducto(nombre,context,mantenedor);
      }else if (tipoConsulta.equals("editar")){
          editarTipoProducto(id,nombre,context,mantenedor);
      }else if (tipoConsulta.equals("eliminar")){
          eliminarTipoProducto(id, context,mantenedor);
      }


    }

    private void agregarNuevoTipoProducto(String nombre,Context contexto,String mantenedor) {
        progreso=new ProgressDialog(contexto);
        progreso.setMessage("Cargando...");
        progreso.show();


        String url="http://www.brotherwareoficial.com/WebServices/Mantenedor"+mantenedor+".php?tipoconsulta=i&nombre"+mantenedor+"="
                +nombre;


        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }

    private void editarTipoProducto(int id,String nombre,Context contexto,String mantenedor){
        progreso=new ProgressDialog(contexto);
        progreso.setMessage("Cargando...");
        progreso.show();


        String url="http://www.brotherwareoficial.com/WebServices/Mantenedor"+mantenedor+".php?tipoconsulta=a&id"+mantenedor+"="+id+"&nombre"+mantenedor+"="
                +nombre;


        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);

    }

    private void eliminarTipoProducto(int id, Context contexto,String mantenedor) {
        progreso=new ProgressDialog(contexto);
        progreso.setMessage("Cargando...");
        progreso.show();


        String url="http://www.brotherwareoficial.com/WebServices/Mantenedor"+mantenedor+".php?tipoconsulta=e&id"+mantenedor+"="
                +String.valueOf(id);
        //url=url.replace(" ","%20");

        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
        CargarBaseDeDatos.eliminar(id);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
       // Toast.makeText(context, "ERROR"+error.toString(), Toast.LENGTH_SHORT).show();
        Log.d("ERROR",error.toString());

    }

    @Override
    public void onResponse(JSONObject response) {
        progreso.hide();


    }
}
