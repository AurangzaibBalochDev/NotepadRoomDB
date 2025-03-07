package com.example.notzeezaibtech.di

import androidx.room.Room
import com.example.notzeezaibtech.data.data_source.AppDatabase
import com.example.notzeezaibtech.data.repository.NotesRepositoryImpl
import com.example.notzeezaibtech.domain.repository.NotesRepository
import com.example.notzeezaibtech.domain.usecases.AddNoteUseCase
import com.example.notzeezaibtech.domain.usecases.AddTodoUseCase
import com.example.notzeezaibtech.domain.usecases.DeleteNoteUsecase
import com.example.notzeezaibtech.domain.usecases.DeleteTodoUsecase
import com.example.notzeezaibtech.domain.usecases.GetNoteById
import com.example.notzeezaibtech.domain.usecases.GetNotesUseCase
import com.example.notzeezaibtech.domain.usecases.GetTodoById
import com.example.notzeezaibtech.domain.usecases.GetTodoUsecase
import com.example.notzeezaibtech.ui.add_note.AddNotesViewModel
import com.example.notzeezaibtech.ui.notes_list.NotesListViewModel
import com.example.notzeezaibtech.ui.notes_list.components.AddTodoViewModel
import com.example.notzeezaibtech.ui.notes_list.components.TodoListViewModel
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
    factory { DeleteTodoUsecase(get()) }
    factory { GetNotesUseCase(get()) }
    factory { GetTodoUsecase(get()) }  // Fixed naming
    factory { GetNoteById(get()) }
    factory { GetTodoById(get()) }

    // ViewModel injections
    viewModel { AddNotesViewModel(get(), get(), get()) }
    viewModel { NotesListViewModel(get(), get()) }
    viewModel { AddTodoViewModel(get(), get(),get())}
    viewModel { TodoListViewModel(get(),get())}
}

