package com.example.dicetwister

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dicetwister.ui.theme.DiceTwisterTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {

    lateinit var diceimage : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)
        val twistButton : Button = findViewById(R.id.twist_button)
        twistButton.text = "Let's Roll!"
        twistButton.setOnClickListener{
            rollDice()
            toastMsg()
        }
        diceimage = findViewById(R.id.dice_image)
    }
    private fun toastMsg(){
        Toast.makeText(this,"Dice Rolled",Toast.LENGTH_SHORT).show()
    }
    private fun rollDice(){
        val randomInt = Random.nextInt(6) + 1
        val drawableResoure = when (randomInt){
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        diceimage.setImageResource(drawableResoure)
    }
}
