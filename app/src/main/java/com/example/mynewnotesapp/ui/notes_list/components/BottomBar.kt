package com.example.mynewnotesapp.ui.notes_list.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Toc
import androidx.compose.material.icons.filled.Today
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mynewnotesapp.R
import com.example.mynewnotesapp.ui.components.HorizentalSpacer
import com.example.mynewnotesapp.ui.components.Routes

@Composable
fun BottomBar(
    navController: NavHostController
) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .width(2.dp)
            .background(colorResource(R.color.black)),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = CenterVertically
    ) {
        IconButton(
            onClick = {
                if (Routes.NotesListScreen.name != navController.currentDestination?.route) {
                    navController.navigate(Routes.NotesListScreen.name)
                }
            }
        ) {
            Icon(
                Icons.Default.Home,
                contentDescription = "Home",
                tint = Color.White,
            )
        }
        HorizentalSpacer(10.dp)

        IconButton(
            onClick = {
                if (Routes.TodoListPage.name != navController.currentDestination?.route) {
                    navController.navigate(Routes.TodoListPage.name)
                }
            }
        ) {
            Icon(
                Icons.Default.Today,
                contentDescription = "Calendar",
                tint = Color.White
            )
        }
        HorizentalSpacer(10.dp)

        IconButton(
            onClick = {

            }
        ) {
            Icon(
                Icons.Default.Search,
                contentDescription = "Search",
                tint = Color.White
            )
        }
        HorizentalSpacer(10.dp)

        IconButton(
            onClick = {
                if (Routes.MyMenueScreen.name != navController.currentDestination?.route) {
                    navController.navigate(Routes.MyMenueScreen.name)
                }
            }
        ) {
            Icon(
                Icons.Default.Toc,
                contentDescription = "More",
                tint = Color.White
            )
        }
    }
}
