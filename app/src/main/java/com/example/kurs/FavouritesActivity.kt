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
    }
}