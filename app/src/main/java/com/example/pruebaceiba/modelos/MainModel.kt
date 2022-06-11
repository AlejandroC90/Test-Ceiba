package com.example.pruebaceiba.modelos

import android.content.Context
import com.example.pruebaceiba.api.Repositorio
import com.example.pruebaceiba.contratos.InterfazContratos

class MainModel(): InterfazContratos.Modelo {

    var repositorio: Repositorio = Repositorio()


    override fun traerUsuarios(context: Context) {
        repositorio.listarUsuarios(context)
        repositorio.listarPostsPorUsuario(context, 1)
    }

    override fun traerDatos(): String {
        return "IMPRIMA ESTO"
    }
}