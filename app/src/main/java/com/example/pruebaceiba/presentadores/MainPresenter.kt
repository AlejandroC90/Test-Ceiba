package com.example.pruebaceiba.presentadores

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebaceiba.api.CallBackVolleyPostsPorUsuario
import com.example.pruebaceiba.api.CallBackVolleyUsuarios
import com.example.pruebaceiba.contratos.InterfazContratos
import com.example.pruebaceiba.data.DTOPost
import com.example.pruebaceiba.data.DTOUsuario
import com.example.pruebaceiba.modelos.MainModel
import kotlinx.coroutines.launch

class MainPresenter(_vista: InterfazContratos.Vista, context: Context) : InterfazContratos.Presentador, ViewModel() {
    private var vista = _vista
    private var model: MainModel = MainModel()

    init {
        consultarUsuarios(context)
    }

    override fun consultarUsuarios(context: Context) {
        viewModelScope.launch{
            vista.mostrarCargando()
            model.traerUsuarios(context,
                object : CallBackVolleyUsuarios {
                    override fun onSuccess(usuarios: ArrayList<DTOUsuario>) {
                        //se muestra la interfaz con los datos
                        vista.mostrarUsuarios(usuarios, true)
                    }
                    override fun onError() {
                        vista.mostrarErrorCarga(true)
                    }
                })
            vista.ocultarCargando()
        }
    }

    override fun consultarPostPorUsuario(context: Context, usuario: DTOUsuario) {

        println("presionaron boton en el listado ${usuario.id}")
        viewModelScope.launch{
            vista.mostrarCargando()
            model.traerPostPorUsuario(context, object : CallBackVolleyPostsPorUsuario {
                override fun onSuccessPost( posts: ArrayList<DTOPost>) {
                    //se muestra la interfaz con los datos de los posts de un usuario
                    vista.mostrarPosts(usuario, posts)
                }

                override fun onError() {
                    vista.mostrarErrorCarga(false)
                }
            }, usuario)
            vista.ocultarCargando()
        }
    }

    override fun filtrarUsuarios(nombre: String) {
        var listado = model.buscarUsuario(nombre)
        if(listado.isEmpty()){
            vista.mostrarVacio()
        }else{
            vista.mostrarUsuarios(listado, false)

        }
    }
}