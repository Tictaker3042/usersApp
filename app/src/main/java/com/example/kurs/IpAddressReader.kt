package com.example.kurs
import android.content.Context
import android.content.res.AssetManager
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

object IpAddressReader {

    fun getIpAddress(context: Context): String? {
        val ipAddress = StringBuilder()
        val assetManager: AssetManager = context.assets

        try {
            val inputStream: InputStream = assetManager.open("ip_addresses.txt")
            val reader = BufferedReader(InputStreamReader(inputStream))
            var line: String?

            // Читаем файл построчно
            while (reader.readLine().also { line = it } != null) {
                ipAddress.append(line)
            }
            reader.close()
            println("Содержимое файла: ${ipAddress.toString()}")
        } catch (e: IOException) {
            e.printStackTrace()
            return null // Возвращаем null в случае ошибки
        }

        return ipAddress.toString() // Возвращаем IP-адрес как строку
    }
}
