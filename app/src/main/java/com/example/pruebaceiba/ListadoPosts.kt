package com.example.pruebaceiba

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebaceiba.data.DTOPost
import com.example.pruebaceiba.data.DTOUsuario
import com.example.pruebaceiba.ui.AdapterPosts

class ListadoPosts : AppCompatActivity() {

    private var usuario: DTOUsuario? = null
    private var posts: List<DTOPost> = emptyList()
    lateinit var recyclearViewPosts: RecyclerView
    lateinit var correo: TextView
    lateinit var telefono: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_posts)
        revisarExtras()
        inicializarInterfaz()
    }

    private fun inicializarInterfaz(){
        correo = findViewById(R.id.correo_post)
        telefono = findViewById(R.id.telefono_posts)


        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = usuario?.nombre
        }

        correo.text = usuario?.correo
        telefono.text = usuario?.telefono


        //inicializacion de la lista de objetos - recycler view
        recyclearViewPosts = findViewById(R.id.listado_post)
        recyclearViewPosts.layoutManager = LinearLayoutManager(this)

        recyclearViewPosts.adapter = AdapterPosts(posts)
    }

    private fun revisarExtras(){
        intent?.extras?.let {
            if(it.containsKey("usuario")){
                usuario = it.getSerializable("usuario") as DTOUsuario
            }
            if(it.containsKey("posts")){
                posts = it.getSerializable("posts") as List<DTOPost>
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}