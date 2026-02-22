package edu.itvo.biblioteca.domain.usecase
import edu.itvo.biblioteca.domain.repository.BookRepository
import edu.itvo.biblioteca.domain.repository.UserRepository
import edu.itvo.biblioteca.domain.repository.PrestamoRepository
import edu.itvo.biblioteca.domain.Policy.PoliticaPrestamo
import edu.itvo.biblioteca.domain.model.Prestamo
import java.time.LocalDate

class PrestarLibroUseCase(
    private val bookRepo: BookRepository,
    private val userRepo: UserRepository,
    private val prestamoRepo: PrestamoRepository,
    private val politica: PoliticaPrestamo
) {

    fun ejecutar(userId: Int, isbn: String): Boolean {
        val usuario = userRepo.obtenerPorId(userId) ?: return false
        val libro = bookRepo.obtenerPorIsbn(isbn) ?: return false
        if (!politica.puedePrestar(usuario, libro)) return false

        val prestamo = Prestamo(
            libro = libro,
            usuario = usuario,
            fechaPrestamo = LocalDate.now()
        )

        libro.disponible = false
        usuario.prestamos.add(prestamo)

        prestamoRepo.guardar(prestamo)

        return true
    }
}