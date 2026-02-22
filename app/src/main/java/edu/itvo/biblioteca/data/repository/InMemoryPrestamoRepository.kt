package edu.itvo.biblioteca.data.repository

import edu.itvo.biblioteca.domain.model.Prestamo
import edu.itvo.biblioteca.domain.repository.PrestamoRepository

class InMemoryPrestamoRepository : PrestamoRepository {

    private val prestamos = mutableListOf<Prestamo>()

    override fun obtenerTodos(): List<Prestamo> = prestamos

    override fun guardar(prestamo: Prestamo) {
        prestamos.add(prestamo)
    }
}