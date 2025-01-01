package com.example.mynewnotesapp.ui.notes_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mynewnotesapp.R

@Composable
fun FabButton(onClick: () -> Unit) {
    FloatingActionButton(
        modifier = Modifier
            .size(50.dp)
            .clip(RoundedCornerShape(100)),
        onClick = { onClick.invoke() },
        containerColor = colorResource(R.color.purple_700),
        contentColor = Color.White
    ) {
        Icon(
            imageVector = Icons.Default.Create,
            contentDescription = null,
            modifier = Modifier.size(20.dp),
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun TestNote() {
    Scaffold(containerColor = colorResource(R.color.black), contentColor = colorResource(R.color.black), topBar = {
    }, bottomBar = {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(8.dp)
                .background(colorResource(R.color.purple_700)),
            contentAlignment = Alignment.Center
        ) {
            Text("SAVE", color = Color.White)
        }
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.black))
                .padding(innerPadding),
        ) {
            
        }
    }
}