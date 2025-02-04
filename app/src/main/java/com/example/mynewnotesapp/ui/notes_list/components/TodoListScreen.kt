package com.example.mynewnotesapp.ui.notes_list.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Toc
import androidx.compose.material.icons.filled.Today
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mynewnotesapp.R
import com.example.mynewnotesapp.ui.base.LocalNavHostController
import com.example.mynewnotesapp.ui.components.HeadingText
import com.example.mynewnotesapp.ui.components.HorizentalSpacer
import com.example.mynewnotesapp.ui.components.LoadingScreen
import com.example.mynewnotesapp.ui.components.Routes
import org.koin.androidx.compose.koinViewModel

@Composable
fun TodoListScreen(
    viewModel: TodoListViewModel = koinViewModel(),
    addTodoViewModel: AddTodoViewModel = koinViewModel()
) {

    val navController = LocalNavHostController.current
    val state by viewModel.state.collectAsState()
    val stateAdd by addTodoViewModel.state.collectAsState()

//    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val filteredTodos by remember {
        mutableStateOf(state.todoResponse.data ?: emptyList())
    }
    var searchedTodos by remember {
        mutableStateOf(state.todoResponse.data ?: emptyList())
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
                        "Todos",
                        color = Color.White,
                        style = TextStyle(fontWeight = FontWeight.Bold),
                        fontSize = 18.sp
                    )

                    if (isSearchActive) {
                        OutlinedTextField(
                            value = query,
                            onValueChange = {
                                query = it
                                searchedTodos = viewModel.filterNotesByTitleandDescription(it)
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

                }

                TextField(
                    value = stateAdd.todo.title,
                    onValueChange = {
                        addTodoViewModel.setTodoTitle(it)
                    },
                    modifier = Modifier.fillMaxWidth(0.9f),
                    placeholder = { Text("create a new todo..") },
                    trailingIcon = {
                      Checkbox(checked = stateAdd.todo.completed, onCheckedChange = {
                        addTodoViewModel.setTodoCheck(it)
                      })
                    })

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
                    onClick = {
                        navController.navigate(Routes.NotesListScreen.name)
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
//                        navController.navigate(Routes.TodoListPage.name)
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
                        if (filteredTodos.isEmpty()) {

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
//                        viewModel.deleteNote(id)
                        query = ""
                        isSearchActive = false
                    }
                    selectedNoteIds = emptySet()

                }

            } else {
                FabButton(onClick = {
                    addTodoViewModel.setTodo { todo ->
                        if (todo.isBlank()) {
                            Toast
                                .makeText(navController.context, "Note Saved", Toast.LENGTH_SHORT)
                                .show()

                        } else {
                            Toast
                                .makeText(
                                    navController.context,
                                    "Fields are empty...",
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        }
                    }

                })
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
            when (state.todoResponse) {
                is TodoResponse.EmptyList -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        HeadingText("Add something to your note")
                        selectedNoteIds = emptySet()
                    }
                }

                is TodoResponse.Error -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        HeadingText("Something went wrong")
                    }
                }

                is TodoResponse.Loading -> {
                    LoadingScreen(modifier = Modifier.fillMaxSize())
                }

                is TodoResponse.Success -> {
                    LazyColumn(
                        modifier = Modifier.padding(horizontal = 16.dp),
                    ) {
                        items(if (query.isNotEmpty()) searchedTodos else filteredTodos) { todo ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                                    .background(
                                        color = if (selectedNoteIds.contains(todo.id.toString())) colorResource(
                                            R.color.purple_700
                                        )
                                        else Color.Transparent, shape = RoundedCornerShape(5.dp)
                                    )
                            ) {
                                TodoItem(todo, onClick = {

                                    if (selectedNoteIds.isEmpty()) {
                                        navController.navigate(
                                            Routes.AddNotesScreen.name + "/${todo.id}"
                                        )

                                    } else if (selectedNoteIds.contains(todo.id.toString())) {
                                        selectedNoteIds -= todo.id.toString()
                                    } else selectedNoteIds += todo.id.toString()


                                }, onLongClick = {
                                    if (selectedNoteIds.contains(todo.id.toString())) {
                                        selectedNoteIds -= todo.id.toString()
                                    } else selectedNoteIds += todo.id.toString()


                                })
                            }
                        }
                    }
                }

                is TodoResponse.Loading -> TODO()
                is TodoResponse.Success -> TODO()
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