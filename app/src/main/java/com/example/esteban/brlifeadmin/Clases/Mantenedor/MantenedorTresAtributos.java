package com.example.esteban.brlifeadmin.Clases.Mantenedor;

public class MantenedorTresAtributos {
    private int idMantenedorTresAtributos;
    private int fkMantenedorTresAtributos;
    private String nombreMantenedorTresAtributos;

    public MantenedorTresAtributos(int idMantenedorTresAtributos, int fkMantenedorTresAtributos, String nombreMantenedorTresAtributos) {
        this.idMantenedorTresAtributos = idMantenedorTresAtributos;
        this.fkMantenedorTresAtributos = fkMantenedorTresAtributos;
        this.nombreMantenedorTresAtributos = nombreMantenedorTresAtributos;
    }

    public MantenedorTresAtributos() {
    }

    public int getIdMantenedorTresAtributos() {
        return idMantenedorTresAtributos;
    }

    public void setIdMantenedorTresAtributos(int idMantenedorTresAtributos) {
        this.idMantenedorTresAtributos = idMantenedorTresAtributos;
    }

    public int getFkMantenedorTresAtributos() {
        return fkMantenedorTresAtributos;
    }

    public void setFkMantenedorTresAtributos(int idTipoProducto) {
        this.fkMantenedorTresAtributos = idTipoProducto;
    }

    public String getNombreMantenedorTresAtributos() {
        return nombreMantenedorTresAtributos;
    }

    public void setNombreMantenedorTresAtributos(String nombreMantenedorTresAtributos) {
        this.nombreMantenedorTresAtributos = nombreMantenedorTresAtributos;
    }
}
