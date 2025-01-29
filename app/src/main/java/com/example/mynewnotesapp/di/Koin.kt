package com.example.mynewnotesapp.di

import androidx.room.Room
import com.example.mynewnotesapp.data.data_source.AppDatabase
import com.example.mynewnotesapp.data.repository.NotesRepositoryImpl
import com.example.mynewnotesapp.domain.repository.NotesRepository
import com.example.mynewnotesapp.domain.usecases.AddNoteUseCase
import com.example.mynewnotesapp.domain.usecases.AddTodoUseCase
import com.example.mynewnotesapp.domain.usecases.DeleteNoteUsecase
import com.example.mynewnotesapp.domain.usecases.GetNoteById
import com.example.mynewnotesapp.domain.usecases.GetNotesUseCase
import com.example.mynewnotesapp.domain.usecases.GetTodoById
import com.example.mynewnotesapp.domain.usecases.GetTodoUsecase
import com.example.mynewnotesapp.ui.add_note.AddNotesViewModel
import com.example.mynewnotesapp.ui.notes_list.NotesListViewModel
import com.example.mynewnotesapp.ui.notes_list.components.AddTodoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val sharedModules = module {
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "NewDatabase")
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<AppDatabase>().getMyDao() }
    single { get<AppDatabase>().getTodoDao() }


    single<NotesRepository> { NotesRepositoryImpl(get(), get()) }

    factory { AddNoteUseCase(get()) }
    factory { AddTodoUseCase(get()) }
    factory { DeleteNoteUsecase(get()) }
    factory { GetNotesUseCase(get()) }
    factory { GetTodoUsecase(get()) }  // Fixed naming
    factory { GetNoteById(get()) }
    factory { GetTodoById(get()) }

    // ViewModel injections
    viewModel { AddNotesViewModel(get(), get(), get()) }
    viewModel { NotesListViewModel(get(), get()) }
    viewModel { AddTodoViewModel(get(), get(), get()) }
}

