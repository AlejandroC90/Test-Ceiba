package com.example.pruebaceiba.contratos

import android.content.Context
import com.example.pruebaceiba.api.CallBackVolleyPostsPorUsuario
import com.example.pruebaceiba.api.CallBackVolleyUsuarios
import com.example.pruebaceiba.data.DTOPost
import com.example.pruebaceiba.data.DTOUsuario

/**
 * Contrato para todas la aplicacion, se requiere de cargar usuarios y cargar post por usuario
 */
interface InterfazContratos {

    interface Vista {
        fun mostrarCargando()
        fun ocultarCargando()
        fun mostrarUsuarios(usuarios: ArrayList<DTOUsuario>)
        fun mostrarVacio()
        fun mostrarPosts(usuario: DTOUsuario, posts: ArrayList<DTOPost>)
    }

    interface Presentador {
        fun consultarUsuarios(context: Context)
        fun consultarPostPorUsuario(context: Context, usuario: DTOUsuario)
        fun filtrarUsuarios(nombre: String)
    }

    interface Modelo {
        suspend fun traerUsuarios(context: Context, callback: CallBackVolleyUsuarios)
        fun buscarUsuario(nombre: String):ArrayList<DTOUsuario>
        suspend fun traerPostPorUsuario(context: Context, callback: CallBackVolleyPostsPorUsuario, usuario: DTOUsuario)
    }
}