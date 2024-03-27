package com.example.assignment9rf

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.assignment9rf.databinding.FragmentUserBinding
import kotlinx.coroutines.launch

class UserFragment : Fragment() {

    lateinit var binding: FragmentUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserBinding.inflate(inflater,container,false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var retrofitInstance = UserApiInstance.getRetrofitInstance().create(UserApiSevice::class.java)
        lifecycleScope.launch {
            var responce = retrofitInstance.getData()
            if (responce.isSuccessful){
                var dummy = ""
                var data = responce.body()
                if (data != null) {
                    for (i in data){
                        dummy += i.name
                        dummy += i.id
                        dummy += "\n"
                    }

                }
                binding.userText.text = dummy
            }
            else{
                println(responce.code())
            }

        }


    }
}