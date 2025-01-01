package com.example.mynewnotesapp.di

import androidx.room.Room
import com.example.mynewnotesapp.data.data_source.AppDatabase
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
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "AppDb")
            .addMigrations()
            .fallbackToDestructiveMigration()
            .build()
    }
    factory {
        get<AppDatabase>().getMyDao()
    }
    factory<NotesRepository> {
        NotesRepositoryImpl(get())
    }
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
    viewModel {
        AddNotesViewModel(get(), get(), get())
    }

    viewModel {
        NotesListViewModel(get(), get())
    }
}
