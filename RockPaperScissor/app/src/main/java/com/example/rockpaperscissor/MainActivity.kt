package com.example.rockpaperscissor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this,"onPause() called",Toast.LENGTH_SHORT).show()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putString("name","vijay")
    }

    override fun onStop(){
        super.onStop()
        Toast.makeText(this,"onStop() is called",Toast.LENGTH_SHORT).show()
    }
    override fun onRestart(){
        super.onRestart()
        Toast.makeText(this,"onRestart() is called",Toast.LENGTH_SHORT).show()
    }
    override fun onDestroy(){
        super.onDestroy()
        Toast.makeText(this,"onDestroy() is called",Toast.LENGTH_SHORT).show()
    }
}