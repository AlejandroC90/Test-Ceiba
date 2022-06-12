package com.example.pruebaceiba.api

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.example.pruebaceiba.data.DTOPost
import com.example.pruebaceiba.data.DTOUsuario

/**
 * Clase de repositorio que permite la carga de los datos de internet o
 * base de datos local
 */
class Repositorio {
    final val URL_BASE = "https://jsonplaceholder.typicode.com/"
    final val URL_USUARIOS = URL_BASE + "users"
    final val URL_POST_USUARIOS =
        URL_BASE + "posts?userId="//se le agrega el user id como param de consulta

    val arregloUsuarios = ArrayList<DTOUsuario>()

    fun listarUsuarios(context: Context, callback: CallBackVolleyUsuarios) {
        val request = JsonArrayRequest(
            Request.Method.GET, URL_USUARIOS, null,
            { response ->
                for (i in 0 until response.length()) {
                    val usuario = DTOUsuario()
                    val objeto = response.getJSONObject(i)

                    usuario.id = objeto.getInt("id")
                    usuario.nombre = objeto.getString("name")
                    usuario.correo = objeto.getString("email")
                    usuario.telefono = objeto.getString("phone")
                    arregloUsuarios.add(usuario)
                }
                callback.onSuccess(arregloUsuarios)
            },
            { error ->
                callback.onError()
            })
        addtoLista(request, context)
    }

    /**
     * Permite busca un usuario usando el nombre, ignora mayusculas o minusculas
     */
    fun buscarUsuario(nombre: String): ArrayList<DTOUsuario> {
        var filtrados = arregloUsuarios.filter { dtoUsuario ->
            dtoUsuario.nombre?.contains(nombre, ignoreCase = true) ?: false
        } as ArrayList<DTOUsuario> /* = java.util.ArrayList<com.example.pruebaceiba.data.DTOUsuario> */
        return filtrados;
    }

    fun listarPostsPorUsuario(context: Context, idUsuario: Int) {
        val request = JsonArrayRequest(
            Request.Method.GET, URL_POST_USUARIOS + idUsuario.toString(), null,
            { response ->
                val arregloPosts = ArrayList<DTOPost>()
                for (i in 0 until response.length()) {
                    val post = DTOPost()
                    val objeto = response.getJSONObject(i)

                    post.titulo = objeto.getString("title")
                    post.cuerpo = objeto.getString("body")
                    arregloPosts.add(post)
                }
            },
            { error ->

            })
        addtoLista(request, context)
    }

    fun addtoLista(stringRequest: JsonArrayRequest, context: Context) {
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest)

    }
}