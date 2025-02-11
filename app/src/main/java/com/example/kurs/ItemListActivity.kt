package com.example.kurs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kurs.databinding.ActivityItemListBinding

class ItemListActivity : AppCompatActivity() {

    lateinit var binding: ActivityItemListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityItemListBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val viewModel = (application as UsersApp).itemListViewModel

        val id = intent.extras!!.getInt("user_id")
        viewModel.getUserData(id, binding)

        binding.backButton.setOnClickListener {
            finish()
        }
    }
}