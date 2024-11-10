package com.example.ikanku.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ikanku.model.PesananSelesai
import com.example.ikanku.R

class BeriUlasanViewModel : ViewModel() {
    private val _selesaiList = MutableLiveData<List<PesananSelesai>>()
    val selesaiList: LiveData<List<PesananSelesai>> = _selesaiList

    init {
        _selesaiList.value = listOf(
            PesananSelesai(
                name = "Ikan Nila",
                weightVariation = "1 Kg",
                price = "50.000.000.000",
                quantity = 1,
                imageResId = R.drawable.ikan_nila,
                status = "Pesanan Selesai"
            ),
            PesananSelesai(
                name = "Ikan Patin",
                weightVariation = "2 Kg",
                price = "80.000",
                quantity = 1,
                imageResId = R.drawable.ikan_patin,
                status = "Pesanan Selesai"
            )
            // Add more completed orders if needed
        )
    }
}
