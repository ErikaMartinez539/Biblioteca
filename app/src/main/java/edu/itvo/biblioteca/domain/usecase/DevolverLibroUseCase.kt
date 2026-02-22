package edu.itvo.biblioteca.domain.usecase
import edu.itvo.biblioteca.domain.repository.BookRepository
import edu.itvo.biblioteca.domain.repository.UserRepository
import edu.itvo.biblioteca.domain.repository.PrestamoRepository
import java.time.LocalDate

class DevolverLibroUseCase(
    private val bookRepo: BookRepository,
    private val userRepo: UserRepository,
    private val prestamoRepo: PrestamoRepository
) {

    fun ejecutar(userId: Int, isbn: String): Boolean {
        val prestamo = prestamoRepo.obtenerTodos().find {
            it.usuario.id == userId && it.libro.isbn == isbn && it.fechaDevolucion == null
        } ?: return false

        prestamo.fechaDevolucion = LocalDate.now()
        prestamo.libro.disponible = true
        prestamo.usuario.prestamos.remove(prestamo)
        return true
    }
}