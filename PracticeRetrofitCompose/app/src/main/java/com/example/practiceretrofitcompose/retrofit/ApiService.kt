package com.example.practiceretrofitcompose.retrofit
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("/comments")
    suspend fun getComments() : Response<List<Comments>>

    companion object {
        var apiService: ApiService? = null
        var baseUrl = "https://jsonplaceholder.typicode.com"
        fun getInstance() : ApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}