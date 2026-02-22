package edu.itvo.biblioteca.domain.Policy

import edu.itvo.biblioteca.domain.model.Book
import edu.itvo.biblioteca.domain.model.User

class Maximo3LibrosPolitica : PoliticaPrestamo {

    override fun puedePrestar(usuario: User, libro: Book): Boolean {
        return usuario.prestamos.size < 3 && libro.disponible
    }
}
