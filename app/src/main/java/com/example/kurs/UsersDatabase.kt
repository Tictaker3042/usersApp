package com.example.kurs

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UsersCacheData::class], version = 1)
abstract class UsersDatabase : RoomDatabase() {
    abstract fun getDAO(): UsersDAO
}