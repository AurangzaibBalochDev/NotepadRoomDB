package com.example.notzeezaibtech.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun LoadingScreen(
    modifier:Modifier = Modifier
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center){
        Column {
            CircularProgressIndicator(
                color = Color.White
            )
        }
    }

}