package com.example.mynewnotesapp.ui.notes_list.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
    onClick: (() -> Unit)? = null,
    onLongClick: ((Boolean) -> Unit)? = null,
    viewModel: AddTodoViewModel
) {
    val backgroundColor = if (isSelected) Color.LightGray else colorResource(id = R.color.creamyWhite)

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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(10.dp)
                        .background(color = colorResource(R.color.skyColor))
                ) {}

                HorizentalSpacer(10.dp)

                Column(
                    modifier = Modifier.weight(1f),
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
                }

                Checkbox(
                    checked = todoData.completed,
                    onCheckedChange = { change->
                        viewModel.updateTodo(
                            todoData.copy(
                                completed = change
                            )
                        ) },
                    modifier = Modifier.padding(end = 8.dp)
                )


            }
        }
    }
}
