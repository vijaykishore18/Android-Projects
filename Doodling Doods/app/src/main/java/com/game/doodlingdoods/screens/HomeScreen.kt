package com.game.doodlingdoods.screens

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.game.doodlingdoods.R
import com.game.doodlingdoods.ui.theme.Blue1
import com.game.doodlingdoods.ui.theme.Blue2


//This is the home screen, where the user either chooses online or local game mode.

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier
        .background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Blue1, Blue2
                )
            )
        )
        .padding(8.dp)
) {
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {


        NavBar()
        TitleBar()
        PlayOption(
            PlayButtonClick = {
                navController.navigate("AccountSetup")
            }
        )
    }
}

// navbar contains user profile and their stats
@Composable
fun NavBar(modifier: Modifier = Modifier) {
    Row(
        modifier.fillMaxWidth()

    ) {
        Row(
            modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.leaderboard
                ),
                contentDescription = "person image",
                modifier
                    .padding(8.dp)
                    .size(50.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "person image",
                modifier
                    .padding(8.dp)
                    .size(50.dp)
            )

        }

    }
}


//title bar for game title it appears on middle of the screen

@Composable
fun TitleBar(modifier: Modifier = Modifier) {
    Row(
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.ic_logo)
            , contentDescription = stringResource(id = R.string.title),
            modifier.padding(8.dp))
//        Text(
//            text = stringResource(id = R.string.title),
//            modifier.padding(8.dp),
//            fontSize = 30.sp
//
//        )
    }


}

//play button

@Composable
fun PlayOption(
    modifier: Modifier = Modifier,
    PlayButtonClick: () -> Unit
) {
    var scale by remember {
        mutableStateOf(1f)
    }

    val animation = rememberInfiniteTransition()

    scale = animation.animateFloat(
        label = "ScaleAnimation",
        initialValue = 1f,
        targetValue = 1.5f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 3000
                0.85f at 1500
                1f at 3000
            },
            repeatMode = RepeatMode.Reverse
        )
    ).value

    Image(
        painter = painterResource(id = R.drawable.play_button),
        contentDescription = "Play!",
        modifier = Modifier
            .size(200.dp * scale)
            .clickable {
                PlayButtonClick()

            })
}
//    Card(
//        modifier
//            .fillMaxWidth()
//            .padding(48.dp)
//            .clickable {
//                PlayButtonClick()
//            },
//        )
//    {
//        Text(
//            text = "Play",
//            fontSize = 30.sp,
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier
//                .align(Alignment.CenterHorizontally)
//                .padding(8.dp)
//        )
//    }




//
//@Preview
//@Composable
//fun PreviewNavbar() {
//    NavBar(modifier = Modifier)
//    Titlebar()
//    PlayOption()
//    HomeScreen()
//}
