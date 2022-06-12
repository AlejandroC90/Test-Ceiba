package com.example.pruebaceiba.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebaceiba.R
import com.example.pruebaceiba.data.DTOPost
import com.example.pruebaceiba.data.DTOUsuario

/**
 * Adapter para mostrar el listado de usuarios despues de cargar
 * usando un Recycler View
 */
class AdapterPosts(
    private val listadoPosts: List<DTOPost>
) :
    RecyclerView.Adapter<AdapterPosts.ViewHolder>() {


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val txtTitulo: TextView = itemView.findViewById(R.id.titulo_publicacion)
        val txtSubtitulo: TextView = itemView.findViewById(R.id.subtitulo_publicacion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = listadoPosts[position]
        holder.txtTitulo.text = post.titulo
        holder.txtSubtitulo.text = post.cuerpo

    }

    override fun getItemCount(): Int {
        return listadoPosts.size
    }
}