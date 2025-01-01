package com.example.mynewnotesapp.ui.notes_list.components

sealed class NoteResponse<T>(
    val data: T? = null, val message: String? = null
) {
    class EmptyList<T>() : NoteResponse<T>()
    class Loading<T>() : NoteResponse<T>()
    class Error<T>(error: String) : NoteResponse<T>(data = null, message = error)
    class Success<T>(data: T) : NoteResponse<T>(data = data, message = null)
}
