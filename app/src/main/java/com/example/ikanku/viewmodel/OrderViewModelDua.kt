//package com.example.ikanku.viewmodel
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.ikanku.model.OrderDua
//import com.example.ikanku.repository.`OrderRepository.kt`
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.launch
//
//class OrderViewModelDua : ViewModel() {
//    private val repository = `OrderRepository.kt`()
//
//    private val _orders = MutableStateFlow<List<OrderDua>>(emptyList())
//    val orders: StateFlow<List<OrderDua>> get() = _orders
//
//    fun loadOrders(status: String) {
//        viewModelScope.launch {
//            try {
//                val result = repository.getOrdersByStatus(status)
//                _orders.value = result
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//    }
//}