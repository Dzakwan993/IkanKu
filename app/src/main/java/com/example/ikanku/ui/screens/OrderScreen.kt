package com.example.ikanku.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ikanku.ui.components.CustomTopAppBar
import com.example.ikanku.ui.components.OrderCard
import com.example.ikanku.ui.components.OrderStatusTabs
import com.example.ikanku.viewmodel.OrderViewModel
import com.example.ikanku.model.OrderStatus

@Composable
fun OrderScreen(viewModel: OrderViewModel = viewModel()) {
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Pesanan saya",
                onBackClick = { /* Handle back navigation here */ }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            OrderStatusTabs(selectedTab = selectedTab) { newIndex ->
                selectedTab = newIndex
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Filter orders based on the selected tab and status
                items(viewModel.orders.size) { index ->
                    val order = viewModel.orders[index]
                    when (selectedTab) {
                        0 -> { // Tab "Belum Bayar" -> Payment Required or Pending Confirmation or Rejected
                            if (order.status == OrderStatus.PAYMENT_REQUIRED ||
                                order.status == OrderStatus.PENDING_CONFIRMATION ||
                                order.status == OrderStatus.REJECTED) {
                                OrderCard(order)
                            }
                        }
                        1 -> { // Tab "Sedang Diproses" -> Shipped
                            if (order.status == OrderStatus.SHIPPED) {
                                OrderCard(order)
                            }
                        }
                        2 -> { // Tab "Selesai" -> Completed
                            if (order.status == OrderStatus.COMPLETED) {
                                OrderCard(order)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun OrderScreenPreview() {
    val previewViewModel = OrderViewModel() // ViewModel with dummy data

    OrderScreen(viewModel = previewViewModel)
}
