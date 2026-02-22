package edu.itvo.biblioteca.domain.repository

import edu.itvo.biblioteca.domain.model.Prestamo

interface PrestamoRepository {
    fun obtenerTodos(): List<Prestamo>
    fun guardar(prestamo: Prestamo)
}