package com.example.esteban.brlifeadmin.ConexionWebService;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class CrudMantenedorTipoProducto implements Response.Listener<JSONObject>,Response.ErrorListener {
    static RequestQueue request;
    static JsonObjectRequest jsonObjectRequest;
    static ProgressDialog progreso;




    public CrudMantenedorTipoProducto(String nombre, boolean variedadsabor, boolean variedadmarca, Context context, String tipoConsulta, int id , String mantenedor){
        request= Volley.newRequestQueue(context);
        if (tipoConsulta.equals("nuevo")){
            agregarNuevoTipoProducto(nombre, variedadsabor, variedadmarca, context ,mantenedor);
        }else if (tipoConsulta.equals("editar")){
            editarTipoProducto(id,nombre, variedadsabor, variedadmarca, context,mantenedor);
        }else if (tipoConsulta.equals("eliminar")){
            eliminarTipoProducto(id, context,mantenedor);
        }


    }

    private void agregarNuevoTipoProducto(String nombre, boolean sabor, boolean marca, Context contexto,String mantenedor) {

        int s, m;
        progreso=new ProgressDialog(contexto);
        progreso.setMessage("Cargando...");
        progreso.show();

        if (sabor){
            s = 1;
        }else{
            s =0;
        }

        if (marca){
            m =1;
        }else{
            m =0;
        }

        String url="http://www.brotherwareoficial.com/WebServices/Mantenedor"+mantenedor+".php?tipoconsulta=i&nombre"+mantenedor+"="
                +nombre + "&variedadSabor="+s+"&variedadMarca="+m;


        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }

    private void editarTipoProducto(int id,String nombre, boolean sabor, boolean marca, Context contexto,String mantenedor){
        progreso=new ProgressDialog(contexto);
        progreso.setMessage("Cargando...");
        progreso.show();
        int s, m;
        if (sabor){
            s = 1;
        }else{
            s =0;
        }

        if (marca){
            m =1;
        }else{
            m =0;
        }


        String url="http://www.brotherwareoficial.com/WebServices/Mantenedor"+mantenedor+".php?tipoconsulta=a&id"+mantenedor+"="+id+"&nombre"+mantenedor+"="
                +nombre+ "&variedadSabor="+s+"&variedadMarca="+m;


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
        CargarBaseDeDatosDosAtributos.eliminar(id);
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
