package com.example.mynewnotesapp.ui.notes_list.components
import com.example.mynewnotesapp.ui.notes_list.components.TodoData

data class AddTodoStatea(
    val todo: TodoData = TodoData(
        id = 0,
        title = "",
        completed = false,
        createdAt = ""
    ),
)