package com.example.ikanku.model

data class Order(
    val imageRes: Int,
    val productName: String, // Pastikan nama ini konsisten dengan "title" di ViewModel
    val variant: String,
    val price: String,
    val quantity: Int,
    val status: OrderStatus,
    val rejectionReason: String? = null // Nullable field for rejection reason
)

enum class OrderStatus {
    PENDING_CONFIRMATION,
    PAYMENT_REQUIRED,
    SHIPPED,
    COMPLETED,
    REJECTED
}