package edu.itvo.biblioteca.data.repository

import edu.itvo.biblioteca.domain.model.User
import edu.itvo.biblioteca.domain.repository.UserRepository

class InMemoryUserRepository : UserRepository {

    private val users = mutableListOf(
        User("Erika", 1),
        User("Peeta", 2),
        User("Tania", 3)
    )

    override fun obtenerTodos(): List<User> = users

    override fun obtenerPorId(id: Int): User? =
        users.find { it.id == id }

    override fun guardar(user: User) {
        users.add(user)
    }
}