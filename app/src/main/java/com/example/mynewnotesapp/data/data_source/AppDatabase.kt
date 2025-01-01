package com.example.mynewnotesapp.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mynewnotesapp.data.model.NotesTable

@Database(entities = [NotesTable::class], version = 4)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getMyDao():NotesTableDao
}