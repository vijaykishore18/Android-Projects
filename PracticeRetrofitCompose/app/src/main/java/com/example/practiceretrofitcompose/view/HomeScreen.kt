package com.example.practiceretrofitcompose.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.practiceretrofitcompose.retrofit.Comments

@Composable
fun CommentsUi(comments: Comments){
    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(4.dp)
                .fillMaxHeight()
                .weight(0.8f)
        ) {
            Text(
                text = comments.name,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = comments.postId.toString(),
                modifier = Modifier
                    .background(
                        Color.LightGray
                    )
                    .padding(4.dp)
            )
            Text(
                text = comments.body,
                maxLines = 2,
            )

        }
    }
}
