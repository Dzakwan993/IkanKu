package com.example.ikanku.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ikanku.R
import com.yourpackage.ikanku.model.Fish
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PembayaranViewModel {
    private val _recommendations = MutableStateFlow<List<Fish>>(emptyList())
    val recommendations: StateFlow<List<Fish>> = _recommendations
}