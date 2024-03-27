package com.example.assignemnt7contact.fragment.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.assignemnt7contact.R
import com.example.assignemnt7contact.model.Contact
import com.example.assignemnt7contact.viewmodel.ContactViewModel
import com.example.assignemnt7contact.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private lateinit var mViewModel: ContactViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater,container,false)
        mViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)

        binding.addButton.setOnClickListener{
            insertDataToDataBase()
        }
        return binding.root
    }

    private fun insertDataToDataBase() {
        val firstName = binding.addFirstName.text.toString()
        val lastName = binding.addLastname.text.toString()
        val phoneNumber = binding.addNumber.text

        if (inputChecker(firstName,lastName,phoneNumber.toString())){
            val contact = Contact(0,firstName,lastName,phoneNumber.toString().toLong())
            mViewModel.addContact(contact)
            Toast.makeText(requireContext(),"Contact Saved Successfully!",Toast.LENGTH_LONG).show()
            //navigate back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
        else{
            Toast.makeText(requireContext(),"Please fill all fields!",Toast.LENGTH_LONG).show()
        }
    }

    private fun inputChecker(firstName : String, lastName : String, phoneNumber : String) : Boolean{
        return ! (TextUtils.isEmpty(firstName)&&TextUtils.isEmpty(lastName)&& phoneNumber.isEmpty() )
    }

}