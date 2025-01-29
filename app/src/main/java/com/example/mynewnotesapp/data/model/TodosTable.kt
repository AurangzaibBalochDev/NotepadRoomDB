package com.example.mynewnotesapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mynewnotesapp.ui.notes_list.components.TodoData

@Entity("todos")
data class TodosTable(
    val title: String,
    val completed: Boolean,
    val createdAt: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)

fun TodosTable.toTodoData() = TodoData(
    id, title, completed, createdAt
)