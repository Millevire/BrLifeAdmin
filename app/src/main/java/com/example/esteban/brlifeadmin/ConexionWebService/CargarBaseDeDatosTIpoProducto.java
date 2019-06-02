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
import com.example.esteban.brlifeadmin.Clases.Mantenedor.TipoProducto;
import com.example.esteban.brlifeadmin.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CargarBaseDeDatosTIpoProducto implements Response.Listener<JSONObject>,Response.ErrorListener{
    public static ArrayList<TipoProducto> listaTipoProducto =new ArrayList<>();


    static RequestQueue request;
    static JsonObjectRequest jsonObjectRequest;
    static ProgressDialog progreso;
    public Context contexto;
    public String mantenedor;

    public CargarBaseDeDatosTIpoProducto(Context contexto, String mantenedor) {
        this.contexto = contexto;
        this.mantenedor = mantenedor;
        request= Volley.newRequestQueue(contexto);
        llenarBaseDeDatosTipoProducto(contexto,mantenedor);
    }

    public static void eliminar(int id){
        for(int x = 0; x< listaTipoProducto.size(); ++x){
            if (listaTipoProducto.get(x).getIdTipoProducto()==id){
                listaTipoProducto.remove(x);

            }

//
        }
    }

    public static TipoProducto buscar(int idMantenedor){
        for(TipoProducto tipoProducto: listaTipoProducto){
            if (tipoProducto.getIdTipoProducto()==idMantenedor){
                return tipoProducto;
            }
        }
        return null;
    }

    public static void editar(int id,String nombre){
        for(int x = 0; x< listaTipoProducto.size(); ++x){
            if (listaTipoProducto.get(x).getIdTipoProducto()==id){
                listaTipoProducto.get(x).setNombreTipoProducto(nombre);

            }


        }

    }

    public static void agregar(TipoProducto tipoProducto){

        listaTipoProducto.add(tipoProducto);
    }




    private void llenarBaseDeDatosTipoProducto(Context context, String mantenedor) {
        progreso=new ProgressDialog(context);
        progreso.setMessage(context.getString(R.string.mensajeBarraProgresoCargando));
        progreso.show();

        String url=context.getString(R.string.URLwebServicePart1)+mantenedor+context.getString(R.string.URLwebServicePart2);

        url=url.replace(" ","%20");

        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }

    public static ArrayList<TipoProducto> getListaTipoProducto() {
        return listaTipoProducto;
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d(contexto.getString(R.string.ERROR),error.toString());

        progreso.hide();
    }

    @Override
    public void onResponse(JSONObject response) {
        TipoProducto tipoProducto = null;
        progreso.hide();
        listaTipoProducto.clear();

        JSONArray json=response.optJSONArray(this.mantenedor);
        try {
            for (int i=0; i<json.length(); i++){
                JSONObject jsonObject=null;

                tipoProducto = new TipoProducto();
                jsonObject=json.getJSONObject(i);

                //Variables para los booleanos
                int variedadsabor, variedadmarca;
                tipoProducto.setIdTipoProducto(jsonObject.getInt("Id_"+ this.mantenedor));
                tipoProducto.setNombreTipoProducto(jsonObject.getString("Nombre_"+ this.mantenedor));
                variedadmarca = jsonObject.getInt("VariedadMarca");
                variedadsabor = jsonObject.getInt("VariedadSabor");

                if (variedadmarca == 0){
                    tipoProducto.setVariedadMarca(false);
                }else{
                    tipoProducto.setVariedadMarca(true);
                }

                if (variedadsabor == 0){
                    tipoProducto.setVaridadSabor(false);
                }else{
                    tipoProducto.setVaridadSabor(true);
                }

                listaTipoProducto.add(tipoProducto);

            }

        }catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
