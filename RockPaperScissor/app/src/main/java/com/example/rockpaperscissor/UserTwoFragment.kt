package com.example.rockpaperscissor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rockpaperscissor.databinding.FragmentUserTwoBinding


class UserTwoFragment : Fragment() {

    lateinit var binding: FragmentUserTwoBinding
    lateinit var viewModel: RockPaperScissorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserTwoBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(requireActivity()).get(RockPaperScissorViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.displayUser2.text = "Welcome ${viewModel.user2name}"
        binding.rockU2.setOnClickListener{
            viewModel.user2move = "rock"
            findNavController().navigate(R.id.action_userTwo_to_result)
        }
        binding.paperU2.setOnClickListener{
            viewModel.user2move = "paper"
            findNavController().navigate(R.id.action_userTwo_to_result)
        }
        binding.scissorU2.setOnClickListener{
            viewModel.user2move = "scissor"
            findNavController().navigate(R.id.action_userTwo_to_result)
        }
        binding.backU2.setOnClickListener{
            findNavController().navigate(R.id.action_userTwo_to_userOne)
        }
    }

}