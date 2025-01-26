package com.example.mynewnotesapp.ui.notes_list.components

import android.annotation.SuppressLint
import com.example.mynewnotesapp.data.model.NotesTable
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@SuppressLint("ConstantLocale")
data class NotesData(
    val id: Int = 0,
    val title: String = "",
    val message: String = "",
    var noteType: String = "",
    val date: String = getCurrentDate(), // Default to current date
)

fun NotesData.toNotesTable(): NotesTable {
    return NotesTable(
        title = title,
        message = message,
        date = date, // Use the date property from NotesData
        noteType = noteType,
        id = id,
    )
}

fun getCurrentDate(): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
    return formatter.format(Date())
}
