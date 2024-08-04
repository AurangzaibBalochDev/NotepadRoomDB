package com.example.mynewnotesapp.ui.base


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mynewnotesapp.ui.add_note.AddNoteUi
import com.example.mynewnotesapp.ui.add_note.AddNotesViewModel
import com.example.mynewnotesapp.ui.components.Routes
import com.example.mynewnotesapp.ui.notes_list.NotesListScreen
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MyNavHost() {

    val controller = LocalNavHostController.current
    NavHost(navController = controller, startDestination = Routes.NotesListScreen.name) {

        composable(Routes.NotesListScreen.name) {
            NotesListScreen()
        }
        composable(Routes.AddNotesScreen.name) {

            AddNoteUi()
        }
        composable(Routes.AddNotesScreen.name + "/{id}") {

            AddNoteUi()
        }

    }
}