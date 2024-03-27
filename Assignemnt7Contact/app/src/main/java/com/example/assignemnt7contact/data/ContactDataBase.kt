package com.example.assignemnt7contact.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.assignemnt7contact.model.Contact

@Database(
    entities = [Contact::class], version = 1, exportSchema = false
)
abstract class ContactDataBase : RoomDatabase(){
    abstract fun contactDao() : ContactDao

    companion object{
        @Volatile
        private var INSTANCE : ContactDataBase? = null

        fun getDatabase(context: Context):ContactDataBase{
            val tempInstace = INSTANCE
            if (tempInstace != null) {
                return tempInstace
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContactDataBase::class.java,
                    "contact_database"
                ).build()
                 INSTANCE = instance
                return instance
            }
        }
    }

}