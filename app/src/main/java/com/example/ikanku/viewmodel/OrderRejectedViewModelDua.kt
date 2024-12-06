package com.example.ikanku.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ikanku.model.OrderDua
import com.example.ikanku.repository.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OrderRejectedViewModelDua : ViewModel() {
    private val _rejectedOrders = MutableStateFlow<List<OrderDua>>(emptyList())
    val rejectedOrders: StateFlow<List<OrderDua>> get() = _rejectedOrders

    init {
        fetchRejectedOrders()
    }

    private fun fetchRejectedOrders() {
        viewModelScope.launch {
            try {
                val orders = RetrofitClient.apiService.getRejectedOrders("Ditolak")
                if (orders.isNotEmpty()) {
                    _rejectedOrders.value = orders
                } else {
                    // Log jika data kosong
                    Log.d("OrderRejectedViewModel", "Tidak ada pesanan yang ditolak.")
                }
            } catch (e: Exception) {
                // Handle error (logging, showing a message, etc.)
                e.printStackTrace()
            }
        }
    }

}
