package com.example.kurs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.kurs.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var retrofit: Retrofit
    private lateinit var service: UserService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Получаем IP-адрес из файла
        val ipAddress = IpAddressReader.getIpAddress(this) ?: "192" // значение по умолчанию
        val baseUrl = "http://$ipAddress:5000" // формируем базовый URL
        // Выводим IP-адрес в консоль

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(UserService::class.java)

        binding.button.setOnClickListener {
            val login = binding.loginEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            // Используем lifecycleScope для запуска корутины
            lifecycleScope.launch {
                try {
                    // Выполняем сетевой запрос в фоновом потоке
                    withContext(Dispatchers.IO) {
                        service.createUser (UserData(login, password))
                    }

                    // Очищаем поля ввода на основном потоке
                    binding.loginEditText.text?.clear()
                    binding.passwordEditText.text?.clear()
                } catch (e: Exception) {
                    Toast.makeText(
                        this@MainActivity, "Login already exists", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        binding.buttonToList.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java).apply {
                putExtra("baseUrl", baseUrl)
            }
            startActivity(intent)
        }

    }
}
