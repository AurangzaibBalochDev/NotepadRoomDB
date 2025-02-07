package com.example.mynewnotesapp.ui.notes_list.components

import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.SavedStateHandle
import com.example.mynewnotesapp.R
import com.example.mynewnotesapp.ui.base.LocalNavHostController
import com.example.mynewnotesapp.ui.components.HeadingText
import com.example.mynewnotesapp.ui.components.HorizentalSpacer
import com.example.mynewnotesapp.ui.components.LoadingScreen
import com.example.mynewnotesapp.ui.components.Routes
import com.example.mynewnotesapp.ui.components.VerticalSpacer
import org.koin.androidx.compose.koinViewModel

@Composable
fun TodoListScreen(
    viewModel: TodoListViewModel = koinViewModel(),
    addTodoViewModel: AddTodoViewModel = koinViewModel(),

    ) {
    val navController = LocalNavHostController.current
    val context = navController.context
    val state by viewModel.state.collectAsState()
    val stateAdd by addTodoViewModel.state.collectAsState()

    val lazyState = rememberLazyListState()

    val filteredTodos by remember(state.todoResponse.data) {
        mutableStateOf(state.todoResponse.data ?: emptyList())
    }

    var searchedTodos by remember(state.todoResponse.data) {
        mutableStateOf(state.todoResponse.data ?: emptyList())
    }


    var isSearchActive by remember { mutableStateOf(false) }
    var isSearchOnceClicked by remember { mutableStateOf(false) }
    var selectedTodoIds by remember { mutableStateOf<Set<String>>(emptySet()) }
    var clickedItemIds by remember { mutableStateOf<Set<String>>(emptySet()) }

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
                                searchedTodos = viewModel.filterTodosBySearch(it)
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
                                    contentDescription = "Search", tint = Color.White,
                                    modifier = Modifier
                                        .clickable {
                                            viewModel.filterTodosBySearch(query)
                                        }
                                        .background(color = Color.White)
                                )
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
                    placeholder = { Text("create a new todo..", color = Color.White) },
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
                        navController.popBackStack()
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
                        if (todo.isEmpty()) {
                            Toast
                                .makeText(context, "Fields are empty", Toast.LENGTH_SHORT)
                                .show()

                        } else {
                            Toast
                                .makeText(
                                    context,
                                    "Todo saved.",
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
                        reverseLayout = true,
                        state = lazyState
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

                                    if (selectedTodoIds.isEmpty()) {

                                        navController.navigate(
                                            Routes.TodoListPage.name + "/${todo.id}"
                                        )


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