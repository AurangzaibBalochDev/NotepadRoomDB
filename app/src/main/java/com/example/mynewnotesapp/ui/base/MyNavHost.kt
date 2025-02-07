package com.example.mynewnotesapp.ui.base


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mynewnotesapp.ui.add_note.AddNoteUi
import com.example.mynewnotesapp.ui.components.Routes
import com.example.mynewnotesapp.ui.notes_list.NotesListScreen
import com.example.mynewnotesapp.ui.notes_list.components.MyMenueScreen
import com.example.mynewnotesapp.ui.notes_list.components.TodoListScreen

@Composable
fun MyNavHost() {

    val controller = LocalNavHostController.current
    NavHost(navController = controller, startDestination = Routes.TodoListPage.name) {

        composable(Routes.NotesListScreen.name) {
            NotesListScreen()
        }
        composable(Routes.AddNotesScreen.name) {

            AddNoteUi()
        }
        composable(Routes.MyMenueScreen.name) {
            MyMenueScreen()
        }
        composable(Routes.TodoListPage.name) {
            TodoListScreen()
        }
        composable(Routes.TodoListPage.name + "/{id}") {
            TodoListScreen()
        }
        composable(Routes.AddNotesScreen.name + "/{id}") {
            AddNoteUi()
        }

    }
}