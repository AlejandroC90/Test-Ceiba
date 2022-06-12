package com.example.pruebaceiba.modelos

import android.content.Context
import com.example.pruebaceiba.api.CallBackVolleyPostsPorUsuario
import com.example.pruebaceiba.api.CallBackVolleyUsuarios
import com.example.pruebaceiba.api.Repositorio
import com.example.pruebaceiba.contratos.InterfazContratos
import com.example.pruebaceiba.data.DTOUsuario

class MainModel(): InterfazContratos.Modelo {

    var repositorio: Repositorio = Repositorio()

    override fun traerUsuarios(context: Context, callback: CallBackVolleyUsuarios) {

        repositorio.listarUsuarios(context, callback)
        //repositorio.listarPostsPorUsuario(context, 1)
    }

    /**
     * Permite la busqueda de un usuario o varios en la pantalla principal
     */
    override fun buscarUsuario(nombre: String): ArrayList<DTOUsuario> {
        return repositorio.buscarUsuario(nombre)
    }

    override fun traerPostPorUsuario(
        context: Context,
        callback: CallBackVolleyPostsPorUsuario,
        usuario: DTOUsuario
    ) {
        repositorio.listarPostsPorUsuario(context,callback, usuario)
    }

}