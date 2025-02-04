package com.example.mynewnotesapp.ui.notes_list.components


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mynewnotesapp.R
import com.example.mynewnotesapp.ui.components.HorizentalSpacer
import com.example.mynewnotesapp.ui.components.VerticalSpacer

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TodoItem(
    todoData: TodoData,
    isSelected: Boolean = false,
    onClick: (() -> Unit)? = null, onLongClick: ((Boolean) -> Unit)? = null,
) {
    val backgroundColor =
        if (isSelected) Color.LightGray else colorResource(id = R.color.creamyWhite)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 4.dp, vertical = 4.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .combinedClickable(
                    onClick = { onClick?.invoke() },
                    onLongClick = { onLongClick?.invoke(isSelected) }
                ),
            shape = RoundedCornerShape(5.dp),
            colors = CardDefaults.cardColors(backgroundColor),
            elevation = CardDefaults.elevatedCardElevation(4.dp)
        ) {
            Row {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(10.dp)
                        .background(color = colorResource(R.color.skyColor))
                ) { /* Placeholder for left-side indicator */ }

                HorizentalSpacer(10.dp)

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = todoData.title,
                        fontSize = 18.sp,
                        color = colorResource(id = R.color.white),
                        fontWeight = FontWeight.Bold,
                        maxLines = 1
                    )
                    VerticalSpacer(5.dp)
                    Text(
                        text = todoData.createdAt,
                        fontSize = 10.sp,
                        color = colorResource(id = R.color.white),
                        maxLines = 1
                    )
                    Text(
                        text = todoData.completed.toString(),
                        fontSize = 10.sp,
                        color = colorResource(id = R.color.amlostBlack),
                        maxLines = 1
                    )

                }
            Column (modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End){
                Checkbox(todoData.completed,onCheckedChange = {
                    todoData.completed = it
                }, modifier = Modifier.padding(8.dp))
            }
            }
        }
    }
}
