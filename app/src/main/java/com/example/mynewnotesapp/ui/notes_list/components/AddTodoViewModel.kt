package com.example.mynewnotesapp.ui.notes_list.components

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynewnotesapp.domain.usecases.AddTodoUseCase
import com.example.mynewnotesapp.domain.usecases.GetNoteById
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddTodoViewModel(
    private val addTodo: AddTodoUseCase,
    savedStateHandle: SavedStateHandle,
//    private val getTodoById: GetTodoById
) : ViewModel() {

    private val _state = MutableStateFlow(AddTodoStatea())
     val state = _state.asStateFlow()

    init {
//        val id = (savedStateHandle.get<String>("id") ?: "-1").toInt()
//        if (id != -1) {
//            viewModelScope.launch {
//                getTodoById(id.toString())?.let { data ->
//                    _state.update {
//                        it.copy(
//                            todo = data
//                        )
//                    }
//                }
//            }
//        }
    }


    fun setTodoTitle(text: String) {
        _state.update {
            it.copy(
                todo = it.todo.copy(
                    title = text
                )
            )
        }
    }
    fun setTodoCheck(isChceked: Boolean) {
        _state.update {
            it.copy(
                todo = it.todo.copy(
                    completed = isChceked
                )
            )
        }
    }

    fun setTodo(callBack: (String) -> Unit) {
        val todoData = state.value.todo

        if (todoData.title.isBlank()) {
            return
        } else
            viewModelScope.launch {
                addTodo(todoData)
                callBack.invoke("")
            }
    }


}
