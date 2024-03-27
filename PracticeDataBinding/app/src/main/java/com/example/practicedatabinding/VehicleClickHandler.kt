package com.example.practicedatabinding

import android.content.Context
import android.view.View
import android.widget.Toast

class VehicleClickHandler(var context: Context) {
    fun displayToastMsg(view: View){
        Toast.makeText(context,"Button Clicked!",Toast.LENGTH_SHORT).show()
    }
}