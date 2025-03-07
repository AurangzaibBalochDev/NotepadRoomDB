package com.example.notzeezaibtech.ui.notes_list.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.notzeezaibtech.R
import com.example.notzeezaibtech.ui.components.HorizentalSpacer
import com.example.notzeezaibtech.ui.components.VerticalSpacer

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TodoItem(
    todoData: TodoData,
    isSelected: Boolean = false,
    onClick: (() -> Unit)? = null,
    onLongClick: ((Boolean) -> Unit)? = null,
    viewModel: AddTodoViewModel
) {
//    val backgroundColor = if (isSelected) Color.Transparent else Color.Transparent

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
            colors = CardDefaults.cardColors(Color.Transparent),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
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




            }
        }
    }
}
