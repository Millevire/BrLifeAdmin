package com.example.esteban.brlifeadmin.Clases.Mantenedor;

public class TipoProducto {
    private int idTipoProducto;
    private String nombreTipoProducto;
    private boolean varidadSabor;
    private boolean variedadMarca;

    public TipoProducto() {
    }

    public TipoProducto(int idTipoProducto, String nombreTipoProducto, boolean varidadSabor, boolean variedadMarca) {
        this.idTipoProducto = idTipoProducto;
        this.nombreTipoProducto = nombreTipoProducto;
        this.varidadSabor = varidadSabor;
        this.variedadMarca = variedadMarca;
    }

    public int getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(int idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }

    public String getNombreTipoProducto() {
        return nombreTipoProducto;
    }

    public void setNombreTipoProducto(String nombreTipoProducto) {
        this.nombreTipoProducto = nombreTipoProducto;
    }

    public boolean isVaridadSabor() {
        return varidadSabor;
    }

    public void setVaridadSabor(boolean varidadSabor) {
        this.varidadSabor = varidadSabor;
    }

    public boolean isVariedadMarca() {
        return variedadMarca;
    }

    public void setVariedadMarca(boolean variedadMarca) {
        this.variedadMarca = variedadMarca;
    }

    @Override
    public String toString() {
        return nombreTipoProducto;
    }
}
