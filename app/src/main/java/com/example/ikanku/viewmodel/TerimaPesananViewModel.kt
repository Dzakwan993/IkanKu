package com.example.ikanku.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ikanku.model.Dikirim
import com.example.ikanku.R

class PesananDiterimaViewModel : ViewModel() {
    private val _dikirimList = MutableLiveData<List<Dikirim>>()
    val dikirimList: LiveData<List<Dikirim>> = _dikirimList

    init {
        _dikirimList.value = listOf(
            Dikirim(
                name = "Ikan Nila",
                weightVariation = "1 Kg",
                price = "40.000",
                quantity = 1,
                imageResId = R.drawable.ikan_nila,
                status = "Pesanan Diterima"
            ),
            Dikirim(
                name = "Ikan Patin",
                weightVariation = "500 Gram",
                price = "20.000",
                quantity = 2,
                imageResId = R.drawable.ikan_patin,
                status = "Pesanan Diterima"
            )
            // You can add more items here if needed
        )
    }
}
