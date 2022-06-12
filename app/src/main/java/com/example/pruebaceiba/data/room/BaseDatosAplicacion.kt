package com.example.pruebaceiba.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RoomUsuario::class], version = 1)
abstract class BaseDatosAplicacion : RoomDatabase() {
    abstract fun usuarioDao() : UsuarioDao
}