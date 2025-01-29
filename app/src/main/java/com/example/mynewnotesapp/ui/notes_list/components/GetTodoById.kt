package com.example.mynewnotesapp.ui.notes_list.components

import com.example.mynewnotesapp.data.model.toNotesData
import com.example.mynewnotesapp.data.model.toTodoData
import com.example.mynewnotesapp.domain.repository.NotesRepository

class GetTodoById (
    private val repository: NotesRepository
){
    suspend operator fun invoke(id:String): TodoData? {
        return repository.getTodoById(id)?.toTodoData()
    }

}