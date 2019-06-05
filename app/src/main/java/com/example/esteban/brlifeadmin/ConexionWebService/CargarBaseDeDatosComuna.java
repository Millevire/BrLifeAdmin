package com.example.esteban.brlifeadmin.ConexionWebService;

import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.Comuna;
import com.example.esteban.brlifeadmin.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CargarBaseDeDatosComuna implements Response.Listener<JSONObject>,Response.ErrorListener{
    public static ArrayList<Comuna> listaComuna =new ArrayList<>();


    static RequestQueue request;
    static JsonObjectRequest jsonObjectRequest;
    static ProgressDialog progreso;
    public Context contexto;
    public String mantenedor;

    public CargarBaseDeDatosComuna(Context contexto, String mantenedor) {
        this.contexto = contexto;
        this.mantenedor = mantenedor;
        request= Volley.newRequestQueue(contexto);
        llenarBaseDeDatosComuna(contexto,mantenedor);
    }

    public static ArrayList<Comuna> getListaComuna() {return listaComuna;}

    public static void eliminar(int id){
        for(int x = 0; x< listaComuna.size(); ++x){
            if (listaComuna.get(x).getIdComuna()==id){
                listaComuna.remove(x);

            }

//
        }
    }

    public static Comuna buscar(int idMantenedor){
        for(Comuna Comuna: listaComuna){
            if (Comuna.getIdComuna()==idMantenedor){
                return Comuna;
            }
        }
        return null;
    }
    //Se pasan todos los parametros para actualizarlo en la lista
    public static void editar(int idcomuna ,String nombre, int idregion, int idprovincia){
        for(int x = 0; x< listaComuna.size(); ++x){
            if (listaComuna.get(x).getIdComuna()==idcomuna){
                listaComuna.get(x).setNombreComuna(nombre);
                listaComuna.get(x).setIdProvincia(idprovincia);
                listaComuna.get(x).setIdComuna(idregion);
            }


        }

    }

    public static void agregar(Comuna Comuna){

        listaComuna.add(Comuna);
    }




    private void llenarBaseDeDatosComuna(Context context, String mantenedor) {
        progreso=new ProgressDialog(context);
        progreso.setMessage(context.getString(R.string.mensajeBarraProgresoCargando));
        progreso.show();

        String url=context.getString(R.string.URLwebServicePart1)+mantenedor+context.getString(R.string.URLwebServicePart2);

        url=url.replace(" ","%20");

        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {
        Comuna Comuna = null;
        progreso.hide();
        listaComuna.clear();

        JSONArray json=response.optJSONArray(this.mantenedor);
        try {
            for (int i=0; i<json.length(); i++){
                JSONObject jsonObject=null;

                Comuna = new Comuna();
                jsonObject=json.getJSONObject(i);

                //Variables para los booleanos
                Comuna.setIdComuna(jsonObject.getInt("Id_"+ this.mantenedor));
                Comuna.setNombreComuna(jsonObject.getString("Nombre_"+ this.mantenedor));
                Comuna.setIdProvincia(jsonObject.getInt("Id_Provincia"));
                Comuna.setIdRegion(jsonObject.getInt("Id_Region"));

                listaComuna.add(Comuna);

            }

        }catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
