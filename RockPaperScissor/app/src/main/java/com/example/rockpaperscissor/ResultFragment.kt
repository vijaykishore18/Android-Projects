package com.example.rockpaperscissor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rockpaperscissor.databinding.FragmentHomeBinding
import com.example.rockpaperscissor.databinding.FragmentResultBinding


class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding
    private lateinit var viewModel: RockPaperScissorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(requireActivity()).get(RockPaperScissorViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.gameResult.text = viewModel.GamePlay()
        binding.homeButton.setOnClickListener{
            findNavController().navigate(R.id.action_result_to_home2)
        }
    }
}