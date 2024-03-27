package com.example.practicedatabinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.practicedatabinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var dataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        var v1 = Vehicle()
        v1.name = "BMW"
        v1.modelYear = "2024"
        dataBinding.myVehicle = v1

        dataBinding.clickHandler = VehicleClickHandler(this)

    }
}