package com.example.mynewnotesapp.ui.add_note.components

import com.example.mynewnotesapp.ui.notes_list.components.NotesData

data class AddNotesState(
    val note: NotesData = NotesData(), val headingText: String = "Add Note"
)