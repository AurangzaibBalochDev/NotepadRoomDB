package com.example.mynewnotesapp.domain.usecases

import com.example.mynewnotesapp.domain.repository.NotesRepository
import com.example.mynewnotesapp.ui.notes_list.components.NotesData
import com.example.mynewnotesapp.ui.notes_list.components.toNotesTable

class AddNoteUseCase(
    private val repository: NotesRepository
) {

    suspend operator fun invoke(notesData: NotesData) {
        repository.insertOrUpdate(notesData.toNotesTable())
    }
}