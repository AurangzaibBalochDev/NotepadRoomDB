package com.example.mynewnotesapp.ui.notes_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynewnotesapp.data.model.NotesTable
import com.example.mynewnotesapp.domain.repository.NotesRepository
import com.example.mynewnotesapp.domain.usecases.DeleteNoteUsecase
import com.example.mynewnotesapp.domain.usecases.GetNotesUseCase
import com.example.mynewnotesapp.ui.add_note.components.AddNotesState
import com.example.mynewnotesapp.ui.notes_list.components.NoteResponse
import com.example.mynewnotesapp.ui.notes_list.components.NotesData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class NoteState(
    val noteResponse: NoteResponse<List<NotesData>> = NoteResponse.EmptyList()
)

class NotesListViewModel(
    private val getNotesUseCase: GetNotesUseCase,
    private val deleteNoteUsecase: DeleteNoteUsecase
) : ViewModel() {

    private val _state = MutableStateFlow(NoteState())
    val state = _state.asStateFlow()

    init {
        getAllNotes()
    }

    private fun getAllNotes() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    noteResponse = NoteResponse.Loading()
                )
            }
            getNotesUseCase.invoke().collectLatest { list ->
                if (list.isEmpty()) {
                    _state.update {
                        it.copy(
                            noteResponse = NoteResponse.EmptyList()
                        )
                    }
                } else {
                    _state.update {
                        it.copy(
                            noteResponse = NoteResponse.Success(list)
                        )
                    }
                }
            }
        }
    }

    fun deleteNote(id: String) {
        viewModelScope.launch {
            deleteNoteUsecase.invoke(id)
        }
    }

}