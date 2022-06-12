package com.example.pruebaceiba.api

import com.example.pruebaceiba.data.DTOUsuario

/**
 * Callback de Volley para usuarios
 */
interface CallBackVolleyUsuarios {
    fun onSuccess(usuarios: ArrayList<DTOUsuario>)
    fun onError()
}

interface CallBackVolleyPostsPorUsuario{
    fun onSuccessPost(usuarios: ArrayList<DTOUsuario>)
}