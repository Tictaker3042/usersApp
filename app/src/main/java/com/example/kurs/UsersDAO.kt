package com.example.kurs

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsersDAO {
    @Insert
    fun addUser(usersCacheData: UsersCacheData)

    @Query("SELECT * FROM users")
    fun getAllFavouriteUsers(): List<UsersCacheData>

    @Delete
    fun deleteUser(user: UsersCacheData)
}