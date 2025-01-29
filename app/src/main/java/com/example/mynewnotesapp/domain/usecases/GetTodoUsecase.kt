package com.example.mynewnotesapp.domain.usecases

import com.example.mynewnotesapp.data.model.toNotesData
import com.example.mynewnotesapp.data.model.toTodoData
import com.example.mynewnotesapp.domain.repository.NotesRepository
import com.example.mynewnotesapp.ui.notes_list.components.NotesData
import com.example.mynewnotesapp.ui.notes_list.components.TodoData
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