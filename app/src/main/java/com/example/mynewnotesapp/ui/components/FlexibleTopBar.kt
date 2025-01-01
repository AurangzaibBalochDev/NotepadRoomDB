package com.example.mynewnotesapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.example.mynewnotesapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlexibleTopBar(modifier: Modifier = Modifier,title:String,colors: TopAppBarColors) {
        TopAppBar(
            modifier = Modifier.fillMaxWidth(),
            title = {
                HeadingText(
                    text = title,
                )
            },
            colors = colors
        )
    }
