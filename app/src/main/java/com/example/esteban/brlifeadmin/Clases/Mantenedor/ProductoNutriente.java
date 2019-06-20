package com.example.esteban.brlifeadmin.Clases.Mantenedor;

public class ProductoNutriente {
    private int idProductoNutriente;
    private int idProducto;
    private int idNutriente;
    private String nombreNutriente;
    private float valor;
    private String accion;

    public ProductoNutriente(int idProductoNutriente, int idProducto, int idNutriente, float valor, String accion) {
        this.idProductoNutriente = idProductoNutriente;
        this.idProducto = idProducto;
        this.idNutriente = idNutriente;
        this.valor = valor;
        this.accion = accion;
    }

    public ProductoNutriente(int idProductoNutriente, int idProducto, int idNutriente, String nombreNutriente, float valor, String accion) {
        this.idProductoNutriente = idProductoNutriente;
        this.idProducto = idProducto;
        this.idNutriente = idNutriente;
        this.nombreNutriente = nombreNutriente;
        this.valor = valor;
        this.accion = accion;
    }

    public ProductoNutriente() {
    }

    public int getIdProductoNutriente() {
        return idProductoNutriente;
    }

    public void setIdProductoNutriente(int idProductoNutriente) {
        this.idProductoNutriente = idProductoNutriente;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdNutriente() {
        return idNutriente;
    }

    public void setIdNutriente(int idNutriente) {
        this.idNutriente = idNutriente;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getNombreNutriente() {
        return nombreNutriente;
    }

    public void setNombreNutriente(String nombreNutriente) {
        this.nombreNutriente = nombreNutriente;
    }
}
