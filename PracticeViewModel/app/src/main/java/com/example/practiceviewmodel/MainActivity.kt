package com.example.practiceviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.practiceviewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var dataBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}