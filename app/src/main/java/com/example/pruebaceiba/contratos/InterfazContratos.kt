package com.example.pruebaceiba.contratos

import android.content.Context
import com.example.pruebaceiba.api.CallBackVolleyUsuarios
import com.example.pruebaceiba.data.DTOUsuario

/**
 * Contrato para todas la aplicacion, se requiere de cargar usuarios y cargar post por usuario
 */
interface InterfazContratos {

    interface Vista {
        fun inicializar()
        fun mostrarCargando()
        fun ocultarCargando()
        fun mostrarUsuarios(usuarios: ArrayList<DTOUsuario>)
    }

    interface Presentador {
        fun consultarUsuarios(context: Context)
        fun consultarPostPorUsuario(context: Context)
        fun filtrarUsuarios()
    }

    interface Modelo {
        fun traerUsuarios(context: Context, callback: CallBackVolleyUsuarios)
        fun traerPostPorUsuario(context: Context, callback: CallBackVolleyUsuarios, idUsuario: Int)
    }
}