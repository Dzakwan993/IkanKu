package com.example.ikanku.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ikanku.R
import com.example.ikanku.model.Order
import com.example.ikanku.model.OrderStatus

class OrderViewModel : ViewModel() {
    val orders = listOf(
        Order(
            imageRes = R.drawable.ikan_nila,
            productName = "Ikan Nila",
            variant = "Berat 1 Kg",
            price = "Rp40.000",
            quantity = 1,
            status = OrderStatus.PAYMENT_REQUIRED
        ),
        Order(
            imageRes = R.drawable.ikan_nila,
            productName = "Ikan Gurame",
            variant = "Berat 1.5 Kg",
            price = "Rp60.000",
            quantity = 3,
            status = OrderStatus.REJECTED
        )
    )
}
