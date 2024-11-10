package com.example.ikanku.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ikanku.R
import com.example.ikanku.model.ProfilPenjual

class ProfilPenjualViewModel : ViewModel() {
    val profilPenjual = ProfilPenjual(
        name = "Tibelat Farm",
        imageRes = R.drawable.ikan_patin // Make sure this image is in the drawable folder
    )
}
