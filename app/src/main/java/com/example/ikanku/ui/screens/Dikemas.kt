package com.example.ikanku.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.model.Dikemas
import com.example.ikanku.ui.components.BottomNavBar
import com.example.ikanku.ui.components.CustomTopAppBar
import com.example.ikanku.ui.components.DikemasCard
import com.example.ikanku.ui.components.OrderStatusTabs
import com.example.ikanku.R
import com.example.ikanku.ui.components.OrderAmbilPesananItem

@Composable
fun DikemasScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf(1) } // State untuk mengelola tab yang dipilih

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // Mengaktifkan scroll
            .padding(bottom = 80.dp) // Padding untuk memberi ruang pada BottomNavBar
    ) {
        // Custom Top App Bar
        CustomTopAppBar(
            title = "Pesanan Saya",
            onBackClick = { navController.popBackStack() }
        )

        // Order Status Tabs
        OrderStatusTabs(
            selectedTab = selectedTab,
            onTabSelected = { index -> selectedTab = index },
            navController = navController
        )

        // List of DikemasCard items
        Column(modifier = Modifier.padding(8.dp)) {
            // Tampilkan hanya satu DikemasCard item
            val dikemasItems = listOf(
                Dikemas(
                    name = "Ikan Nila",
                    weightVariation = "1 Kg",
                    price = "40.000",
                    quantity = 1,
                    imageResId = R.drawable.ikan_nila,
                    status = "Pesanan Anda sedang dikemas*"
                )
            )

            dikemasItems.forEach { dikemas ->
                DikemasCard(dikemas = dikemas)
            }

            // Tambahkan komponen OrderAmbilPesananItem di sini
            OrderAmbilPesananItem()
        }
    }

    // Tambahkan BottomNavBar di bagian bawah layar
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        BottomNavBar(navController =navController)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDikemasScreen() {
    val navController = rememberNavController()
    DikemasScreen(navController = navController)
}
