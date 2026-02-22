package edu.itvo.biblioteca.domain.model

data class Book(
    val titulo: String,
    val autor: String,
    val isbn: String,
    var disponible: Boolean = true
)
