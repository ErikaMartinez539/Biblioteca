package edu.itvo.biblioteca.data.repository

import edu.itvo.biblioteca.domain.model.Book
import edu.itvo.biblioteca.domain.repository.BookRepository

class InMemoryBookRepository : BookRepository {

    private val books = mutableListOf(
        Book("El héroe perdido", "Rick Riordan", "9786073117081"),
        Book("1984", "George Orwell", "9780155658110"),
        Book("El secreto", "Donna Tartt", "9780241982884"),
        Book("El retrato de Dorian Gray", "Oscar Wilde", "9780192833655"),
        Book("El día que dejó de nevar en Alaska", "Alice Kellen", "9788408172451"),
        Book("Los juegos del hambre", "Suzanne Collins", "9780439023481"),
        Book("The Maze Runner", "James Dashner", "9780385737951")
    )

    override fun obtenerTodos(): List<Book> = books

    override fun obtenerPorIsbn(isbn: String): Book? =
        books.find { it.isbn == isbn }

    override fun guardar(book: Book) {
        books.add(book)
    }
}