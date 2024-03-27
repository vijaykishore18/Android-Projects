package com.example.assignemnt7contact.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.assignemnt7contact.data.ContactDataBase
import com.example.assignemnt7contact.repository.ContactRepository
import com.example.assignemnt7contact.model.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData : LiveData<List<Contact>>
    private val repository : ContactRepository


    init {
        val contactDao = ContactDataBase.getDatabase(application).contactDao()
        repository = ContactRepository(contactDao)
        readAllData = repository.readAllData
    }
    fun addContact(contact: Contact){
        viewModelScope.launch(Dispatchers.IO){
            repository.addContact(contact)
        }
    }
}