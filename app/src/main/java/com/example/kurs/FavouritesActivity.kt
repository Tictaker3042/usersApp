package com.example.kurs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kurs.databinding.ActivityFavouritesBinding

class FavouritesActivity : AppCompatActivity() {
    lateinit var binding : ActivityFavouritesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavouritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonToList.setOnClickListener {
            startActivity(Intent(this, ListActivity::class.java))
        }

        val viewModel = (application as UsersApp).favouritesViewModel
        val baseUrl = (application as UsersApp).baseUrl

        val lambda: (id: Int) -> Unit = {id ->
            val intent = Intent(this, ItemListActivity::class.java)
            val bundle = Bundle()
            bundle.putInt("user_id", id)
            intent.putExtras(bundle)
            startActivity(intent)
        }
        val favouritesLambda: (UserData) -> Unit = { userData ->
            viewModel.removeUser(userData)
        }

        val adapter = UsersListAdapter(baseUrl, emptyList(), lambda, favouritesLambda)

        binding.recyclerView.adapter = adapter
        viewModel.getFavourites(adapter)
    }
}