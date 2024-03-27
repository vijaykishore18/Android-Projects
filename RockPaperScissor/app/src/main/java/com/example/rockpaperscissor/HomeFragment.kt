package com.example.rockpaperscissor

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rockpaperscissor.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: RockPaperScissorViewModel
    private lateinit var Intent:Intent

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(requireActivity()).get(RockPaperScissorViewModel::class.java)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.playButton.setOnClickListener{
            viewModel.user1name = binding.useroneName.text.toString()
            viewModel.user2name = binding.usertwoName.text.toString()
            findNavController().navigate(R.id.action_home2_to_userOne)
        }
    }
}

