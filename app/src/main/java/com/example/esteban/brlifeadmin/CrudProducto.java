package com.example.esteban.brlifeadmin;

import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class CrudProducto  implements Response.Listener<JSONObject>,Response.ErrorListener{
    static RequestQueue request;
    static JsonObjectRequest jsonObjectRequest;
    static ProgressDialog progreso;

    public CrudProducto(int idProducto, String codigoBarra, int fkTipoProducto, int idMarca, int idSabor, String nombreProducto, float cantidadRacion, int tipoMedicion, boolean validacion, Context context, String tipoconsulta, String mantenedor) {
        request= Volley.newRequestQueue(context);
        switch (tipoconsulta){

            //Enviar a actividad para agregar Producto
            case "nuevo":
                agregarNuevoProducto(codigoBarra,fkTipoProducto,idMarca,idSabor,nombreProducto,cantidadRacion,tipoMedicion, validacion,context, mantenedor);
                break;
                //Abrir Alert dialogo para agregar Provincia
            case "editar":
                editarProducto(idProducto,codigoBarra,fkTipoProducto,idMarca,idSabor,nombreProducto,cantidadRacion,tipoMedicion, validacion,context, mantenedor);
                break;

            //Abrir Alert dialogo para agregar Sabor
            case "eliminar":
                eliminarProducto(idProducto,codigoBarra,context,mantenedor);
                break;
        }
    }

    private void agregarNuevoProducto(String codigoBarra, int fkTipoProducto, int idMarca, int idSabor, String nombreProducto, float cantidadRacion, int tipoMedicion, boolean validacion, Context context, String mantenedor) {

        progreso=new ProgressDialog(context);
        progreso.setMessage("Cargando...");
        progreso.show();


        String url="http://www.brotherwareoficial.com/WebServices/Mantenedor"+
                mantenedor+".php?tipoconsulta=i" +
                "&CodigoBarra="+codigoBarra +
                "&idTipoProducto="+fkTipoProducto +
                "&idMarca="+idMarca +
                "&idSabor=1"+idSabor +
                "&nombreProducto="+nombreProducto +
                "&CantidadRacion="+cantidadRacion +
                "&TipoMedicion="+tipoMedicion +
                "&validacion="+validacion;


        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }

    private void editarProducto(int idProducto, String codigoBarra, int fkTipoProducto, int idMarca, int idSabor, String nombreProducto, float cantidadRacion, int tipoMedicion, boolean validacion, Context context, String mantenedor) {

        progreso=new ProgressDialog(context);
        progreso.setMessage("Cargando...");
        progreso.show();


        String url="http://www.brotherwareoficial.com/WebServices/Mantenedor"+
                mantenedor+".php?tipoconsulta=i" +
                "&idProducto="+idProducto+
                "&CodigoBarra="+codigoBarra +
                "&idTipoProducto="+fkTipoProducto +
                "&idMarca="+idMarca +
                "&idSabor=1"+idSabor +
                "&nombreProducto="+nombreProducto +
                "&CantidadRacion="+cantidadRacion +
                "&TipoMedicion="+tipoMedicion +
                "&validacion="+validacion;


        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }


    private void eliminarProducto(int id, String codigoBarra, Context contexto,String mantenedor) {
        progreso=new ProgressDialog(contexto);
        progreso.setMessage("Cargando...");
        progreso.show();


        String url="http://www.brotherwareoficial.com/WebServices/Mantenedor"+
                mantenedor+".php?tipoconsulta=e&id"+mantenedor+"="
                +String.valueOf(id)+"&CodigoBarra="+codigoBarra;
        //url=url.replace(" ","%20");

        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }


    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {

    }
}
