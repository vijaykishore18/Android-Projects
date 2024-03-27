package com.example.assignment6e_commerce

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.assignment6e_commerce.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var viewModel : LiveDataViewModel
    private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        viewModel = ViewModelProvider(requireActivity()).get(LiveDataViewModel::class.java)
        binding.myViewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.shopButton.setOnClickListener{
            viewModel.userName = binding.usernameText.text.toString()
            findNavController().navigate(R.id.action_homeFragment_to_homePage)
        }
    }


}