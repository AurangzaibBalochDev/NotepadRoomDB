package com.example.mynewnotesapp.ui.add_note.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.mynewnotesapp.R
//Ab dejh
@Composable
fun MyTextField(value: String, modifier: Modifier=Modifier,onValueChange: (String) -> Unit = {}, hint: String) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(1.dp, color = colorResource(id = R.color.amlostBlack), RoundedCornerShape(10))
           ,
        colors = TextFieldDefaults.colors(
            unfocusedTextColor = Color.Black,
            focusedTextColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,

            ),

        placeholder = { Text(text = hint, color = Color.Gray) }
    )
}