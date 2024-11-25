package com.example.ikanku.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.ui.components.BottomNavBar
import com.example.ikanku.ui.components.CustomTopAppBar
import com.example.ikanku.ui.components.OrderCardDitolak
import com.example.ikanku.ui.components.OrderStatusTabs
import com.example.ikanku.viewmodel.OrderRejectedViewModel

@Composable
fun RejectedOrdersScreen(viewModel: OrderRejectedViewModel = viewModel(), navController: NavController) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        topBar = {
            Column {
                CustomTopAppBar(
                    title = "Pesanan Ditolak",
                    onBackClick = { navController.popBackStack() }
                )
                OrderStatusTabs(selectedTab = 4, onTabSelected = { /* Aksi untuk memilih tab */ }, navController = navController)
            }

        },


    )
     { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            items(viewModel.rejectedOrders) { order ->
                OrderCardDitolak(order, navController = navController)
            }
        }
    }
}

@Composable
@Preview(showBackground = true, )
fun RejectedOrdersScreenPreview() {
    val previewViewModel = OrderRejectedViewModel()
    val navController = rememberNavController()// ViewModel khusus pesanan ditolak
    RejectedOrdersScreen(viewModel = previewViewModel, navController = navController)
}
