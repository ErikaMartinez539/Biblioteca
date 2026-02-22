package edu.itvo.biblioteca.domain.usecase

import edu.itvo.biblioteca.domain.repository.BookRepository

class ReporteLibrosUseCase(
    private val bookRepo: BookRepository
) {

    fun librosDisponibles() =
        bookRepo.obtenerTodos().filter { it.disponible }

    fun librosEnPrestamo() =
        bookRepo.obtenerTodos().filter { !it.disponible }
}