package com.example.notzeezaibtech.ui.notes_list.components

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import com.example.notzeezaibtech.ui.base.LocalNavHostController
import com.example.notzeezaibtech.ui.base.isSearchActiveFromNavNote
import com.example.notzeezaibtech.ui.components.HeadingText
import com.example.notzeezaibtech.ui.components.LoadingScreen
import com.example.notzeezaibtech.ui.components.Routes
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

val isEditModeOn: MutableState<Boolean> = mutableStateOf(false)


@SuppressLint("UnrememberedMutableState")
@Composable
fun TodoListScreen(
    viewModel: TodoListViewModel = koinViewModel(),
    addTodoViewModel: AddTodoViewModel = koinViewModel(),

    ) {
    val navController = LocalNavHostController.current
    val context = navController.context
    val state by viewModel.state.collectAsState()
    val stateAdd by addTodoViewModel.state.collectAsState()


    val filteredTodos by remember(state.todoResponse.data) {
        mutableStateOf(state.todoResponse.data ?: emptyList())
    }

    var searchedTodos by remember(state.todoResponse.data) {
        mutableStateOf(state.todoResponse.data ?: emptyList())
    }

    val emptyTodo by remember {
        mutableStateOf(state.todoResponse.data ?: emptyList())
    }


    var isSearchActive by remember { mutableStateOf(false) }
    var isSearchOnceClicked by remember { mutableStateOf(false) }
    var selectedTodoIds by remember { mutableStateOf<Set<String>>(emptySet()) }
    var clickedItemIds by remember { mutableStateOf<Set<String>>(emptySet()) }

    var backPressedOnce by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()
    var backPressJob: Job? by remember { mutableStateOf(null) }

    BackHandler {
        if (backPressedOnce) {
            navController.navigate(Routes.NotesListScreen.name)
//            (context as? android.app.Activity)?.finish() // Exit app
        } else {
            backPressedOnce = true
//            Toast.makeText(context, "Press again to exit", Toast.LENGTH_SHORT).show()

            // Reset the flag after 2 seconds
            backPressJob?.cancel()
            backPressJob = coroutineScope.launch {
//                delay(2000)
                backPressedOnce = false
            }
        }
    }

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

                    if (isSearchActiveFromNavNote.value) {

                        OutlinedTextField(
                            value = query,
                            onValueChange = {
                                query = it
                                searchedTodos = viewModel.filterTodosBySearch(it)
                            }, maxLines = 1,
                            placeholder = {
                                Text("search here...")
                            },
                            leadingIcon = {

                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "Back", Modifier.clickable {
                                        isSearchActiveFromNavNote.value = false
                                        query = ""
                                        viewModel.filterTodosBySearch(query)
                                    }
                                )
                            },
                            colors = TextFieldDefaults.colors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                unfocusedSuffixColor = Color.Transparent,
                            ),
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search", tint = Color.Black,
                                    modifier = Modifier
                                        .clickable {
                                            viewModel.filterTodosBySearch(query)
                                        }
                                )
                            },
                            modifier = Modifier.padding(5.dp), shape = CircleShape,
                        )


                    } else {
                        isSearchActiveFromNavNote.value = false
                    }

                }
                if (!isSearchActiveFromNavNote.value) {
                    TextField(
                        value = stateAdd.todo.title,
                        onValueChange = {
                            addTodoViewModel.setTodoTitle(it)

                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedTrailingIconColor = Color.White,
                            unfocusedTrailingIconColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .padding(top = 10.dp)
                            .border(1.dp, Color.White, shape = RoundedCornerShape(5.dp)),
                        placeholder = { Text("create a new todo..", color = Color.White) }, maxLines = 2,
                        trailingIcon = {
                            Checkbox(checked = stateAdd.todo.completed, onCheckedChange = {
                                if (stateAdd.todo.title.isNotEmpty()) {
                                    addTodoViewModel.setTodoCheck(it)

                                }
                            })
                        })
                }
            }
        },


        floatingActionButton = {

            if (selectedTodoIds.isNotEmpty()) {
                DeleteIconButton {
                    selectedTodoIds.forEach { id ->
                        viewModel.deleteTodo(id)
                        query = ""
                        isSearchActive = false
                    }
                    selectedTodoIds = emptySet()

                }

            } else {
                FabButton(onClick = {
                    addTodoViewModel.setTodo { todo ->
                        if (todo.isNotEmpty()||todo.isNotBlank()) {
                            Toast.makeText(context,"Todo is empty",Toast.LENGTH_SHORT).show()

                        } else {

                            Toast.makeText(context,"Todo is saved",Toast.LENGTH_SHORT).show()

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
                .background(color = colorResource(id = R.color.black))
                .padding(top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (state.todoResponse) {
                is TodoResponse.EmptyList -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        HeadingText("Add something to your Todo")
                        selectedTodoIds = emptySet()
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
                                        color = if (selectedTodoIds.contains(todo.id.toString())) colorResource(
                                            R.color.purple_700
                                        )
                                        else Color.Transparent, shape = RoundedCornerShape(5.dp)
                                    )
                            ) {
                                TodoItem(todo, onClick = {

                                    isEditModeOn.value = true

                                    if (selectedTodoIds.isEmpty()) {
                                        if (stateAdd.todo.title != todo.title) {
                                            navController.navigate(
                                                Routes.TodoListPage.name + "/${todo.id}"
                                            )


                                        } else {
                                            Toast.makeText(
                                                context,
                                                "Note is already in Edit Mode",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }


                                    } else if (selectedTodoIds.contains(todo.id.toString())) {
                                        selectedTodoIds -= todo.id.toString()
                                    } else selectedTodoIds += todo.id.toString()


                                }, onLongClick = {
                                    if (selectedTodoIds.contains(todo.id.toString())) {
                                        selectedTodoIds -= todo.id.toString()
                                    } else selectedTodoIds += todo.id.toString()
                                }, viewModel = addTodoViewModel)
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
//            .padding(bottom = 10.dp)
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