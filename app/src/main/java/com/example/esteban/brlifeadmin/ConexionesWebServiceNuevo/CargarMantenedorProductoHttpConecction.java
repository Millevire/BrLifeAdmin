package com.example.esteban.brlifeadmin.ConexionesWebServiceNuevo;

import android.content.Context;

import com.example.esteban.brlifeadmin.Clases.Mantenedor.Mantenedor;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.Producto;
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

public class CargarMantenedorProductoHttpConecction {
    public static ArrayList<Producto> listaProducto =new ArrayList<>();

    public static void eliminar(int id){
        for(int x = 0; x< listaProducto.size(); ++x){
            if (listaProducto.get(x).getIdProducto()==id){
                listaProducto.remove(x);

            }


        }
    }

    public static void editar(int id, int fkTipoProducto, int marca, int sabor, String nombre, float cantidadracion, int tipomedicion, boolean validacion){
        for(int x = 0; x< listaProducto.size(); ++x){
            if (listaProducto.get(x).getIdProducto()==id){
                listaProducto.get(x).setFkTipoProducto(fkTipoProducto);
                listaProducto.get(x).setIdMarca(marca);
                listaProducto.get(x).setIdSabor(sabor);
                listaProducto.get(x).setNombreProducto(nombre);
                listaProducto.get(x).setCantidadRacion(cantidadracion);
                listaProducto.get(x).setCantidadRacion(cantidadracion);
                listaProducto.get(x).setTipoMedicion(tipomedicion);
                listaProducto.get(x).setValidacion(validacion);
            }


        }

    }

    public static void agregar(Producto producto){

        listaProducto.add(producto);
    }

    public static ArrayList<Producto> getListaProducto() {
        return listaProducto;
    }

    public static ArrayList<Producto> buscarMantenedorProducto(Context context, String mantenedo) throws IOException, JSONException {
        listaProducto = new ArrayList<>();
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


                Producto producto=null;

                listaProducto.clear();

                JSONArray json=response.optJSONArray(mantenedo);


                try {
                    for (int i=0; i<json.length(); i++){
                        JSONObject jsonObject=null;

                        int validacion = 0;

                        producto=new Producto();
                        jsonObject=json.getJSONObject(i);
                        producto.setIdProducto(jsonObject.getInt("Id_"+ mantenedo));
                        producto.setCodigoBarra(jsonObject.getString("CodigoBarra"));
                        producto.setFkTipoProducto(jsonObject.getInt("Id_Tipo"+ mantenedo));
                        producto.setIdMarca(jsonObject.getInt("Id_Marca"));
                        producto.setIdSabor(jsonObject.getInt("Id_Sabor"));
                        producto.setNombreProducto(jsonObject.getString("Nombre"+ mantenedo));
                        producto.setCantidadRacion((float) jsonObject.getDouble("CantidadRacion"));
                        producto.setTipoMedicion(jsonObject.getInt("TipoMedicion"));
                        validacion = jsonObject.getInt("Validacion");

                        if (validacion == 0){
                            producto.setValidacion(false);
                        }else{
                            producto.setValidacion(true);
                        }

                        producto.setNombreTipoProducto(jsonObject.getString("Nombre_Tipo"+mantenedo));
                        listaProducto.add(producto);

                    }

                }catch (JSONException e) {
                    e.printStackTrace();
                }

            }else
                throw new RuntimeException("No se puede contectar al servidor");
        }finally {
            conexion.disconnect();
        }

        return listaProducto;
    }

}
