package com.example.mynewnotesapp.ui.notes_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mynewnotesapp.R

@Preview(showSystemUi = true)
@Composable
fun MyMenueScreen() {
    val menue = listOf("Privacy Policy", "Terms and Conditions", "Help", "About", "Share")

    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.black))
            .padding(10.dp, vertical = 20.dp),
    ) {
        items(menue) { item ->
            Card(
                modifier = Modifier.fillMaxSize(),
                colors = CardColors(
                    containerColor = colorResource(R.color.creamyWhite),
                    contentColor = colorResource(R.color.creamyWhite),
                    disabledContentColor = colorResource(R.color.creamyWhite),
                    disabledContainerColor = colorResource(R.color.creamyWhite)
                ),
            ) {
                Button(
                    shape =RectangleShape,
                    onClick = {},
                    modifier = Modifier
                        .fillMaxSize()
                        .height(80.dp),
                    colors = ButtonColors(
                        containerColor = colorResource(R.color.black),
                        contentColor = colorResource(R.color.creamyWhite),
                        disabledContainerColor = colorResource(R.color.creamyWhite),
                        disabledContentColor = colorResource(R.color.creamyWhite)
                    ),
                ) {
                    Text(item, color = Color.White)
                }
            }
        }


    }

}
