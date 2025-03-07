package com.example.notzeezaibtech.domain.usecases

import com.example.notzeezaibtech.domain.repository.NotesRepository

class DeleteTodoUsecase(
    private val repository: NotesRepository
) {
    suspend fun invoke(id: String) {
        repository.deleteTodo(id)

    }
}