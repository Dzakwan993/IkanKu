package com.example.ikanku.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ikanku.model.Dikemas
import com.example.ikanku.ui.components.BottomNavBar
import com.example.ikanku.ui.components.CustomTopAppBar
import com.example.ikanku.ui.components.DikemasCard
import com.example.ikanku.ui.components.OrderStatusTabs
import com.example.ikanku.R

@Composable
fun DikemasScreen() {
    var selectedTab by remember { mutableStateOf(1) } // State to keep track of the selected tab

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // Enable scrolling
            .padding(bottom = 80.dp) // Padding untuk memberi ruang bagi BottomNavBar
    ) {
        // Custom Top App Bar
        CustomTopAppBar(
            title = "Pesanan Saya",
            onBackClick = { /* Aksi untuk kembali, bisa menggunakan navigator */ }
        )

        // Order Status Tabs
        OrderStatusTabs(
            selectedTab = selectedTab,
            onTabSelected = { index -> selectedTab = index }
        )

        // List of DikemasCard items
        Column(modifier = Modifier.padding(8.dp)) {
            // Sample data for demonstration
            val dikemasItems = listOf(
                Dikemas(
                    name = "Ikan Nila",
                    weightVariation = "1 Kg",
                    price = "40.000",
                    quantity = 1,
                    imageResId = R.drawable.ikan_nila,
                    status = "Pesanan Anda sedang dikemas*"
                ),
                Dikemas(
                    name = "Ikan Patin",
                    weightVariation = "1.5 Kg",
                    price = "50.000",
                    quantity = 2,
                    imageResId = R.drawable.ikan_patin,
                    status = "Pesanan Anda sedang dikemas*"
                )
            )

            // Display each DikemasCard
            dikemasItems.forEach { dikemas ->
                DikemasCard(dikemas = dikemas)
            }
        }
    }

    // Add BottomNavBar at the bottom of the screen
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        BottomNavBar()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDikemasScreen() {
    DikemasScreen()
}
