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
public class CargarBaseDeDatosDosAtributos implements Response.Listener<JSONObject>,Response.ErrorListener{
    public static ArrayList<Mantenedor> listaMantenedors =new ArrayList<>();

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
    public CargarBaseDeDatosDosAtributos(Context context, String tipoMantenedor){
        mantenedor=tipoMantenedor;
        contexto=context;
        request= Volley.newRequestQueue(context);
        llenarBaseDeDatosTipoProducto(context,mantenedor);

    }

    public static void eliminar(int id){
        for(int x = 0; x< listaMantenedors.size(); ++x){
            if (listaMantenedors.get(x).getIdTipoProducto()==id){
                listaMantenedors.remove(x);

            }


        }
    }

    public static Mantenedor buscar(int idMantenedor){
        for(Mantenedor mantenedor: listaMantenedors){
            if (mantenedor.getIdTipoProducto()==idMantenedor){
                return mantenedor;
            }
        }
        return null;
    }

    public static void editar(int id,String nombre){
        for(int x = 0; x< listaMantenedors.size(); ++x){
            if (listaMantenedors.get(x).getIdTipoProducto()==id){
                listaMantenedors.get(x).setNombreTipoProducto(nombre);

            }


        }

    }

    public static void agregar(Mantenedor mantenedor){

        listaMantenedors.add(mantenedor);
    }

    public static ArrayList<Mantenedor> getListaMantenedors() {
        return listaMantenedors;
    }



    private void llenarBaseDeDatosTipoProducto(Context context, String mantenedor) {
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
        Mantenedor mantenedor =null;

        progreso.hide();
        listaMantenedors.clear();

        JSONArray json=response.optJSONArray(this.mantenedor);
        try {
            for (int i=0; i<json.length(); i++){
                JSONObject jsonObject=null;



                    mantenedor =new Mantenedor();
                    jsonObject=json.getJSONObject(i);
                    mantenedor.setIdTipoProducto(jsonObject.getInt("Id_"+ this.mantenedor));
                    // mantenedor.setIdTipoProducto(Integer.parseInt(jsonObject.toString()));
                    mantenedor.setNombreTipoProducto(jsonObject.getString("Nombre_"+ this.mantenedor));
                    listaMantenedors.add(mantenedor);

            }

        }catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
