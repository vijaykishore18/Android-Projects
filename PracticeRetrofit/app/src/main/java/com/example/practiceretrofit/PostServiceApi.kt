package com.example.practiceretrofit

import retrofit2.Response
import retrofit2.http.GET

interface PostServiceApi {
    @GET("/albums")
    suspend fun getData() : Response<List<Post>>
}