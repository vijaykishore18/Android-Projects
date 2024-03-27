package com.example.assignment6e_commerce

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.assignment6e_commerce.databinding.FragmentSamsungBinding


class SamsungFragment : Fragment() {

    private lateinit var viewModel: LiveDataViewModel
    private lateinit var binding: FragmentSamsungBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_samsung, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(LiveDataViewModel::class.java)
        binding.myViewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.samsungBack.setOnClickListener {
            findNavController().navigate(R.id.action_samsungFragment_to_homePage)
        }
    }
}