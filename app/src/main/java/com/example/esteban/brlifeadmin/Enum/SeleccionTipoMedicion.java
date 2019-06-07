package com.example.esteban.brlifeadmin.Enum;


import com.example.esteban.brlifeadmin.Clases.Mantenedor.Mantenedor;

import java.util.ArrayList;

public class   SeleccionTipoMedicion {
        public  static ArrayList<Mantenedor> TipoMedicion;

    public SeleccionTipoMedicion() {
        TipoMedicion = new ArrayList<>();
        TipoMedicion.add(new Mantenedor(1,"ml"));
        TipoMedicion.add(new Mantenedor(2,"gr"));

    }

    public static ArrayList<Mantenedor> getTipoMedicion() {
        return TipoMedicion;
    }
}

