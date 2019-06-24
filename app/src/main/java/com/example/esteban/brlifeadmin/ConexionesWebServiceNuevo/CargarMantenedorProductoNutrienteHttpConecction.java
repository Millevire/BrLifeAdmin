package com.example.esteban.brlifeadmin.ConexionesWebServiceNuevo;

import android.content.Context;
import android.widget.AdapterView;

import com.example.esteban.brlifeadmin.Clases.Mantenedor.Mantenedor;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.Producto;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.ProductoNutriente;
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

public class CargarMantenedorProductoNutrienteHttpConecction {
    public static ArrayList<ProductoNutriente> listaProductoNutriente=new ArrayList<>();
    public static ArrayList<ProductoNutriente>listaProductoNutrienteacion = new ArrayList<>();

    public int idProducto;
    public String mantenedor;

    public static ArrayList<ProductoNutriente> getListaProductoNutrienteacion() {
        return listaProductoNutrienteacion;
    }

    public static ArrayList<ProductoNutriente> getListaProductoNutriente() {
        return listaProductoNutriente;
    }

    public static void limpiarlista(){
        listaProductoNutriente.clear();
        listaProductoNutrienteacion.clear();
    }

    /**
     *
     * agregar un nuevo Producto nutriente
     * @param productoNutriente objeto a ingresar a lsta
     */
    public static void agregar(ProductoNutriente productoNutriente){
        listaProductoNutriente.add(productoNutriente);
        productoNutriente.setAccion("i");
        listaProductoNutrienteacion.add(productoNutriente);
    }


    /**
     *
     * metodo para eliminar un objeto de lista mediante id de objeto
     * @param idNutriente
     */
    public static void eliminar(int idNutriente){
        ProductoNutriente productoNutrientere = null;
        for(ProductoNutriente productoNutriente: listaProductoNutriente){
            if (productoNutriente.getIdNutriente()== idNutriente){
                productoNutriente.setAccion("e");
                listaProductoNutrienteacion.add(productoNutriente);
                //listaProductoNutriente.remove(productoNutriente);
                productoNutrientere = productoNutriente;
            }
        }
        listaProductoNutriente.remove(productoNutrientere);
    }

    public static ArrayList<ProductoNutriente> buscarMantenedorProductoNutriente(Context context, String mantenedo, int idProducto) throws IOException, JSONException {
        listaProductoNutriente = new ArrayList<>();


        URL url = new URL("http://www.brotherwareoficial.com/WebServices/Mantenedor"+ mantenedo +".php?tipoconsulta=s&idProducto="+idProducto);
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


                ProductoNutriente productoNutriente =null;

                listaProductoNutriente.clear();

                JSONArray json=response.optJSONArray(mantenedo);

                try {
                    for (int i=0; i<json.length(); i++){
                        JSONObject jsonObject=null;

                        productoNutriente = new ProductoNutriente();
                        jsonObject=json.getJSONObject(i);

                        productoNutriente.setIdProductoNutriente(jsonObject.getInt("Id_"+ mantenedo));
                        productoNutriente.setIdProducto(jsonObject.getInt("Id_Producto"));
                        productoNutriente.setIdNutriente(jsonObject.getInt("Id_Nutriente"));
                        productoNutriente.setValor((float) jsonObject.getDouble("Valor"));
                        productoNutriente.setNombreNutriente(jsonObject.getString("Nombre_Nutriente"));
                        productoNutriente.setAccion("a");
                        listaProductoNutriente.add(productoNutriente);
                        //listaProductoNutrienteacion.add(productoNutriente);
                    }

                }catch (JSONException e) {
                    e.printStackTrace();
                }

            }else
                throw new RuntimeException("No se puede contectar al servidor");
        }finally {
            conexion.disconnect();
        }

        return listaProductoNutriente;
    }
}
