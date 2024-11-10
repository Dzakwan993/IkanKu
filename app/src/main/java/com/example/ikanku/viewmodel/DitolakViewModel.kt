package com.example.ikanku.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ikanku.R
import com.example.ikanku.model.Order
import com.example.ikanku.model.OrderStatus

class OrderRejectedViewModel : ViewModel() {
    val rejectedOrders = listOf(
        Order(
            imageRes = R.drawable.ikan_nila,
            productName = "Ikan Gurame",
            variant = "Pilih Variasi Berat 1.5 Kg",
            price = "Rp60.000",
            quantity = 3,
            status = OrderStatus.REJECTED,
            rejectionReason = "Stok habis"
        ),
        Order(
            imageRes = R.drawable.ikan_nila,
            productName = "Ikan Mujair",
            variant = "Pilih Variasi Berat 2 Kg",
            price = "Rp50.000",
            quantity = 2,
            status = OrderStatus.REJECTED,
            rejectionReason = "Permintaan melebihi stok"
        )
        // Tambahkan pesanan lain yang berstatus REJECTED jika diperlukan
    )
}
