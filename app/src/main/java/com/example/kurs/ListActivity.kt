package com.example.kurs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kurs.databinding.ActivityListBinding
import androidx.recyclerview.widget.GridLayoutManager // Импортируем GridLayoutManager

class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val baseUrl = (application as UsersApp).baseUrl


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Устанавливаем GridLayoutManager с 2 колонками
        val layoutManager = GridLayoutManager(this, 1)
        binding.recyclerView.layoutManager = layoutManager

        val lambda : (id: Int) -> Unit = { id ->
            val intent = Intent(this, ItemListActivity::class.java)
            val bundle = Bundle()
            bundle.putInt("user_id", id)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        val listViewModel = (application as UsersApp).listViewModel

        val favouritesLambda : (userData: UserData) -> Unit = { userData ->
            listViewModel.saveUser(userData)
        }

        val adapter = UsersListAdapter(baseUrl, emptyList(), lambda, favouritesLambda)
        binding.recyclerView.adapter = adapter



        listViewModel.getUsers(adapter)

        binding.buttonToMain.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.buttonToFavorites.setOnClickListener {
            startActivity(Intent(this, FavouritesActivity::class.java))
        }
    }
}
