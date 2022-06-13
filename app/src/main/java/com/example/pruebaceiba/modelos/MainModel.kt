package com.example.pruebaceiba.modelos

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.pruebaceiba.api.CallBackVolleyPostsPorUsuario
import com.example.pruebaceiba.api.CallBackVolleyUsuarios
import com.example.pruebaceiba.api.Repositorio
import com.example.pruebaceiba.contratos.InterfazContratos
import com.example.pruebaceiba.data.DTOPost
import com.example.pruebaceiba.data.DTOUsuario
import kotlinx.coroutines.delay

class MainModel(): InterfazContratos.Modelo {

    var repositorio: Repositorio = Repositorio()

    override suspend fun traerUsuarios(context: Context, callback: CallBackVolleyUsuarios) {
        delay(2000)
        repositorio.listarUsuarios(context, callback)
    }

    /**
     * Permite la busqueda de un usuario o varios en la pantalla principal
     */
    override fun buscarUsuario(nombre: String): ArrayList<DTOUsuario> {
        return repositorio.buscarUsuario(nombre)
    }

     override suspend fun traerPostPorUsuario(
        context: Context,
        callback: CallBackVolleyPostsPorUsuario,
        usuario: DTOUsuario
    ) {
        delay(2000)
        repositorio.listarPostsPorUsuario(context,callback, usuario)
    }

}