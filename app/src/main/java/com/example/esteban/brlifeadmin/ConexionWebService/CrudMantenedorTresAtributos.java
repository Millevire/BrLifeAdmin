package com.example.esteban.brlifeadmin.ConexionWebService;

import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.esteban.brlifeadmin.ConexionWebService.CargarBaseDeDatosMantenedorTresAtributos;

import org.json.JSONObject;

public class CrudMantenedorTresAtributos implements Response.Listener<JSONObject>,Response.ErrorListener {
    static RequestQueue request;
    static JsonObjectRequest jsonObjectRequest;
    static ProgressDialog progreso;



    //Nuevo mantenedor 3 atributos
    private void agregarNuevoMantenedorTresAtributos( Context contexto,String nombre,int fk_mantenedor, String mantenedor) {
        progreso=new ProgressDialog(contexto);
        progreso.setMessage("Cargando...");
        progreso.show();


        String url="http://www.brotherwareoficial.com/WebServices/Mantenedor"+mantenedor+".php?tipoconsulta=i&nombre"+mantenedor+"="
                +nombre;


        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }

    //Editar mantenedor 3 atributos
    private void editarTipoProducto(int id_mantenedor,String nombre,int fk_mantenedor,Context contexto,String mantenedor){
        progreso=new ProgressDialog(contexto);
        progreso.setMessage("Cargando...");
        progreso.show();


        String url="http://www.brotherwareoficial.com/WebServices/Mantenedor"+mantenedor+".php?tipoconsulta=a&id"+mantenedor+"="+id_mantenedor+"&nombre"+mantenedor+"="
                +nombre;


        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);

    }

    //Eliminar mantenedor 3 atributos
    private void eliminarTipoProducto(int id, Context contexto,String mantenedor) {
        progreso=new ProgressDialog(contexto);
        progreso.setMessage("Cargando...");
        progreso.show();


        String url="http://www.brotherwareoficial.com/WebServices/Mantenedor"+mantenedor+".php?tipoconsulta=e&id"+mantenedor+"="
                +String.valueOf(id);
        //url=url.replace(" ","%20");

        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
        CargarBaseDeDatosMantenedorTresAtributos.eliminar(id);
    }
    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {

    }
}
