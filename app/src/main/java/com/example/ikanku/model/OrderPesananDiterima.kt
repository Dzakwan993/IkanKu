package com.example.ikanku.model

data class OrederPesananDiterima(
    val name: String,
    val weightVariation: String,
    val price: String, // Price is a String
    val quantity: Int,
    val imageResId: Int,
    val status: String
)
