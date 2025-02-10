package com.example.kurs

import com.google.gson.annotations.SerializedName

data class UserData(
    val login: String,
    val password: String,
    @SerializedName("image_link")
    val link: String
)
