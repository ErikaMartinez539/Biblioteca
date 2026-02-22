package edu.itvo.biblioteca.domain.repository

import edu.itvo.biblioteca.domain.model.User

interface UserRepository {
    fun obtenerTodos(): List<User>
    fun obtenerPorId(id: Int): User?
    fun guardar(user: User)
}