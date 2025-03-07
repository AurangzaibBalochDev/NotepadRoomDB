package com.example.notzeezaibtech.domain.usecases

import com.example.notzeezaibtech.data.model.toTodoData
import com.example.notzeezaibtech.domain.repository.NotesRepository
import com.example.notzeezaibtech.ui.notes_list.components.TodoData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class GetTodoUsecase(
    private val repository: NotesRepository
) {
    operator fun invoke(): Flow<List<TodoData>> {
        return repository.getAllTodos().map { list ->
            list.map {
                it.toTodoData()
            }
        }
    }
}