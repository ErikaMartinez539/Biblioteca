package edu.itvo.biblioteca

import edu.itvo.biblioteca.data.repository.*
import edu.itvo.biblioteca.domain.Policy.Maximo3LibrosPolitica
import edu.itvo.biblioteca.domain.usecase.*
import edu.itvo.biblioteca.domain.model.User

fun main() {

    val bookRepo = InMemoryBookRepository()
    val userRepo = InMemoryUserRepository()
    val prestamoRepo = InMemoryPrestamoRepository()

    val politica = Maximo3LibrosPolitica()

    val autorizar = AutorizarPrestamoUseCase(bookRepo, userRepo, prestamoRepo, politica)
    val prestar = PrestarLibroUseCase(bookRepo, userRepo, prestamoRepo, politica)
    val devolver = DevolverLibroUseCase(bookRepo, userRepo, prestamoRepo)
    val reporte = ReporteLibrosUseCase(bookRepo)

    val userId = 1
    val isbn = "9780439023481"

    if (autorizar.ejecutar(userId, isbn)) {
        prestar.ejecutar(userId, isbn)
        println("Libro prestado correctamente")
    } else {
        println("No se puede prestar el libro")
    }

    println("Libros en préstamo:")
    reporte.librosEnPrestamo().forEach { println(it) }
}