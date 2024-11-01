package com.yourpackage.ikanku.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ikanku.R
import com.yourpackage.ikanku.model.Fish
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class PreviewBerandaViewModel : ViewModel() {
    private val _recommendations = MutableStateFlow(
        listOf(
            Fish("Ikan Discus", "Rp. 100.000", 4.5f, R.drawable.ikan_patin),
            Fish("Ikan Cupang", "Rp. 40.000", 4.5f, R.drawable.ikan_patin),
            Fish("Ikan Guppy", "Rp. 60.000", 4.2f, R.drawable.ikan_patin)
        )
    )
    val recommendations: StateFlow<List<Fish>> = _recommendations
}
