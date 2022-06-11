package com.example.pruebaceiba

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.pruebaceiba.contratos.InterfazContratos
import com.example.pruebaceiba.presentadores.MainPresenter

class MainActivity : AppCompatActivity(), InterfazContratos.Vista {
    lateinit var dialog: AlertDialog
    lateinit var boton: Button
    var presentador: MainPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presentador = MainPresenter(this)
    }

    override fun inicializar() {
        boton = findViewById(R.id.boton_presioname)
        boton.setOnClickListener{
            presentador?.consultarUsuarios(this)
        }
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setView(R.layout.dialog_cargando)
        dialog = builder.create()
    }

    override fun mostrarCargando(datos: String) {
        dialog.show()
        println(datos)
    }

    override fun ocultarCargando() {
        dialog.hide()
    }

}