package com.example.assignment6e_commerce

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LiveDataViewModel : ViewModel(){

    var userName : String = ""
    var counter = MutableLiveData<Int>()

    init {
        counter.value = 0
    }

    fun incrementCounter(view: View){
        counter.value = (counter.value)?.plus(1)
    }

    fun decrementCounter(view: View){
        counter.value?.let {
            if (it > 0){
                counter.value = it - 1
            }
        }
    }

}