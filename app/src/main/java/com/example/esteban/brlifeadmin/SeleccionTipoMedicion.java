package com.example.esteban.brlifeadmin;


    public enum  SeleccionTipoMedicion {
        ml("ml"),
        gr("gr");

        private String seleccion;

        SeleccionTipoMedicion(String medicion) {
            this.seleccion = medicion;
        }
        public String getSeleccion(){
            return seleccion;
        }
    }

