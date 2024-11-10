package com.example.ikanku.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ikanku.R
import com.example.ikanku.model.Profile
import com.example.ikanku.model.OrderStatusPenjualItem

class OrderStatusPenjualViewModel : ViewModel() {
    val profile = Profile(
        name = "Miftahul Fazra",
        imageRes = R.drawable.profil_kucing
    )

    val orderStatusPenjual = listOf(
        OrderStatusPenjualItem(label = "Pembayaran Menunggu", iconRes = R.drawable.belum_bayar),
        OrderStatusPenjualItem(label = "Dikemas", iconRes = R.drawable.dikemas),
        OrderStatusPenjualItem(label = "Dikirim", iconRes = R.drawable.dikirim),
        OrderStatusPenjualItem(label = "Pesanan Selesai", iconRes = R.drawable.pesanan_selesai),
        OrderStatusPenjualItem(label = "Pesanan Ditolak", iconRes = R.drawable.pesanan_ditolak)
    )

    // Data counts untuk setiap status pesanan
    val counts = listOf(3, 0, 5, 1, 2) // Sesuaikan dengan data dinamis jika ada
}
