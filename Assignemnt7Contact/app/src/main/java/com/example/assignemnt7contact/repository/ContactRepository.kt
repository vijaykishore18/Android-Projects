package com.example.assignemnt7contact.repository

import androidx.lifecycle.LiveData
import com.example.assignemnt7contact.data.ContactDao
import com.example.assignemnt7contact.model.Contact

class ContactRepository (private val contactDao : ContactDao) {

    val readAllData : LiveData<List<Contact>> = contactDao.readAllData()

    suspend fun addContact(contact: Contact){
        contactDao.insertContact(contact)
    }
}