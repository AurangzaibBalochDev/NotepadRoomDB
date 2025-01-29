package com.example.mynewnotesapp.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mynewnotesapp.data.model.NotesTable
import com.example.mynewnotesapp.data.model.TodosTable

@Database(entities = [NotesTable::class, TodosTable::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getMyDao():NotesTableDao
    abstract fun getTodoDao():TodoTableDao
}