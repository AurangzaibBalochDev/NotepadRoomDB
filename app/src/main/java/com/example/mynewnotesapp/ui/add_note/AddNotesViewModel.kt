package com.example.mynewnotesapp.ui.add_note
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynewnotesapp.data.model.NotesTable
import com.example.mynewnotesapp.domain.usecases.AddNoteUseCase
import com.example.mynewnotesapp.domain.usecases.GetNoteById
import com.example.mynewnotesapp.ui.add_note.components.AddNotesState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddNotesViewModel(
    private val addNote: AddNoteUseCase,
    savedStateHandle: SavedStateHandle,
    private val getNoteById: GetNoteById
) : ViewModel() {

    private val _state = MutableStateFlow(AddNotesState())
    val state = _state.asStateFlow()

    init {
        val id = (savedStateHandle.get<String>("id") ?: "-1").toInt()
        if (id != -1) {
            viewModelScope.launch {
                getNoteById(id.toString())?.let { data ->
                    _state.update {
                        it.copy(
                            note = data
                        ) } } } } }


    fun setTitle(text: String) {
        _state.update {
            it.copy(
                note = it.note.copy(
                    title = text
                )) } }

    fun setMessage(text: String) {
        _state.update {
            it.copy(
                note = it.note.copy(
                    message = text
                )
            )
        }
    }

    fun saveNote(callBack: (String) -> Unit) {
        val notesData = state.value.note
        if (notesData.title.isBlank() || notesData.message.isBlank()) {
            callBack.invoke("Fields Empty")
            return
        }
        viewModelScope.launch {
            addNote(state.value.note)
            callBack.invoke("")
        }
    }


}