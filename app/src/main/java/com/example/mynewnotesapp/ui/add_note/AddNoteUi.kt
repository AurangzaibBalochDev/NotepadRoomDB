package com.example.mynewnotesapp.ui.add_note

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mynewnotesapp.R
import com.example.mynewnotesapp.ui.add_note.components.MyTextField
import com.example.mynewnotesapp.ui.base.LocalNavHostController
import com.example.mynewnotesapp.ui.components.VerticalSpacer
import com.example.mynewnotesapp.ui.components.HeadingText
import org.koin.androidx.compose.koinViewModel

@Preview(showSystemUi = true)
@Composable
fun AddNoteUi(viewModel: AddNotesViewModel = koinViewModel()) {
    val context = LocalContext.current
    val navController = LocalNavHostController.current
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 5.dp)
                    .background(
                        colorResource(id = R.color.mainColor),
                        RoundedCornerShape(20)
                    )
                    .padding(horizontal = 8.dp, vertical = 10.dp)
                    .clickable {
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
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Save",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.white)
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val state by viewModel.state.collectAsState()

            VerticalSpacer(dp = 10.dp)
            HeadingText(text = "Add Note")
            VerticalSpacer(dp = 20.dp)

            MyTextField(value = state.note.title,
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                hint = "title...", onValueChange = {
                    viewModel.setTitle(it)
                })
            VerticalSpacer(dp = 10.dp)
            MyTextField(
                value = state.note.message,
                hint = "body...",
                modifier = Modifier
                    .height(100.dp)
                    .padding(horizontal = 8.dp),
                onValueChange = {
                    viewModel.setMessage(it)
                })
        }
    }
}