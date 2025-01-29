package com.example.mynewnotesapp.domain.usecases


import com.example.mynewnotesapp.data.model.TodosTable
import com.example.mynewnotesapp.domain.repository.NotesRepository
import com.example.mynewnotesapp.ui.notes_list.components.TodoData
import com.example.mynewnotesapp.ui.notes_list.components.toTodosTable

class AddTodoUseCase(
    private val repository: NotesRepository
) {

    suspend operator fun invoke(todoData: TodoData) {
        repository.insertOrUpdateTodo(todoData.toTodosTable())
    }
}