// File: viewmodel/ShoppingCartViewModel.kt
package com.example.ikanku.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import com.example.ikanku.model.CartItemModel
import com.example.ikanku.R

class ShoppingCartViewModel : ViewModel() {
    // Data sample untuk item di keranjang
    var cartItems = mutableStateListOf(
        CartItemModel("Ikan Nila", "Pilih Variasi Berat 1 Kg", "Rp. 40.000", R.drawable.back),
        CartItemModel("Ikan Patin", "Pilih Variasi Berat 500 gr", "Rp. 30.000", R.drawable.back),
        CartItemModel("Ikan Lele", "Pilih Variasi Berat 2 Kg", "Rp. 50.000", R.drawable.back)
    )

    fun increaseQuantity(index: Int) {
        cartItems[index] = cartItems[index].copy(quantity = cartItems[index].quantity + 1)
    }

    fun decreaseQuantity(index: Int) {
        if (cartItems[index].quantity > 1) {
            cartItems[index] = cartItems[index].copy(quantity = cartItems[index].quantity - 1)
        }
    }
}

