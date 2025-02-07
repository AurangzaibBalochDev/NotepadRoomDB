package com.example.mynewnotesapp.domain.usecases

import com.example.mynewnotesapp.domain.repository.NotesRepository

class DeleteTodoUsecase(
    private val repository: NotesRepository
) {
    suspend fun invoke(id: String) {
        repository.deleteTodo(id)

    }
}