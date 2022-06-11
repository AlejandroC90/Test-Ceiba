package com.example.pruebaceiba.contratos

import android.content.Context

interface InterfazContratos {

    interface Vista {
        fun inicializar()
        fun mostrarCargando(datos: String)
        fun ocultarCargando()
    }

    interface Presentador {
        fun cargarUsuarios()
        fun consultarUsuarios(context: Context)
    }

    interface Modelo {
        fun traerUsuarios(context: Context)
        fun traerDatos(): String
    }
}