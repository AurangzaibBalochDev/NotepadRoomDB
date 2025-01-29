package com.example.mynewnotesapp.ui.notes_list

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Toc
import androidx.compose.material.icons.filled.Today
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
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


@Preview(showBackground = true)
@Composable
fun NotesListScreen(viewModel: NotesListViewModel = koinViewModel()) {

    val navController = LocalNavHostController.current
    val state by viewModel.state.collectAsState()

    var selectedButton by remember { mutableStateOf("All") }
//    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    var filteredNotes by remember {
        mutableStateOf(state.noteResponse.data ?: emptyList())
    }
    var searchedNotes by remember {
        mutableStateOf(state.noteResponse.data ?: emptyList())
    }



    LaunchedEffect(state.noteResponse) {
        filteredNotes = if (selectedButton == "All") {
            state.noteResponse.data ?: emptyList()
        } else {
            state.noteResponse.data?.filter { it.noteType == selectedButton } ?: emptyList()
        }
    }

    var buttonList by remember {
        mutableStateOf(
            listOf(
                "All",
                "Personal",
                "Home",
                "Office",
                "Others",
            )
        )
    }

    var isSearchActive by remember { mutableStateOf(false) }
    var isSearchOnceClicked by remember { mutableStateOf(false) }
    var selectedNoteIds by remember { mutableStateOf<Set<String>>(emptySet()) }

    var query by remember {
        mutableStateOf("")
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column(modifier = Modifier.background(color = colorResource(id = R.color.black))) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = CenterVertically
                ) {
                    Text(
                        "Notes",
                        color = Color.White,
                        style = TextStyle(fontWeight = FontWeight.Bold),
                        fontSize = 18.sp
                    )

                    if (isSearchActive) {
                        OutlinedTextField(
                            value = query,
                            onValueChange = {
                                query = it
                                searchedNotes = viewModel.filterNotesByTitleandDescription(it)
                            },
                            placeholder = {
                                Text("search here...")
                            },
                            leadingIcon = {

                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "Back", Modifier.clickable {
                                        isSearchActive = false
                                        query = ""
                                        viewModel.filterNotesByTitleandDescription(query)
                                    }
                                )
                            },
                            colors = TextFieldDefaults.colors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                unfocusedSuffixColor = Color.Transparent
                            ),
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search",
                                    modifier = Modifier.clickable {
                                        viewModel.filterNotesByTitleandDescription(query)
                                    })
                            },
                            modifier = Modifier.padding(5.dp), shape = CircleShape,
                        )
                    } else {
                        isSearchActive = false
                    }

                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .padding(5.dp)
                            .clip(shape = CircleShape),
//                        state = lazyListState,
                    ) {

                        items(buttonList) { currentItem ->
                            Button(
                                onClick = {
                                    selectedButton = currentItem
                                    filteredNotes = viewModel.filterNotes(currentItem)
                                    if (currentItem != "All") {
                                        buttonList =
                                            listOf("All") + listOf(currentItem) + buttonList.filter { it != "All" && it != currentItem }
//                                        coroutineScope.launch {
//                                            lazyListState.scrollToItem(0)
//                                        }
                                    } else {
                                        filteredNotes = state.noteResponse.data ?: emptyList()
                                        searchedNotes = state.noteResponse.data ?: emptyList()
                                    }
                                },
                                modifier = Modifier.padding(
                                    start = if (currentItem != buttonList[0]) 5.dp else 0.dp,
                                    end = 5.dp
                                ), colors = ButtonDefaults.buttonColors(
                                    if (selectedButton == currentItem) colorResource(id = R.color.amlostBlack)
                                    else colorResource(R.color.purple_700)
                                )
                            ) {
                                Text(currentItem, fontSize = 12.sp)
                            }
                        }
                    }

                }
            }
        },
        bottomBar = {

            Divider(
                color = colorResource(R.color.amlostBlack),
                thickness = 1.dp,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .width(2.dp)
                    .background(colorResource(R.color.black)),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = CenterVertically
            ) {
                IconButton(
                    onClick = {},
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
                        navController.navigate(Routes.TodoListPage.name)
                    }
                ) {
                    Icon(
                        Icons.Default.Today,
                        contentDescription = "Calender",
                        tint = Color.White
                    )
                }
                HorizentalSpacer(10.dp)

                IconButton(

                    onClick = {
                        if (filteredNotes.isEmpty()) {

                            isSearchActive = false
                            Toast.makeText(
                                navController.context,
                                "Nothing to search",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            isSearchActive = true
                            isSearchOnceClicked = true
                        }

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
                        navController.navigate(Routes.MyMenueScreen.name)
                    }
                ) {
                    Icon(
                        Icons.Default.Toc,
                        contentDescription = "More",
                        tint = Color.White
                    )
                }
            }

        },


        floatingActionButton = {

            if (selectedNoteIds.isNotEmpty()) {
                DeleteIconButton {
                    selectedNoteIds.forEach { id ->
                        viewModel.deleteNote(id)
                        query = ""
                        isSearchActive = false
                    }
                    selectedNoteIds = emptySet()

                }

            } else {
                FabButton {
                    navController.navigate(Routes.AddNotesScreen.name)
                }
            }
        },


        ) { paddingValues ->


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(color = colorResource(id = R.color.black)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (state.noteResponse) {
                is NoteResponse.EmptyList -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        HeadingText("Add something to your note")
                        selectedNoteIds = emptySet()
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
                        modifier = Modifier.padding(horizontal = 16.dp),
                    ) {
                        items(if (query.isNotEmpty()) searchedNotes else filteredNotes) { note ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                                    .background(
                                        color = if (selectedNoteIds.contains(note.id.toString())) colorResource(
                                            R.color.purple_700
                                        )
                                        else Color.Transparent, shape = RoundedCornerShape(5.dp)
                                    )
                            ) {
                                NoteItem(note, onClick = {

                                    if (selectedNoteIds.isEmpty()) {
                                        navController.navigate(
                                            Routes.AddNotesScreen.name + "/${note.id}"
                                        )

                                    } else if (selectedNoteIds.contains(note.id.toString())) {
                                        selectedNoteIds -= note.id.toString()
                                    } else selectedNoteIds += note.id.toString()


                                }, onLongClick = {
                                    if (selectedNoteIds.contains(note.id.toString())) {
                                        selectedNoteIds -= note.id.toString()
                                    } else selectedNoteIds += note.id.toString()


                                })
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DeleteIconButton(onClick: () -> Unit) {
    FloatingActionButton(
        modifier = Modifier
            .padding(bottom = 10.dp)
            .size(50.dp)
            .clip(RoundedCornerShape(100)),
        onClick = onClick,
        containerColor = Color.Red,
        contentColor = Color.White
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = null,
            modifier = Modifier.size(20.dp),
        )
    }
}