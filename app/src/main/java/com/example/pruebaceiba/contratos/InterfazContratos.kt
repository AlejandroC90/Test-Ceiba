package com.example.pruebaceiba.contratos

interface InterfazContratos {

    interface Vista {
        fun inicializar()
        fun mostrarCargando(datos: String)
        fun ocultarCargando()
    }

    interface Presentador {
        fun cargarUsuarios()
        fun traerInformacion()
    }

    interface Modelo {
        fun traerUsuarios()
        fun traerDatos(): String
    }
}