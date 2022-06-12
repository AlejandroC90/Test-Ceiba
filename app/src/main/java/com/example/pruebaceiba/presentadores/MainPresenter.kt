package com.example.pruebaceiba.presentadores

import android.content.Context
import com.example.pruebaceiba.api.CallBackVolleyUsuarios
import com.example.pruebaceiba.contratos.InterfazContratos
import com.example.pruebaceiba.data.DTOUsuario
import com.example.pruebaceiba.modelos.MainModel

class MainPresenter(_vista: InterfazContratos.Vista) : InterfazContratos.Presentador {
    private var vista = _vista
    private var model: MainModel = MainModel()

    init {
        vista.inicializar()
        //traerInformacion()
    }


    override fun consultarUsuarios(context: Context) {
        vista.mostrarCargando()
        model.traerUsuarios(context,
            object : CallBackVolleyUsuarios {
                override fun onSuccess(usuarios: ArrayList<DTOUsuario>) {
                   //se muestra la interfaz con los datos
                    vista.mostrarUsuarios(usuarios)
                }
                override fun onError() {
                    vista.ocultarCargando()
                }
            })
        vista.ocultarCargando()
    }

    override fun consultarPostPorUsuario(context: Context) {
        TODO("Not yet implemented")
    }

    override fun filtrarUsuarios() {
        TODO("Not yet implemented")
    }
}