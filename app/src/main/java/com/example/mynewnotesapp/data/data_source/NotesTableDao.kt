package com.example.mynewnotesapp.data.data_source

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.mynewnotesapp.data.model.NotesTable
import kotlinx.coroutines.flow.Flow


@Dao
interface NotesTableDao {
    @Upsert
    suspend fun insertNote(notesTable: NotesTable)

    @Query("delete from notes where id = :id")
    suspend fun deleteNote(id: String)

    @Query("select * from Notes")
    fun getAllNotes(): Flow<List<NotesTable>>

    @Query("select * from notes where id = :id")
    suspend fun getNoteById(id:String):NotesTable?
}

