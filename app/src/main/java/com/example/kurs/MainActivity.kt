package com.example.kurs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kurs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val mainViewModel = (application as UsersApp).mainViewModel

        binding.button.setOnClickListener {
            mainViewModel.createUser(binding)
        }

        binding.buttonToList.setOnClickListener {
            startActivity(Intent(this, ListActivity::class.java))
        }
    }
}
