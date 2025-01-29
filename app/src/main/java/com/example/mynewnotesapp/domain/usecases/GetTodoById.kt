package com.example.mynewnotesapp.domain.usecases

import com.example.mynewnotesapp.data.model.toTodoData
import com.example.mynewnotesapp.domain.repository.NotesRepository
import com.example.mynewnotesapp.ui.notes_list.components.TodoData

class GetTodoById (
    private val repository: NotesRepository
){
    suspend operator fun invoke(id:String): TodoData? {
        return repository.getTodoById(id)?.toTodoData()
    }

}