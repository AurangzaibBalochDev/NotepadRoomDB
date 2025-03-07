package com.example.notzeezaibtech.ui.add_note.components

import android.annotation.SuppressLint
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
import com.example.notzeezaibtech.R

@Composable
fun CustomTextField(
    value: String,
    hint: String,
    weight: FontWeight = FontWeight.Normal,
    onValueChange: (String) -> Unit,
    textColor:Color,
    maxLines:Int,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        maxLines = maxLines,
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
