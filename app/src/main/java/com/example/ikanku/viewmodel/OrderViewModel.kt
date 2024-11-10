package com.example.ikanku.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ikanku.R
import com.example.ikanku.model.Order
import com.example.ikanku.model.OrderStatus

class OrderViewModel : ViewModel() {
    val orders = listOf(
        Order(
            imageRes = R.drawable.ikan_nila,
            productName = "Bibit Ikan Lele",
            variant = "Pilih Variasi Berat 2 Kg",
            price = "Rp80.000",
            quantity = 2,
            status = OrderStatus.PENDING_CONFIRMATION
        ),
        Order(
            imageRes = R.drawable.ikan_nila,
            productName = "Ikan Nila",
            variant = "Pilih Variasi Berat 1 Kg",
            price = "Rp40.000",
            quantity = 1,
            status = OrderStatus.PAYMENT_REQUIRED
        ),
        Order(
            imageRes = R.drawable.ikan_nila,
            productName = "Ikan Gurame",
            variant = "Pilih Variasi Berat 1.5 Kg",
            price = "Rp60.000",
            quantity = 3,
            status = OrderStatus.REJECTED,
            rejectionReason = "Stok habis" // Adding rejection reason for rejected order
        )
    )
}
