package com.example.mynewnotesapp.ui.notes_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mynewnotesapp.R

// This is FAB Button
@Composable
fun FabButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .background(
                color = colorResource(id = R.color.creamyWhite),
                RoundedCornerShape(30),

                )
            .clickable {
                onClick.invoke()
            },
        contentAlignment = Alignment.Center

    ) {
        Icon(
            painter = painterResource(id = R.drawable.white_add),

            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
        )
    }
}
