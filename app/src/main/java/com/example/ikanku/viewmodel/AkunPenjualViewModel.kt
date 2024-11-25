package com.example.ikanku.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.ikanku.R
import com.example.ikanku.model.ProfilAkunPenjual

class ProfilPenjualAkunViewModel : ViewModel() {

    val profilAkunPenjual = mutableStateOf(
        ProfilAkunPenjual(
            name = "Tibelat Farm",
            imageRes = R.drawable.tibelat_farm
        )
    )

    fun updateProfil(name: String, imageRes: Int) {
        profilAkunPenjual.value = ProfilAkunPenjual(name, imageRes)
    }
}
