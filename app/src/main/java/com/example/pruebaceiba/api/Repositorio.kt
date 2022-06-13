package com.example.pruebaceiba.api

import android.content.Context
import androidx.room.Room
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.example.pruebaceiba.data.DTOPost
import com.example.pruebaceiba.data.DTOUsuario
import com.example.pruebaceiba.data.room.BaseDatosAplicacion
import com.example.pruebaceiba.data.room.RoomUsuario
import com.example.pruebaceiba.data.room.UsuarioDao

/**
 * Clase de repositorio que permite la carga de los datos de internet o
 * base de datos local
 */
class Repositorio {
    final val URL_BASE = "https://jsonplaceholder.typicode.com/"
    final val URL_USUARIOS = URL_BASE.plus("users")
    final val URL_POST_USUARIOS =
        URL_BASE.plus("posts?userId=")//se le agrega el user id como param de consulta

    val arregloUsuarios = ArrayList<DTOUsuario>()

    fun listarUsuarios(context: Context, callback: CallBackVolleyUsuarios) {
        //antes de buscar los usuarios revisamos si no tenemos almacenados en local
       var usuariosDB = interfazDBParaUsuarios(context)

        var listado = usuariosDB.listarUsuariosBaseDatos()
        //hay usuarios en base de datos, se envian esos
        if(listado.isNotEmpty()){
            println("Consultando base de de datos local")
            for(uroom in listado){
                arregloUsuarios.add(transformarUsuario(uroom))
            }
            callback.onSuccess(arregloUsuarios)
        }else{
            println("Consultando internet")
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
                    //se guardan los usuarios
                    usuariosDB.borrarTodo()
                    for(udto in arregloUsuarios){
                        usuariosDB.agregarUsuarios(transformarUsuarioRoom(udto))
                    }

                    callback.onSuccess(arregloUsuarios)
                },
                { error ->
                    callback.onError()
                })
            addtoLista(request, context)
        }
    }

    /**
     * Acceso a base de datos de usuarios
     */
    fun interfazDBParaUsuarios(context: Context): UsuarioDao{
        val db = Room.databaseBuilder(
            context,
            BaseDatosAplicacion::class.java, "usuarios-ceiba"
        ).allowMainThreadQueries().build()

        return db.usuarioDao()
    }


    fun transformarUsuario(uroom: RoomUsuario): DTOUsuario{
        var usuariodto: DTOUsuario = DTOUsuario()
        usuariodto.id = uroom.id
        usuariodto.nombre = uroom.nombre
        usuariodto.correo = uroom.correo
        usuariodto.telefono = uroom.telefono
        return usuariodto
    }

    fun transformarUsuarioRoom(dtoUsuario: DTOUsuario): RoomUsuario{
        var usuarioroom: RoomUsuario = RoomUsuario(dtoUsuario.id)
        usuarioroom.id = dtoUsuario.id
        usuarioroom.nombre = dtoUsuario.nombre
        usuarioroom.correo = dtoUsuario.correo
        usuarioroom.telefono = dtoUsuario.telefono
        return usuarioroom
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

    fun listarPostsPorUsuario(context: Context, callback: CallBackVolleyPostsPorUsuario,  usuario: DTOUsuario) {
        val request = JsonArrayRequest(
            Request.Method.GET, URL_POST_USUARIOS + usuario.id.toString(), null,
            { response ->
                val arregloPosts = ArrayList<DTOPost>()
                for (i in 0 until response.length()) {
                    val post = DTOPost()
                    val objeto = response.getJSONObject(i)

                    post.titulo = objeto.getString("title")
                    post.cuerpo = objeto.getString("body")
                    arregloPosts.add(post)
                }
                callback.onSuccessPost(arregloPosts)
            },
            { error ->
                callback.onError()
            })
        addtoLista(request, context)
    }

    fun addtoLista(stringRequest: JsonArrayRequest, context: Context) {
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest)

    }
}