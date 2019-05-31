package com.example.esteban.brlifeadmin.Enum;

     public enum  SelccionMantenedor {
        TipoProducto("TipoProducto"),
        Region("Region"),
        Rol("Rol"),
        Interes("Interes"),
         HorarioComida("HorarioComida"),
         Producto("Producto"),
         Sabor("Sabor"),
         Marca("Marca"),
         Nutriente("Nutriente"),
         Provincia("Provincia"),
         Comuna("Comuna")
         ;

        private String seleccion;

         SelccionMantenedor(String mantenedor) {
             this.seleccion = mantenedor;
         }
          public String getSeleccion(){
             return seleccion;
          }
     }




