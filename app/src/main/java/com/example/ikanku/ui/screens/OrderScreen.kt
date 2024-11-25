package com.example.ikanku.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(viewModel: OrderViewModel = viewModel(), navController: NavController) {
    var selectedTab by remember { mutableIntStateOf(0) }
    var isBottomSheetVisible by remember { mutableStateOf(false) }
    var selectedOrder by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Pesanan saya",
                onBackClick = { navController.popBackStack() }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            // Updated OrderStatusTabs with the correct lambda
            OrderStatusTabs(
                selectedTab = selectedTab,
                onTabSelected = { newIndex -> selectedTab = newIndex },
                navController = navController
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
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
        }

        if (isBottomSheetVisible) {
            ModalBottomSheet(
                onDismissRequest = { isBottomSheetVisible = false },
                sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.peringatan_pembatalan),
                        contentDescription = null,
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Apakah anda yakin untuk membatalkan pesanan \"${selectedOrder}\"?",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(
                            onClick = {
                                // Add order cancellation logic here
                                isBottomSheetVisible = false
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF4238))
                        ) {
                            Text("Ya", color = Color.White)
                        }
                        OutlinedButton(onClick = { isBottomSheetVisible = false }) {
                            Text("Tidak")
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
    val navController = rememberNavController()
    OrderScreen(viewModel = previewViewModel, navController = navController)
}
