package com.example.mynewnotesapp.domain.repository

import com.example.mynewnotesapp.data.model.NotesTable
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    suspend fun insertOrUpdate(notesTable: NotesTable)
    suspend fun deleteNotes(id:String)
    fun getAllNotes(): Flow<List<NotesTable>>
    suspend fun getNoteById(id:String):NotesTable?

}