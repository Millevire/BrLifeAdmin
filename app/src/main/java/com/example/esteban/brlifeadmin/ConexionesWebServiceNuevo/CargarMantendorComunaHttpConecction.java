package com.example.esteban.brlifeadmin.ConexionesWebServiceNuevo;

import android.content.Context;

import com.example.esteban.brlifeadmin.Clases.Mantenedor.Comuna;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.Mantenedor;
import com.example.esteban.brlifeadmin.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class CargarMantendorComunaHttpConecction {
    public static ArrayList<Comuna> listaComuna =new ArrayList<>();

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


    public static ArrayList<Comuna> buscarMantenedorComuna(Context context, String mantenedo) throws IOException, JSONException {
        listaComuna = new ArrayList<>();
        URL url = new URL(context.getString(R.string.URLwebServicePart1)
                +mantenedo
                +context.getString(R.string.URLwebServicePart2));
        HttpURLConnection conexion = null;
        try {
            conexion = (HttpURLConnection) url.openConnection();
            conexion.setConnectTimeout(20000);
            conexion.setReadTimeout(20000);
            conexion.setUseCaches(false);
            if (conexion.getResponseCode() == 200) {
                String jsonCompleto = "";
                InputStream responseBody = conexion.getInputStream();
                InputStreamReader isr = new InputStreamReader(responseBody, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                String linea;
                StringBuilder responseSTR = new StringBuilder();
                while ((linea = br.readLine()) != null)
                    responseSTR.append(linea);

                JSONObject response = new JSONObject(responseSTR.toString());


                Comuna Comuna =null;

                listaComuna.clear();

                JSONArray json=response.optJSONArray(mantenedo);


                try {
                    for (int i=0; i<json.length(); i++){
                        JSONObject jsonObject=null;

                        Comuna = new Comuna();
                        jsonObject=json.getJSONObject(i);

                        //Variables para los booleanos
                        Comuna.setIdComuna(jsonObject.getInt("Id_"+ mantenedo));
                        Comuna.setNombreComuna(jsonObject.getString("Nombre_"+ mantenedo));
                        Comuna.setIdProvincia(jsonObject.getInt("Id_Provincia"));
                        Comuna.setIdRegion(jsonObject.getInt("Id_Region"));

                        listaComuna.add(Comuna);

                    }

                }catch (JSONException e) {
                    e.printStackTrace();
                }




            }else
                throw new RuntimeException("No se puede contectar al servidor");
        }finally {
            conexion.disconnect();
        }

        return listaComuna;
    }
}
