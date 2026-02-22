package edu.itvo.biblioteca

import edu.itvo.biblioteca.data.repository.*
import edu.itvo.biblioteca.domain.Policy.Maximo3LibrosPolitica
import edu.itvo.biblioteca.domain.usecase.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class SistemaBibliotecaTest {

    private lateinit var bookRepo: InMemoryBookRepository
    private lateinit var userRepo: InMemoryUserRepository
    private lateinit var prestamoRepo: InMemoryPrestamoRepository
    private lateinit var politica: Maximo3LibrosPolitica

    private lateinit var autorizar: AutorizarPrestamoUseCase
    private lateinit var prestar: PrestarLibroUseCase
    private lateinit var devolver: DevolverLibroUseCase
    private lateinit var reporte: ReporteLibrosUseCase

    @Before
    fun setUp() {
        bookRepo = InMemoryBookRepository()
        userRepo = InMemoryUserRepository()
        prestamoRepo = InMemoryPrestamoRepository()
        politica = Maximo3LibrosPolitica()

        autorizar = AutorizarPrestamoUseCase(bookRepo, userRepo, prestamoRepo, politica)
        prestar = PrestarLibroUseCase(bookRepo, userRepo, prestamoRepo, politica)
        devolver = DevolverLibroUseCase(bookRepo, userRepo, prestamoRepo)
        reporte = ReporteLibrosUseCase(bookRepo)
    }

    @Test
    fun noSePuedePrestarLibroYaPrestado() {
        val userId = 1
        val isbn = bookRepo.obtenerTodos().first().isbn

        prestar.ejecutar(userId, isbn)
        val autorizado = autorizar.ejecutar(userId, isbn)

        assertFalse(autorizado)
    }

    @Test
    fun usuarioNoPuedeTenerMasDe3LibrosPrestados() {
        val userId = 1
        val libros = bookRepo.obtenerTodos()

        prestar.ejecutar(userId, libros[0].isbn)
        prestar.ejecutar(userId, libros[1].isbn)
        prestar.ejecutar(userId, libros[2].isbn)

        val autorizado = autorizar.ejecutar(userId, libros[3].isbn)

        assertFalse(autorizado)
    }

    @Test
    fun puedePrestarSiUsuarioTieneMenosDe3LibrosYLibroDisponible() {
        val userId = 1
        val isbn = bookRepo.obtenerTodos().first().isbn

        val autorizado = autorizar.ejecutar(userId, isbn)

        assertTrue(autorizado)
    }

    @Test
    fun devolverLibroLoMarcaComoDisponibleOtraVez() {
        val userId = 1
        val isbn = bookRepo.obtenerTodos().first().isbn

        prestar.ejecutar(userId, isbn)
        devolver.ejecutar(userId, isbn)

        val libro = bookRepo.obtenerPorIsbn(isbn)
        assertTrue(libro?.disponible == true)
    }

    @Test
    fun reporteLibrosDisponiblesFunciona() {
        val userId = 1
        val isbn = bookRepo.obtenerTodos().first().isbn

        prestar.ejecutar(userId, isbn)

        val disponibles = reporte.librosDisponibles()

        assertFalse(disponibles.any { it.isbn == isbn })
    }
}