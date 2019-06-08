package com.example.esteban.brlifeadmin.Clases.Mantenedor;

public class ProductoNutriente {
    private int idProductoNutriente;
    private int idProducto;
    private int idNutriente;
    private float valor;

    public ProductoNutriente(int idProductoNutriente, int idProducto, int idNutriente, float valor) {
        this.idProductoNutriente = idProductoNutriente;
        this.idProducto = idProducto;
        this.idNutriente = idNutriente;
        this.valor = valor;
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
}
