package com.example.notzeezaibtech.ui.add_note

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Save
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notzeezaibtech.R
import com.example.notzeezaibtech.ui.add_note.components.CustomTextField
import com.example.notzeezaibtech.ui.base.LocalNavHostController
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddNoteUi(viewModel: AddNotesViewModel = koinViewModel()) {
    //159345
    val navController = LocalNavHostController.current
    val context = navController.context
    val state by viewModel.state.collectAsState()
    var selectedButton by remember { mutableStateOf("All") }
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

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

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = colorResource(R.color.black),
        contentColor = colorResource(R.color.white),
        topBar = {
            LazyRow(
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .fillMaxWidth()
                    .padding(10.dp), state = lazyListState,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                items(buttonList) { currentItem ->
                    OutlinedButton(
                        onClick = {
                            selectedButton = currentItem
                            viewModel.setType(currentItem)

                            if (currentItem != "All") {
                                buttonList =
                                    listOf("All") + listOf(currentItem) + buttonList.filter { it != "All" && it != currentItem }
                                coroutineScope.launch {
                                    lazyListState.scrollToItem(0)
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            if (selectedButton == currentItem) colorResource(id = R.color.amlostBlack)
                            else colorResource(R.color.creamyWhite)
                        )
                    ) {
                        Text(currentItem, fontSize = 12.sp)
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                modifier = Modifier.padding(5.dp),
                onClick = {
                    viewModel.saveNote { note ->
                        if (note.isBlank()) {
                            Toast
                                .makeText(context, "Note Saved", Toast.LENGTH_SHORT)
                                .show()

                            navController.popBackStack()

                        } else {
                            Toast
                                .makeText(context, "Fields are empty...", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                },

                contentColor = colorResource(R.color.white),
                containerColor = colorResource(R.color.purple_700)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Save,
                    contentDescription = "Save"
                )
            }
        },
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            CustomTextField(
                value = state.note.title,
                hint = "Title",
                weight = FontWeight.Bold,
                textColor = colorResource(R.color.white),
                onValueChange = {
                    viewModel.setTitle(it)
                }, maxLines = 2,modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            CustomTextField(
                value = state.note.message,
                hint = "Description",
                textColor = colorResource(R.color.white),
                onValueChange = { viewModel.setMessage(it) }, maxLines = 40,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
        }
    }
}

