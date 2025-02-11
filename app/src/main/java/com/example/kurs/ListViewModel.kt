package com.example.kurs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel(
    private val repository: Repository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    fun getUsers(adapter: UsersListAdapter) = viewModelScope.launch(dispatcher) {

        val usersData = repository.getUser()

        adapter.update(usersData) // Обновляем данные адаптера
    }

    fun saveUser(userData: UserData) = viewModelScope.launch(dispatcher) {
        repository.addToFavourites(userData)
    }
}