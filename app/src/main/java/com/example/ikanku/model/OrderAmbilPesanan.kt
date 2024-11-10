package com.example.ikanku.model

data class OrderAmbilPesananModel(
    val imageUrl: String,
    val title: String,
    val weightOption: String,
    val price: String,
    val quantity: Int,
    var isPickedUp: Boolean = false // Represents the "picked up" state
)
