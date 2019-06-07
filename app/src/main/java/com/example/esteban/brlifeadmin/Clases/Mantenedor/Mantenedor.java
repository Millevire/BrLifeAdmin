package com.example.esteban.brlifeadmin.Clases.Mantenedor;



public class Mantenedor {
    private int idTipoProducto;
    private String nombreTipoProducto;

    public Mantenedor() {
    }


    public Mantenedor(String nombreTipoProducto) {
        this.nombreTipoProducto = nombreTipoProducto;
    }

    public Mantenedor(int idTipoProducto, String nombreTipoProducto) {
        this.idTipoProducto = idTipoProducto;
        this.nombreTipoProducto = nombreTipoProducto;
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

    @Override
    public String toString() {
        return nombreTipoProducto;
    }
}
