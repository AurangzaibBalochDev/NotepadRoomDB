package com.example.mynewnotesapp.ui.add_note

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mynewnotesapp.R
import com.example.mynewnotesapp.ui.base.LocalNavHostController
import com.example.mynewnotesapp.ui.components.FlexibleTopBar
import com.example.mynewnotesapp.ui.components.HorizentalSpacer
import com.example.mynewnotesapp.ui.components.VerticalSpacer
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Preview(showSystemUi = true)
@Composable
fun AddNoteUi(viewModel: AddNotesViewModel = koinViewModel()) {
    val context = LocalContext.current
    val navController = LocalNavHostController.current
    val state by viewModel.state.collectAsState()
    var selectedButton by remember { mutableStateOf("All") }

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

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.black)), // Ensure full black background
        containerColor = colorResource(R.color.black),
        contentColor = colorResource(R.color.white), // Text color
        topBar = {

            Row(
                modifier = Modifier
                    .background(color = colorResource(id = R.color.black))
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {

                HorizentalSpacer(10.dp)
                LazyRow(
                    modifier = Modifier.fillMaxWidth(), state = lazyListState,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    items(buttonList) { currentItem ->
                        OutlinedButton(
                            onClick = {
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
                IconButton(
                    onClick = {
                        viewModel.saveNote {
                            if (it.isBlank()) {
                                Toast
                                    .makeText(context, "Note Saved", Toast.LENGTH_SHORT)
                                    .show()
                                navController.popBackStack()
                            } else {
                                Toast
                                    .makeText(context, it, Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }, colors = IconButtonDefaults.iconButtonColors(
                        contentColor = Color.White
                    )
                ) {
                    Icon(imageVector = Icons.Default.Save, contentDescription = "")
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(colorResource(R.color.black)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TextField(
                value = state.note.title,
                modifier = Modifier.fillMaxWidth(0.9f),
                onValueChange = { viewModel.setTitle(it) },
                maxLines = 2,
                textStyle = TextStyle(fontWeight = FontWeight.Bold),
                placeholder = {
                    Text(
                        text = "title...",
                        color = colorResource(R.color.white),
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = colorResource(R.color.black),
                    unfocusedContainerColor = colorResource(R.color.black),
                    focusedTextColor = colorResource(R.color.white),
                    unfocusedTextColor = colorResource(R.color.white),
                    focusedPlaceholderColor = Color.Gray,
                    unfocusedPlaceholderColor = Color.Gray,
                ),
            )

            VerticalSpacer(dp = 10.dp)

            TextField(
                value = state.note.message,
                modifier = Modifier.fillMaxWidth(0.9f),
                onValueChange = { viewModel.setMessage(it) },
                placeholder = {
                    Text(
                        text = "body...",
                        color = colorResource(R.color.white)
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = colorResource(R.color.black),
                    unfocusedContainerColor = colorResource(R.color.black),
                    focusedTextColor = colorResource(R.color.white),
                    unfocusedTextColor = colorResource(R.color.white),
                    focusedPlaceholderColor = Color.Gray,
                    unfocusedPlaceholderColor = Color.Gray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                )
            )
        }
    }
}
