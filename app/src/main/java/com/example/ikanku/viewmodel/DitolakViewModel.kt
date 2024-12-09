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
            variant = "1.5 Kg",  // hanya angka dan satuan berat
            price = "Rp60.000",
            quantity = 3,
            status = OrderStatus.REJECTED,
            rejectionReason = "Stok habis"
        ),
        Order(
            imageRes = R.drawable.ikan_nila,
            productName = "Ikan Mujair",
            variant = "2 Kg",  // hanya angka dan satuan berat
            price = "Rp50.000",
            quantity = 2,
            status = OrderStatus.REJECTED,
            rejectionReason = "Permintaan melebihi stok"
        ),
        Order(
            imageRes = R.drawable.ikan_nila,
            productName = "Ikan Lele",
            variant = "1 Kg",  // hanya angka dan satuan berat
            price = "Rp40.000",
            quantity = 4,
            status = OrderStatus.REJECTED,
            rejectionReason = "Stok habis"
        ),
        Order(
            imageRes = R.drawable.ikan_nila,
            productName = "Ikan Kerapu",
            variant = "1.2 Kg",  // hanya angka dan satuan berat
            price = "Rp80.000",
            quantity = 1,
            status = OrderStatus.REJECTED,
            rejectionReason = "Stok habis"
        ),
        Order(
            imageRes = R.drawable.ikan_nila,
            productName = "Ikan Patin",
            variant = "2.5 Kg",  // hanya angka dan satuan berat
            price = "Rp75.000",
            quantity = 5,
            status = OrderStatus.REJECTED,
            rejectionReason = "Permintaan melebihi stok"
        ),
        Order(
            imageRes = R.drawable.ikan_nila,
            productName = "Ikan Salmon",
            variant = "1 Kg",  // hanya angka dan satuan berat
            price = "Rp100.000",
            quantity = 3,
            status = OrderStatus.REJECTED,
            rejectionReason = "Stok habis"
        ),
        Order(
            imageRes = R.drawable.ikan_nila,
            productName = "Ikan Tongkol",
            variant = "1.8 Kg",  // hanya angka dan satuan berat
            price = "Rp55.000",
            quantity = 6,
            status = OrderStatus.REJECTED,
            rejectionReason = "Permintaan melebihi stok"
        )




    )
}
