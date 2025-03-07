package com.example.notzeezaibtech.domain.usecases

import com.example.notzeezaibtech.data.model.toTodoData
import com.example.notzeezaibtech.domain.repository.NotesRepository
import com.example.notzeezaibtech.ui.notes_list.components.TodoData

class GetTodoById (
    private val repository: NotesRepository
){
    suspend operator fun invoke(id:String): TodoData? {
        return repository.getTodoById(id)?.toTodoData()
    }

}