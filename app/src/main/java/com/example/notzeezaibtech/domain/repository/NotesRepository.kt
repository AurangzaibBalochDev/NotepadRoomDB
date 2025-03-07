package com.example.notzeezaibtech.domain.repository

import com.example.notzeezaibtech.data.model.NotesTable
import com.example.notzeezaibtech.data.model.TodosTable
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