package com.example.ikanku.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ikanku.model.RatingBreakdown
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RatingViewModel : ViewModel() {
    // StateFlow untuk menyimpan data rating
    private val _ratingBreakdown = MutableStateFlow(
        RatingBreakdown(
            averageRating = 4.5f,
            totalReviews = 900,
            starsCount = listOf(500, 300, 80, 15, 5)
        )
    )
    val ratingBreakdown: StateFlow<RatingBreakdown> = _ratingBreakdown

    // Fungsi untuk memperbarui data rating (contoh jika ada interaksi baru)
    fun updateRatings(newData: RatingBreakdown) {
        viewModelScope.launch {
            _ratingBreakdown.emit(newData)
        }
    }
}
