package com.example.kurs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kurs.databinding.ActivityItemListBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import retrofit2.http.Url

class ItemListViewModel(
    private val repository: Repository,
    private val baseUrl: String
    ) : ViewModel() {

        fun getUserData(id: Int, binding: ActivityItemListBinding) = viewModelScope.launch(
            Dispatchers.Main) {

            val userData = repository.getUser(id)

            binding.passView.text = userData.password
            binding.loginView.text = userData.login

            Picasso.get()
                .load(baseUrl + userData.link)
                .into(binding.imageView)
        }
}