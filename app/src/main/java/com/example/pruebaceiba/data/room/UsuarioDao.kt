package com.example.pruebaceiba.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pruebaceiba.data.DTOUsuario

@Dao
interface UsuarioDao {
    @Query("DELETE FROM roomusuario")
    fun borrarTodo()

    @Query("SELECT * FROM roomusuario")
    fun listarUsuariosBaseDatos(): List<RoomUsuario>

    @Insert
    fun agregarUsuarios(vararg usuario: RoomUsuario)
}