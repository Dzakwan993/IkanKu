package com.example.ikanku.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ikanku.R
import com.example.ikanku.model.Profile
import com.example.ikanku.model.OrderStatusItem

class ProfileViewModel : ViewModel() {
    val profile = Profile(
        name = "Miftahul Fazra",
        imageRes = R.drawable.profil_kucing // Pastikan gambar ini ada di folder drawable
    )

    val orderStatusItems = listOf(
        OrderStatusItem(label = "Belum Bayar", iconRes = R.drawable.belum_bayar),
        OrderStatusItem(label = "Dikemas", iconRes = R.drawable.dikemas),
        OrderStatusItem(label = "Dikirim", iconRes = R.drawable.dikirim),
        OrderStatusItem(label = "Selesai", iconRes = R.drawable.pesanan_selesai)
    )
}
