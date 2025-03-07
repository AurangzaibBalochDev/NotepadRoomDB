package com.example.notzeezaibtech.data.repository

import com.example.notzeezaibtech.data.data_source.NotesTableDao
import com.example.notzeezaibtech.data.data_source.TodoTableDao
import com.example.notzeezaibtech.data.model.NotesTable
import com.example.notzeezaibtech.data.model.TodosTable
import com.example.notzeezaibtech.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow

class NotesRepositoryImpl(
    private val notesTableDao: NotesTableDao,
    private val todoTableDao: TodoTableDao
) : NotesRepository {
    override suspend fun insertOrUpdate(notesTable: NotesTable) {
        notesTableDao.insertNote(notesTable)
    }

    override suspend fun insertOrUpdateTodo(todosTable: TodosTable) {
        todoTableDao.insertTodo(todosTable)
    }


    override suspend fun deleteNotes(id: String) {
        notesTableDao.deleteNote(id)
    }

    override suspend fun deleteTodo(id: String) {
        todoTableDao.deleteTodo(id)
    }

    override fun getAllNotes(): Flow<List<NotesTable>> = notesTableDao.getAllNotes()
    override suspend fun getNoteById(id: String): NotesTable? {
        return notesTableDao.getNoteById(id)
    }

    override suspend fun getTodoById(id: String): TodosTable? {
        return todoTableDao.getTodoById(id)
    }

    override fun getAllTodos(): Flow<List<TodosTable>> = todoTableDao.getAllTodo()
}