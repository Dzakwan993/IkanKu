package com.example.ikanku.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ikanku.R
import com.example.ikanku.model.Order
import com.example.ikanku.model.OrderStatus

class OrderRejectedViewModel : ViewModel() {
    val rejectedOrders = listOf(
        Order(
            imageRes = R.drawable.ikan_patin,
            productName = "Ikan Patin",
            variant = "1.5 Kg",  // hanya angka dan satuan berat
            price = "Rp60.000",
            quantity = 3,
            status = OrderStatus.REJECTED,
            rejectionReason = "Stok habis"
        ),




    )
}
