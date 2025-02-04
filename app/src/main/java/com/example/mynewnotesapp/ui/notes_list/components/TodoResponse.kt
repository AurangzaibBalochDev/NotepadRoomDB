package com.example.mynewnotesapp.ui.notes_list.components

sealed class TodoResponse<T>(
    val data: T? = null, var message: String? = null
) {
    class EmptyList<T>() : TodoResponse<T>()
    class Loading<T>() : TodoResponse<T>()
    class Error<T>(error: String) : TodoResponse<T>(data = null, message = error)
    class Success<T>(data: T) : TodoResponse<T>(data = data, message = null)
}
