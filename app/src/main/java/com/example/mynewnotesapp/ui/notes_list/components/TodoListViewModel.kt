package com.example.mynewnotesapp.ui.notes_list.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynewnotesapp.domain.usecases.DeleteNoteUsecase
import com.example.mynewnotesapp.domain.usecases.DeleteTodoUsecase
import com.example.mynewnotesapp.domain.usecases.GetNotesUseCase
import com.example.mynewnotesapp.domain.usecases.GetTodoUsecase
import com.example.mynewnotesapp.ui.notes_list.components.NoteResponse
import com.example.mynewnotesapp.ui.notes_list.components.NotesData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class TodoState(
    val todoResponse: TodoResponse<List<TodoData>> = TodoResponse.EmptyList()
)
class TodoListViewModel(
    private val getTodoUsecase: GetTodoUsecase,
    private val deleteTodoUsecase: DeleteTodoUsecase
) : ViewModel() {

    private val _state = MutableStateFlow(TodoState())
    val state = _state.asStateFlow()



    init {
        getAllTodos()
    }

    private fun getAllTodos() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    todoResponse = TodoResponse.Loading()
                )
            }
            getTodoUsecase.invoke().collectLatest { list ->
                if (list.isEmpty()) {
                    _state.update {
                        it.copy(
                            todoResponse = TodoResponse.EmptyList()
                        )
                    }
                } else {
                    _state.update {
                        it.copy(
                            todoResponse = TodoResponse.Success(list)
                        )

                }
            }
        }}
    }

    fun deleteTodo(id: String) {
        viewModelScope.launch {
            deleteTodoUsecase.invoke(id)
        }
    }
    fun putItemTextToState(text: String) {
        state.value.todoResponse.data?.forEach {
            it.title = text
        }
    }
    fun updateCheck(id: String, isChecked: Boolean) {
        state.value.todoResponse.data?.forEach {

                it.completed = isChecked

        }
    }

    fun filterByCompletedOrNot(){
        state.value.todoResponse.data?.filter {
            it.completed
        }

    }

//    fun filterNotes(category: String): List<TodoData> {
//
//    }

    fun filterTodosBySearch(keywords: String): List<TodoData> {
        val data = state.value.todoResponse.data ?: return emptyList()
        return if (keywords.isEmpty()) {
            data
        } else {
            data.filter { note ->
                note.title.contains(keywords, ignoreCase = true) ||
                        note.title.contains(keywords, ignoreCase = true)
            }
        }
    }

}