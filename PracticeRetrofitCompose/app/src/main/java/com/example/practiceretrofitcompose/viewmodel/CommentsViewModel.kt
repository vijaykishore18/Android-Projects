package com.example.practiceretrofitcompose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practiceretrofitcompose.retrofit.ApiService
import com.example.practiceretrofitcompose.retrofit.Comments
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CommentsViewModel : ViewModel(){
    var commentsListResponse : List<Comments> by mutableStateOf(listOf())
    var errorMessage : String by mutableStateOf("")

    private var _isListUpdated =  MutableStateFlow(false)
    val isUpdated:StateFlow<Boolean>
        get() = _isListUpdated.asStateFlow()

    var list = listOf(Comments("",1, ""))

    fun getComments(){
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            try {
                val response = apiService.getComments()
                if (response.isSuccessful){
                    list = response.body()?: listOf(Comments("",1, ""))
                    println(response.body())
                    _isListUpdated.value = true

                }
                else{
                    println(response.code())
                }
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}

