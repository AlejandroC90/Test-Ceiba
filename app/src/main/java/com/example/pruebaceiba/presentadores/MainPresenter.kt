package com.example.pruebaceiba.presentadores

import com.example.pruebaceiba.contratos.InterfazContratos
import com.example.pruebaceiba.modelos.MainModel

class MainPresenter(_vista: InterfazContratos.Vista) : InterfazContratos.Presentador {
    private var vista = _vista
    private var model: MainModel = MainModel()

    init {
        vista.inicializar()
        traerInformacion()
    }

    override fun cargarUsuarios() {

    }

    override fun traerInformacion() {
        vista.mostrarCargando(model.traerDatos())
    }
}