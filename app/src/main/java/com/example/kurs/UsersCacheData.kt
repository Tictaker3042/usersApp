package com.example.kurs

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("users")
data class UsersCacheData(
    @PrimaryKey
    val id: Int,
    @ColumnInfo("image_link")
    val link : String,
    @ColumnInfo("login")
    val login : String,
    @ColumnInfo("password")
    val password : String
)
