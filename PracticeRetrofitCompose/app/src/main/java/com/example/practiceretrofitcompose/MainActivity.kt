package com.example.practiceretrofitcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.practiceretrofitcompose.retrofit.Comments
import com.example.practiceretrofitcompose.viewmodel.CommentsViewModel

class MainActivity : ComponentActivity() {

    val viewModel by viewModels<CommentsViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isListUpdate by viewModel.isUpdated.collectAsState()
            var apiRequest by remember {
                mutableStateOf(false)
            }
            LaunchedEffect(Unit) {
                viewModel.getComments()
                apiRequest = true
            }
            if (apiRequest){
                CommentsCard(comment = viewModel.list, viewModel = viewModel)
            }

            Surface(color = MaterialTheme.colorScheme.background) {
                if (isListUpdate){
                    CommentsCard(comment = viewModel.commentsListResponse, viewModel)

                }

            }
        }
    }
}

@Composable
fun CommentsCard(comment : List<Comments>, viewModel: CommentsViewModel){
    LazyColumn {
        items(viewModel.list){
            Text(text = it.name)
        }
    }
}