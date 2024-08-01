package com.example.mynewnotesapp.ui.notes_list.components

import com.example.mynewnotesapp.data.model.NotesTable
import org.koin.core.time.TimeInMillis

data class NotesData(
    val id: Int = 0,
    val title: String = "",
    val message: String = ""
)

fun NotesData.toNotesTable(): NotesTable {
    return NotesTable(
        title,
        message,
        System.currentTimeMillis(),
        id,
    )
}