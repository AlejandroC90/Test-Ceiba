package com.example.pruebaceiba.presentadores

import android.content.Context
import com.example.pruebaceiba.contratos.InterfazContratos
import com.example.pruebaceiba.modelos.MainModel

class MainPresenter(_vista: InterfazContratos.Vista) : InterfazContratos.Presentador {
    private var vista = _vista
    private var model: MainModel = MainModel()

    init {
        vista.inicializar()
        //traerInformacion()
    }

    override fun cargarUsuarios() {

    }

    override fun consultarUsuarios(context: Context) {
        vista.mostrarCargando(model.traerDatos())
        model.traerUsuarios(context)
        vista.ocultarCargando()
    }
}