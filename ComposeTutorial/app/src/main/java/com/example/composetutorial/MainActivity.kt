package com.example.composetutorial

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetutorial.ui.theme.ComposeTutorialTheme

class MainActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            User()
            Conversation(messages = SampleData.conversationSample)
        }
    }
}

data class Message(val author : String, val body : String)

//@Preview(name = "Light Theme")

@Composable
fun User(){
    LazyRow(modifier = Modifier.padding(all = 10.dp)){
        items(25) { index ->
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = "Android Logo",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .border(2.dp, MaterialTheme.colorScheme.secondary, CircleShape)
        )
        Spacer(modifier = Modifier.width(20.dp))
        }
    }
}

@Preview
@Composable
fun PreviewUser(){
    ComposeTutorialTheme {
        User()
    }
}




@Composable
fun MessageCard( msg : Message){

    var isExpanded by remember {
        mutableStateOf(false)
    }

    val surfaceColor by animateColorAsState(
        if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
    )

    Row(modifier = Modifier.padding(all = 10.dp)) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = "Android Logo",
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .border(2.dp, MaterialTheme.colorScheme.secondary, CircleShape)
        )
        Spacer(modifier = Modifier.width(10.dp))

        Column (modifier = Modifier.clickable { isExpanded =! isExpanded }) {
            Text(
                text = msg.author,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.secondary)

            Spacer(modifier = Modifier.height(10.dp))

            Surface (
                shape = MaterialTheme.shapes.medium ,
                shadowElevation = 10.dp,
                color = surfaceColor,
                modifier = Modifier.animateContentSize().padding(2.dp)
                ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(10.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode",
)
@Composable
fun PreviewMessageCard(){
    ComposeTutorialTheme {
        Surface {
            MessageCard(
                msg = Message( "Vijay" ,
                    "Compose is really cool!")
            )
        }
    }
}

@Composable
fun Conversation(messages : List<Message>){

    LazyColumn(
        modifier = Modifier.padding(vertical = 60.dp)
    ){
        items(messages) {message ->
            MessageCard(message)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewConversation(){
    ComposeTutorialTheme {
        Conversation(SampleData.conversationSample)
    }
}

/**
 * SampleData for Jetpack Compose Tutorial
 */
object SampleData {
    // Sample conversation data
    val conversationSample = listOf(
        Message(
            "Lexi",
            "Test...Test...Test..."
        ),
        Message(
            "Lexi",
            """List of Android versions:
            |Android KitKat (API 19)
            |Android Lollipop (API 21)
            |Android Marshmallow (API 23)
            |Android Nougat (API 24)
            |Android Oreo (API 26)
            |Android Pie (API 28)
            |Android 10 (API 29)
            |Android 11 (API 30)
            |Android 12 (API 31)""".trim()
        ),
        Message(
            "Lexi",
            """I think Kotlin is my favorite programming language.
            |It's so much fun!""".trim()
        ),
        Message(
            "Lexi",
            "Searching for alternatives to XML layouts..."
        ),
        Message(
            "Lexi",
            """Hey, take a look at Jetpack Compose, it's great!
            |It's the Android's modern toolkit for building native UI.
            |It simplifies and accelerates UI development on Android.
            |Less code, powerful tools, and intuitive Kotlin APIs :)""".trim()
        ),
        Message(
            "Lexi",
            "It's available from API 21+ :)"
        ),
        Message(
            "Lexi",
            "Writing Kotlin for UI seems so natural, Compose where have you been all my life?"
        ),
        Message(
            "Lexi",
            "Android Studio next version's name is Arctic Fox"
        ),
        Message(
            "Lexi",
            "Android Studio Arctic Fox tooling for Compose is top notch ^_^"
        ),
        Message(
            "Lexi",
            "I didn't know you can now run the emulator directly from Android Studio"
        ),
        Message(
            "Lexi",
            "Compose Previews are great to check quickly how a composable layout looks like"
        ),
        Message(
            "Lexi",
            "Previews are also interactive after enabling the experimental setting"
        ),
        Message(
            "Lexi",
            "Have you tried writing build.gradle with KTS?"
        ),
    )
}

//
//@Composable
//fun Name(msg : Message){
//    Row (modifier = Modifier.padding(all = 10.dp)){
//        Image(
//            painter = painterResource(R.drawable.ic_launcher_background),
//            contentDescription = "Android Logo",
//            modifier = Modifier
//                .size(60.dp)
//                .clip(CircleShape)
//                .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)
//        )
//
//        Spacer(modifier = Modifier.width(10.dp))
//
//        Column {
//            Text(
//                text = msg.author,
//                style = MaterialTheme.typography.titleLarge,
//                color = MaterialTheme.colorScheme.secondary
//            )
//
//            Spacer(modifier = Modifier.height(4.dp))
//
//            Surface (shape = MaterialTheme.shapes.medium, shadowElevation = 10.dp){
//                Text(
//                    text = msg.body,
//                    Modifier.padding(10.dp)
//                )
//            }
//        }
//    }
//}
//
//@Preview
//@Composable
//fun PreviewName(){
//    Surface {
//        Name(msg = Message(
//            "Manju","Yes Vijay, you are Right!"
//        )
//        )
//    }
//}