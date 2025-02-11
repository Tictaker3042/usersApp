package com.example.kurs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouritesViewModel(
    private val repository: Repository
) : ViewModel(){

    fun getFavourites(adapter: UsersListAdapter) = viewModelScope.launch(Dispatchers.Main) {
        adapter.update(repository.getFromFavourites())
    }

    fun removeUser(userData: UserData) =
        viewModelScope.launch(Dispatchers.Main) {
            repository.removeFromFavourites(userData)
        }
}