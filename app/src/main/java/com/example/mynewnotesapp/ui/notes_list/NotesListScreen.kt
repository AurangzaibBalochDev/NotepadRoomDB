package com.example.mynewnotesapp.ui.notes_list

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mynewnotesapp.R
import com.example.mynewnotesapp.ui.base.LocalNavHostController
import com.example.mynewnotesapp.ui.components.HeadingText
import com.example.mynewnotesapp.ui.components.HorizentalSpacer
import com.example.mynewnotesapp.ui.components.LoadingScreen
import com.example.mynewnotesapp.ui.components.Routes
import com.example.mynewnotesapp.ui.notes_list.components.FabButton
import com.example.mynewnotesapp.ui.notes_list.components.NoteItem
import com.example.mynewnotesapp.ui.notes_list.components.NoteResponse
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun NotesListScreen(viewModel: NotesListViewModel = koinViewModel()) {

    val navController = LocalNavHostController.current
    val state by viewModel.state.collectAsState()

    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    var buttonList by remember {
        mutableStateOf(
            listOf(
                "All",
                "Home",
                "Office",
                "Grocery",
                "Others",
                "Untitled1",
                "Untitled2",
                "Untitled3",
            )
        )
    }

    var selectedButton by remember { mutableStateOf("All") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier
                    .background(color = colorResource(id = R.color.black))
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    "Notes",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier.padding(start = 10.dp)
                )
                HorizentalSpacer(10.dp)
                LazyRow(
                    modifier = Modifier.fillMaxWidth(), state = lazyListState,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    items(buttonList) { currentItem ->
                        OutlinedButton(
                            onClick = {
                                //Code 159345
                                selectedButton = currentItem
                                if (currentItem != "All") {

                                    buttonList =
                                        listOf("All") + listOf(currentItem) + buttonList.filter { it != "All" && it != currentItem }

                                    coroutineScope.launch {
                                        lazyListState.scrollToItem(0)
                                    }
                                }


                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                if (selectedButton == currentItem) colorResource(id = R.color.amlostBlack)
                                else colorResource(R.color.purple_700)
                            )
                        ) {
                            Text(currentItem, fontSize = 12.sp)
                        }
                    }
                }
            }
        },
        floatingActionButton = {
            FabButton {
                navController.navigate(Routes.AddNotesScreen.name)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(color = colorResource(id = R.color.black)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (val response = state.noteResponse) {
                is NoteResponse.EmptyList -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        HeadingText("Add something to your note")
                    }
                }

                is NoteResponse.Error -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        HeadingText("Something went wrong")
                    }
                }

                is NoteResponse.Loading -> {
                    LoadingScreen(modifier = Modifier.fillMaxSize())
                }

                is NoteResponse.Success -> {
                    LazyColumn(
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        items(response.data ?: emptyList()) { note ->
                            NoteItem(note, onClick = {
                                navController.navigate(
                                    Routes.AddNotesScreen.name + "/${note.id}"
                                )
                            }, onDeleteClick = {
                                viewModel.deleteNote(note.id.toString())
                            })
                        }
                    }
                }
            }
        }
    }
}
