package com.example.kurs

import androidx.room.Insert
import androidx.room.Query

interface UsersDAO {
    @Insert
    fun addUser(usersCacheData: UsersCacheData)

    @Query("SELECT * FROM users")
    fun getAllFavouriteUsers(): List<<UsersCacheData>
}