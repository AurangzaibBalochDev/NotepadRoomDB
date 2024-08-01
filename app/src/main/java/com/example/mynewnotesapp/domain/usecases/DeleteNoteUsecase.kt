package com.example.mynewnotesapp.domain.usecases

import com.example.mynewnotesapp.domain.repository.NotesRepository

class DeleteNoteUsecase(
    private val repository: NotesRepository
) {
    suspend fun invoke(id: String) {
        repository.deleteNotes(id)

    }
}