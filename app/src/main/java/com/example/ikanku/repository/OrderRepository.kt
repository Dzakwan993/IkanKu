package com.example.ikanku.repository

import android.util.Log
import com.example.ikanku.model.OrderDua

class OrderRepository {
    private val apiService = RetrofitClient.apiService

    suspend fun getRejectedOrders(): List<OrderDua> {
        return apiService.getRejectedOrders("Ditolak")
    }
}
