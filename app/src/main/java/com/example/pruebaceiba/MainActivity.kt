package com.example.pruebaceiba

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebaceiba.contratos.InterfazContratos
import com.example.pruebaceiba.data.DTOPost
import com.example.pruebaceiba.data.DTOUsuario
import com.example.pruebaceiba.presentadores.MainPresenter
import com.example.pruebaceiba.ui.AdapterUsuarios
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.io.Serializable

class MainActivity : AppCompatActivity(), InterfazContratos.Vista {
    lateinit var dialog: AlertDialog
    lateinit var boton: Button
    var presentador: MainPresenter? = null
    lateinit var recyclearViewUsuarios: RecyclerView
    lateinit var busqueda: TextInputEditText
    lateinit var emptyList: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inicializar()
        //inicializamos el presentador

        presentador = MainPresenter(this, this)
    }

    /**
     * funcion que inicializa la interfaz de la aplicacion
     */
    fun inicializar() {
        //boton para busqueda de usuarios
        boton = findViewById(R.id.boton_presioname)
        boton.setOnClickListener {
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
        busqueda = findViewById(R.id.input_edit_text)
        busqueda.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                //aqui se maneja la busqueda de usuarios en la pantalla principal
                presentador?.filtrarUsuarios(p0.toString())
            }

        })

        emptyList = findViewById(R.id.empty_textview)

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
        if(recyclearViewUsuarios.visibility.equals(View.INVISIBLE)){
            recyclearViewUsuarios.visibility = View.VISIBLE
            emptyList.visibility = View.INVISIBLE
        }
        recyclearViewUsuarios.adapter = AdapterUsuarios(usuarios) { it: DTOUsuario ->
            presentador?.consultarPostPorUsuario(this, it)
        }
    }

    override fun mostrarVacio() {
        recyclearViewUsuarios.visibility = View.INVISIBLE
        emptyList.visibility = View.VISIBLE
    }

    /**
     * Despues de realiza el cargado de los post posts por usuario se envia a una nueva pantalla
     */
    override fun mostrarPosts(usuario: DTOUsuario, posts: ArrayList<DTOPost>) {
        val bundle:Bundle= Bundle().apply {
            putSerializable("usuario",usuario?:"")
            putSerializable("posts", posts as Serializable)
        }
        startActivity(Intent(this,ListadoPosts::class.java).putExtras(bundle))
    }
}