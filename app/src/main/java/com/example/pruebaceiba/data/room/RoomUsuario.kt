package com.example.pruebaceiba.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomUsuario (
    @PrimaryKey var id: Int,
    var nombre: String? = null,
    var telefono: String? = null,
    var correo: String? = null
)