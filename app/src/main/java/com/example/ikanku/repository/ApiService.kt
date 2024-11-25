package com.example.ikanku.repository

import com.example.ikanku.model.OrderDua
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("pesanan")
    suspend fun getRejectedOrders(@Query("status") status: String): List<OrderDua>
}