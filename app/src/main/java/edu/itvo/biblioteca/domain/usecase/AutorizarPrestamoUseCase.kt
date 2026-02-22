package edu.itvo.biblioteca.domain.usecase
import edu.itvo.biblioteca.domain.repository.BookRepository
import edu.itvo.biblioteca.domain.repository.UserRepository
import edu.itvo.biblioteca.domain.repository.PrestamoRepository
import edu.itvo.biblioteca.domain.Policy.PoliticaPrestamo

class AutorizarPrestamoUseCase(
    private val bookRepo: BookRepository,
    private val userRepo: UserRepository,
    private val prestamoRepo: PrestamoRepository,
    private val politica: PoliticaPrestamo
) {

    fun ejecutar(userId: Int, isbn: String): Boolean {
        val usuario = userRepo.obtenerPorId(userId) ?: return false
        val libro = bookRepo.obtenerPorIsbn(isbn) ?: return false
        return politica.puedePrestar(usuario, libro)
    }
}