package com.example.notzeezaibtech.ui.notes_list.components

import android.annotation.SuppressLint
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notzeezaibtech.domain.usecases.AddTodoUseCase
import com.example.notzeezaibtech.domain.usecases.GetTodoById
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddTodoViewModel(
    private val addTodo: AddTodoUseCase,
    savedStateHandle: SavedStateHandle,
    private val getTodoById: GetTodoById
) : ViewModel() {

    private val _state = MutableStateFlow(AddTodoState())
    val state = _state.asStateFlow()

    init {
        val id = (savedStateHandle.get<String>("id") ?: "-1").toInt()
        if (id != -1) {
            viewModelScope.launch {
                getTodoById(id.toString())?.let { data ->
                    _state.update {
                        it.copy(
                            todo = data,
                        )
                    }
                }
            }
        }
    }


    fun updateTodo(updatedTodo: TodoData) {
        viewModelScope.launch {
            try {
                addTodo(updatedTodo)
            } catch (e: Exception) {
                // Handle failure (e.g., show an error message)
            }
        }
    }

    fun setTodoTitle(text: String) {
        _state.update {
            val currentTodo = it.todo
            if (currentTodo.title != text) {
                it.copy(
                    todo = currentTodo.copy(
                        title = text,
                        createdAt = getCurrentDateForTodo(),
                    )
                )
            } else it
        }
    }

    fun setTodoCheck(isChecked: Boolean) {
        _state.update {
            val currentTodo = it.todo
            if (currentTodo.completed != isChecked) {
                it.copy(todo = currentTodo.copy(completed = isChecked))
            } else it
        }
    }

    @SuppressLint("SuspiciousIndentation")
    fun setTodo(callBack: (String) -> Unit) {
        val todoData = state.value.todo

        if (todoData.title.isEmpty() && !todoData.completed) {
            return
        } else if (todoData.title.isEmpty()||todoData.title.isBlank()){
            return
        }
        else

            viewModelScope.launch {
                try {
                    addTodo(todoData)
                    _state.update { it.copy(todo = TodoData()) } // Reset state after saving
                    callBack.invoke("")
                } catch (e: Exception) {
                    // Handle error case (e.g., show a toast)
                }
            }
    }
}
