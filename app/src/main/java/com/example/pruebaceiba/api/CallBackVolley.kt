package com.example.pruebaceiba.api

import com.example.pruebaceiba.data.DTOPost
import com.example.pruebaceiba.data.DTOUsuario

/**
 * Callback de Volley para usuarios
 */
interface CallBackVolleyUsuarios {
    fun onSuccess(usuarios: ArrayList<DTOUsuario>)
    fun onError()
}

interface CallBackVolleyPostsPorUsuario{
    fun onSuccessPost(posts: ArrayList<DTOPost>)
}