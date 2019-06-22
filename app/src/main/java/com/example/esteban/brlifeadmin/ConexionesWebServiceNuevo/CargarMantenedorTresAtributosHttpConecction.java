package com.example.esteban.brlifeadmin.ConexionesWebServiceNuevo;

import android.content.Context;

import com.example.esteban.brlifeadmin.Clases.Mantenedor.Mantenedor;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.MantenedorTresAtributos;
import com.example.esteban.brlifeadmin.Enum.SelccionMantenedor;
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

public class CargarMantenedorTresAtributosHttpConecction {
    public static ArrayList<MantenedorTresAtributos> listaMantenedorTresAtributos =new ArrayList<>();

    //lista especifica de Sabor para usos como llenar mas de un spinner en una actividad. la lista sigue siendo de tres atributos
    public static ArrayList<MantenedorTresAtributos> listaSabor =new ArrayList<>();

    //lista especifica de Marca para usos como llenar mas de un spinner en una actividad. la lista sigue siendo de tres atributos
    public static ArrayList<MantenedorTresAtributos> listaMarca =new ArrayList<>();


    //listas filtro

    public static ArrayList<MantenedorTresAtributos>listaFiltroMarca=new ArrayList<>();
    public static ArrayList<MantenedorTresAtributos>listaFiltrSabor=new ArrayList<>();

    public static String buscarMarca(int idMarca){
        for (MantenedorTresAtributos mantenedorTresAtributos: listaMarca){
            if (mantenedorTresAtributos.getIdMantenedorTresAtributos()==idMarca){
                return mantenedorTresAtributos.getNombreMantenedorTresAtributos();
            }

        }
        return "";

    }

    public static String buscaSabor(int idMarca){
        for (MantenedorTresAtributos mantenedorTresAtributos: listaSabor){
            if (mantenedorTresAtributos.getIdMantenedorTresAtributos()==idMarca){
                return mantenedorTresAtributos.getNombreMantenedorTresAtributos();
            }

        }
        return "";

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

    public static int buscarIndice(MantenedorTresAtributos mantenedorTresAtributos){
        int position;
        position = listaMantenedorTresAtributos.indexOf(mantenedorTresAtributos);
        return position;
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

    public static ArrayList<MantenedorTresAtributos>filtroSabor(int idTipoProducto){
        listaFiltrSabor.clear();
        for (MantenedorTresAtributos mantenedorTresAtributos: listaSabor){

            if(mantenedorTresAtributos.getFkMantenedorTresAtributos()==idTipoProducto){
                listaFiltrSabor.add(mantenedorTresAtributos);
            }
        }
        return listaFiltrSabor;

    }

    public static ArrayList<MantenedorTresAtributos>filtro(int idTipoProducto){
        listaFiltrSabor.clear();
        for (MantenedorTresAtributos mantenedorTresAtributos: listaMantenedorTresAtributos){

            if(mantenedorTresAtributos.getFkMantenedorTresAtributos()==idTipoProducto){
                listaFiltrSabor.add(mantenedorTresAtributos);
            }
        }
        return listaFiltrSabor;

    }


    public static ArrayList<MantenedorTresAtributos>filtroMarca(int idTipoProducto){
        listaFiltroMarca.clear();
        for (MantenedorTresAtributos mantenedorTresAtributos: listaMarca){

            if(mantenedorTresAtributos.getFkMantenedorTresAtributos()==idTipoProducto){
                listaFiltroMarca.add(mantenedorTresAtributos);
            }
        }

        return listaFiltroMarca;

    }

    public static void limpiarListaMarcaSabor(){
        listaSabor.clear();
        listaMarca.clear();
    }

    public static ArrayList<MantenedorTresAtributos> buscarMantenedorTresAtributos(Context context, String mantenedo) throws IOException, JSONException {
        MantenedorTresAtributos mantenedorTresAtributos = null;

        // progreso.hide();
        listaMantenedorTresAtributos.clear();
        URL url = new URL(context.getString(R.string.URLwebServicePart1)
                + mantenedo
                + context.getString(R.string.URLwebServicePart2));
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

                JSONArray json = response.optJSONArray(mantenedo);

                try {
                    for (int i = 0; i < json.length(); i++) {
                        JSONObject jsonObject = null;

                        mantenedorTresAtributos = new MantenedorTresAtributos();
                        jsonObject = json.getJSONObject(i);
                        mantenedorTresAtributos.setIdMantenedorTresAtributos(jsonObject.getInt("Id_" + mantenedo));
                        SelccionMantenedor selccionMantenedor = SelccionMantenedor.valueOf(mantenedo);
                        switch (selccionMantenedor) {
                            case Sabor:
                                mantenedorTresAtributos.setFkMantenedorTresAtributos(jsonObject.getInt("Id_TipoProducto"));
                                break;
                            case Marca:
                                mantenedorTresAtributos.setFkMantenedorTresAtributos(jsonObject.getInt("Id_TipoProducto"));
                                break;
                            case Provincia:
                                mantenedorTresAtributos.setFkMantenedorTresAtributos(jsonObject.getInt("Id_Region"));
                                break;
                            default:
                                break;
                        }

                        mantenedorTresAtributos.setNombreMantenedorTresAtributos(jsonObject.getString("Nombre_" + mantenedo));

                        switch (selccionMantenedor) {
                            case Sabor:
                                listaSabor.add(mantenedorTresAtributos);
                                break;
                            case Marca:
                                listaMarca.add(mantenedorTresAtributos);
                                break;
                        }
                        listaMantenedorTresAtributos.add(mantenedorTresAtributos);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else
                throw new RuntimeException("No se puede contectar al servidor");
        } finally {
            conexion.disconnect();
        }

        return listaMantenedorTresAtributos;
    }

}
