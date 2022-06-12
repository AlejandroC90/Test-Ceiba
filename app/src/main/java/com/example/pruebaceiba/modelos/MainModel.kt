package com.example.pruebaceiba.modelos

import android.content.Context
import com.example.pruebaceiba.api.CallBackVolleyUsuarios
import com.example.pruebaceiba.api.Repositorio
import com.example.pruebaceiba.contratos.InterfazContratos

class MainModel(): InterfazContratos.Modelo {

    var repositorio: Repositorio = Repositorio()

    override fun traerUsuarios(context: Context, callback: CallBackVolleyUsuarios) {

        repositorio.listarUsuarios(context, callback)
        //repositorio.listarPostsPorUsuario(context, 1)
    }

    override fun traerPostPorUsuario(context: Context, callback: CallBackVolleyUsuarios, idUsuario: Int) {
        repositorio.listarPostsPorUsuario(context, idUsuario)
    }

}