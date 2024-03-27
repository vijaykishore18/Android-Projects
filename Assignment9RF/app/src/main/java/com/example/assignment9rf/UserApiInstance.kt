package com.example.assignment9rf

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserApiInstance {
    companion object {
        var baseUrl = "https://jsonplaceholder.typicode.com"


        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create())).build()
        }
    }
}