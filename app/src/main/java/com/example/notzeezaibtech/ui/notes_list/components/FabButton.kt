package com.example.notzeezaibtech.ui.notes_list.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.notzeezaibtech.R

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
            contentDescription = "create",
            modifier = Modifier.size(20.dp),
        )
    }
}
