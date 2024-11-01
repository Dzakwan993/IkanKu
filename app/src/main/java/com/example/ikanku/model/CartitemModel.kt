// File: model/CartItemModel.kt
package com.example.ikanku.model

data class CartItemModel(
    val name: String,
    val weight: String,
    val price: String,
    val imageRes: Int,
    var quantity: Int = 1
)
