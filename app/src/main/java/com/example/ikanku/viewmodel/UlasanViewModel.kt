package com.example.ikanku.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ikanku.R // Make sure to import R for accessing the drawable resources
import com.example.ikanku.model.Ulasan
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UlasanViewModel : ViewModel() {
    // Data ulasan pengguna
    private val _ulasan = MutableStateFlow(
        listOf(
            Ulasan(
                profilePictureResId = R.drawable.pak_vincent, // Example drawable
                username = "Pak Vincent",
                review = "Pelayanan sangat baik! Produk sesuai deskripsi.",
                rating = 4.5f
            ),
            Ulasan(
                profilePictureResId = R.drawable.dzaky_indomie, // Example drawable
                username = "Dzaky Indomie",
                review = "Produk dikirim cepat dan berkualitas.",
                rating = 5.0f
            )
        )
    )

    val ulasan: StateFlow<List<Ulasan>> = _ulasan
}
