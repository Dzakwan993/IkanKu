package com.example.ikanku.model

data class OrderStatusPenjualItem(
    val label: String,
    val iconRes: Int,
    val count: Int = 0 // Default count = 0 jika tidak di-set
)
