package com.example.practicarecufirebasesqlite

import com.example.mapifilms.Genero

data class Pelicula(
    val id: Int, val titulo: String, val tituloOriginal: String, val director: String,
    val duracion: Int,val genero: Genero,val src:String
) {
}