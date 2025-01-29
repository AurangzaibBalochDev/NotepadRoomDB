package com.example.mynewnotesapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mynewnotesapp.ui.notes_list.components.NotesData


@Entity("Notes")
data class NotesTable(
    val title: String,
    val message: String,
    val date:String,
    val noteType:String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)

fun NotesTable.toNotesData() = NotesData(
    id, title, message,noteType,date
)
