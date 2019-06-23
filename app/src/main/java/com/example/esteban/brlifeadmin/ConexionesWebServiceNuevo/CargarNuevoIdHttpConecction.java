package com.example.esteban.brlifeadmin.ConexionesWebServiceNuevo;

import android.app.ProgressDialog;
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

public class CargarNuevoIdHttpConecction {
    public static int nuevaid;
    public Context contexto;
    public String mantenedor;

    public static int getNuevaid() {
        return nuevaid;
    }

    public static int buscarMantenedorNuevoId(Context context, String mantenedo) throws IOException, JSONException {


        URL url = new URL("http://www.brotherwareoficial.com/WebServices/Mantenedor"+mantenedo+".php?tipoconsulta=n");
        HttpURLConnection conexion = null;
        try {
            conexion = (HttpURLConnection) url.openConnection();
            conexion.setConnectTimeout(20000);
            conexion.setReadTimeout(20000);
            conexion.setUseCaches(false);
            if (conexion.getResponseCode() == 200) {
                InputStream responseBody = conexion.getInputStream();
                InputStreamReader isr = new InputStreamReader(responseBody, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                String linea;
                StringBuilder responseSTR = new StringBuilder();
                while ((linea = br.readLine()) != null)
                    responseSTR.append(linea);

                JSONObject response = new JSONObject(responseSTR.toString());
                JSONArray json=response.optJSONArray("Id_Producto_Nuevo");

                try {
                    for (int i=0; i<json.length(); i++){
                        JSONObject jsonObject=null;
                        jsonObject=json.getJSONObject(i);
                        nuevaid = jsonObject.getInt("nuevoid");
                        //Toast.makeText(contexto, "" + nuevaid, Toast.LENGTH_SHORT).show();
                    }

                }catch (JSONException e) {
                    e.printStackTrace();
                }


            }else
                throw new RuntimeException("No se puede contectar al servidor");
        }finally {
            conexion.disconnect();
        }
        return nuevaid;
    }

}
