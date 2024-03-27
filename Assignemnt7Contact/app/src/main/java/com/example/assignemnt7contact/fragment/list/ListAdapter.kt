package com.example.assignemnt7contact.fragment.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignemnt7contact.model.Contact
import com.example.assignemnt7contact.databinding.CustomRowBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var contactList = emptyList<Contact>()

    fun setData(contactList: List<Contact>){
        this.contactList = contactList
        notifyDataSetChanged()
    }

    inner class MyViewHolder(private val binding : CustomRowBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(contact: Contact){
            binding.customId.text = contact.id.toString()
            binding.customFirstname.text = contact.firstName
            binding.customLastname.text = contact.lastName
            binding.customNumber.text = contact.number.toString()

            binding.customConstraint.setOnClickListener{
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CustomRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(contactList[position])

    }

}