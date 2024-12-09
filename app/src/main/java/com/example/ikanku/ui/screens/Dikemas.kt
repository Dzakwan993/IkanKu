package com.example.ikanku.ui.screens

import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
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
import com.example.ikanku.ui.components.AlertBottomSheet


@Composable
fun DikemasScreen(navController: NavController) {
    // State untuk mengontrol visibilitas Bottom Sheet dan nama produk yang dipilih
    var isBottomSheetVisible by remember { mutableStateOf(false) }
    var selectedOrder by remember { mutableStateOf<String?>(null) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        topBar = {
            Column {
                CustomTopAppBar(
                    title = "Pesanan Saya",
                    onBackClick = { navController.navigate("profile_screen") }
                )
                OrderStatusTabs(
                    selectedTab = 1, // Tab 'Dikemas' dipilih
                    onTabSelected = { /* Aksi untuk tab selection */ },
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
                .padding(horizontal = 16.dp),
        ) {
            // Daftar item DikemasCard
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
                    name = "Ikan Gurame",
                    weightVariation = "2 Kg",
                    price = "80.000",
                    quantity = 1,
                    imageResId = R.drawable.ikan_patin,
                    status = "Pesanan Anda sedang dikemas*"
                )
            )

            items(dikemasItems) { dikemas ->
                DikemasCard(
                    dikemas = dikemas,
                    onCancelClick = {
                        selectedOrder = dikemas.name // Simpan nama produk yang dipilih
                        isBottomSheetVisible = true // Tampilkan Bottom Sheet
                    }
                )
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
                    // Logika konfirmasi
                    isBottomSheetVisible = false
                },
                onCancel = {
                    // Logika batal
                    isBottomSheetVisible = false
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewDikemasScreen() {
    val navController = rememberNavController()
    DikemasScreen(navController = navController)
}
