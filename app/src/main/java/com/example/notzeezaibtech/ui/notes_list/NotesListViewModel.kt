package com.example.notzeezaibtech.ui.notes_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notzeezaibtech.domain.usecases.DeleteNoteUsecase
import com.example.notzeezaibtech.domain.usecases.GetNotesUseCase
import com.example.notzeezaibtech.ui.notes_list.components.NoteResponse
import com.example.notzeezaibtech.ui.notes_list.components.NotesData
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

    fun filterNotes(category: String): List<NotesData> {
        val data = state.value.noteResponse.data ?: return emptyList()

        return if (category == "All") {
            data
        } else if (category == "Others") {
            data.filter { it.noteType != "Personal" && it.noteType != "Home" && it.noteType != "Office" }
        } else {
            data.filter { it.noteType == category }
        }
    }

    fun filterNotesByTitleandDescription(keywords: String): List<NotesData> {
        val data = state.value.noteResponse.data ?: return emptyList()
        return if (keywords.isEmpty()) {
            data
        } else {
            data.filter { note ->
                note.title.contains(keywords, ignoreCase = true) ||
                        note.message.contains(keywords, ignoreCase = true)
            }
        }
    }

}