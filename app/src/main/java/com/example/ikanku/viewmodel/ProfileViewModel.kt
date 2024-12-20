package com.example.ikanku.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ikanku.R
import com.example.ikanku.model.Profile
import com.example.ikanku.model.OrderStatusItem

class ProfileViewModel : ViewModel() {
    val profile = Profile(
        name = "Naufal Fadhilah",
        imageRes = R.drawable.profil_kucing
    )

    val orderStatusItems = listOf(
        OrderStatusItem(label = "Pesanan", iconRes = R.drawable.belum_bayar),
        OrderStatusItem(label = "Dikemas", iconRes = R.drawable.dikemas),
        OrderStatusItem(label = "Dikirim", iconRes = R.drawable.dikirim),
        OrderStatusItem(label = "Selesai", iconRes = R.drawable.pesanan_selesai),

    )

    // Data counts untuk setiap status pesanan
    val counts = listOf(2, 2, 2, 3, 2) // Sesuaikan dengan data dinamis jika ada
}
