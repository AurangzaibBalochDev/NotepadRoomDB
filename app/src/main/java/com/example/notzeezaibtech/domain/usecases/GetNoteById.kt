package com.example.notzeezaibtech.domain.usecases

import com.example.notzeezaibtech.data.model.toNotesData
import com.example.notzeezaibtech.domain.repository.NotesRepository
import com.example.notzeezaibtech.ui.notes_list.components.NotesData

class GetNoteById (
    private val repository: NotesRepository
){
    suspend operator fun invoke(id:String): NotesData? {
        return repository.getNoteById(id)?.toNotesData()
    }

}