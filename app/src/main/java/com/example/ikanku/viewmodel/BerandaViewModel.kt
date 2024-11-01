package com.yourpackage.ikanku.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ikanku.R
import com.yourpackage.ikanku.model.Fish
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BerandaViewModel : ViewModel() {
    private val _recommendations = MutableStateFlow<List<Fish>>(emptyList())
    val recommendations: StateFlow<List<Fish>> = _recommendations

    init {
        loadRecommendations()
    }

    private fun loadRecommendations() {
        viewModelScope.launch {
            _recommendations.value = listOf(
                Fish("Ikan Discus", "Rp. 100.000", 4.5f, R.drawable.ikan_patin),
                Fish("Ikan Cupang", "Rp. 40.000", 4.5f, R.drawable.ikan_patin)
                // Tambahkan data lainnya jika diperlukan
            )
        }
    }
}
