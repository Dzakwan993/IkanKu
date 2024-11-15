package com.example.ikanku.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ikanku.model.Dikemas
import com.example.ikanku.R

class DikemasViewModel : ViewModel() {
    private val _dikemasList = MutableLiveData<List<Dikemas>>()
    val dikemasList: LiveData<List<Dikemas>> = _dikemasList

    init {
        // Mock data with formatted price
        _dikemasList.value = listOf(
            Dikemas(
                name = "Ikan Nila",
                weightVariation = "1 Kg",
                price = "40.000", // Formatted price
                quantity = 1,
                imageResId = R.drawable.ikan_nila,
                status = "Pesanan Anda sedang dikemas"
            )
            // Add more items as needed
        )
    }
}
