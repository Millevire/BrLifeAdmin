package com.example.esteban.brlifeadmin.AlertDialog;

import android.content.Context;

import com.example.esteban.brlifeadmin.Clases.Mantenedor.Comuna;
import com.example.esteban.brlifeadmin.Clases.Mantenedor.TipoProducto;

public class AlertNuevoMantendorComuna {

    //metodo interfaz que comunicara Alert dialogo con actividad donde se implemente
    public interface FinalizoCuadroDialogoAgregar{
        void ResultadoCuadroDialogoAgregarComuna(boolean val);
    }

    private FinalizoCuadroDialogoAgregar interfaz;

    public AlertNuevoMantendorComuna(final Context contexto, final FinalizoCuadroDialogoAgregar actividad, final boolean tipo, final Comuna comuna, final String mantenedor) {
        interfaz = actividad;

    }
}
