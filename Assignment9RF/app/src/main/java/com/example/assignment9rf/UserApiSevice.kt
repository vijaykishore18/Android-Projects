package com.example.assignment9rf

import retrofit2.Response
import retrofit2.http.GET

interface UserApiSevice {
    @GET("/users")
    suspend fun getData() : Response<List<User>>
}
