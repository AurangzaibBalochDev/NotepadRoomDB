package com.example.notzeezaibtech.domain.usecases


import com.example.notzeezaibtech.domain.repository.NotesRepository
import com.example.notzeezaibtech.ui.notes_list.components.TodoData
import com.example.notzeezaibtech.ui.notes_list.components.toTodosTable

class AddTodoUseCase(
    private val repository: NotesRepository
) {

    suspend operator fun invoke(todoData: TodoData) {
        repository.insertOrUpdateTodo(todoData.toTodosTable())
    }
}