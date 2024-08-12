package com.example.mynewnotesapp.ui.notes_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mynewnotesapp.R


@Preview(showSystemUi = true)
@Composable
fun NoteItem(
    notesData: NotesData = NotesData(1, "Hello", "This is message"),
    onClick: (() -> Unit)? = null,
    onDeleteClick: (() -> Unit)? = null
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick?.invoke()
            }
            .padding(horizontal = 6.dp, vertical = 4.dp),
        shape = RoundedCornerShape(10),
        colors = CardDefaults.cardColors(
            colorResource(id = R.color.creamyWhite)
        ), elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp) ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column(
                modifier = Modifier.weight(1f) // Allow the column to take up available space
            ) {

                Text(
                    text = notesData.title,
                    fontSize = 12.sp,
                    color = colorResource(id = R.color.black)
                )
                Text(
                    text = notesData.message,
                    fontSize = 10.sp,
                    color = colorResource(id = R.color.amlostBlack)
                )
                Text(
                    text = System.currentTimeMillis().toString(), fontSize = 10.sp,
                    color = colorResource(id = R.color.amlostBlack)
                )
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "delete",
                    Modifier.clickable { onDeleteClick?.invoke()})
            }
        }

    }
}

