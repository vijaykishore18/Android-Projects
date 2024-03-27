package com.example.popup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PopUp(modifier = Modifier)
        }
    }
}

@Composable
fun PopUp(modifier: Modifier){

   var isPoPedUp by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            onClick = {
                isPoPedUp = !isPoPedUp
            }) {
            Text(text = "Click Me!")
        }
    }

    Column (modifier = Modifier
        .padding(10.dp)
        .fillMaxSize())
    {
            if (isPoPedUp){

//                Text(text = "X", fontSize = 36.sp,
//                    modifier = Modifier.clickable {
//                        isPoPedUp = false
//                    })
//
//                Column ( modifier = Modifier
//                    .background(Color.Blue)
//                    .fillMaxHeight(0.7f)
//                    .fillMaxWidth(),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally) {
//
//
//
//                    Card (modifier = Modifier
//                        .padding(20.dp)) {
//
//                        Text(text = "Hello Bro",
//                            modifier = Modifier
//                                .fillMaxWidth(0.7f)
//                                .padding(20.dp))
//                    }
//                    Card (modifier = Modifier
//                        .padding(20.dp)) {
//
//                        Text(text = "Hello Bro",
//                            modifier = Modifier
//                                .fillMaxWidth(0.7f)
//                                .padding(20.dp))
//                    }
//                    Card (modifier = Modifier
//                        .padding(20.dp)) {
//
//                        Text(text = "Hello Bro",
//                            modifier = Modifier
//                                .fillMaxWidth(0.7f)
//                                .padding(20.dp))
//                    }
                TspPopUp()


//                }

            }
    }
}



@Composable
fun TspPopUp(
    modifier: Modifier = Modifier
        .fillMaxSize()
) {

    val progressValue = 0f
    val infiniteTransition = rememberInfiniteTransition()
    val progressAnimationValue by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = progressValue,
        animationSpec = infiniteRepeatable(animation = tween(5000))
    )

//    Image(
//        painter = painterResource(id = R.drawable.background_leaderboard),
//        contentDescription = "Background Image",
//        modifier = Modifier.fillMaxSize(),
//        contentScale = ContentScale.FillBounds
//    )
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            colors = CardDefaults.cardColors(
                Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp,
                pressedElevation = 10.dp
            ),
            modifier = Modifier
                .padding(20.dp)
                .padding(bottom = 150.dp)
                .fillMaxHeight(0.5f)
        )
        {
            Column (
                modifier = Modifier
                    .padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Choose your Word",
//                    fontFamily = ov_soge_bold,
                    fontSize = 30.sp)

                LinearProgressIndicator(
                    modifier = Modifier
                        .padding(20.dp)
                        .height(15.dp),
                    color = Color.Blue,
                    trackColor = Color.White,
                    strokeCap = StrokeCap.Round,
                    progress = progressAnimationValue
                )

                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Word 1",
                        color = Color.Black,
                        fontSize = 20.sp,
//                        fontFamily = ov_soge_bold,
                        modifier = Modifier
                            .padding(20.dp)
                            .clickable {
                                //Word Clickable functions
                            }
                    )

                    Text(
                        text = "Word 1",
                        color = Color.Black,
                        fontSize = 20.sp,
//                        fontFamily = ov_soge_bold,
                        modifier = Modifier
                            .padding(top = 100.dp)
                            .clickable {
                                //Word Clickable functions
                            }

                    )



                    Text(
                        text = "Word 1",
                        color = Color.Black,
                        fontSize = 20.sp,
//                        fontFamily = ov_soge_bold,
                        modifier = Modifier
                            .padding(20.dp)
                            .clickable {
                                //Word Clickable functions
                            }
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewPopUp() {
    PopUp(modifier = Modifier)
}

