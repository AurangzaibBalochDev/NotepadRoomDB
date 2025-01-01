package com.example.mynewnotesapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mynewnotesapp.ui.notes_list.components.NotesData
import org.koin.core.time.TimeInMillis


@Entity("Notes")
data class NotesTable(
    val title: String,
    val message: String,
    val timeInMillis: Long,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val imageUri:String?
)

fun NotesTable.toNotesData() = NotesData(
    id, title, message
)