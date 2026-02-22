package edu.itvo.biblioteca.domain.model

data class User(
    val nombre: String,
    val id: Int,
    val prestamos: MutableList<Prestamo> = mutableListOf()
)
