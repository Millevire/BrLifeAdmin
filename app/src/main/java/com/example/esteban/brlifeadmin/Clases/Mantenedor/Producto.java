package com.example.esteban.brlifeadmin.Clases.Mantenedor;

import java.io.Serializable;

public class Producto implements Serializable {
    private int idProducto;
    private String codigoBarra;
    private int fkTipoProducto;
    private int idMarca;
    private int idSabor;
    private String nombreProducto;
    private float cantidadRacion;
    private int tipoMedicion;
    private boolean validacion;
    private String nombreTipoProducto;

    public Producto() {
    }

    public Producto(int idProducto, String codigoBarra, int fkTipoProducto, int idMarca, int idSabor, String nombreProducto,  float cantidadRacion, int tipoMedicion, boolean validacion, String nombreTipoProducto) {
        this.idProducto = idProducto;
        this.codigoBarra = codigoBarra;
        this.fkTipoProducto = fkTipoProducto;
        this.idMarca = idMarca;
        this.idSabor = idSabor;
        this.nombreProducto = nombreProducto;
        this.cantidadRacion = cantidadRacion;
        this.tipoMedicion = tipoMedicion;
        this.validacion = validacion;
        this.nombreTipoProducto = nombreTipoProducto;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public int getIdSabor() {
        return idSabor;
    }

    public void setIdSabor(int idSabor) {
        this.idSabor = idSabor;
    }

    public int getFkTipoProducto() {
        return fkTipoProducto;
    }

    public void setFkTipoProducto(int fkTipoProducto) {
        this.fkTipoProducto = fkTipoProducto;
    }

    public float getCantidadRacion() {
        return cantidadRacion;
    }

    public void setCantidadRacion(float cantidadRacion) {
        this.cantidadRacion = cantidadRacion;
    }

    public int getTipoMedicion() {
        return tipoMedicion;
    }

    public void setTipoMedicion(int tipoMedicion) {
        this.tipoMedicion = tipoMedicion;
    }

    public String getNombreTipoProducto() {
        return nombreTipoProducto;
    }

    public boolean isValidacion() {
        return validacion;
    }

    public void setValidacion(boolean validacion) {
        this.validacion = validacion;
    }

    public void setNombreTipoProducto(String nombreTipoProducto) {
        this.nombreTipoProducto = nombreTipoProducto;
    }
}
