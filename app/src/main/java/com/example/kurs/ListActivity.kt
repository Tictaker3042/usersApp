package com.example.kurs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kurs.databinding.ActivityListBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.lifecycle.lifecycleScope
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.recyclerview.widget.GridLayoutManager // Импортируем GridLayoutManager

class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding
    private lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Устанавливаем GridLayoutManager с 2 колонками
        val layoutManager = GridLayoutManager(this, 1)
        binding.recyclerView.layoutManager = layoutManager

        val adapter = UsersListAdapter(emptyList())
        binding.recyclerView.adapter = adapter

        lifecycleScope.launch(Dispatchers.Main) {
            // Извлекаем значение IP адреса сервера из другого Activity по ключу
            val baseUrl = intent.getStringExtra("baseUrl") ?: return@launch

            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(UserService::class.java)

            val userData = withContext(Dispatchers.IO) {
                service.getUsers() // Предполагается, что getUsers возвращает список пользователей
            }

            adapter.update(userData) // Обновляем данные адаптера
        }

        binding.buttonToMain.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
