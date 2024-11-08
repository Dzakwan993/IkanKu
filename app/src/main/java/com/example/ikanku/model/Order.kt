package com.example.ikanku.model

data class Order(
    val imageRes: Int,
    val productName: String, // Pastikan nama ini konsisten dengan "title" di ViewModel
    val variant: String,
    val price: String,
    val quantity: Int,
    val status: OrderStatus
)

enum class OrderStatus {
    SHIPPED,
    COMPLETED,
    REJECTED
}