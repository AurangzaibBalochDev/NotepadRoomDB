package com.example.notzeezaibtech.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notzeezaibtech.data.model.NotesTable
import com.example.notzeezaibtech.data.model.TodosTable

@Database(entities = [NotesTable::class, TodosTable::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getMyDao():NotesTableDao
    abstract fun getTodoDao():TodoTableDao
}