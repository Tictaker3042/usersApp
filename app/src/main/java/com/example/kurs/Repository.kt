package com.example.kurs

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(
    private val service: UserService,
    private val dao: UsersDAO,
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

    suspend fun addToFavourites(userData: UserData) {
        withContext(dispatcher) {
            dao.addUser(
                UsersCacheData(
                    userData.id,
                    userData.link,
                    userData.login,
                    userData.password
                )
            )
        }
    }

    suspend fun getFromFavourites() : List<UserData> {
        val usersCacheData = withContext(dispatcher) {
            return@withContext dao.getAllFavouriteUsers()
        }

        return usersCacheData.map { user ->
            UserData(
                user.login,
                user.password,
                user.link,
                user.id
            )
        }
    }

    suspend fun removeFromFavourites(userData: UserData) {
        withContext(dispatcher) {
            dao.deleteUser(
                UsersCacheData(
                    userData.id,
                    userData.link,
                    userData.login,
                    userData.password
                )
            )
        }
    }
}