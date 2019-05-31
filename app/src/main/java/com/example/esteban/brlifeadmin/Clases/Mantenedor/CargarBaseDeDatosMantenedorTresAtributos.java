package com.example.esteban.brlifeadmin.Clases.Mantenedor;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.esteban.brlifeadmin.Enum.SelccionMantenedor;
import com.example.esteban.brlifeadmin.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CargarBaseDeDatosMantenedorTresAtributos implements Response.Listener<JSONObject>,Response.ErrorListener {

    public static ArrayList<MantenedorTresAtributos> listaMantenedorTresAtributos =new ArrayList<>();

    static RequestQueue request;
    static JsonObjectRequest jsonObjectRequest;
    static ProgressDialog progreso;
    public Context contexto;
    public String mantenedor;


    public CargarBaseDeDatosMantenedorTresAtributos(Context context, String tipoMantenedor){
        mantenedor=tipoMantenedor;
        contexto=context;
        request= Volley.newRequestQueue(context);
        llenarBaseDeDatosTipoProducto(context,mantenedor);

    }

    public static void eliminar(int id){
        for(int x = 0; x< listaMantenedorTresAtributos.size(); ++x){
            if (listaMantenedorTresAtributos.get(x).getIdMantenedorTresAtributos()==id){
                listaMantenedorTresAtributos.remove(x);

            }


        }
    }

    public static MantenedorTresAtributos buscar(int idMantenedor){
        for(MantenedorTresAtributos mantenedorTresAtributos: listaMantenedorTresAtributos){
            if (mantenedorTresAtributos.getIdMantenedorTresAtributos()==idMantenedor){
                return mantenedorTresAtributos ;
            }
        }
        return null;
    }

    public static void editar(int id,int idTipoProducto,String nombre){
        for(int x = 0; x< listaMantenedorTresAtributos.size(); ++x){
            if (listaMantenedorTresAtributos.get(x).getIdMantenedorTresAtributos()==id){
                listaMantenedorTresAtributos.get(x).setNombreMantenedorTresAtributos(nombre);
                listaMantenedorTresAtributos.get(x).setFkMantenedorTresAtributos(idTipoProducto);

            }


        }

    }

    public static void agregar(MantenedorTresAtributos mantenedorTresAtributos){

       listaMantenedorTresAtributos.add(mantenedorTresAtributos);
    }

    public static ArrayList<MantenedorTresAtributos> getListaMantenedorTresAtributos() {
        return listaMantenedorTresAtributos;
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
        MantenedorTresAtributos mantenedorTresAtributos =null;

        progreso.hide();
        listaMantenedorTresAtributos.clear();

        JSONArray json=response.optJSONArray(this.mantenedor);
        try {
            for (int i=0; i<json.length(); i++){
                JSONObject jsonObject=null;



                mantenedorTresAtributos =new MantenedorTresAtributos();
                jsonObject=json.getJSONObject(i);
                mantenedorTresAtributos.setIdMantenedorTresAtributos(jsonObject.getInt("Id_"+ this.mantenedor));
                SelccionMantenedor selccionMantenedor  = SelccionMantenedor.valueOf(mantenedor);
                switch (selccionMantenedor){
                    case Sabor:
                        mantenedorTresAtributos.setFkMantenedorTresAtributos(jsonObject.getInt("Id_TipoProducto"));
                        break;
                    case Marca:
                        mantenedorTresAtributos.setFkMantenedorTresAtributos(jsonObject.getInt("Id_TipoProducto"));
                        break;
                    case Provincia:
                        mantenedorTresAtributos.setFkMantenedorTresAtributos(jsonObject.getInt("Id_Region"));
                        break;
                    default:break;


                }

                // mantenedor.setIdTipoProducto(Integer.parseInt(jsonObject.toString()));
                mantenedorTresAtributos.setNombreMantenedorTresAtributos(jsonObject.getString("Nombre_"+ this.mantenedor));
                listaMantenedorTresAtributos.add(mantenedorTresAtributos);

            }

        }catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
