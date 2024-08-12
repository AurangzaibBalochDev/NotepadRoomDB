package com.example.mynewnotesapp.di

import androidx.room.Room
import com.example.mynewnotesapp.data.data_source.AppDatabase
import com.example.mynewnotesapp.data.data_source.NotesTableDao
import com.example.mynewnotesapp.data.repository.NotesRepositoryImpl
import com.example.mynewnotesapp.domain.repository.NotesRepository
import com.example.mynewnotesapp.domain.usecases.AddNoteUseCase
import com.example.mynewnotesapp.domain.usecases.DeleteNoteUsecase
import com.example.mynewnotesapp.domain.usecases.GetNoteById
import com.example.mynewnotesapp.domain.usecases.GetNotesUseCase
import com.example.mynewnotesapp.ui.add_note.AddNotesViewModel
import com.example.mynewnotesapp.ui.notes_list.NotesListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val sharedModules = module {

    // Provide a singleton instance of AppDatabase
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "AppDb")
            .build()
    }

    // Provide a factory instance of NotesTableDao
    factory {
        get<AppDatabase>().getMyDao()
    }

    // Provide a factory instance of NotesRepository
    factory<NotesRepository> {
        NotesRepositoryImpl(get())
    }

    // Provide use cases
    factory {
        AddNoteUseCase(get())
    }

    factory {
        DeleteNoteUsecase(get())
    }

    factory {
        GetNotesUseCase(get())
    }

    factory {
        GetNoteById(get())
    }

    // Provide ViewModels
    viewModel {
        AddNotesViewModel(get(), get(), get())
    }

    viewModel {
        NotesListViewModel(get(), get())
    }
}
