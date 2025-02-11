package com.example.kurs

import android.app.Application
import androidx.room.Room
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UsersApp: Application() {

    val baseUrl = "http://192.168.1.33:5000"

    lateinit var mainViewModel : MainViewModel

    lateinit var listViewModel : ListViewModel

    lateinit var itemListViewModel : ItemListViewModel

    lateinit var favouritesViewModel : FavouritesViewModel

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(UserService::class.java)

        val db = Room.databaseBuilder(
            this,
            UsersDatabase::class.java,
            "database-users"
        ).build()

        val dao = db.getDAO()

        val repository = Repository(service, dao)

        mainViewModel = MainViewModel(repository)

        listViewModel = ListViewModel(repository)

        itemListViewModel = ItemListViewModel(repository, baseUrl)

        favouritesViewModel = FavouritesViewModel(repository)
    }
}