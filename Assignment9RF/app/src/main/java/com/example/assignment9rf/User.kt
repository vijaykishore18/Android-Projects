package com.example.assignment9rf

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,

)

data class Address(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: Geo
)

data class Geo(
    val lat: String,
    val lng: String
)

data class Company(
    val name: String,
    val catchPhrase: String,
    val bs: String
)
