package com.example.pruebaceiba.presentadores

import android.content.Context
import com.example.pruebaceiba.api.CallBackVolleyUsuarios
import com.example.pruebaceiba.contratos.InterfazContratos
import com.example.pruebaceiba.data.DTOUsuario
import com.example.pruebaceiba.modelos.MainModel

class MainPresenter(_vista: InterfazContratos.Vista, context: Context) : InterfazContratos.Presentador {
    private var vista = _vista
    private var model: MainModel = MainModel()

    init {
        consultarUsuarios(context)
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
    }

    override fun filtrarUsuarios(nombre: String) {
        var listado = model.buscarUsuario(nombre)
        if(listado.isEmpty()){
            vista.mostrarVacio()
        }else{
            vista.mostrarUsuarios(listado)

        }
    }
}