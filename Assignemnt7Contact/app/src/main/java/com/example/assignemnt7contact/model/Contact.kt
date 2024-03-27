package com.example.assignemnt7contact.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "contact_table")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val firstName : String,
    val lastName : String,
    val number: Long
) : Parcelable{
}