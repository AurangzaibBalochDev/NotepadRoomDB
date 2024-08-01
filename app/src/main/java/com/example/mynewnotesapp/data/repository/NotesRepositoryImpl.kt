package com.example.mynewnotesapp.data.repository

import com.example.mynewnotesapp.data.data_source.NotesTableDao
import com.example.mynewnotesapp.data.model.NotesTable
import com.example.mynewnotesapp.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow

class NotesRepositoryImpl(
    private val notesTableDao: NotesTableDao
) : NotesRepository {
    override suspend fun insertOrUpdate(notesTable: NotesTable) {
        notesTableDao.insertNote(notesTable)
    }

    override suspend fun deleteNotes(id: String) {
        notesTableDao.deleteNote(id)
    }
    override fun getAllNotes(): Flow<List<NotesTable>> = notesTableDao.getAllNotes()
    override suspend fun getNoteById(id: String): NotesTable? {
        return notesTableDao.getNoteById(id)
    }
}