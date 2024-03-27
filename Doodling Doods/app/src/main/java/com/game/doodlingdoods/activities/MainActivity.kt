package com.game.doodlingdoods.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.game.doodlingdoods.R
import com.game.doodlingdoods.screens.AccountSetup
import com.game.doodlingdoods.screens.HomeScreen
import com.game.doodlingdoods.screens.LoginScreen
import com.game.doodlingdoods.screens.SignUpScreen
import com.game.doodlingdoods.ui.theme.DoodlingDoodsTheme
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    @SuppressLint("RememberReturnType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DoodlingDoodsTheme {
                Surface (color = Color(0xF202020), modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()

                    val navGraph = remember(navController) {

                        navController.createGraph(startDestination = "SplashScreen") {
                            composable("SplashScreen") {
                                SplashScreen(navController = navController)
                            }
                            composable("HomeScreen") {
                                HomeScreen(navController = navController)
                            }

                            composable("AccountSetup") {
                                AccountSetup(navController = navController)
                            }

                            composable("LoginScreen") {
                                LoginScreen(navController = navController)
                            }

                            composable("SignUpScreen") {
                                SignUpScreen(navController = navController)
                            }
                        }

                    }
                    NavHost(navController = navController, graph = navGraph)
                }
            }
        }
    }
}

@Composable
fun SplashScreen(navController: NavController){
    val scale = remember{
        Animatable(0f)
    }
    LaunchedEffect(key1 = true ){
        scale.animateTo(
            targetValue = 0.3f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(1500)
        navController.navigate("HomeScreen")
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.ic_logo)
            , contentDescription = "logo" )
    }
}
