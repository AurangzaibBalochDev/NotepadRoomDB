package com.example.notzeezaibtech.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.notzeezaibtech.R

@Composable
fun HeadingText(text: String) {
    Text(
        text = text,
        color = colorResource(id = R.color.white),
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
    )
}