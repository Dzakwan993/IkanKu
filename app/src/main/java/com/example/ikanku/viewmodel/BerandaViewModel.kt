package com.example.ikanku.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ikanku.R
import com.example.ikanku.model.*

class BerandaViewModel : ViewModel() {
    val categories = listOf(
        Category("Hias", R.drawable.kategori_ikanhias),
        Category("Air Tawar", R.drawable.kategori_airtawar),
        Category("Bibit", R.drawable.kategori_bibit)
    )

    val promotions = listOf(
        Promotion(R.drawable.promosi_besar, "Ikan Mas", "Diskon 50%"),
        Promotion(R.drawable.promosikecil_1, "Ikan Mujair", "Diskon 30%")
    )

    val smallPromotions = listOf(
        Promotion(R.drawable.promosikecil_1, "Ikan Tetra", "Diskon 15%"),
        Promotion(R.drawable.promosikecil_2, "Ikan Platy", "Diskon 10%"),
        Promotion(R.drawable.promosikecil_2, "Ikan Molly", "Diskon 20%"),
        Promotion(R.drawable.promosikecil_2, "Ikan Cichlid", "Diskon 25%"),
        Promotion(R.drawable.promosikecil_2, "Ikan Guppy", "Diskon 30%")
    )

    val recommendations = listOf(
        Recommendation(R.drawable.gambar_rekomen_1, "Ikan Discus", "Ikan discus dari Amazon", "Rp. 100.000", 4.5),
        Recommendation(R.drawable.gambar_rekomen_2, "Ikan Cupang", "Ikan lokal yang cantik", "Rp. 40.000", 4.0)
    )

    val products = listOf(
        Product(R.drawable.ikan_bluedevil, "Ikan Blue Devil", "Rp. 500.000", 4.3),
        Product(R.drawable.ikan_fire, "Ikan Fire", "Rp. 700.000", 4.0),
        Product(R.drawable.ikan_mandarin, "Ikan Mandarin", "Rp. 800.000", 4.0),
        Product(R.drawable.ikan_angel, "Ikan Angel", "Rp. 1.000.000", 4.5),
        Product(R.drawable.ikan_guppy, "Ikan Guppy", "Rp. 600.000", 4.0)
    )
}
