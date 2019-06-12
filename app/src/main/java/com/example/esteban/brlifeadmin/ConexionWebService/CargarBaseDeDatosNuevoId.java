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
import com.example.esteban.brlifeadmin.Clases.Mantenedor.Mantenedor;
import com.example.esteban.brlifeadmin.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 *
 *
 */
public class CargarBaseDeDatosNuevoId implements Response.Listener<JSONObject>,Response.ErrorListener{
    //public static ArrayList<Mantenedor> listaMantenedors =new ArrayList<>();

    public static int nuevaid;
    static RequestQueue request;
    static JsonObjectRequest jsonObjectRequest;
    static ProgressDialog progreso;
    public Context contexto;
    public String mantenedor;

    /**
     *
     * @param context variable de contexto proveniente de donde se invoque metodo.
     * @param tipoMantenedor variable que nos dara el nombre del mantenedor al que estemos llamando.
     * Metodo contructor que llenara una lista
     */
    public CargarBaseDeDatosNuevoId(Context context, String tipoMantenedor){
        mantenedor=tipoMantenedor;
        contexto=context;
        request= Volley.newRequestQueue(context);
        llenarBaseDeDatosDosAtributos(context,mantenedor);

    }


    public static int getNuevaid() {
        return nuevaid;
    }

    public static void setNuevaid(int nuevaid) {
        CargarBaseDeDatosNuevoId.nuevaid = nuevaid;
    }

    private void llenarBaseDeDatosDosAtributos(Context context, String mantenedor) {
        progreso=new ProgressDialog(context);
        progreso.setMessage(context.getString(R.string.mensajeBarraProgresoCargando));
        progreso.show();

        //String url=context.getString(R.string.URLwebServicePart1)+mantenedor+context.getString(R.string.URLwebServicePart2);

        String url="http://www.brotherwareoficial.com/WebServices/MantenedorProducto.php?tipoconsulta=n";
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
        Mantenedor mantenedor =null;

        progreso.hide();

        JSONArray json=response.optJSONArray("Id_Producto_Nuevo");
        try {
            for (int i=0; i<json.length(); i++){
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);
                this.nuevaid = jsonObject.getInt("nuevoid");
                //Toast.makeText(contexto, "" + nuevaid, Toast.LENGTH_SHORT).show();
            }

        }catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
