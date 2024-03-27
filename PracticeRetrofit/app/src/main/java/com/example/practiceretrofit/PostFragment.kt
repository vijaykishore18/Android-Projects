package com.example.practiceretrofit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.practiceretrofit.databinding.FragmentPostBinding
import kotlinx.coroutines.launch


class PostFragment : Fragment() {

    lateinit var binding: FragmentPostBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var retrofitInstance = PostApiInstance.getRetrofitInstance().create(PostServiceApi::class.java)

        lifecycleScope.launch {
            var response = retrofitInstance.getData()

            if (response.isSuccessful){
                println("data received")
                var displayText = ""
                var data = response.body()
                println(data)
                if (data != null) {
                    for (i in data){
                        displayText += i.id
                        displayText += ". "
                        displayText += i.title
                        displayText += "\n"
                    }
                    binding.postText.text = displayText
                }
            }
            else{
                println("Not Successful")
            }
        }
    }

}