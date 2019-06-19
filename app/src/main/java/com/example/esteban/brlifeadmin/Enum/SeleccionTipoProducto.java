package com.example.esteban.brlifeadmin.Enum;

     public enum  SeleccionTipoProducto {
         Crustaceo("Crustaceo"),
         Agua("Agua"),
         Huevo("Huevo"),
         Pescado("Pescado"),
         Carne("Carne"),
         Cereal("Cereal"),
         Legrumbre("Legumbre"),
         Lacteo("Lacteo"),
         Bebida("Bebida"),
         Fruta("Fruta"),
         Galleta("Galleta"),
         Fritura("Fritura")
         ;

     private String seleccion;

    SeleccionTipoProducto(String medicion) {
        this.seleccion = medicion;
    }
    public String getSeleccion(){
        return seleccion;
    }
}
