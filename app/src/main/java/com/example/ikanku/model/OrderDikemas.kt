package com.example.ikanku.model

data class Dikemas(
    val name: String,
    val weightVariation: String,
    val price: String, // Change to String
    val quantity: Int,
    val imageResId: Int,
    val status: String
)
