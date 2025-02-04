package com.example.mynewnotesapp.ui.notes_list.components

import com.example.mynewnotesapp.data.model.TodosTable
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


data class TodoData(
    var id: Int = 0,
    var title: String = "",
    var completed: Boolean = false,
    var createdAt: String = getCurrentDateForTodo(),
)


fun TodoData.toTodosTable(): TodosTable {
    return TodosTable(
        title = title,
        completed = completed,
        createdAt = createdAt,
        id = id,
    )
}


fun getCurrentDateForTodo(): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
    return formatter.format(Date())
}
