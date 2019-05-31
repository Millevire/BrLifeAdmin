package com.example.esteban.brlifeadmin.Enum;

     public enum  SeleccionTipoProducto {
         Crustaceo("Crustaceo"),
         Agua("Agua"),
         Huevo("Huevo"),
         Pescado("Pescado"),
         Carne("Carne"),
         Cereal("Cereal"),
         Legrumbres("Legumbres"),
         Lacteos("Lacteos"),
         Bebidas("Bebidas"),
         Frutas("Frutas"),
         Galleta("Galleta")
         ;

     private String seleccion;

    SeleccionTipoProducto(String medicion) {
        this.seleccion = medicion;
    }
    public String getSeleccion(){
        return seleccion;
    }
}
