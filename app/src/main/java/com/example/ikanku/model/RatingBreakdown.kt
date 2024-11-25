package com.example.ikanku.model

// Model untuk representasi data ulasan
data class RatingBreakdown(
    val averageRating: Float, // Rata-rata rating
    val totalReviews: Int, // Total ulasan
    val starsCount: List<Int> // Jumlah ulasan per bintang (5, 4, 3, 2, 1)
)
