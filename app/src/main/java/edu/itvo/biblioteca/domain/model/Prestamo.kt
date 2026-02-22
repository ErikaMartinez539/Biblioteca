package edu.itvo.biblioteca.domain.model

import java.time.LocalDate

data class Prestamo(
    val libro: Book,
    val usuario: User,
    val fechaPrestamo: LocalDate,
    var fechaDevolucion: LocalDate? = null
)
