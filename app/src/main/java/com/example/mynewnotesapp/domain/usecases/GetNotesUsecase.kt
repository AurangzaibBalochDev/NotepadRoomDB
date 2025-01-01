package com.example.mynewnotesapp.domain.usecases

import com.example.mynewnotesapp.data.model.toNotesData
import com.example.mynewnotesapp.domain.repository.NotesRepository
import com.example.mynewnotesapp.ui.notes_list.components.NotesData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class GetNotesUseCase(
    private val repository: NotesRepository
) {
    operator fun invoke(): Flow<List<NotesData>> {
        return repository.getAllNotes().map { list ->
            list.map {
                it.toNotesData()
            }
        }
    }
}