package com.example.esteban.brlifeadmin.ConexionWebService;

import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class CrudMantenedorComuna implements Response.Listener<JSONObject>,Response.ErrorListener {

    static RequestQueue request;
    static JsonObjectRequest jsonObjectRequest;
    static ProgressDialog progreso;

    public CrudMantenedorComuna(int idcomuna, int idprovincia, int idregion, String nombrecomuna, Context context, String tipoconsulta, String mantenedor) {
        request= Volley.newRequestQueue(context);
        switch (tipoconsulta){

            //Enviar a actividad para agregar Producto
            case "nuevo":
                agregarNuevoComuna(idprovincia,idcomuna, nombrecomuna,context,mantenedor);
                break;
            //Abrir Alert dialogo para agregar Provincia
            case "editar":
                editarComuna(idcomuna,idprovincia,idregion,nombrecomuna,context,mantenedor);
                break;

            //Abrir Alert dialogo para agregar Sabor
            case "eliminar":
                eliminarComuna(idcomuna,context,mantenedor);
                break;
        }
    }

    private void agregarNuevoComuna(int idprovincia, int idregion, String nombrecomuna, Context contexto, String mantenedor) {

        progreso=new ProgressDialog(contexto);
        progreso.setMessage("Cargando...");
        progreso.show();


        String url="http://www.brotherwareoficial.com/WebServices/Mantenedor"+mantenedor+".php?tipoconsulta=i&nombre"+mantenedor+"="
                +nombrecomuna + "&idProvincia="+idprovincia+"&idRegion="+idprovincia;


        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }

    private void editarComuna(int idcomuna, int idprovincia, int idregion, String nombrecomuna, Context contexto, String mantenedor){
        progreso=new ProgressDialog(contexto);
        progreso.setMessage("Cargando...");
        progreso.show();


        String url="http://www.brotherwareoficial.com/WebServices/Mantenedor"+mantenedor+".php?tipoconsulta=a&id"+mantenedor+"="+idcomuna+"&nombre"+mantenedor+"="
                +nombrecomuna+ "&idProvincia="+idprovincia+"&idRegion="+idregion;


        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);

    }

    private void eliminarComuna(int id, Context contexto,String mantenedor) {
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

    }

    @Override
    public void onResponse(JSONObject response) {

    }
}
