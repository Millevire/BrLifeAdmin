package com.example.esteban.brlifeadmin;

import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.esteban.brlifeadmin.ConexionesWebServiceNuevo.CargarMantenedorProductoHttpConecction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CrudProducto  implements Response.Listener<JSONObject>,Response.ErrorListener{
    static RequestQueue request;
    static JsonObjectRequest jsonObjectRequest;
    static ProgressDialog progreso;
    public static String tipoconsula;
    public static int nuevaid;

    public CrudProducto(int idProducto, String codigoBarra, int fkTipoProducto, int idMarca, int idSabor, String nombreProducto, float cantidadRacion, int tipoMedicion, boolean validacion, Context context, String tipoconsulta, String mantenedor) {
        request= Volley.newRequestQueue(context);
        switch (tipoconsulta){

            //Enviar a actividad para agregar Producto
            case "nuevo":
                this.tipoconsula = tipoconsulta;
                agregarNuevoProducto(context, mantenedor);
                break;
                //Abrir Alert dialogo para agregar Provincia
            case "editar":
                editarProducto(idProducto,codigoBarra,fkTipoProducto,idMarca,idSabor,nombreProducto,cantidadRacion,tipoMedicion, validacion,context, mantenedor);
                break;

            //Abrir Alert dialogo para agregar Sabor
            case "eliminar":
                eliminarProducto(idProducto,context,mantenedor);
                break;
        }
    }

    public CrudProducto(int idProducto, Context context, String tipoconsulta, String mantenedor) {
        request= Volley.newRequestQueue(context);
        this.tipoconsula = tipoconsulta;

        switch (tipoconsulta){
            //Enviar a actividad para agregar Producto
            case "nuevo":
                agregarNuevoProducto(context, mantenedor);
                break;

            //Abrir Alert dialogo para agregar Sabor
            case "eliminar":
                eliminarProducto(idProducto,context,mantenedor);
                break;
        }
    }

    private void agregarNuevoProducto(Context context, String mantenedor) {

        progreso=new ProgressDialog(context);
        progreso.setMessage("Cargando...");
        progreso.show();


        String url="http://www.brotherwareoficial.com/WebServices/Mantenedor"+
                mantenedor+".php?tipoconsulta=n";


        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }

    private void editarProducto(int idProducto, String codigoBarra, int fkTipoProducto, int idMarca, int idSabor, String nombreProducto, float cantidadRacion, int tipoMedicion, boolean validacion, Context context, String mantenedor) {

        progreso=new ProgressDialog(context);
        progreso.setMessage("Cargando...");
        progreso.show();

        int val = (validacion) ? 1 :0;

        String url="http://www.brotherwareoficial.com/WebServices/Mantenedor"+
                mantenedor+".php?tipoconsulta=a" +
                "&idProducto="+idProducto+
                "&CodigoBarra="+codigoBarra +
                "&idTipoProducto="+fkTipoProducto +
                "&idMarca="+idMarca +
                "&idSabor="+idSabor +
                "&nombreProducto="+nombreProducto +
                "&CantidadRacion="+cantidadRacion +
                "&TipoMedicion="+tipoMedicion +
                "&validacion="+val;


        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }


    private void eliminarProducto(int id, Context contexto,String mantenedor) {
        progreso=new ProgressDialog(contexto);
        progreso.setMessage("Cargando...");
        progreso.show();

        CargarMantenedorProductoHttpConecction.eliminar(id);


        String url="http://www.brotherwareoficial.com/WebServices/Mantenedor"+
                mantenedor+".php?tipoconsulta=e&id"+mantenedor+"="
                +String.valueOf(id);
        //url=url.replace(" ","%20");

        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);


    }

    public static int getNuevaid() {
        return nuevaid;
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {
        progreso.hide();

        if (tipoconsula != null) {
            if (tipoconsula.equals("nuevo") && nuevaid == 0) {
                JSONArray json = response.optJSONArray("Id_Producto_Nuevo");
                try {
                    for (int i = 0; i < json.length(); i++) {
                        JSONObject jsonObject = null;
                        jsonObject = json.getJSONObject(i);
                        this.nuevaid = jsonObject.getInt("nuevoid");
                        //Toast.makeText(contexto, "" + nuevaid, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
