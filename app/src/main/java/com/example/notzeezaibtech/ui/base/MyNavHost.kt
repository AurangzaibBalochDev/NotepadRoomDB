package com.example.notzeezaibtech.ui.base

import android.annotation.SuppressLint

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.notzeezaibtech.R
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.ui.unit.dp
import com.example.notzeezaibtech.ui.add_note.AddNoteUi
import com.example.notzeezaibtech.ui.components.Routes
import com.example.notzeezaibtech.ui.notes_list.NotesListScreen
import com.example.notzeezaibtech.ui.notes_list.components.MyMenueScreen
import com.example.notzeezaibtech.ui.notes_list.components.PrivacyPolicy
import com.example.notzeezaibtech.ui.notes_list.components.TodoListScreen

//@Composable
//fun MyNavHost() {
//
//    val controller = LocalNavHostController.current
//    NavHost(navController = controller, startDestination = Routes.TodoListPage.name) {
//
//        composable(Routes.NotesListScreen.name) {
//            NotesListScreen()
//        }
//        composable(Routes.AddNotesScreen.name) {
//
//            AddNoteUi()
//        }
//        composable(Routes.MyMenueScreen.name) {
//            MyMenueScreen()
//        }
//        composable(Routes.TodoListPage.name) {
//            TodoListScreen()
//        }
//
//    }
//}


@Composable
fun MainScreen() {
    val navController = LocalNavHostController.current // ✅ Use the provided NavController
    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->
        MyNavHost(navController, Modifier.padding(innerPadding))
    }
}

@Composable
fun MyNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Routes.TodoListPage.name,
        modifier = modifier
    ) {


        composable(Routes.NotesListScreen.name) { NotesListScreen() }
        composable(Routes.AddNotesScreen.name) { AddNoteUi() }
        composable(Routes.MyMenueScreen.name) { MyMenueScreen() }
        composable(Routes.TodoListPage.name) { TodoListScreen() }
        composable(Routes.PrivacyPolicy.name) { PrivacyPolicy() }
        composable(Routes.PrivacyPolicy.name + "/{id}") {
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

val isSearchActiveFromNavNote: MutableState<Boolean> = mutableStateOf(false)

@SuppressLint("UnrememberedMutableState")
@Composable
fun BottomNavBar(navController: NavHostController) {
    val context = LocalContext.current


    val items = listOf(
        BottomNavItem("Home", Routes.NotesListScreen.name, Icons.Default.Home),
        BottomNavItem("Todo", Routes.TodoListPage.name, Icons.Default.CalendarMonth),
        BottomNavItem("Search", Routes.Search.name, Icons.Default.Search),
        BottomNavItem("Menu", Routes.MyMenueScreen.name, Icons.Default.Menu),
    )

    Surface(
        color = Color.Transparent,
        border = BorderStroke(width = 0.dp, color = colorResource(R.color.black))
    ) {

        NavigationBar(containerColor = colorResource(R.color.black), contentColor = Color.White) {
            val currentRoute = currentRoute(navController)

            items.forEach { item ->
                NavigationBarItem(
                    icon = { Icon(item.icon, contentDescription = item.label, tint = Color.White) },
                    label = { Text(item.label, color = Color.White) },
                    selected = currentRoute == item.route,
                    onClick = {
                        if (item.label == "Search") {
                            isSearchActiveFromNavNote.value = true
                        } else {
                            isSearchActiveFromNavNote.value = false
                            navController.navigate(item.route)
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Black,
                        selectedTextColor = Color.Black,
                        indicatorColor = colorResource(R.color.purple_700),
                        unselectedIconColor = Color.White,
                        unselectedTextColor = Color.White,

                        )
                )
            }
        }
    }
}


@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

data class BottomNavItem(
    val label: String,
    val route: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)


