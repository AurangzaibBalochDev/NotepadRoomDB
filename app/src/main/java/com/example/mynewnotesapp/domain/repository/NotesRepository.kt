package com.example.mynewnotesapp.domain.repository

import com.example.mynewnotesapp.data.model.NotesTable
import com.example.mynewnotesapp.data.model.TodosTable
import com.example.mynewnotesapp.ui.notes_list.components.TodoData
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    suspend fun insertOrUpdate(notesTable: NotesTable)
    suspend fun insertOrUpdateTodo(todosTable: TodosTable)
    suspend fun deleteNotes(id:String)
    suspend fun deleteTodo(id:String)
    fun getAllNotes(): Flow<List<NotesTable>>
    suspend fun getNoteById(id:String):NotesTable?
    suspend fun getTodoById(id:String):TodosTable?
    fun getAllTodos(): Flow<List<TodosTable>>


}