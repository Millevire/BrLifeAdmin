package com.example.esteban.brlifeadmin.Clases.Mantenedor;

public class Comuna {
    private int idComuna;
    private String nombreComuna;
    private int idProvincia;
    private int idRegion;

    public Comuna(int idComuna, String nombreComuna, int idProvincia, int idRegion) {
        this.idComuna = idComuna;
        this.nombreComuna = nombreComuna;
        this.idProvincia = idProvincia;
        this.idRegion = idRegion;
    }

    public int getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(int idComuna) {
        this.idComuna = idComuna;
    }

    public String getNombreComuna() {
        return nombreComuna;
    }

    public void setNombreComuna(String nombreComuna) {
        this.nombreComuna = nombreComuna;
    }

    public int getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(int idProvincia) {
        this.idProvincia = idProvincia;
    }

    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }
}
