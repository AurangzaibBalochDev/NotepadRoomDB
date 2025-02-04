package com.example.mynewnotesapp.ui.add_note.components

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mynewnotesapp.R

@Composable
fun CustomTextField(
    value: String,
    hint: String,
    weight: FontWeight = FontWeight.Normal,
    onValueChange: (String) -> Unit,
    textColor:Color,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = { onValueChange(it) },

        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        placeholder = {
            Text(hint, fontWeight = weight, color = colorResource(R.color.white))
        },
        textStyle = TextStyle(
            fontWeight = weight
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedTextColor = textColor,
            unfocusedTextColor = textColor
        ),


    )
}
