package com.example.ikanku.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.R
import com.example.ikanku.ui.components.CustomTopAppBar
import com.example.ikanku.ui.components.OrderCard
import com.example.ikanku.ui.components.OrderStatusTabs
import com.example.ikanku.viewmodel.OrderViewModel
import com.example.ikanku.model.OrderStatus
import com.example.ikanku.ui.components.AlertBottomSheet
import com.example.ikanku.ui.components.BottomNavBar



@Composable
fun OrderScreen(viewModel: OrderViewModel = viewModel(), navController: NavController) {
    var selectedTab by remember { mutableIntStateOf(0) }
    var isBottomSheetVisible by remember { mutableStateOf(false) }
    var selectedOrder by remember { mutableStateOf<String?>(null) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(), // Tambahkan padding navigasi seperti DikemasScreen
        topBar = {
            Column {
                CustomTopAppBar(
                    title = "Pesanan saya",
                    onBackClick = { navController.navigate("profile_screen") }
                )
                OrderStatusTabs(
                    selectedTab = selectedTab,
                    onTabSelected = { newIndex -> selectedTab = newIndex },
                    navController = navController
                )
            }
        },
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp), // Sama dengan padding di DikemasScreen

        ) {
            items(viewModel.orders.size) { index ->
                val order = viewModel.orders[index]
                when (selectedTab) {
                    0 -> {
                        if (order.status == OrderStatus.PAYMENT_REQUIRED ||
                            order.status == OrderStatus.PENDING_CONFIRMATION ||
                            order.status == OrderStatus.REJECTED
                        ) {
                            OrderCard(
                                order = order,
                                onCancelClick = {
                                    selectedOrder = order.productName
                                    isBottomSheetVisible = true
                                },
                                navController = navController
                            )
                        }
                    }
                    1 -> {
                        if (order.status == OrderStatus.SHIPPED) {
                            OrderCard(
                                order = order,
                                onCancelClick = {
                                    selectedOrder = order.productName
                                    isBottomSheetVisible = true
                                },
                                navController = navController
                            )
                        }
                    }
                    2 -> {
                        if (order.status == OrderStatus.COMPLETED) {
                            OrderCard(
                                order = order,
                                onCancelClick = {
                                    selectedOrder = order.productName
                                    isBottomSheetVisible = true
                                },
                                navController = navController
                            )
                        }
                    }
                }
            }
        }

        if (isBottomSheetVisible) {
            AlertBottomSheet(
                isVisible = isBottomSheetVisible,
                onDismiss = { isBottomSheetVisible = false },
                imageResId = R.drawable.peringatan_pembatalan,
                alertText = "Apakah anda yakin untuk membatalkan pesanan \"$selectedOrder\"?",
                confirmButtonText = "Ya",
                cancelButtonText = "Tidak",
                onConfirm = {
                    isBottomSheetVisible = false
                },
                onCancel = {
                    isBottomSheetVisible = false
                }
            )
        }
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun OrderScreenPreview() {
    val previewViewModel = OrderViewModel() // ViewModel with dummy data
    val navController = rememberNavController()
    OrderScreen(viewModel = previewViewModel, navController = navController)
}
