package com.example.esteban.brlifeadmin.ConexionesWebServiceNuevo;

import android.content.Context;

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

public class CargarMantenedorDosAtributosHttpConecction {
    public static ArrayList<Mantenedor> listaMantenedors;


    public static ArrayList<Mantenedor> buscarMantenedorDosAtributos(Context context, String mantenedo) throws  IOException, JSONException {
        listaMantenedors = new ArrayList<>();
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


                Mantenedor mantenedor =null;

                listaMantenedors.clear();

                JSONArray json=response.optJSONArray(mantenedo);
                try {
                    for (int i=0; i<json.length(); i++){
                        JSONObject jsonObject=null;
                        
                        mantenedor =new Mantenedor();
                        jsonObject=json.getJSONObject(i);
                        mantenedor.setIdTipoProducto(jsonObject.getInt("Id_"+ mantenedo));
                        mantenedor.setNombreTipoProducto(jsonObject.getString("Nombre_"+ mantenedo));
                        listaMantenedors.add(mantenedor);

                    }

                }catch (JSONException e) {
                    e.printStackTrace();
                }

            }else
                throw new RuntimeException("No se puede contectar al servidor");
        }finally {
            conexion.disconnect();
        }

        return listaMantenedors;
    }

    public static ArrayList<Mantenedor> getListaMantenedors() {
        return listaMantenedors;
    }

    public static void eliminar(int id){
        for(int x = 0; x< listaMantenedors.size(); ++x){
            if (listaMantenedors.get(x).getIdTipoProducto()==id){
                listaMantenedors.remove(x);

            }


        }
    }

    public static Mantenedor buscar(int idMantenedor, Context context, String mantenedo){
        for(Mantenedor mantenedor: listaMantenedors){
            if (mantenedor.getIdTipoProducto()==idMantenedor){
                return mantenedor;
            }
        }
        return null;
    }

    public static Mantenedor buscar(int idMantenedor){
        for(Mantenedor mantenedor: listaMantenedors){
            if (mantenedor.getIdTipoProducto()==idMantenedor){
                return mantenedor;
            }
        }
        return null;
    }

    public static void agregar(Mantenedor mantenedor){
        listaMantenedors.add(mantenedor);
    }

    public static int buscarIndice(Mantenedor mantenedorTresAtributos){
        int position;
        position = listaMantenedors.indexOf(mantenedorTresAtributos);
        return position;
    }

    public static void editar(int id,String nombre){
        for(int x = 0; x< listaMantenedors.size(); ++x){
            if (listaMantenedors.get(x).getIdTipoProducto()==id){
                listaMantenedors.get(x).setNombreTipoProducto(nombre);

            }


        }

    }
}
