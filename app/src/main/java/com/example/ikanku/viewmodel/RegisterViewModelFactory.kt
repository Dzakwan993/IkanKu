package com.example.ikanku.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ikanku.network.ApiService

class RegisterViewModelFactory(private val apiService: ApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(apiService) as T // Pastikan RegisterViewModel menerima apiService dari konstruktor
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
