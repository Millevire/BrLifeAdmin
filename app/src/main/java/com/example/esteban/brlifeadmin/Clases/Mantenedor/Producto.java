package com.example.esteban.brlifeadmin.Clases.Mantenedor;

public class Producto {
    private int idProducto;
    private String codigoBarra;
    private String nombreProducto;
    private int idMarca;
    private int idSabor;
    private int fkTipoProducto;
    private float cantidadRacion;
    private int tipoMedicion;
    private int calorias;
    private float proteinas;
    private float grasaTotal;
    private float colesterol;
    private float carbohidratos;
    private float azucarTotal;
    private float sodio;
    private String nombreTipoProducto;

    public Producto() {
    }

    public Producto(int idProducto, String codigoBarra, String nombreProducto, int idMarca, int idSabor, int fkTipoProducto, float cantidadRacion, int tipoMedicion, int calorias, float proteinas, float grasaTotal, float colesterol, float carbohidratos, float azucarTotal, float sodio,String nombreTipoProducto) {
        this.idProducto = idProducto;
        this.codigoBarra = codigoBarra;
        this.nombreProducto = nombreProducto;
        this.idMarca = idMarca;
        this.idSabor = idSabor;
        this.fkTipoProducto = fkTipoProducto;
        this.cantidadRacion = cantidadRacion;
        this.tipoMedicion = tipoMedicion;
        this.calorias = calorias;
        this.proteinas = proteinas;
        this.grasaTotal = grasaTotal;
        this.colesterol = colesterol;
        this.carbohidratos = carbohidratos;
        this.azucarTotal = azucarTotal;
        this.sodio = sodio;
        this.nombreTipoProducto=nombreTipoProducto;
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

    public int getCalorias() {
        return calorias;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    public float getProteinas() {
        return proteinas;
    }

    public void setProteinas(float proteinas) {
        this.proteinas = proteinas;
    }

    public float getGrasaTotal() {
        return grasaTotal;
    }

    public void setGrasaTotal(float grasaTotal) {
        this.grasaTotal = grasaTotal;
    }

    public float getColesterol() {
        return colesterol;
    }

    public void setColesterol(float colesterol) {
        this.colesterol = colesterol;
    }

    public float getCarbohidratos() {
        return carbohidratos;
    }

    public void setCarbohidratos(float carbohidratos) {
        this.carbohidratos = carbohidratos;
    }

    public float getAzucarTotal() {
        return azucarTotal;
    }

    public void setAzucarTotal(float azucarTotal) {
        this.azucarTotal = azucarTotal;
    }

    public float getSodio() {
        return sodio;
    }

    public void setSodio(float sodio) {
        this.sodio = sodio;
    }

    public String getNombreTipoProducto() {
        return nombreTipoProducto;
    }

    public void setNombreTipoProducto(String nombreTipoProducto) {
        this.nombreTipoProducto = nombreTipoProducto;
    }
}
