package com.example.ikanku.api


import com.example.ikanku.network.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiServiceProvider {
    fun create(): ApiService {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.4:5000/") // Ganti dengan URL API Anda
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
