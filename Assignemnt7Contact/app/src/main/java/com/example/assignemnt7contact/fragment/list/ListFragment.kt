package com.example.assignemnt7contact.fragment.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignemnt7contact.R
import com.example.assignemnt7contact.viewmodel.ContactViewModel
import com.example.assignemnt7contact.databinding.FragmentListBinding

class ListFragment : Fragment() {

    lateinit var binding : FragmentListBinding
    lateinit var mContactVieModel : ContactViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mContactVieModel = ViewModelProvider(this).get(ContactViewModel :: class.java)
        binding = FragmentListBinding.inflate(inflater,container,false)
        binding.addContact.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }


        val adapter = ListAdapter()
        val recyclerView = binding.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mContactVieModel.readAllData.observe(viewLifecycleOwner, Observer {
            contact -> adapter.setData(contact)
        })


        return binding.root
    }

}