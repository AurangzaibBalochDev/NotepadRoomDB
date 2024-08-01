package com.example.mynewnotesapp.domain.usecases

import com.example.mynewnotesapp.data.model.toNotesData
import com.example.mynewnotesapp.domain.repository.NotesRepository
import com.example.mynewnotesapp.ui.notes_list.components.NotesData

class GetNoteById (
    private val repository: NotesRepository
){
    suspend operator fun invoke(id:String): NotesData? {
        return repository.getNoteById(id)?.toNotesData()
    }

}