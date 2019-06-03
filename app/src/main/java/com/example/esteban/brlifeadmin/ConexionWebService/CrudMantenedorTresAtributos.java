package com.example.esteban.brlifeadmin.ConexionWebService;

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
import com.example.esteban.brlifeadmin.Clases.Mantenedor.MantenedorTresAtributos;
import com.example.esteban.brlifeadmin.ConexionWebService.CargarBaseDeDatosMantenedorTresAtributos;
import com.example.esteban.brlifeadmin.Enum.SelccionMantenedor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CrudMantenedorTresAtributos implements Response.Listener<JSONObject>,Response.ErrorListener {
    static RequestQueue request;
    static JsonObjectRequest jsonObjectRequest;
    static ProgressDialog progreso;
    String url;
    String tipoConsulta;
    String nombre;
    int fkMantenedor;
    public String mantenedor;
    Context contexto;


    public CrudMantenedorTresAtributos(Context context,String nombre,int fk,  String tipoConsulta, int id , String mantenedor){
        request= Volley.newRequestQueue(context);
        this.tipoConsulta=tipoConsulta;
        this.mantenedor=mantenedor;
        this.nombre=nombre;
        this.fkMantenedor=fk;
        this.contexto=context;
        if (tipoConsulta.equals("nuevo")){
            agregarNuevoMantenedorTresAtributos(context,nombre,fk,mantenedor);
        }else if (tipoConsulta.equals("editar")){
            editarMantenedorTresAtributos(id,nombre,fk,context,mantenedor);
        }else if (tipoConsulta.equals("eliminar")){
            eliminarMantenedorTresAtributos(id, context,mantenedor);
        }

    }

    //Nuevo mantenedor 3 atributos
    private void agregarNuevoMantenedorTresAtributos( Context contexto,String nombre,int fk_mantenedor, String mantenedor) {
        progreso=new ProgressDialog(contexto);
        progreso.setMessage("Cargando...");
        progreso.show();

        if (mantenedor.equals(SelccionMantenedor.Provincia.getSeleccion())){

            url="http://www.brotherwareoficial.com/WebServices/Mantenedor"+mantenedor+".php?tipoconsulta=i&nombre"+mantenedor+"="
                    +nombre+"&idRegion="+fk_mantenedor;
        }else{

             url="http://www.brotherwareoficial.com/WebServices/Mantenedor"+mantenedor+".php?tipoconsulta=i&nombre"+mantenedor+"="
                +nombre+"&idTipoProducto="+fk_mantenedor;
        }


        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }

    //Editar mantenedor 3 atributos
    private void editarMantenedorTresAtributos(int id_mantenedor,String nombre,int fk_mantenedor,Context contexto,String mantenedor){
        progreso=new ProgressDialog(contexto);
        progreso.setMessage("Cargando...");
        progreso.show();


        String url="http://www.brotherwareoficial.com/WebServices/Mantenedor"+mantenedor+".php?tipoconsulta=a&id"+mantenedor+"="+id_mantenedor+"&nombre"+mantenedor+"="
                +nombre;


        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);

    }

    //Eliminar mantenedor 3 atributos
    private void eliminarMantenedorTresAtributos(int id, Context contexto,String mantenedor) {
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
        progreso.hide();
        // Toast.makeText(context, "ERROR"+error.toString(), Toast.LENGTH_SHORT).show();
        Log.d("ERROR",error.toString());
        Toast.makeText(contexto, "error "+error.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        if (tipoConsulta.equals("nuevo")){
            JSONArray json=response.optJSONArray("Id_"+this.mantenedor+"_Nuevo");

            JSONObject jsonObject=null;
            try {
                jsonObject=json.getJSONObject(0);

           //  CargarBaseDeDatosMantenedorTresAtributos.agregar(new MantenedorTresAtributos(jsonObject.getInt("Id_"+ this.mantenedor+"_Nuevo"),fkMantenedor,nombre));

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        progreso.hide();
    }
}
