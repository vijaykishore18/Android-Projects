package com.example.aboutme

import android.content.Context
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Nickname
import android.view.View
import android.view.inputmethod.InputBinding
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.databinding.DataBindingUtil
import com.example.aboutme.ui.theme.AboutMeTheme
import com.example.aboutme.databinding.LayoutBinding

class MainActivity : ComponentActivity() {

    private lateinit var binding: LayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.layout)
        binding.doneButton.setOnClickListener{
            addNickName(it)
        }
        binding.nameText.text = "Vijay Kishore K"
    }

    private fun addNickName(view:View){
        binding.apply {
            nicknameText.text = binding.nicknameEdit.text
            invalidateAll()
            nicknameEdit.visibility = View.GONE
            doneButton.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
        }
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
    data class MyName(var name2 : String = "Vijay",var nickname2: String = "")
}


