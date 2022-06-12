package com.example.pruebaceiba.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebaceiba.R
import com.example.pruebaceiba.data.DTOUsuario

/**
 * Adapter para mostrar el listado de usuarios despues de cargar
 * usando un Recycler View
 */
class AdapterUsuarios(
    private val listadoUsuarios: List<DTOUsuario>,
    private var itemClicked: ((movie: DTOUsuario) -> Unit),
) :
    RecyclerView.Adapter<AdapterUsuarios.ViewHolder>() {


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val txtNombre: TextView = itemView.findViewById(R.id.nombre)
        val txtTelefono: TextView = itemView.findViewById(R.id.telefono)
        val txtCorreo: TextView = itemView.findViewById(R.id.correo)
        val bottonPublicacion: Button = itemView.findViewById(R.id.publications_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val u = listadoUsuarios[position]
        holder.txtNombre.text = u.nombre
        holder.txtCorreo.text = u.correo
        holder.txtTelefono.text = u.telefono

        holder.bottonPublicacion.setOnClickListener {
            itemClicked(u)
        }
    }

    override fun getItemCount(): Int {
        return listadoUsuarios.size
    }
}