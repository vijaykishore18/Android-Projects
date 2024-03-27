package com.example.composetutorial

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StrokeText(){
    Box(modifier = Modifier.fillMaxSize()){
        Text(
            text = "Hello",
            style = TextStyle(
                color = Color.Green,
                fontSize = 24.sp,
//                drawStyle = 
            )
            )
    }
}