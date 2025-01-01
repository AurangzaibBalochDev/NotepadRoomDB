package com.example.mynewnotesapp.ui.notes_list.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mynewnotesapp.R
import com.example.mynewnotesapp.ui.components.HorizentalSpacer
import com.example.mynewnotesapp.ui.components.VerticalSpacer

@OptIn(ExperimentalFoundationApi::class)
@Composable

fun NoteItem(
    notesData: NotesData = NotesData(
        1,
        "This is my title for the very first time",
        "This is message and also you can say it as the description of title."
    ),
    onClick: (() -> Unit)? = null,
    onDeleteClick: (() -> Unit)? = null
) {
    var swipeOffset by remember { mutableFloatStateOf(0f) }
    var isSwiped by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(125.dp)
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, dragAmount ->
                    swipeOffset += dragAmount

                    // Handle swipe gestures
                    if (swipeOffset > 400f) {
                        // Trigger delete when swiped enough
                        if (!isSwiped) {
                            onDeleteClick?.invoke()
                            isSwiped = true
                        }
                    }
                    if (swipeOffset < -400f) {
                        // Reset swipe when moved in the opposite direction
                        swipeOffset = 0f
                        isSwiped = false
                    }
                }
            }
            .offset { IntOffset(swipeOffset.toInt(), 0) } // Apply the swipe offset
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(125.dp)
                .combinedClickable(
                    onClick = { onClick?.invoke() },
                    onLongClick = { onDeleteClick?.invoke() }
                )
                .padding(horizontal = 6.dp, vertical = 4.dp),
            shape = RoundedCornerShape(5),
            colors = CardDefaults.cardColors(colorResource(id = R.color.creamyWhite)),
            elevation = CardDefaults.elevatedCardElevation(4.dp)
        ) {
            Row {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(10.dp)
                        .background(color = Color.Yellow)
                ) { /* Placeholder for left side indicator if needed */ }

                HorizentalSpacer(10.dp)

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = notesData.title,
                        fontSize = 18.sp,
                        color = colorResource(id = R.color.white),
                        fontWeight = FontWeight.Bold
                    )
                    VerticalSpacer(5.dp)
                    Text(
                        text = notesData.message,
                        fontSize = 10.sp,
                        color = colorResource(id = R.color.white)
                    )
                    Text(
                        text = System.currentTimeMillis().toString(),
                        fontSize = 10.sp,
                        color = colorResource(id = R.color.amlostBlack)
                    )
                }
            }
        }
    }
}
