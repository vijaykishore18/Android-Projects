package com.example.rockpaperscissor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rockpaperscissor.databinding.FragmentUserOneBinding

class UserOneFragment : Fragment() {

    lateinit var binding: FragmentUserOneBinding
    lateinit var viewModel: RockPaperScissorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserOneBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(requireActivity()).get(RockPaperScissorViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.displayUser1.text = "Welcome ${viewModel.user1name}"
        binding.rockU1.setOnClickListener{
            viewModel.user1move = "rock"
            findNavController().navigate(R.id.action_userOne_to_userTwo)
        }
        binding.paperU1.setOnClickListener{
            viewModel.user1move = "paper"
            findNavController().navigate(R.id.action_userOne_to_userTwo)
        }
        binding.scissorU1.setOnClickListener{
            viewModel.user1move = "scissor"
            findNavController().navigate(R.id.action_userOne_to_userTwo)
        }
        binding.backU1.setOnClickListener{
            findNavController().navigate(R.id.action_userOne_to_home2)
        }
    }
}