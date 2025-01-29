package com.example.mynewnotesapp.data.data_source

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.mynewnotesapp.data.model.NotesTable
import com.example.mynewnotesapp.data.model.TodosTable
import kotlinx.coroutines.flow.Flow


@Dao
interface TodoTableDao {
    @Upsert
    suspend fun insertTodo(todosTable: TodosTable)

    @Query("delete from todos where id = :id")
    suspend fun deleteTodo(id: String)

    @Query("select * from todos")
    fun getAllTodo(): Flow<List<TodosTable>>

    @Query("select * from todos where id = :id")
    suspend fun getTodoById(id:String):TodosTable?
}

