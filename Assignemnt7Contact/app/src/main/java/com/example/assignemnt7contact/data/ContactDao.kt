package com.example.assignemnt7contact.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.assignemnt7contact.model.Contact

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertContact(contact: Contact)

    @Query("SELECT * FROM contact_table ORDER BY id ASC")
    fun readAllData() : LiveData<List<Contact>>



}