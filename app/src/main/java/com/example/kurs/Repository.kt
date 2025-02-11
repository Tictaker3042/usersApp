package com.example.kurs

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(
    private val service: UserService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getUser(): List<UserData> {
        return withContext(dispatcher) {
            return@withContext service.getUsers()
        }
    }

    suspend fun createUser(login: String, password: String) {
        withContext(dispatcher) {
            service.createUser(UserData(login, password, "/uploads/User_icon_2.png"))
        }
    }

    suspend fun getUser(id: Int): UserData {
        return withContext(dispatcher){
            return@withContext service.getUser(id)
        }
    }
}