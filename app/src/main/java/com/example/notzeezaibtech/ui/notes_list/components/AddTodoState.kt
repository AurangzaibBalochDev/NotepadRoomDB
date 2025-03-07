package com.example.notzeezaibtech.ui.notes_list.components

data class AddTodoState(
    val todo: TodoData = TodoData(
        id = 0,
        title = "",
        completed = false,
        createdAt = ""
    ),
)