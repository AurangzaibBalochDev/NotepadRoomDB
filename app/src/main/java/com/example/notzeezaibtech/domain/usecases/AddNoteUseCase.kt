package com.example.notzeezaibtech.domain.usecases

import com.example.notzeezaibtech.domain.repository.NotesRepository
import com.example.notzeezaibtech.ui.notes_list.components.NotesData
import com.example.notzeezaibtech.ui.notes_list.components.toNotesTable

class AddNoteUseCase(
    private val repository: NotesRepository
) {

    suspend operator fun invoke(notesData: NotesData) {
        repository.insertOrUpdate(notesData.toNotesTable())
    }
}