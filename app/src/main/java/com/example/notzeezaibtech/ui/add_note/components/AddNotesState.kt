package com.example.notzeezaibtech.ui.add_note.components

import com.example.notzeezaibtech.ui.notes_list.components.NotesData

data class AddNotesState(
    val note: NotesData = NotesData(), val headingText: String = "Add Note"
)