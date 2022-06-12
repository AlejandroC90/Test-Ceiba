package com.example.pruebaceiba

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebaceiba.contratos.InterfazContratos
import com.example.pruebaceiba.data.DTOUsuario
import com.example.pruebaceiba.presentadores.MainPresenter
import com.example.pruebaceiba.ui.AdapterUsuarios
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity(), InterfazContratos.Vista {
    lateinit var dialog: AlertDialog
    lateinit var boton: Button
    var presentador: MainPresenter? = null
    lateinit var recyclearViewUsuarios: RecyclerView
    lateinit var busqueda: TextInputLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //inicializamos el presentador
        presentador = MainPresenter(this)
    }

    /**
     * funcion que inicializa la interfaz de la aplicacion
     */
    override fun inicializar() {
        //boton para busqueda de usuarios
        boton = findViewById(R.id.boton_presioname)
        boton.setOnClickListener{
            presentador?.consultarUsuarios(this)
        }

        //inicializacion de Alert Dialog para mostrar el cargando o no
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setView(R.layout.dialog_cargando)
        dialog = builder.create()

        //inicializacion de la lista de objetos - recycler view
        recyclearViewUsuarios = findViewById(R.id.listado_usuarios_view)
        recyclearViewUsuarios.layoutManager = LinearLayoutManager(this)

        //busqueda

    }

    override fun mostrarCargando() {
        dialog.show()
    }

    override fun ocultarCargando() {
        dialog.hide()
    }

    /**
     * funcion que se llama cuando se termina de cargar de usuarios y los
     * muestra
     */
    override fun mostrarUsuarios(usuarios: ArrayList<DTOUsuario>) {
        recyclearViewUsuarios.adapter = AdapterUsuarios(usuarios)
    }

}