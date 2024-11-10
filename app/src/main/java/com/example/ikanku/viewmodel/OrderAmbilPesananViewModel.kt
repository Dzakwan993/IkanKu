package com.example.ikanku.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.ikanku.model.OrderAmbilPesananModel

class OrderAmbilPesananViewModel : ViewModel() {
    // Sample order data
    val orderData = mutableStateOf(
        OrderAmbilPesananModel(
            imageUrl = "https://example.com/image.jpg",
            title = "Ikan Nila",
            weightOption = " 1 Kg",
            price = " 40.000",
            quantity = 1,
            isPickedUp = false
        )
    )

    // Function to mark the order as picked up
    fun markAsPickedUp() {
        orderData.value = orderData.value.copy(isPickedUp = true)
    }

    // Function to cancel the order
    fun cancelOrder() {
        orderData.value = orderData.value.copy(isPickedUp = false)
    }
}
