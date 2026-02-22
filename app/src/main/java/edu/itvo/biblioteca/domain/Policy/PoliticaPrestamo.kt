package edu.itvo.biblioteca.domain.Policy

import edu.itvo.biblioteca.domain.model.Book
import edu.itvo.biblioteca.domain.model.User

interface PoliticaPrestamo {
    fun puedePrestar(usuario: User, libro: Book): Boolean
}
