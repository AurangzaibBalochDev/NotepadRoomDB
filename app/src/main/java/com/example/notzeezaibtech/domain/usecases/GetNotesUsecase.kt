package com.example.notzeezaibtech.domain.usecases

import com.example.notzeezaibtech.data.model.toNotesData
import com.example.notzeezaibtech.domain.repository.NotesRepository
import com.example.notzeezaibtech.ui.notes_list.components.NotesData
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