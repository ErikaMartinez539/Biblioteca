package edu.itvo.biblioteca.domain.repository
import edu.itvo.biblioteca.domain.model.Book

interface BookRepository {
    fun obtenerTodos(): List<Book>
    fun obtenerPorIsbn(isbn: String): Book?
    fun guardar(book: Book)
}